/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad2.DTGMulti;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

/**
 *
 * @author memotets89
 */
public class Client {
    private MulticastSocket socket;
    private int port = 1234;
    private String ip = "224.0.0.2";
    private InetAddress address;
    private DatagramPacket pack;
    private byte[] buffer;
    private String nombre;
    
    public Client(){
        try{
            socket = new MulticastSocket(port);
            address = InetAddress.getByName(ip);
            socket.joinGroup(address);
            Scanner help = new Scanner(System.in);
            System.out.println("Escribir tu nombre");
            nombre = help.nextLine();
             //RECIBIR
             msn aux = new msn(socket, nombre);
             aux.start();
            //ENVIAR
            Scanner sc = new Scanner(System.in);
            System.out.println("Comienza a escribir un mensaje");
            while(true){
                String msn =this.nombre+">>"+  sc.nextLine();
                buffer =msn.getBytes();
                pack = new DatagramPacket(buffer, buffer.length, address, port);
                socket.send(pack);
            }

            
        }catch(IOException e){
        }
    }
    
    public static void main(String args[]){
        Client cc = new Client();
    }
    
}
