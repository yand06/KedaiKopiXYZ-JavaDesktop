package Main;


import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import koneksi.koneksi;

public class Register extends javax.swing.JFrame {

    int xx,xy;
    private String user, pass;
    private final java.util.List<String> userList = new ArrayList<>();
    private final java.util.List<String> passList = new ArrayList<>();
    private Connection con;
    private Statement statement;
    Login login = new Login();
    
    public Register() {
        initComponents();
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(226,135,67));
        addPlaceholder(txUsernameReg, "Username");
        addPlaceholder(txPasswordReg, "Password");
        toLogin.setBorder(bottomBorder);
        hover();
        txIdUsernameReg.setEnabled(false);
    }
    
    private void hover(){
        toLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                toLogin.setForeground(new java.awt.Color(158,95,0));
            }
            public void mouseExited(MouseEvent e){
                toLogin.setForeground(new java.awt.Color(226,135,67));
            }
        });
    }
    
    private void Register() {
        koneksi koneksiDatabase = new koneksi();
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = koneksiDatabase.connect();

            // Periksa apakah username dan password tidak kosong
            if (txUsernameReg.getText().isEmpty() || txPasswordReg.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username dan password tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "INSERT INTO pengguna (username, password) VALUES (?, ?)";
            st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, txUsernameReg.getText());
            st.setString(2, txPasswordReg.getText());

            int affectedRows = st.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    String idUser = rs.getString(1);
                    JOptionPane.showMessageDialog(null, "Registrasi Berhasil\nID Pengguna: " + idUser, "Sukses", JOptionPane.INFORMATION_MESSAGE);
                }
                clear();
            } else {
                JOptionPane.showMessageDialog(null, "Registrasi Gagal", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void clear(){
        txIdUsernameReg.setText("");
        txUsernameReg.setText("");
        txPasswordReg.setText("");
        txIdUsernameReg.requestFocus();
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
        txUsernameReg = new javax.swing.JTextField();
        btnRegister = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txPasswordReg = new javax.swing.JPasswordField();
        cbTampilkanSandi = new javax.swing.JCheckBox();
        toLogin = new javax.swing.JLabel();
        txIdUsernameReg = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

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
        jLabel2.setText("REGISTER");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel2.setFocusable(false);
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txUsernameReg.setBackground(new java.awt.Color(255, 255, 255));
        txUsernameReg.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txUsernameReg.setForeground(new java.awt.Color(0, 0, 0));
        txUsernameReg.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txUsernameReg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txUsernameRegFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txUsernameRegFocusLost(evt);
            }
        });
        txUsernameReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txUsernameRegKeyPressed(evt);
            }
        });

        btnRegister.setBackground(new java.awt.Color(226, 135, 67));
        btnRegister.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnRegister.setForeground(new java.awt.Color(0, 0, 0));
        btnRegister.setText("REGISTER");
        btnRegister.setBorder(null);
        btnRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegisterMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegisterMouseExited(evt);
            }
        });
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });
        btnRegister.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnRegisterKeyPressed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(226, 135, 67));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_male_user_30px.png"))); // NOI18N

        jLabel4.setForeground(new java.awt.Color(226, 135, 67));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_password_key_30px_1.png"))); // NOI18N

        txPasswordReg.setBackground(new java.awt.Color(255, 255, 255));
        txPasswordReg.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txPasswordReg.setForeground(new java.awt.Color(0, 0, 0));
        txPasswordReg.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txPasswordReg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txPasswordRegFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txPasswordRegFocusLost(evt);
            }
        });
        txPasswordReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txPasswordRegActionPerformed(evt);
            }
        });
        txPasswordReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txPasswordRegKeyPressed(evt);
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

        toLogin.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        toLogin.setForeground(new java.awt.Color(226, 135, 67));
        toLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        toLogin.setText("Login");
        toLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        toLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                toLoginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                toLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                toLoginMouseExited(evt);
            }
        });

        txIdUsernameReg.setEditable(false);
        txIdUsernameReg.setBackground(new java.awt.Color(255, 255, 255));
        txIdUsernameReg.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txIdUsernameReg.setForeground(new java.awt.Color(0, 0, 0));
        txIdUsernameReg.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txIdUsernameReg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txIdUsernameRegFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txIdUsernameRegFocusLost(evt);
            }
        });
        txIdUsernameReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txIdUsernameRegKeyPressed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ID.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addComponent(jLabel2)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbTampilkanSandi, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txPasswordReg)
                                        .addComponent(txUsernameReg, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                        .addComponent(txIdUsernameReg, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)))))
                        .addGap(71, 71, 71))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(toLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txIdUsernameReg, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txUsernameReg)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(txPasswordReg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTampilkanSandi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toLogin)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout bg11Layout = new javax.swing.GroupLayout(bg11);
        bg11.setLayout(bg11Layout);
        bg11Layout.setHorizontalGroup(
            bg11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg11Layout.createSequentialGroup()
                .addContainerGap(672, Short.MAX_VALUE)
                .addComponent(minimize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(close)
                .addContainerGap())
            .addGroup(bg11Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
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
        dispose();
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

    private void txUsernameRegFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txUsernameRegFocusGained
        
    }//GEN-LAST:event_txUsernameRegFocusGained

    private void txUsernameRegFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txUsernameRegFocusLost
        
    }//GEN-LAST:event_txUsernameRegFocusLost

    private void txPasswordRegFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txPasswordRegFocusGained
    
    }//GEN-LAST:event_txPasswordRegFocusGained

    private void txPasswordRegFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txPasswordRegFocusLost
  
    }//GEN-LAST:event_txPasswordRegFocusLost

    private void txPasswordRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txPasswordRegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txPasswordRegActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        if (txUsernameReg.getText().isEmpty() || txPasswordReg.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Harap isi semua field", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Register();
                JOptionPane.showMessageDialog(null, "Registrasi berhasil", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                clear();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Registrasi gagal: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void txUsernameRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txUsernameRegKeyPressed
        int kode = evt.getKeyCode();
        
        if(kode == evt.VK_ENTER){
            txPasswordReg.requestFocus();
        }
    }//GEN-LAST:event_txUsernameRegKeyPressed

    private void txPasswordRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txPasswordRegKeyPressed
        int kode = evt.getKeyCode();
        
        if(kode == evt.VK_ENTER){
            btnRegister.requestFocus();
        }
    }//GEN-LAST:event_txPasswordRegKeyPressed

    private void btnRegisterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnRegisterKeyPressed
