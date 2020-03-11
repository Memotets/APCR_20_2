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

/**
 *
 * @author memotets89
 */
public class Serv {
    private MulticastSocket serv;
    private InetAddress ip;
    private byte[] buffer;
    private DatagramPacket pack;
    private int port;
    
    
    public Serv(){
        try{
           this.port = 1234;
           this.serv = new MulticastSocket();
           this.ip = InetAddress.getByName("224.10.10.25");
     //      this.serv.joinGroup(this.ip);
           
           String hi = "WOP";
           
           this.buffer = hi.getBytes();
           
           serv.joinGroup(this.ip);
           
           this.pack = new DatagramPacket(this.buffer,this.buffer.length,this.ip,this.port);
           
           serv.send(pack);
           
        }catch(IOException help){
            help.printStackTrace();
        }
    }
    
    public static void main(String []arg){
        Serv mew = new Serv();
    }
}
