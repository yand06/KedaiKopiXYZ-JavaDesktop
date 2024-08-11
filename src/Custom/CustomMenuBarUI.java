package Custom;

import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuBarUI;
import java.awt.*;

public class CustomMenuBarUI extends BasicMenuBarUI {

    @Override
    public void paint(Graphics g, JComponent c) {
        g.setColor(new Color(98, 48, 0)); // Set warna latar belakang menuBar
        g.fillRect(0, 0, c.getWidth(), c.getHeight()); // Gambar latar belakang menuBar
    }
}
