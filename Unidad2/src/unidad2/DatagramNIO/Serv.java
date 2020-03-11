/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad2.DatagramNIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author memotets89
 */
public class Serv {
    private DatagramChannel canal;
    private ByteBuffer buffer;
    private int port = 1234;
    private String mensaje;
        
    public Serv(){
        try{
            canal = DatagramChannel.open();
            canal.configureBlocking(false);
            InetSocketAddress sa = new InetSocketAddress("localhost", port );
            canal.bind(sa);
            System.out.println("Servidor a la escucha");
            reciveN();
        }catch(IOException e){
            
        }
    }
    
    private void reciveN(){
        buffer = ByteBuffer.allocate(1024);
        while(true){
            try {            
                SocketAddress add;
                if((add = canal.receive(buffer)) != null){
                    buffer.flip();
                    mensaje = new String(buffer.array());
                    System.out.println("El cliente dice: "+mensaje);
                    canal.send(buffer, add);
                    buffer.clear();
                    //               canal.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Serv.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main( String []arg){
        Serv a = new Serv();
    }

}
