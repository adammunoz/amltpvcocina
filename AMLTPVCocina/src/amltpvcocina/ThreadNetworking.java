/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package amltpvcocina;

import java.io.IOException;


/**
 *
 * @author adam
 */
public class ThreadNetworking implements Runnable {
    public void run(){
        try {
            System.out.println("Intentando abrir conexion con servidor principal en puerto 8");
            Conexion conexion = new Conexion(Main.serverName,8);
            while (true){
                conexion.waitForMsgs(true);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
