package interfaz.image_loader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class BufferedImageLoader {

    BufferedImage image;

    public BufferedImage loadImage(String path){
        try {
            File f = new File(path);

            /*
            String[] children = f.list();

            if (children == null) {
                System.out.println("does not exist or is not a directory");
            } else {
                for (int i = 0; i < children.length; i++) {
                    String filename = children[i];
                    System.out.println(filename);
                }
            }

            */

            if(f.exists() && !f.isDirectory()) {
                //image=ImageIO.read(getClass().getResource(path));
                image = (BufferedImage) (new ImageIcon(path).getImage());
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }



}