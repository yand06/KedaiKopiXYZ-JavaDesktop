package Main;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class bg1 extends JPanel{
    private Image image;
    
//  Konstruktor  
    public bg1(){
        image = new ImageIcon(getClass().getResource("/img/bg.jpg")).getImage();
    }
    
    @Override
    protected void paintComponent (Graphics graphcs){
        super.paintComponent(graphcs);
        
        Graphics2D gd = (Graphics2D) graphcs.create();
        gd.drawImage(image, 0, 0, getWidth(), getHeight(),null);
        gd.dispose();
    }

}
