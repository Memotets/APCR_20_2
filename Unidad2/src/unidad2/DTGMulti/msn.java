/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad2.DTGMulti;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author memotets89
 */
public class msn extends Thread{
    
    private MulticastSocket sc;
    private DatagramPacket dg;
    private byte[] arr;
    private String nombre;
    
    public msn (MulticastSocket sc, String aux){
        this.sc = sc;
        this.nombre = aux;
    }
    
    @Override
    public void run(){
        while(true){
            try {
                arr = new byte[1024];
                dg = new DatagramPacket(arr, arr.length);
                sc.receive(dg);
                byte [] msg = new byte[dg.getLength()];
                System.arraycopy(dg.getData(), 0, msg, 0, msg.length);
                String mg = new String(msg,"UTF8");
                String[] aux = mg.split(">>");
                if (aux[0] == null ? this.nombre != null : !aux[0].equals(this.nombre))
                System.out.println(mg);
            } catch (IOException ex) {
                Logger.getLogger(msn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
