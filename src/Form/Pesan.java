package Form;

import example.PemesananKopi;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

public class Pesan extends javax.swing.JPanel {

    private final Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    private List<MenuItem> cartItems;
    private GridBagConstraints gbc;
    private Map<String, JLabel> stockLabels = new HashMap<>();
    
    public Pesan() {
        initComponents();
        txPemesanan.setEnabled(false);
        setTime();
        cartItems = new ArrayList<>();
        loadMenuCards();
        dataTable();
        CartMenu.setLayout(new GridLayout(0, 5, 20, 20));
        CartMenu.setBorder(new EmptyBorder(30, 30, 30, 30));
        CartMenu.setPreferredSize(new Dimension(1000,1400));
        tabelDetailPesan.setRowHeight(30);
        tabelDetailPesan.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        tabelDetailPesan.setDefaultRenderer(Object.class, new CustomCellRenderer());
    }
    
    private void dataTable(){
        Object[] Baris = {"ID Menu","Nama Menu","Jumlah","Harga","Total"};

//      Mengubah Huruf pada Baris Tabel  
        for (int i = 0; i < Baris.length; i++){
            Baris[i] = "<html><font face='SansSerif' size='5' style='font-weight"
                    + ": bold;'>" + Baris[i] + "</font></html>";
        }
        tabmode = new DefaultTableModel(null,Baris);
        tabelDetailPesan.setModel(tabmode);
    }
    
    public String id,nama,telepon;
    public String getId(){
        return id;
    }
    public String getNama(){
        return nama;
    }
    public String getTelepon(){
        return telepon;
    }
    
