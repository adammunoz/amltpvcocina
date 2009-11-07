/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package amltpvcocina;
import java.io.*;
import java.net.*;
import java.util.Vector;




/**
 *
 * @author adam
 */
public class Conexion {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private String answerToQuery = null;
    private String serverName;
    private boolean canIsend = true;
    static Vector<String> mesasOcupadas = new Vector();

    Conexion(String serverName) throws UnknownHostException, IOException {
        this.serverName = serverName;
        start(7);
        System.out.println("Conexión con servidor temporal abierta");
    }

    Conexion(String serverName, int port) throws UnknownHostException, IOException {
        this.serverName = serverName;
        start(port);
        System.out.println("Conexión con servidor principal abierta");
    }
    
    String waitForMsgs(boolean decode) throws IOException{
        if (decode){
            System.out.println("Waiting for answer from main server");
        }
        else{
            System.out.println("Waiting for answer from temporary server");
        }

        String msg=null;
        while ((msg = in.readLine())!=null){
            System.out.println("Answer received:"+msg);
            if (decode){
                return decode(msg);
            }
            else{
                close();
                return msg;
            }
        }
        return null;
    }
    private void start(int port) throws UnknownHostException, IOException{
        socket = new Socket(serverName, 8);
        out = new PrintWriter(
                new OutputStreamWriter(socket.getOutputStream(),"UTF-8"), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
    }

    private void close(){
        try {
            socket.close();
            out.close();
            in.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
 
    String decode(String msg) {
        System.out.println("Decoding server message: " + msg);
        
        if (msg.startsWith("comanda")){
            String[] a = msg.split("@");
            String mesa = a[1];
            String producto = a[2];
            if (Main.listening.contains(mesa)){
                Object[] rowArray = {mesa,null,new Integer(1),producto};
                Ventana.selfReference.addComanda(rowArray);
                return "done";
            }
            else {
                return "nothing done";
            }
            
        }
        else if (msg.startsWith("borrar")){
            if (Ventana.selfReference.lastInsertedMesa != null){
                String[] a = msg.split("@");
                String mesa = a[1];
                String producto = a[2];
                Ventana.selfReference.borrarProducto(mesa,producto);
                return "done";
            }
            else{
                System.out.println("Ignorando mensaje de borrar porque no hay productos");
                return "nothing done";
            }
        }
        else{
            System.out.println("Message ignored");
            return "nothind done";
        }
        

    }
    
    void sendMsg(String msg){
        canIsend = false;
        System.out.println("sending " + msg);
        out.println(msg);
    }
    
 }
