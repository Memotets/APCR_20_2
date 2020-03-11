/*
 * Guillermo Ignacio Bautista Garcia
 * DatagramSocket_client
 * practica3
 * 29/01/20
 */
package unidad2.DtgMsg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;

/**
 *
 * @author sndmonreal
 */
public class Client {

    static int serverPort;
    static String filename;

    public static void main(String args[]) throws InterruptedException {
        try {
            int count = 0;
            int MAX_SIZE = 1024;

            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IpAddress = InetAddress.getByName("localhost");

            byte[] sendData = new byte[MAX_SIZE];

            String filePath = "Godzilla.mp3";
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);

            int totLength = 0;

            while ((count = fis.read(sendData)) != -1)
            {
                totLength += count;
            }

            System.out.println("Longitud total :" + totLength);

            int noOfPackets = totLength / MAX_SIZE;
            System.out.println("No de paquetes : " + noOfPackets);

            int off = noOfPackets * MAX_SIZE;

            int lastPackLen = totLength - off;
            System.out.println("\nLongitud del último paquete : " + lastPackLen);

            byte[] lastPack = new byte[lastPackLen - 1];

            fis.close();
            
            sendData = new byte[MAX_SIZE];
            FileInputStream fis1 = new FileInputStream(file);
            int aux = noOfPackets;
            while ((count = fis1.read(sendData)) != -1) {
                if (noOfPackets <= 0) {
                    break;
                }
                //System.out.println(new String(sendData));
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IpAddress, 1234);
                clientSocket.send(sendPacket);
                System.out.println("paquete "+noOfPackets%aux  +" enviado" + sendPacket);
                noOfPackets--;
                sendData = new byte[MAX_SIZE];
                sleep(10);
            }

            System.out.println("\núltimo paquete\n");
            //System.out.println(new String(sendData));

            lastPack = Arrays.copyOf(sendData, lastPackLen);
            System.out.println("\nActual último paquete\n");
            //System.out.println(new String(lastPack));
            DatagramPacket sendPacket1 = new DatagramPacket(lastPack, lastPack.length, IpAddress, 1234);
            clientSocket.send(sendPacket1);
            System.out.println("último paquete enviado" + sendPacket1);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
