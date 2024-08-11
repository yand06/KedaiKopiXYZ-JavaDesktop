package Custom;


import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuUI;
import java.awt.*;

public class CenteredMenuUI extends BasicMenuUI {

    @Override
    public void paint(Graphics g, JComponent c) {
        JMenu menu = (JMenu) c;
        Graphics2D g2d = (Graphics2D) g;
        Font font = menu.getFont();
        FontMetrics metrics = g.getFontMetrics(font);
        String text = menu.getText();
        
        int width = menu.getWidth();
        int height = menu.getHeight();
        
        // Calculate the x position to center the text
        int textWidth = metrics.stringWidth(text);
        int x = (width - textWidth) / 2;
        
        // Calculate the y position to center the text
        int textHeight = metrics.getAscent();
        int y = (height + textHeight) / 2 - metrics.getDescent();
        
        // Paint background
        g2d.setColor(menu.getBackground());
        g2d.fillRect(0, 0, width, height);
        
        // Paint text
        g2d.setFont(font);
        g2d.setColor(menu.getForeground());
        g2d.drawString(text, x, y);
    }
}
