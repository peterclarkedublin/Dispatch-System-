package android;

/*
 * Reader
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

import javax.swing.JFrame;

public class Receiver {
    public static void main(String[] args) 
    throws IOException, ClassNotFoundException {

    	InetSocketAddress address = new InetSocketAddress("localhost", 31113);
        SocketChannel sChannel = SocketChannel.open();
        sChannel.configureBlocking(true);
       
        if (sChannel.connect(address)) {

            ObjectInputStream ois = 
                     new ObjectInputStream(sChannel.socket().getInputStream());

          
            JFrame f = (JFrame)ois.readObject();
            f.setVisible(true);
        }

        System.out.println("End Receiver");
    }
}