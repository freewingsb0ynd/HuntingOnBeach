import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Hoangelato on 14/07/2016.
 */
public class Background {
    BufferedImage blankBackground, loadedItem;

    public void update(){

    }

    public void draw(Graphics g){
        try {
            loadedItem= ImageIO.read(new File("Resource/Background/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(loadedItem, 0, 0, null);

        try {
            loadedItem=ImageIO.read(new File("Resource/Background/image 132.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(loadedItem, 420, 200, null);


    };





}
