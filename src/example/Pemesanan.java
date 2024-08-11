package example;

import example.PemesananKopi;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import koneksi.koneksi;

public class Pemesanan extends javax.swing.JFrame {

    private final Connection conn = new koneksi().connect();
    private List<MenuItem> cartItems;
    public Pemesanan() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        setTime();
        loadMenuCards();
        cartItems = new ArrayList<>();
        CartMenu.setLayout(new GridLayout(0,3,30,30));
        CartMenu.setBorder(new EmptyBorder(30, 30, 30, 30));
        CartMenu.setBorder(new EmptyBorder(20, 20, 20, 20));
    }

    private void setTime(){
        new Thread (new Runnable() {
            @Override
            public void run() {
                while(true){
                    try
                    {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex)
                    {
                        Logger.getLogger(PemesananKopi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Date date = new Date();
                    SimpleDateFormat tf = new SimpleDateFormat("h:mm:ss aa");
                    SimpleDateFormat df = new SimpleDateFormat("EEEE, dd-MM-yyyy");
                    String time = tf.format(date);
                    txTime.setText(time.split(" ")[0]+" "+time.split(" ")[1]);
                    txDate.setText(df.format(date));
                }
            }
        }).start();
    }
    
    private void loadMenuCards() {
        String sql = "SELECT * FROM menu";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            ResultSet hasil = stat.executeQuery();
            while (hasil.next()) {
                String idMenu = hasil.getString("idMenu");
                String namaMenu = hasil.getString("namaMenu");
                String harga = hasil.getString("harga");
                byte[] imageData = hasil.getBytes("gambarMenu");

                JPanel card = new JPanel(new BorderLayout());
                card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
                card.setBackground(Color.WHITE);

                ImageIcon icon = new ImageIcon(imageData);
                JLabel labelGambar = new JLabel(icon);
                labelGambar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                card.add(labelGambar, BorderLayout.NORTH);

                JPanel detailPanel = new JPanel(new GridLayout(3, 1, 10, 10));
                detailPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                detailPanel.setBackground(Color.WHITE);
                JLabel labelNama = new JLabel(namaMenu);
                labelNama.setFont(new Font("SansSerif", Font.BOLD, 16));
                JLabel labelHarga = new JLabel("Harga: Rp " + harga);
                labelHarga.setFont(new Font("SansSerif", Font.PLAIN, 14));
                JSpinner spinnerQuantity = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
                spinnerQuantity.setPreferredSize(new Dimension(100, 30));
                JButton buttonPurchase = new JButton("Beli");
                buttonPurchase.setBackground(new Color(52, 152, 219));
                buttonPurchase.setForeground(Color.WHITE);
                buttonPurchase.setFont(new Font("SansSerif", Font.BOLD, 14));
                buttonPurchase.addActionListener(new PurchaseActionListener(idMenu, namaMenu, harga, spinnerQuantity));
                detailPanel.add(labelNama);
                detailPanel.add(labelHarga);
                detailPanel.add(spinnerQuantity);
                detailPanel.add(buttonPurchase);

                card.add(detailPanel, BorderLayout.CENTER);
                CartMenu.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public class PurchaseActionListener implements ActionListener {
        private String idMenu;
        private String namaMenu;
        private String harga;
        private JSpinner spinnerQuantity;

        public PurchaseActionListener(String idMenu, String namaMenu, String harga, JSpinner spinnerQuantity) {
            this.idMenu = idMenu;
            this.namaMenu = namaMenu;
            this.harga = harga;
            this.spinnerQuantity = spinnerQuantity;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int quantity = (int) spinnerQuantity.getValue();
            double totalHargaDouble = Double.parseDouble(harga) * quantity;
            int totalHarga = (int) Math.round(totalHargaDouble);
            MenuItem item = new MenuItem(idMenu, namaMenu, harga, quantity, totalHarga);
            cartItems.add(item);

            updateReceiptArea();
        }
    }

    private void updateReceiptArea() {
        StringBuilder struk = new StringBuilder();
        int totalPembelian = 0;

        for (MenuItem item : cartItems) {
            struk.append("ID Menu: ").append(item.getIdMenu()).append("\n");
            struk.append("Nama Menu: ").append(item.getNamaMenu()).append("\n");
            struk.append("Harga: Rp ").append(item.getHarga()).append("\n");
            struk.append("Quantity: ").append(item.getQuantity()).append("\n");
            struk.append("Total: Rp ").append(item.getTotalHarga()).append("\n\n");
            totalPembelian += item.getTotalHarga();
        }

        struk.append("Total Pembelian: Rp ").append(totalPembelian).append("\n");
        txKeranjang.setText(struk.toString());
    }

    private static class MenuItem {
        private String idMenu;
        private String namaMenu;
        private String harga;
        private int quantity;
        private int totalHarga;

        public MenuItem(String idMenu, String namaMenu, String harga, int quantity, int totalHarga) {
            this.idMenu = idMenu;
            this.namaMenu = namaMenu;
            this.harga = harga;
            this.quantity = quantity;
            this.totalHarga = totalHarga;
        }

        public String getIdMenu() {
            return idMenu;
        }

        public String getNamaMenu() {
            return namaMenu;
        }

        public String getHarga() {
            return harga;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getTotalHarga() {
            return totalHarga;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        navbar = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txTime = new javax.swing.JLabel();
        txDate = new javax.swing.JLabel();
        menuPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        CartMenu = new javax.swing.JPanel();
        struckBelanja = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txKeranjang = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        button1 = new Custom.Button();
        button2 = new Custom.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        navbar.setBackground(new java.awt.Color(254, 216, 179));
        navbar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("P E M E S A N A N");

        txTime.setFont(new java.awt.Font("Segoe UI Black", 1, 20)); // NOI18N
        txTime.setForeground(new java.awt.Color(0, 0, 0));
        txTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        txDate.setFont(new java.awt.Font("Segoe UI Black", 1, 20)); // NOI18N
        txDate.setForeground(new java.awt.Color(0, 0, 0));
        txDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout navbarLayout = new javax.swing.GroupLayout(navbar);
        navbar.setLayout(navbarLayout);
        navbarLayout.setHorizontalGroup(
            navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navbarLayout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
                .addGap(32, 32, 32)
                .addGroup(navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txDate, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(txTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
        navbarLayout.setVerticalGroup(
            navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navbarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(navbarLayout.createSequentialGroup()
                        .addComponent(txDate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txTime, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        menuPanel.setBackground(new java.awt.Color(254, 216, 179));
        menuPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        CartMenu.setBackground(new java.awt.Color(254, 216, 179));
        CartMenu.setPreferredSize(new java.awt.Dimension(1000, 660));
        CartMenu.setLayout(new java.awt.GridLayout(1, 0));
        jScrollPane3.setViewportView(CartMenu);

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1157, Short.MAX_VALUE)
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
        );

        struckBelanja.setBackground(new java.awt.Color(254, 216, 179));
        struckBelanja.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jScrollPane2.setBackground(new java.awt.Color(254, 216, 179));

        jPanel1.setBackground(new java.awt.Color(254, 216, 179));
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 150));

        txKeranjang.setBackground(new java.awt.Color(255, 255, 255));
        txKeranjang.setColumns(20);
        txKeranjang.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        txKeranjang.setLineWrap(true);
        txKeranjang.setRows(5);
        jScrollPane1.setViewportView(txKeranjang);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_shopping_cart_30px.png"))); // NOI18N
        jLabel1.setText("  Keranjang Belanja");

        button1.setBackground(new java.awt.Color(204, 204, 204));
        button1.setForeground(new java.awt.Color(0, 0, 0));
        button1.setText("Receipt");
        button1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N

        button2.setBackground(new java.awt.Color(204, 204, 204));
        button2.setForeground(new java.awt.Color(0, 0, 0));
        button2.setText("Reset");
        button2.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout struckBelanjaLayout = new javax.swing.GroupLayout(struckBelanja);
        struckBelanja.setLayout(struckBelanjaLayout);
        struckBelanjaLayout.setHorizontalGroup(
            struckBelanjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, struckBelanjaLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
        );
        struckBelanjaLayout.setVerticalGroup(
            struckBelanjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(struckBelanja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(navbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(struckBelanja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        txKeranjang.setText("");
    }//GEN-LAST:event_button2ActionPerformed

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
            java.util.logging.Logger.getLogger(Pemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Pemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Pemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Pemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pemesanan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CartMenu;
    private Custom.Button button1;
    private Custom.Button button2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JPanel navbar;
    private javax.swing.JPanel struckBelanja;
    private javax.swing.JLabel txDate;
    private javax.swing.JTextArea txKeranjang;
    private javax.swing.JLabel txTime;
    // End of variables declaration//GEN-END:variables
}
