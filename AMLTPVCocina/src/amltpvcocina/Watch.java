/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package amltpvcocina;



/**
 *
 * @author pc-xp1
 */
public class Watch implements Runnable{
    int minutes = 0;
    int seconds = 0;
    boolean running = false;
    String result ="";
    int row;

    public Watch(int row){
        this.row = row;
    }
    public void stop(){
        running = false;
        System.out.println("Reloj thread parado");
    }

    public void upRows(int positions){
        this.row = this.row - positions;
        System.out.println("The watch will be moved " + positions + " positions");
    }

    public void run(){
        running = true;
        while (running){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            if (seconds <59){
                seconds = seconds + 1;
            }
            else if (seconds == 59){
                seconds = 0;
                minutes = minutes +1;
            }
            result = minutes + ":" + seconds;
            try{
                Ventana.selfReference.model.setValueAt(result, row, 1);
            }
            catch (java.lang.ArrayIndexOutOfBoundsException ex){
                System.out.println("Este reloj ya no está activo");
            }
        }
    }
}
