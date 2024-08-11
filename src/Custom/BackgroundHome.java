package Custom;
import javax.swing.*;
import java.awt.*;
public class BackgroundHome extends JPanel{
    private Image image;
    //  Konstruktor  
    public BackgroundHome(){
        image = new ImageIcon(getClass().getResource("/img/bgHome.jpg")).getImage();
    }
    
    @Override
    protected void paintComponent (Graphics graphcs){
        super.paintComponent(graphcs);
        
        Graphics2D gd = (Graphics2D) graphcs.create();
        gd.drawImage(image, 0, 0, getWidth(), getHeight(),null);
        gd.dispose();
    }
}
