package client_1;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Client_1 {

    public static void main(String[] args) throws IOException {
        // set connection 
        Socket s1 = new Socket("localhost" ,  1234) ; 
        System.out.println("Connected to the server ") ;
        
        // design simple Gui 
        JFrame jf = new JFrame("Client") ;
        jf.setSize(400, 400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // get image path 
        ImageIcon image_icon = new ImageIcon("C:\\Users\\Latitude E7270\\Downloads\\hazem.jpg") ; 
        
        JLabel label = new JLabel(image_icon ) ;
        JButton btn = new JButton("Send to the Server =>  ") ; 
        
        jf.add(label , BorderLayout.CENTER) ;
        jf.add( btn, BorderLayout.SOUTH ) ;
        
        jf.setVisible(true);
        
        // add button action
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    
                    // open a stream 
                    OutputStream outputstream = s1.getOutputStream() ;
                    BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(outputstream );
                    
                    Image image = image_icon.getImage() ;
                    
                    BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
                
                    Graphics graphics = bufferedImage.createGraphics() ;
                    graphics.drawImage(image, 0, 0, null) ;
                    graphics.dispose();
                    
                    // class used for encoding and decoding 
                    ImageIO.write(bufferedImage, "jpg", bufferedoutputstream) ;
                    
                    // close connection 
                    bufferedoutputstream.close();
                    s1.close();
                    
                    
                
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
            }
       

            
            
        });
        
        
    }


    
}
