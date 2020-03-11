/*
 * Guillermo Ignacio Bautista Garcia
 * DatagramSocket_Server
 * practica3
 * 29/01/20
 */
package unidad2.DtgMsg;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;
//import java.util.Arrays;

/**
 *
 * @author sndmonreal
 */
public class Serv {
    
    public static void main(String args[]) {
        try{
        DatagramSocket serverSocket = new DatagramSocket(1234);
        byte[] recData;
        int i =0;
        
        mkDir();
        Random rn = new Random();
        File file = new File("JavaFiles/copia"+(rn.nextDouble())+".mp3");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream out = new BufferedOutputStream(fos);
        
        while(true)
        {
            recData = new byte[1024];
            DatagramPacket recPacket = new DatagramPacket(recData, recData.length);
            serverSocket.receive(recPacket);
            out.write(recPacket.getData());
            System.out.println("\nPaquete " + (++i) + " escrito en el archivo\n");
            out.flush();
            
        }
        
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void mkDir(){
        File directorio = new File("JavaFiles"); //ruta relativa que sale del proyecto, es donde guardaremos todo
        if (!directorio.exists()){ //si no existe
            directorio.mkdir(); // lo crea
            System.out.println("Carpeta creada");
        }else{ // en caso contrario solo indica que existe
            System.out.println("Carpeta existente");
        }
    }
}
