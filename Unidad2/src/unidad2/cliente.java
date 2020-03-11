/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author memotets89
 */
public class cliente {
    private DatagramSocket cliente;
    private DatagramPacket send, get;
    private byte msnSend[], msnGet[];
    private final int port = 1234;
    private InetAddress ip;
    
        public static void main(String[] args) throws IOException {
        cliente c = new cliente("Hola yo soy el numero 1");
    }
    
    public cliente(String msn) throws IOException{
        cliente = new DatagramSocket();
        msnSend = msn.getBytes();
        ip = InetAddress.getByName("localhost");
        
        send = new DatagramPacket(msnSend, msnSend.length,ip,port);
        cliente.send(send);
       
        msnGet = new byte[100];
        this.get = new DatagramPacket(msnGet, msnGet.length);
        cliente.receive(get);
                
        System.out.print(new String(get.getData()));
    }
}
