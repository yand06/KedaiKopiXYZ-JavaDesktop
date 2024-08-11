package Custom;

import Form.Home;
import java.awt.Color;
import javax.swing.JFrame;

public class LoginBerhasil extends javax.swing.JFrame {
    
    public LoginBerhasil() {
        initComponents();
        setBackground(new Color(0,0,0,0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button1 = new Custom.Button();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        getContentPane().setLayout(null);

        button1.setBackground(new java.awt.Color(226, 135, 67));
        button1.setForeground(new java.awt.Color(0, 0, 0));
        button1.setText("OK");
        button1.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button1MouseExited(evt);
            }
        });
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        getContentPane().add(button1);
        button1.setBounds(100, 140, 100, 25);

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Login Berhasil !");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(80, 90, 160, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BGBear-removebg-preview.png"))); // NOI18N
        jLabel1.setText("OK");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(6, 6, 300, 195);

        setSize(new java.awt.Dimension(309, 207));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        Home home = new Home();
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setVisible(true);
        home.repaint();
        home.revalidate();
        dispose();
    }//GEN-LAST:event_button1ActionPerformed

    private void button1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button1MouseEntered
        button1.setBackground(new Color(158,95,0));
    }//GEN-LAST:event_button1MouseEntered

    private void button1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button1MouseExited
        button1.setBackground(new Color(226,135,67));
    }//GEN-LAST:event_button1MouseExited

    int xx,xy;
    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
//      Mengambil kordinat x dari kursor saat evetn MousePressed terjadi, dengan memanggil metode getX() pada objek evt. 
        xx = evt.getX();
//      Mengambil kordinat y dari kursor saat evetn MousePressed terjadi, dengan memanggil metode getY() pada objek evt.  
        xy = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
//      Mendapatkan kordinat x & y dari kursor saat event terjadi, relatif terhadap layar.
//      Metode getXOnScreen() & getYOnScreen digunakan untuk mendapatkan kordinat kursor dalam sistem kordinat layar.
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
//      Mengubah posisi form aplikasi pada layar. this merujuk pada objek itu sendiri.
//      Metode setLocation(x - xx, y - xy), nilai x & y adalah kordinat saat relatif terhadap layar, sedangkan
//      xx & xy adalah variabel yang menyimpan kordinat awal saat pengguna menggeser/drag from
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_formMouseDragged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(LoginBerhasil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(LoginBerhasil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(LoginBerhasil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(LoginBerhasil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginBerhasil().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Custom.Button button1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
