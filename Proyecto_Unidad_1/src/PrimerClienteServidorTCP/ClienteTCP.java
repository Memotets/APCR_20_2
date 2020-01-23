/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimerClienteServidorTCP;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Alumno
 */
public class ClienteTCP {
    
    private Socket clint;
    private InputStream in;
    private OutputStream out;
    private DataInputStream msnIn;
    private DataOutputStream msnOut;
    private int host_port;
    private String host_ip;
    
    
    public ClienteTCP(int p, String ip){
        this.host_port= p;
        this.host_ip=ip;
    }
    public static void main (String []ad){
       test();
       test();
       test();
       
    }
    public static void test(){
        ClienteTCP wop = new ClienteTCP(1234, "127.0.0.1");
        wop.openClient();
        wop.recive();
        wop.send("Hola, soy nuevo");
        wop.closeClient();
    }
    
    public void openClient(){
        try {
            this.clint= new Socket(this.host_ip,this.host_port);
            //this.clint.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    public void closeClient(){
        try {
           // this.clint= new Socket(this.host_ip,this.host_port);
            this.clint.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public void send (String msn){
        try {
            //Flujo de salida
            this.out = this.clint.getOutputStream();
             // enviar mensaje al cliente
            msnOut = new DataOutputStream(out);
            msnOut.writeUTF(this.host_ip+": "+msn);
            
        } catch (IOException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void recive (){
        try {
            //Flujo de entrada
            this.in = this.clint.getInputStream();
            //mensaje de entrada
            this.msnIn = new DataInputStream(in);
            System.out.println(this.msnIn.readUTF());
        } catch (IOException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