//        String user = txUsernameReg.getText();
//        String pass = txPasswordReg.getText();
//        
//
//        
//        if(user.equals("Admin") && pass.equals("Admin")){
//            JOptionPane.showMessageDialog(null, "Login Berhasil");
//            dispose();
//        } else {
//            JOptionPane.showMessageDialog(null, "Login Gagal");
//            txUsernameReg.setText("");
//            txPasswordReg.setText("");
//            txUsernameReg.requestFocus();
//        }

        if (txUsernameReg.getText().isEmpty() || txPasswordReg.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Harap isi semua field", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Register();
                JOptionPane.showMessageDialog(null, "Registrasi berhasil", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                clear();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Registrasi gagal: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnRegisterKeyPressed

    private void cbTampilkanSandiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTampilkanSandiActionPerformed
        boolean showPassword = cbTampilkanSandi.isSelected();

        if (showPassword) {
            txPasswordReg.setEchoChar((char)0); // Menggunakan karakter kosong untuk menampilkan password
        } else {
            txPasswordReg.setEchoChar('*'); // Menggunakan karakter '*' untuk menyembunyikan password
        }
    }//GEN-LAST:event_cbTampilkanSandiActionPerformed

    private void toLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toLoginMouseClicked
        dispose();
        login.setVisible(true);
        login.validate();
    }//GEN-LAST:event_toLoginMouseClicked

    private void btnRegisterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegisterMouseEntered
        btnRegister.setBackground(new java.awt.Color(158,95,0));
    }//GEN-LAST:event_btnRegisterMouseEntered

    private void btnRegisterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegisterMouseExited
        btnRegister.setBackground(new java.awt.Color(226,135,67));
    }//GEN-LAST:event_btnRegisterMouseExited

    private void txIdUsernameRegFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txIdUsernameRegFocusGained

    }//GEN-LAST:event_txIdUsernameRegFocusGained

    private void txIdUsernameRegFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txIdUsernameRegFocusLost

    }//GEN-LAST:event_txIdUsernameRegFocusLost

    private void txIdUsernameRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txIdUsernameRegKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txIdUsernameRegKeyPressed

    private void toLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toLoginMouseEntered
         // TODO add your handling code here:
    }//GEN-LAST:event_toLoginMouseEntered

    private void toLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toLoginMouseExited
         // TODO add your handling code here:
    }//GEN-LAST:event_toLoginMouseExited

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
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Main.bg1 bg11;
    private javax.swing.JButton btnRegister;
    private javax.swing.JCheckBox cbTampilkanSandi;
    private javax.swing.JLabel close;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel minimize;
    private javax.swing.JLabel toLogin;
    private javax.swing.JTextField txIdUsernameReg;
    private javax.swing.JPasswordField txPasswordReg;
    private javax.swing.JTextField txUsernameReg;
    // End of variables declaration//GEN-END:variables
}
