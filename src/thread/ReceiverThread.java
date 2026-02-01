/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;
import communication.Communication;
import communication.Response;
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
            // Na osnovu onoga što je stiglo, ažuriramo formu
            // Npr. ako je stigla nova poruka, dodajemo je u tabelu
            frmMain.handleResponse(response);
            }
        } catch (Exception e) {
            System.out.println("Veza sa serverom je prekinuta");
        }
    }
    
    
    
}
