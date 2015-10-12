package android;

/*
 * Writer
 */
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import javax.swing.JFrame;

public class Sender {
    public static void main(String[] args) throws IOException {
        System.out.println("Sender Start");

        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        ssChannel.configureBlocking(true);
        int port = 31113;
        ssChannel.socket().bind(new InetSocketAddress(port));

       
        JFrame f = new JFrame();
        
        f.setSize(200,200);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        while (true) {
        	
        	System.out.println(ssChannel.socket());
            SocketChannel sChannel = ssChannel.accept();
            System.out.println(sChannel);
            ObjectOutputStream  oos = new 
                      ObjectOutputStream(sChannel.socket().getOutputStream());
            oos.writeObject(f);
            oos.close();

            System.out.println("Connection ended");
        }
    }
}