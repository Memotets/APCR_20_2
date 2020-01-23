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
public class ServidorTCP {
    private ServerSocket serv;
    private Socket clint;
    private InputStream in;
    private OutputStream out;
    private String ip, protocolo;
    private int port;
    private DataInputStream msnIn;
    private DataOutputStream msnOut;
    
    public ServidorTCP(){
        this.port = 1234;
    }
    public void openServ(){
        try {
            this.serv = new ServerSocket(port);
            System.out.println("Servidor a la escucha...");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void closeServ(){
        try {
           // this.clint= new Socket(this.host_ip,this.host_port);
            this.serv.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    public static void main (String []ad){
        ServidorTCP wop = new ServidorTCP();
        wop.openServ();
        while (true){
            wop.clientConnected();
            wop.send("Bienvenido al infierno... gustas galletas(?)");
            wop.recive();
            wop.clientDisconnected();
        }
    }
    public void clientConnected(){
        try {
            this.clint = serv.accept();
            InetAddress ipCliente = clint.getInetAddress();
            System.out.println("Se conecto: " + ipCliente.getHostAddress());
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    public void clientDisconnected(){
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
            msnOut.writeUTF("Host: "+msn);
            
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
