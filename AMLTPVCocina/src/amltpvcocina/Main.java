/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package amltpvcocina;

import java.util.Vector;
import persistance.Persister;
/**
 *
 * @author pc-xp1
 */
public class Main {
    static String serverName;
    static Conexion conexion;
    static Vector<String> listening = new Vector();
    static Persister persister = new Persister();


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerNameDialog serverNameDialog = new ServerNameDialog(null,true);
        serverNameDialog.setLocationRelativeTo(null);
        serverNameDialog.setVisible(true);
        Ventana mainWindow = new Ventana();
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }

}
