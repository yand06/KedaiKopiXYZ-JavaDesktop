package Main;

import Custom.LoginBerhasil;
import Form.Home;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import koneksi.koneksi;


public class Login extends javax.swing.JFrame {

    int xx,xy;
    private String user, pass;
    private java.util.List<String> userList = new ArrayList<>();
    private java.util.List<String> passList = new ArrayList<>();
    private Connection con;
    
    public Login() {
        initComponents();
        User();
        addPlaceholder(txUsername, "Username");
        addPlaceholder(txPassword, "Password");
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(226,135,67));
        toRegister.setBorder(bottomBorder);
        hover();
    }
    
    public JTextField getTxUsername(){
        return txUsername;
    }
    
    private void hover(){
        toRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                toRegister.setForeground(new java.awt.Color(158,95,0));
            }
            public void mouseExited(MouseEvent e){
                toRegister.setForeground(new java.awt.Color(226,135,67));
            }
        });
    }
    
    private void User(){
        koneksi koneksiDatabase = new koneksi();
        con = koneksiDatabase.connect();
        
         try{
            String query = "Select * From pengguna;";
//          Menyiapkan statement SQL untuk dieksekusi dengan menggunakan objek Connection bernama con  
            PreparedStatement st = con.prepareStatement(query);
//          Mengeksekusi query SQL dan menyimpan hasilnya dalam objek ResulSet bernama rs  
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                user = rs.getString("username");
                pass = rs.getString("password");
                userList.add(user);
                passList.add(pass);
            }
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void login(){
        User();
        //      Mendapatkan nilai dari inputan user pada username dan password  
        String username = txUsername.getText();
        String password = new String(txPassword.getPassword());
        
//      Inisialisasi variabel untuk autentikasi  
        boolean autentikasi = false;
        
//      Melakukan perulangan melalui daftar username dan password  
        for(int i = 0; i < userList.size(); i++){
//          Membandingkan input username dan password dengan setiap pasangan di database 
            if(username.equals(userList.get(i)) && password.equals(passList.get(i))){
//          Jika cocok, set autentikasi menjadi true dan keluar dari perulangan  
                autentikasi = true;
                break;
            }
        }
        
//      Membuat instance dari kelas MenuUtama  
//      MenuUtama menu = new MenuUtama();
        Home h = new Home();

//      Ketika pengguna tidak menginput apapun (kosong)  
        if (username.isEmpty() && password.isEmpty()){
            JOptionPane.showMessageDialog(null, "Inputan tidak valid !!!");
            txUsername.requestFocus();
//      Memeriksa status autentikasi            
        }else if(autentikasi) {
//          Jika autentikasi berhasil  
//            JOptionPane.showMessageDialog(null, "Login Berhasil"); // Tampilkan pesan sukses 
            LoginBerhasil logSuccses = new LoginBerhasil();
            logSuccses.setVisible(true);

//            h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////            h.setExtendedState(JFrame.MAXIMIZED_BOTH);
//            h.setVisible(true);
//            h.validate(); // Memvalidasi komponen Form Dashboard

        }else {
//          Jika autentikasi gagal  
            JOptionPane.showMessageDialog(this,"Username atau Password Salah" ,"Login Gagal", JOptionPane.ERROR_MESSAGE); // Tampilkan pesan gagal
            txUsername.requestFocus(); // Memfokuskan kursor pada field input username
        }
    }
    
    private void addPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg11 = new Main.bg1();
        close = new javax.swing.JLabel();
        minimize = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txUsername = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txPassword = new javax.swing.JPasswordField();
        cbTampilkanSandi = new javax.swing.JCheckBox();
        toRegister = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 5));

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_close_window_30px.png"))); // NOI18N
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });

        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_macos_minimize_30px.png"))); // NOI18N
        minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeMouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 135, 67), 3));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/left.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(226, 135, 67));
        jLabel2.setText("LOGIN");

        txUsername.setBackground(new java.awt.Color(255, 255, 255));
        txUsername.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txUsername.setForeground(new java.awt.Color(0, 0, 0));
        txUsername.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txUsernameFocusLost(evt);
            }
        });
        txUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txUsernameKeyPressed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(226, 135, 67));
        btnLogin.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(0, 0, 0));
        btnLogin.setText("LOGIN");
        btnLogin.setBorder(null);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLoginMouseExited(evt);
            }
        });
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        btnLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLoginKeyPressed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(226, 135, 67));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_male_user_30px.png"))); // NOI18N

        jLabel4.setForeground(new java.awt.Color(226, 135, 67));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_password_key_30px_1.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Awali pagimu dengan secangkir Kopi");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txPassword.setBackground(new java.awt.Color(255, 255, 255));
        txPassword.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txPassword.setForeground(new java.awt.Color(0, 0, 0));
        txPassword.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txPasswordFocusLost(evt);
            }
        });
        txPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txPasswordActionPerformed(evt);
            }
        });
        txPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txPasswordKeyPressed(evt);
            }
        });

        cbTampilkanSandi.setBackground(new java.awt.Color(255, 255, 255));
        cbTampilkanSandi.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbTampilkanSandi.setForeground(new java.awt.Color(0, 0, 0));
        cbTampilkanSandi.setText("Tampilkan Sandi");
        cbTampilkanSandi.setBorder(null);
        cbTampilkanSandi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTampilkanSandiActionPerformed(evt);
            }
        });

        toRegister.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        toRegister.setForeground(new java.awt.Color(226, 135, 67));
        toRegister.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        toRegister.setText("Register");
        toRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        toRegister.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                toRegisterMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                toRegisterMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                toRegisterMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbTampilkanSandi, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txPassword)
                                        .addComponent(txUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)))))
                        .addGap(71, 71, 71))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(125, 125, 125))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(toRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(162, 162, 162))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel5)
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txUsername)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txPassword)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTampilkanSandi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toRegister)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout bg11Layout = new javax.swing.GroupLayout(bg11);
        bg11.setLayout(bg11Layout);
        bg11Layout.setHorizontalGroup(
            bg11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg11Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(bg11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg11Layout.createSequentialGroup()
                        .addComponent(minimize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(close)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg11Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(73, Short.MAX_VALUE))))
        );
        bg11Layout.setVerticalGroup(
            bg11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bg11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(minimize)
                    .addComponent(close))
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        getContentPane().add(bg11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 420));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        System.exit(0);
    }//GEN-LAST:event_closeMouseClicked

    private void minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseClicked
        setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_minimizeMouseClicked

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

    private void txUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txUsernameFocusGained

    }//GEN-LAST:event_txUsernameFocusGained

    private void txUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txUsernameFocusLost

    }//GEN-LAST:event_txUsernameFocusLost

    private void cbTampilkanSandiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTampilkanSandiActionPerformed
        // Mendapatkan status ceklis pada JCheckBox
        boolean showPassword = cbTampilkanSandi.isSelected();

        // Menetapkan karakter echo untuk JPasswordField
        if (showPassword) {
            txPassword.setEchoChar((char)0); // Menggunakan karakter kosong untuk menampilkan password
        } else {
            txPassword.setEchoChar('*'); // Menggunakan karakter '*' untuk menyembunyikan password
        }
    }//GEN-LAST:event_cbTampilkanSandiActionPerformed

    private void txPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txPasswordFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txPasswordFocusGained

    private void txPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txPasswordFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txPasswordFocusLost

    private void txPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txPasswordActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        if(txUsername.getText().isEmpty()||txPassword.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Isi Username dan Password Terlebih Dahulu!");
        }else{
            login();
        }
        
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txUsernameKeyPressed
        int kode = evt.getKeyCode();
        
        if(kode == evt.VK_ENTER){
            txPassword.requestFocus();
        }
    }//GEN-LAST:event_txUsernameKeyPressed

    private void txPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txPasswordKeyPressed
        int kode = evt.getKeyCode();
        
        if(kode == evt.VK_ENTER){
            btnLogin.requestFocus();
        }
    }//GEN-LAST:event_txPasswordKeyPressed

    private void btnLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLoginKeyPressed
        login();
    }//GEN-LAST:event_btnLoginKeyPressed

    private void toRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toRegisterMouseClicked
        dispose();
        Register registrasi = new Register();
        registrasi.setVisible(true);
        registrasi.validate();
    }//GEN-LAST:event_toRegisterMouseClicked

    private void btnLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseEntered
        btnLogin.setBackground(new java.awt.Color(158,95,0));
    }//GEN-LAST:event_btnLoginMouseEntered

    private void btnLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseExited
        btnLogin.setBackground(new java.awt.Color(226,135,67));
    }//GEN-LAST:event_btnLoginMouseExited

    private void toRegisterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toRegisterMouseEntered
         // TODO add your handling code here:
    }//GEN-LAST:event_toRegisterMouseEntered

    private void toRegisterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toRegisterMouseExited
         // TODO add your handling code here:
    }//GEN-LAST:event_toRegisterMouseExited

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Main.bg1 bg11;
    private javax.swing.JButton btnLogin;
    private javax.swing.JCheckBox cbTampilkanSandi;
    private javax.swing.JLabel close;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel minimize;
    private javax.swing.JLabel toRegister;
    private javax.swing.JPasswordField txPassword;
    private javax.swing.JTextField txUsername;
    // End of variables declaration//GEN-END:variables
}
