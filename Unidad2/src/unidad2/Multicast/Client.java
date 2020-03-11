/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad2.Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author memotets89
 */
public class Client {
    private MulticastSocket client;
    private InetAddress ip;
    private byte[] buffer;
    private DatagramPacket pack;
    private int port;
    
    public Client(){
        try {
            this.port = 1234;
            this.client = new MulticastSocket(this.port);
            this.ip = InetAddress.getByName("224.10.10.25");
            this.client.joinGroup(this.ip);
            this.buffer = new byte[512];
            this.pack = new DatagramPacket(this.buffer,this.buffer.length);
            this.client.receive(pack);
            System.out.println(new String(this.pack.getData()));
            this.client.leaveGroup(ip);
            this.client.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        public static void main(String []arg){
        Client mew = new Client();
    }
}
