package example;
import java.awt.Desktop;
import java.net.URI;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class HyperlinkExample extends javax.swing.JFrame {

    public HyperlinkExample() {
        initComponents();
        
        // Buat JLabel
        JLabel hyperlink = new JLabel();
        hyperlink.setText("<html><a href=''>Klik di sini untuk membuka website</a></html>");
        
        // Tambahkan mouse listener
        hyperlink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    // Buka URL di browser
                    Desktop.getDesktop().browse(new URI("http://example.com"));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Gagal membuka link: " + e.getMessage());
                }
            }
        });
        
        // Tambahkan JLabel ke form
        this.add(hyperlink);
        this.pack();
        this.setVisible(true);
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HyperlinkExample.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HyperlinkExample.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HyperlinkExample.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HyperlinkExample.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HyperlinkExample().setVisible(true);
            }
        });
    }

    private void initComponents() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
