/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import domain.User;
import java.net.Socket;

/**
 *
 * @author Ognjen
 */
public class Communication {
    
    private static Communication instance;
    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    
    private Communication(){
        try {
            socket = new Socket("localhost", 9000);
            sender = new Sender(socket);
            receiver = new Receiver(socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Communication getInstance(){
        if(instance == null){
            instance = new Communication();
        }
        return instance;
    }
    
    public User login(String email, String password) throws Exception{
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        
        Request request = new Request(Operation.LOGIN, user);
        sender.send(request);
        
        Response response = (Response) receiver.receive();
        if(response.getException() != null){
            throw response.getException();
        }
        return (User) response.getResult();  
    }
    
    public Receiver getReceiver(){
        return receiver;
    }
    
    public Sender getSender(){
        return sender;
    }
    
}
