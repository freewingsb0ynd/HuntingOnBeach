import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Hoangelato on 13/07/2016.
 */
public class GameWindow extends Frame implements  Runnable{
    BufferedImage background;
    BufferedImage bufferImage;

    BufferedImage mouseIcon;

    Background drawedBackground= new Background();
    int mousepressedtime=0;
    int pressingornot=0;
    Player player=new Player();
    Enemies enemy1= new EnemyLvl1(player);
    Enemies enemy2= new EnemyLvl2(player);
    Enemies enemy3= new EnemyLvl3(player);
    Enemies enemy4= new EnemyLvl4(player);

    @Override
    public void run() {
        gameLoop();
    }
    public GameWindow() throws IOException {
        initGame();
    }

    void initGame(){
        this.setTitle("Hunting on beach");
        this.setSize(640,480);
        this.setVisible(true);
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");
        this.setCursor(blankCursor);

        //cam bien chuot
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                player.posX = e.getX()-25;
                player.posY = e.getY()-25;
            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 1) {
                    mousepressedtime++;

                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton()==1) {
                    mousepressedtime=0;
                    pressingornot=0;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    void gameUpdate(){
        if (mousepressedtime>0) {
            pressingornot=1;
        }
        if (pressingornot==1) player.shot();
        enemy1.checkIfHit(player.posX, player.posY);
        enemy2.checkIfHit(player.posX, player.posY);
        enemy3.checkIfHit(player.posX, player.posY);
        enemy4.checkIfHit(player.posX, player.posY);

        enemy1.update();
        enemy2.update();
        enemy3.update();
        enemy4.update();
        //

    }

    public void drawCursor(Graphics g,int posX, int posY) {
        try
        {
            mouseIcon=ImageIO.read(new File("Resource/Char/aim_icon.png"));
            mouseIcon = resizeicon(mouseIcon, 50, 50);
        }catch(Exception e){}
        g.drawImage(mouseIcon, posX, posY, null);
    }
    public static BufferedImage resizeicon(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }


    void gameLoop(){
        while (true) {
            try {
                gameUpdate();
                repaint();
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ;
        }
    }
    @Override
    public void update(Graphics g) {
        if(bufferImage == null){
            bufferImage = new BufferedImage(640, 480, 1);
        }
        Graphics bufferGraphics = bufferImage.getGraphics();
        bufferGraphics.drawImage(bufferImage, 0, 0, null);
        drawedBackground.draw(bufferGraphics);
        enemy1.draw(bufferGraphics);
        enemy2.draw(bufferGraphics);
        enemy3.draw(bufferGraphics);
        enemy4.draw(bufferGraphics);
        player.draw(bufferGraphics);

        drawCursor(bufferGraphics,player.posX,player.posY);
        g.drawImage(bufferImage, 0, 0, null);

    }



}
