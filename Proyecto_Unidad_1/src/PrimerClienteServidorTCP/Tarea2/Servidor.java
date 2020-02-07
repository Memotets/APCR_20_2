/*
 * Guillermo Ignacio Bautista Garcia
 * Sockets
 * emular un servidor de dns
 * 30/01/20
 */
package PrimerClienteServidorTCP.Tarea2;
import PrimerClienteServidorTCP.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
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
    private ArrayList<EstructuraDNS> Lista;

    public Servidor(){
        this.port = 1234;
        Lista = new ArrayList<>();
        Lista.add(new EstructuraDNS("www.google.com","8.8.8.8"));
        Lista.add(new EstructuraDNS("www.ejemplo.com","10.123.2.123"));
        Lista.add(new EstructuraDNS("www.gatosHaciendoCosas.net","6.45.9.182"));
        Lista.add(new EstructuraDNS("Monografias.com","134.2.34.253"));
        Lista.add(new EstructuraDNS("www.AscoDeVida.com","204.231.4.23"));
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
            wop.Lista.forEach((l) -> {
                if (l.esIgual(wop.msn)){
                    wop.send(l);
                }
            }); 
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
            this.clint.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public void send (EstructuraDNS e){
        try {
            //Flujo de salida
            this.out = this.clint.getOutputStream();
             // enviar mensaje al cliente
            msnOut = new DataOutputStream(out);
            if(e.getDominio().equals(msn)){
                msnOut.writeUTF(e.getIP());
            }else{
                msnOut.writeUTF(e.getDominio());
            }

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
