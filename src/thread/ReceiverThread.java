/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;
import communication.Communication;
import communication.Response;
import javax.swing.JOptionPane;
import ui.form.FrmMain;
/**
 *
 * @author Ognjen
 */
public class ReceiverThread extends Thread{
    
    private FrmMain frmMain; // Referenca na glavnu formu da bismo osvežili UI
    
    public ReceiverThread(FrmMain frmMain){
        this.frmMain = frmMain;
    }

    @Override
    public void run() {
        try {
            while(!isInterrupted()){
                // Stalno cekamo objekte od servera
                Response response = (Response) Communication.getInstance().getReceiver().receive();
            
            // provera da li je stigao signal za gasenje    
            if (response.getResult() instanceof String && response.getResult().equals("SERVER_STOPPED")) {
                JOptionPane.showMessageDialog(frmMain, "Server je ugašen. Aplikacija će se zatvoriti.", "Obaveštenje", JOptionPane.WARNING_MESSAGE);
                System.exit(0); // Gasimo klijenta
                break;
            }
                
            // Ako nije gašenje, prosledi regularan odgovor formi
            frmMain.handleResponse(response);
            }
        } catch (Exception e) {
            System.out.println("Veza sa serverom je prekinuta");
        }
    }
    
    
    
}
