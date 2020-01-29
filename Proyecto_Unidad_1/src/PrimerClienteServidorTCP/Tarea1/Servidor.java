/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimerClienteServidorTCP.Tarea1;
import PrimerClienteServidorTCP.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Alumno
 */
public class Servidor {
    private ServerSocket serv;
    private Socket clint;
    private InputStream in;
    private OutputStream out;
    private String ip, protocolo, msn;
    private int port;
    private DataInputStream msnIn;
    private DataOutputStream msnOut;
    private boolean ServOn;
    
    public Servidor(){
        this.port = 1234;
    }
    public void openServ(){
        try {
            this.ServOn = true;
            this.serv = new ServerSocket(port);
            System.out.println("Servidor a la escucha...");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void closeServ(){
        try {
            this.ServOn = false;
            this.serv.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    public static void main (String []ad){
        Servidor wop = new Servidor();
        wop.openServ();
        wop.clientConnected();
        while (wop.ServOn){
            wop.recive();
            if (wop.msn.equals("exit")){
                wop.clientDisconnected();
                wop.closeServ();
                break;
            }
            if (wop.msn.indexOf("echo ") == 0){
                wop.msn = wop.msn.replaceFirst("echo ", "");
                wop.send();
                System.out.println("Mensaje enviado");
            }else{
                wop.msn =  "Hay un error en su comando, recuerde que solo puede utilizar 'echo' para repetir y 'exit' para salir";
                wop.send();
            }            
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

    public void send (){
        try {
            //Flujo de salida
            this.out = this.clint.getOutputStream();
             // enviar mensaje al cliente
            msnOut = new DataOutputStream(out);
            msnOut.writeUTF(msn);
            
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
            this.msn=this.msnIn.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
