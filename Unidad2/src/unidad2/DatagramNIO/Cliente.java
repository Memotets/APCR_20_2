/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad2.DatagramNIO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author memotets89
 */
public class Cliente {
    private DatagramChannel canal;
    private ByteBuffer buffer;
    private int port;
    private String ip;
    private String mensaje;
    
    public Cliente(int n, String p){
        this.port = n;
        this.ip = p;
        
        try{
            canal = DatagramChannel.open();
            canal.configureBlocking(false);
            canal.bind(null);
            System.out.println("Cliente activo");
            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void SendN(String nWord){
        try {
            buffer = ByteBuffer.wrap(nWord.getBytes());
            InetSocketAddress sa = new InetSocketAddress(ip, port);
            canal.send(buffer,sa);
            buffer.clear();
            System.out.println("Enviado");
            canal.receive(buffer);
            buffer.flip();
            mensaje = new String(buffer.array());
            System.out.println("El cliente dice: "+mensaje);
                    buffer.clear();
                    canal.close();            
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main( String []arg){
        Cliente a = new Cliente(1234,"localhost");
        a.SendN("N-Word pass");

    }

}
