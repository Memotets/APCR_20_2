/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class servidor {
    private DatagramSocket wop;
    private DatagramPacket send, get;
    private byte msnSend[], msnGet[],  aux[];
    private final int port = 1234;
    
    public servidor() throws IOException{
        this.wop = new DatagramSocket(port);
        
        this.msnGet = new byte[100];
        this.get = new DatagramPacket(msnGet, msnGet.length);
        this.wop.receive(get);

        this.aux = new byte[100];
        this.send = new DatagramPacket(aux, aux.length);
        this.wop.receive(send);
        
        
        this.msnSend = this.get.getData();
        this.send = new DatagramPacket(msnSend, msnSend.length, send.getAddress(),send.getPort());
        this.wop.send(send);
        
        this.send = new DatagramPacket(aux, aux.length, get.getAddress(),get.getPort()); 
        this.wop.send(send);
        
        wop.close();
        
    }
    
    
    public static void main(String[] args) throws IOException {
        servidor s = new servidor();
    }
    
}