    public void pelangganTerpilih(){
        dataPelanggan dp = new dataPelanggan();
        dp.pesan = this;
        txId.setText(id);
        txNamaPelanggan.setText(nama);
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
                    SimpleDateFormat df = new SimpleDateFormat("EEEE, dd-MM-yyyy, h:mm:ss aa");
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
                int stok = hasil.getInt("stok");
                byte[] imageData = hasil.getBytes("gambarMenu");
                
                JPanel card = new JPanel(new BorderLayout());
                card.setBorder(BorderFactory.createLineBorder(new Color(98,48,0), 3));
                card.setBackground(Color.WHITE);
                card.setPreferredSize(new Dimension(400, 500));
//                card.setMinimumSize(new Dimension(300, 400));
//                card.setMaximumSize(new Dimension(900, 1000));
                
                ImageIcon icon = new ImageIcon(imageData);
                JLabel labelGambar = new JLabel(icon);
                labelGambar.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));
                card.add(labelGambar, BorderLayout.NORTH);
                
                JPanel detailPanel = new JPanel(new BorderLayout());
                detailPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                detailPanel.setBackground(Color.WHITE);
                
                JPanel menuInfoPanel = new JPanel();
                menuInfoPanel.setLayout(new BoxLayout(menuInfoPanel, BoxLayout.Y_AXIS));
                menuInfoPanel.setBackground(new Color(255, 255, 255));
                JLabel labelNama = new JLabel(namaMenu);
                labelNama.setFont(new Font("Segoe UI Black", Font.BOLD, 22));
                labelNama.setAlignmentX(CENTER_ALIGNMENT);
                JLabel labelHarga = new JLabel("Rp. " + harga);
                labelHarga.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                labelHarga.setAlignmentX(CENTER_ALIGNMENT);

                JLabel labelStok = new JLabel("Stok: " + stok);
                labelStok.setFont(new Font("Segoe UI", Font.ITALIC, 18));
                labelStok.setForeground(Color.GRAY);
                labelStok.setAlignmentX(CENTER_ALIGNMENT);
                stockLabels.put(idMenu, labelStok);
                menuInfoPanel.add(Box.createVerticalGlue());
                menuInfoPanel.add(labelNama);
                menuInfoPanel.add(labelHarga);
                menuInfoPanel.add(Box.createVerticalStrut(25));
                menuInfoPanel.add(Box.createVerticalGlue());
                menuInfoPanel.add(labelStok);
                JSpinner spinnerQuantity = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
                spinnerQuantity.setPreferredSize(new Dimension(100, 25));
                spinnerQuantity.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
                allSpinners.add(spinnerQuantity);

                JButton buttonPurchase = new JButton("Beli");
                buttonPurchase.setBackground(new Color(52, 152, 219));
                buttonPurchase.setForeground(Color.WHITE);
                buttonPurchase.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
                buttonPurchase.addActionListener(new PurchaseActionListener(idMenu, namaMenu, harga, spinnerQuantity, stok,labelStok));

                JPanel bottomPanel = new JPanel(new BorderLayout(10, 0)); // 5px horizontal gap
                bottomPanel.setBackground(Color.WHITE);
                bottomPanel.add(spinnerQuantity, BorderLayout.WEST);
                bottomPanel.add(buttonPurchase, BorderLayout.CENTER);

                detailPanel.add(menuInfoPanel, BorderLayout.NORTH);
                detailPanel.add(bottomPanel, BorderLayout.SOUTH);
                card.add(detailPanel, BorderLayout.CENTER);
                CartMenu.add(card, gbc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public class PurchaseActionListener implements ActionListener {
        private String idMenu;
        private String namaMenu;
        private String harga;
        private int stok;
        private JLabel labelStok;
        private JSpinner spinnerQuantity;

        public PurchaseActionListener(String idMenu, String namaMenu, String harga, JSpinner spinnerQuantity, int stok, JLabel labelStok) {
            this.idMenu = idMenu;
            this.namaMenu = namaMenu;
            this.harga = harga;
            this.stok = stok;
            this.labelStok = labelStok;
            this.spinnerQuantity = spinnerQuantity;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (spinnerQuantity == null || labelStok == null) {
                System.out.println("Error: spinnerQuantity or labelStok is null");
                return;
            }

            int quantity = (int) spinnerQuantity.getValue();
            double totalHargaDouble = Double.parseDouble(harga) * quantity;
            int totalHarga = (int) Math.round(totalHargaDouble);

            MenuItem item = new MenuItem(idMenu, namaMenu, harga, quantity, totalHarga, stok);
            cartItems.add(item);

            stok -= quantity;
            labelStok.setText("Stok: " + stok);

            updateTabelDetailPesan();
            updateStokInDatabase(idMenu, stok);
        }
    }

    private void updateTabelDetailPesan() {
        DefaultTableModel model = (DefaultTableModel) tabelDetailPesan.getModel();
        model.setRowCount(0);
        for (MenuItem item : cartItems) {
            model.addRow(new Object[]{ item.getIdMenu(), item.getNamaMenu(), item.getQuantity(), item.getHarga(), item.getTotalHarga() });
        }
    }

    private void updateStokInDatabase(String idMenu, int newStok) {
        String sql = "UPDATE menu SET stok = ? WHERE idMenu = ?";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1, newStok);
            stat.setString(2, idMenu);
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
    
    private int getStokFromDatabase(String idMenu) {
        String sql = "SELECT stok FROM menu WHERE idMenu = ?";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, idMenu);
            ResultSet rs = stat.executeQuery();
            if (rs.next()) {
                return rs.getInt("stok");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    private static class MenuItem {
        private String idMenu;
        private String namaMenu;
        private String harga;
        private int stok;
        private int quantity;
        private double totalHarga;

        public MenuItem(String idMenu, String namaMenu, String harga, int quantity, int totalHarga, int stok) {
            this.idMenu = idMenu;
            this.namaMenu = namaMenu;
            this.harga = harga;
            this.stok = stok;
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
        
        public int getStok(){
            return stok;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getTotalHarga() {
            return totalHarga;
        }
        
        public void setStok(int stok) {
            this.stok = stok;
        }
    }
    
    private List<JSpinner> allSpinners = new ArrayList<>();
    private void resetAllSpinners() {
        for (JSpinner spinner : allSpinners) {
            spinner.setValue(1);
        }
        cartItems.clear(); // Bersihkan keranjang belanja
    }
    
    class CustomHeaderRenderer extends DefaultTableCellRenderer {
        public CustomHeaderRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBackground(new Color(98,48,0)); // Warna latar belakang header
            setForeground(Color.WHITE); // Warna teks header
            return this;
        }
    }
    
    class CustomCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setHorizontalAlignment(SwingConstants.CENTER);
            if (row % 2 == 0) {
                setBackground(new Color(255, 236, 217)); // Warna latar belakang baris genap
            } else {
                setBackground(Color.WHITE); // Warna latar belakang baris ganjil
            }
            
            if (isSelected) {
                setBackground(new Color(173, 216, 230)); // Warna saat sel dipilih
            }
            
            return this;
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
        jLabel1 = new javax.swing.JLabel();
        btnSave = new Custom.Button();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelDetailPesan = new javax.swing.JTable();
        btnDelete = new Custom.Button();
        jLabel2 = new javax.swing.JLabel();
        txPemesanan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txNamaPelanggan = new javax.swing.JTextField();
        txId = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnCari = new Custom.Button();
        btnReset = new Custom.Button();
        jLabel6 = new javax.swing.JLabel();
        cbbStatus = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(254, 216, 179));

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
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)
                .addGap(32, 32, 32)
                .addGroup(navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txDate, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(txTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        navbarLayout.setVerticalGroup(
            navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navbarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(navbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(navbarLayout.createSequentialGroup()
                        .addComponent(txDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        menuPanel.setBackground(new java.awt.Color(254, 216, 179));
        menuPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        menuPanel.setPreferredSize(new java.awt.Dimension(1100, 1000));

        CartMenu.setBackground(new java.awt.Color(254, 216, 179));
        CartMenu.setPreferredSize(new java.awt.Dimension(1000, 990));
        CartMenu.setLayout(new java.awt.GridLayout(1, 0));
        jScrollPane3.setViewportView(CartMenu);

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
        );

        struckBelanja.setBackground(new java.awt.Color(254, 216, 179));
        struckBelanja.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jScrollPane2.setBackground(new java.awt.Color(254, 216, 179));

        jPanel1.setBackground(new java.awt.Color(254, 216, 179));
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 150));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_shopping_cart_30px.png"))); // NOI18N
        jLabel1.setText("  Keranjang Belanja");

        btnSave.setBackground(new java.awt.Color(52, 152, 219));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Save");
        btnSave.setFont(new java.awt.Font("Segoe UI Black", 1, 20)); // NOI18N
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSaveMouseExited(evt);
            }
        });
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        tabelDetailPesan.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tabelDetailPesan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelDetailPesan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelDetailPesanMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabelDetailPesan);

        btnDelete.setBackground(new java.awt.Color(255, 255, 255));
        btnDelete.setForeground(new java.awt.Color(52, 152, 219));
        btnDelete.setText("Delete");
        btnDelete.setFont(new java.awt.Font("Segoe UI Black", 1, 20)); // NOI18N
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteMouseExited(evt);
            }
        });
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout struckBelanjaLayout = new javax.swing.GroupLayout(struckBelanja);
        struckBelanja.setLayout(struckBelanjaLayout);
        struckBelanjaLayout.setHorizontalGroup(
            struckBelanjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, struckBelanjaLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
        );
        struckBelanjaLayout.setVerticalGroup(
            struckBelanjaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ID Pemesanan");

        txPemesanan.setEditable(false);
        txPemesanan.setBackground(new java.awt.Color(255, 255, 255));
        txPemesanan.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txPemesanan.setForeground(new java.awt.Color(0, 0, 0));
        txPemesanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));
        txPemesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txPemesananActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nama Pelanggan");

        txNamaPelanggan.setBackground(new java.awt.Color(255, 255, 255));
        txNamaPelanggan.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txNamaPelanggan.setForeground(new java.awt.Color(0, 0, 0));
        txNamaPelanggan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));
        txNamaPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNamaPelangganActionPerformed(evt);
            }
        });

        txId.setBackground(new java.awt.Color(255, 255, 255));
        txId.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txId.setForeground(new java.awt.Color(0, 0, 0));
        txId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));
        txId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txIdActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("ID Pelanggan");

        btnCari.setBackground(new java.awt.Color(98, 48, 0));
        btnCari.setForeground(new java.awt.Color(255, 255, 255));
        btnCari.setText("...");
        btnCari.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        btnCari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCariMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCariMouseExited(evt);
            }
        });
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(98, 48, 0));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Reset");
        btnReset.setFont(new java.awt.Font("Segoe UI Black", 1, 20)); // NOI18N
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnResetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnResetMouseExited(evt);
            }
        });
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Status");

        cbbStatus.setBackground(new java.awt.Color(255, 255, 255));
        cbbStatus.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        cbbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cbbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Belum Bayar" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1089, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txPemesanan)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txId)
                        .addGap(18, 18, 18)
                        .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txNamaPelanggan)
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addComponent(struckBelanja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(navbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txPemesanan, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addComponent(struckBelanja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txPemesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txPemesananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txPemesananActionPerformed

    private void txNamaPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txNamaPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txNamaPelangganActionPerformed

    private void txIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txIdActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String namaPelanggan = txNamaPelanggan.getText();
        String waktuPesan = txDate.getText();

        try {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO pemesanan (namaMenu, namaPelanggan, jumlah, harga, totalHarga, waktuPesan, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stat = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            for (MenuItem item : cartItems) {
                stat.setString(1, item.getNamaMenu());
                stat.setString(2, namaPelanggan);
                stat.setInt(3, item.getQuantity());
                stat.setDouble(4, Double.parseDouble(item.getHarga()));
                stat.setDouble(5, item.getTotalHarga());
                stat.setString(6, waktuPesan);
                stat.setString(7, cbbStatus.getSelectedItem().toString());
                stat.addBatch();
            }

            stat.executeBatch();
            conn.commit();
            JOptionPane.showMessageDialog(null, "Data Pemesanan Berhasil Di Simpan");
            ResultSet rs = stat.getGeneratedKeys();
            if (rs.next()) {
                String idPemesanan = rs.getString(1);
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan\nID Pegawai : " + idPemesanan);
            }

            // Reset form dan keranjang
            txPemesanan.setText("");
            txId.setText("");
            txNamaPelanggan.setText("");
            cbbStatus.setSelectedIndex(0);
            resetAllSpinners();
            updateTabelDetailPesan();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal menyimpan data: " + e.getMessage());
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        resetAllSpinners();
        txId.setText("");
        txNamaPelanggan.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        try
        {
            dataPelanggan cd = new dataPelanggan();
            cd.pesan = this;
            cd.setVisible(true);
            cd.setResizable(false);
        } catch (Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnCariMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariMouseEntered
        btnCari.setBackground(new Color(210,100,0));
    }//GEN-LAST:event_btnCariMouseEntered

    private void btnCariMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariMouseExited
        btnCari.setBackground(new Color(98,48,0));
    }//GEN-LAST:event_btnCariMouseExited

    private void btnSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseEntered
        btnSave.setBackground(new Color(42, 122, 175));
    }//GEN-LAST:event_btnSaveMouseEntered

    private void btnSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseExited
        btnSave.setBackground(new Color(52,152,219));
    }//GEN-LAST:event_btnSaveMouseExited

    private void btnResetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseEntered
        btnReset.setBackground(new Color(210,100,0));
    }//GEN-LAST:event_btnResetMouseEntered

    private void btnResetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseExited
        btnReset.setBackground(new Color(98,48,0));
    }//GEN-LAST:event_btnResetMouseExited

    private void btnDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseEntered
        btnDelete.setBackground(new Color(200,200,200));
    }//GEN-LAST:event_btnDeleteMouseEntered

    private void btnDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseExited
        btnDelete.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btnDeleteMouseExited

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRow = tabelDetailPesan.getSelectedRow();
        if (selectedRow >= 0) {
            MenuItem item = cartItems.get(selectedRow);
            cartItems.remove(selectedRow);
            updateTabelDetailPesan();

            // Kembalikan stok
            String idMenu = item.getIdMenu();
            int returnedQuantity = item.getQuantity();
            int newStok = getStokFromDatabase(idMenu) + returnedQuantity;
            updateStokInDatabase(idMenu, newStok);

            // Update tampilan stok pada kartu menu
            JLabel stockLabel = stockLabels.get(idMenu);
            if (stockLabel != null) {
                stockLabel.setText("Stok: " + newStok);
            }

            // Update stok pada MenuItem jika diperlukan
            for (MenuItem menuItem : cartItems) {
                if (menuItem.getIdMenu().equals(idMenu)) {
                    menuItem.setStok(newStok);
                }
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tabelDetailPesanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelDetailPesanMouseClicked
        
    }//GEN-LAST:event_tabelDetailPesanMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CartMenu;
    private Custom.Button btnCari;
    private Custom.Button btnDelete;
    private Custom.Button btnReset;
    private Custom.Button btnSave;
    private javax.swing.JComboBox<String> cbbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JPanel navbar;
    private javax.swing.JPanel struckBelanja;
    private javax.swing.JTable tabelDetailPesan;
    private javax.swing.JLabel txDate;
    private javax.swing.JTextField txId;
    private javax.swing.JTextField txNamaPelanggan;
    private javax.swing.JTextField txPemesanan;
    private javax.swing.JLabel txTime;
    // End of variables declaration//GEN-END:variables
}
