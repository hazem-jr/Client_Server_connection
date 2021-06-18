
package server;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Server {

    public static void main(String[] args) throws IOException {
        
         // design simple Gui 
        JFrame jf = new JFrame("Server") ;
        jf.setSize(400, 400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel descriptionlabel = new JLabel( "Waiting for image... " ) ;
        jf.add(descriptionlabel , BorderLayout.SOUTH);
        
        jf.setVisible(true);
        
        // set connection to client 
        ServerSocket serverSocket = new ServerSocket(1234) ;
        Socket socket = serverSocket.accept() ;
        
        // get data stream 
        InputStream inputStream = socket.getInputStream() ;
        
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream) ;
        BufferedImage bufferedimage = ImageIO.read(bufferedInputStream) ;
        
        bufferedInputStream.close();
        socket.close();
        
        JLabel imageLabel  = new JLabel(new ImageIcon(bufferedimage)) ;
        descriptionlabel.setText("Image received ");
        
        
        jf.add(imageLabel , BorderLayout.CENTER) ;
        
        
        
    }
    
}
