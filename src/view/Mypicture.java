package view;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.*;
import java.io.IOException;

public class Mypicture extends JPanel{


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        File file=new File("img/hilton1.png");
        try {
            BufferedImage image= ImageIO.read(file);
            g.drawImage(image,0,0,getWidth(),getHeight(),null);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
