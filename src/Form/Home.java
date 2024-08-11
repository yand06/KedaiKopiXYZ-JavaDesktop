package Form;

import Custom.CenteredMenuUI;
import Custom.CustomMenuBarUI;
import Main.Login;
import example.PemesananKopi;
import java.sql.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import koneksi.koneksi;

public class Home extends javax.swing.JFrame {
    
    private final Connection conn ;
    private int X,Y;
    String url = "jdbc:mysql://localhost:3306/kedaikopi";
    String user = "root";
    String password = "";
    
    public Home() {
        conn = new koneksi().connect();
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        menuBar.setUI(new CustomMenuBarUI());
        Home.setUI(new CenteredMenuUI());
        Master.setUI(new CenteredMenuUI());
        Transaksi.setUI(new CenteredMenuUI());
        Report.setUI(new CenteredMenuUI());
        Logout.setUI(new CenteredMenuUI());
        About.setUI(new CenteredMenuUI());
        
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(98,48,0));
        lsBarang.setBorder(bottomBorder);
        lsMenu.setBorder(bottomBorder);
        lsCustomer.setBorder(bottomBorder);
        lsTransaksi.setBorder(bottomBorder);
        hoverLsKategori();
        hoverLsMenu();
        hoverLsCustomer();
        hoverLsTransaksi();
        getJumlahCustomer();
        getJumlahBarang();
        getJumlahMenu();
        getJumlahPegawai();
        setTime();
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
                    java.util.Date date = new java.util.Date();
                    SimpleDateFormat tf = new SimpleDateFormat("h:mm:ss aa");
                    SimpleDateFormat df = new SimpleDateFormat("EEEE, dd-MM-yyyy h:mm:ss aa");
//                    String time = tf.format(date);
//                    txTime.setText(time.split(" ")[0]+" "+time.split(" ")[1]);
                    txDate.setText(df.format(date));
                }
            }
        }).start();
    }
    
    public JPanel getMenu() {
        return menu;
    }

    public Custom.BackgroundHome getBgHome() {
        return bgHome;
    }
    
    public void getJumlahCustomer() {
        String query = "SELECT COUNT(*) FROM pelanggan";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Ambil hasil query
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                // Set hasil ke JLabel
                jCustomer.setText("" + count);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            jCustomer.setText("Gagal mengambil data");
        }
    }
    
    public void getJumlahBarang(){
        String query = "SELECT COUNT(*) FROM databarang";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Ambil hasil query
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                // Set hasil ke JLabel
                jBarang.setText("" + count);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            jCustomer.setText("Gagal mengambil data");
        }
    }
    
    public void getJumlahMenu(){
        String query = "SELECT COUNT(*) FROM menu";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Ambil hasil query
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                // Set hasil ke JLabel
                jMenu.setText("" + count);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            jCustomer.setText("Gagal mengambil data");
        }
    }
    
    public void getJumlahPegawai(){
        String query = "SELECT COUNT(*) FROM pegawai";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Ambil hasil query
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                // Set hasil ke JLabel
                jPegawai.setText("" + count);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            jCustomer.setText("Gagal mengambil data");
        }
    }
    
    private void hoverLsKategori(){
        lsBarang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                lsBarang.setForeground(new java.awt.Color(173,84,0));
            }
            @Override
            public void mouseExited(MouseEvent e){
                lsBarang.setForeground(new java.awt.Color(71,34,0));
            }
        });
    }
    
    private void hoverLsMenu(){
        lsMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                lsMenu.setForeground(new java.awt.Color(173,84,0));
            }
            @Override
            public void mouseExited(MouseEvent e){
                lsMenu.setForeground(new java.awt.Color(71,34,0));
            }
        });
    }
    
    private void hoverLsCustomer(){
        lsCustomer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                lsCustomer.setForeground(new java.awt.Color(173,84,0));
            }
            @Override
            public void mouseExited(MouseEvent e){
                lsCustomer.setForeground(new java.awt.Color(71,34,0));
            }
        });
    }
    
    private void hoverLsTransaksi(){
        lsTransaksi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                lsTransaksi.setForeground(new java.awt.Color(173,84,0));
            }
            @Override
            public void mouseExited(MouseEvent e){
                lsTransaksi.setForeground(new java.awt.Color(71,34,0));
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JPanel();
        bgHome = new Custom.BackgroundHome();
        panel1 = new Custom.Panel();
        jLabel1 = new javax.swing.JLabel();
        jBarang = new javax.swing.JLabel();
        lsBarang = new javax.swing.JLabel();
        panel4 = new Custom.Panel();
        jLabel5 = new javax.swing.JLabel();
        panel2 = new Custom.Panel();
        jLabel8 = new javax.swing.JLabel();
        jPegawai = new javax.swing.JLabel();
        lsTransaksi = new javax.swing.JLabel();
        panel3 = new Custom.Panel();
        jLabel11 = new javax.swing.JLabel();
        jCustomer = new javax.swing.JLabel();
        lsCustomer = new javax.swing.JLabel();
        panel5 = new Custom.Panel();
        jLabel14 = new javax.swing.JLabel();
        jMenu = new javax.swing.JLabel();
        lsMenu = new javax.swing.JLabel();
        txDate = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        Home = new javax.swing.JMenu();
        Master = new javax.swing.JMenu();
        itemBarang = new javax.swing.JMenuItem();
        itemPelanggan = new javax.swing.JMenuItem();
        itemPegawai = new javax.swing.JMenuItem();
        itemMenuKopi = new javax.swing.JMenuItem();
        Transaksi = new javax.swing.JMenu();
        itemPemesanan = new javax.swing.JMenuItem();
        itemPembayaran = new javax.swing.JMenuItem();
        Report = new javax.swing.JMenu();
        reportBarang = new javax.swing.JMenuItem();
        reportPelanggan = new javax.swing.JMenuItem();
        reportMenu = new javax.swing.JMenuItem();
        reportPembayaran = new javax.swing.JMenuItem();
        Logout = new javax.swing.JMenu();
        About = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1600, 897));

        menu.setLayout(new java.awt.CardLayout());

        panel1.setBackground(new java.awt.Color(254, 216, 179, 200));
        panel1.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        panel1.setRoundBottomLeft(35);
        panel1.setRoundBottomRight(35);
        panel1.setRoundTopLeft(35);
        panel1.setRoundTopRight(35);

        jLabel1.setBackground(new java.awt.Color(98, 48, 0));
        jLabel1.setFont(new java.awt.Font("Bauhaus 93", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_holding_box_30px.png"))); // NOI18N
        jLabel1.setText("  Barang");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(71, 34, 0), 3));
        jLabel1.setOpaque(true);

        jBarang.setFont(new java.awt.Font("SansSerif", 1, 70)); // NOI18N
        jBarang.setForeground(new java.awt.Color(71, 34, 0));
        jBarang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jBarang.setText("0");

        lsBarang.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        lsBarang.setForeground(new java.awt.Color(71, 34, 0));
        lsBarang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lsBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_right_30px.png"))); // NOI18N
        lsBarang.setText("Lihat Selengkapnya");
        lsBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lsBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lsBarangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lsBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lsBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        panel4.setBackground(new java.awt.Color(254, 216, 179, 200));
        panel4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        panel4.setRoundBottomLeft(35);
        panel4.setRoundBottomRight(35);
        panel4.setRoundTopLeft(35);
        panel4.setRoundTopRight(35);

        jLabel5.setBackground(new java.awt.Color(98, 48, 0));
        jLabel5.setFont(new java.awt.Font("Bauhaus 93", 1, 28)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Laporan Penjualan");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(71, 34, 0), 3));
        jLabel5.setOpaque(true);

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(250, Short.MAX_VALUE))
        );

        panel2.setBackground(new java.awt.Color(254, 216, 179, 200));
        panel2.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        panel2.setRoundBottomLeft(35);
        panel2.setRoundBottomRight(35);
        panel2.setRoundTopLeft(35);
        panel2.setRoundTopRight(35);

        jLabel8.setBackground(new java.awt.Color(98, 48, 0));
        jLabel8.setFont(new java.awt.Font("Bauhaus 93", 1, 28)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Layoff_30px.png"))); // NOI18N
        jLabel8.setText("  Pegawai");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(71, 34, 0), 3));
        jLabel8.setOpaque(true);

        jPegawai.setFont(new java.awt.Font("SansSerif", 1, 70)); // NOI18N
        jPegawai.setForeground(new java.awt.Color(71, 34, 0));
        jPegawai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPegawai.setText("0");

        lsTransaksi.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        lsTransaksi.setForeground(new java.awt.Color(71, 34, 0));
        lsTransaksi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lsTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_right_30px.png"))); // NOI18N
        lsTransaksi.setText("Lihat Selengkapnya");
        lsTransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lsTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lsTransaksiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lsTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lsTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        panel3.setBackground(new java.awt.Color(254, 216, 179, 200));
        panel3.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        panel3.setRoundBottomLeft(35);
        panel3.setRoundBottomRight(35);
        panel3.setRoundTopLeft(35);
        panel3.setRoundTopRight(35);

        jLabel11.setBackground(new java.awt.Color(98, 48, 0));
        jLabel11.setFont(new java.awt.Font("Bauhaus 93", 1, 28)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_customer_30px.png"))); // NOI18N
        jLabel11.setText("  Customer");
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(71, 34, 0), 3));
        jLabel11.setOpaque(true);

        jCustomer.setFont(new java.awt.Font("SansSerif", 1, 70)); // NOI18N
        jCustomer.setForeground(new java.awt.Color(71, 34, 0));
        jCustomer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCustomer.setText("0");

        lsCustomer.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        lsCustomer.setForeground(new java.awt.Color(71, 34, 0));
        lsCustomer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lsCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_right_30px.png"))); // NOI18N
        lsCustomer.setText("Lihat Selengkapnya");
        lsCustomer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lsCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lsCustomerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lsCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lsCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        panel5.setBackground(new java.awt.Color(254, 216, 179, 200));
        panel5.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        panel5.setRoundBottomLeft(35);
        panel5.setRoundBottomRight(35);
        panel5.setRoundTopLeft(35);
        panel5.setRoundTopRight(35);

        jLabel14.setBackground(new java.awt.Color(98, 48, 0));
        jLabel14.setFont(new java.awt.Font("Bauhaus 93", 1, 28)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Xbox_Menu_30px.png"))); // NOI18N
        jLabel14.setText("  Menu");
        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(71, 34, 0), 3));
        jLabel14.setOpaque(true);

        jMenu.setFont(new java.awt.Font("SansSerif", 1, 70)); // NOI18N
        jMenu.setForeground(new java.awt.Color(71, 34, 0));
        jMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu.setText("0");

        lsMenu.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        lsMenu.setForeground(new java.awt.Color(71, 34, 0));
        lsMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lsMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_right_30px.png"))); // NOI18N
        lsMenu.setText("Lihat Selengkapnya");
        lsMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lsMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lsMenuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lsMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lsMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        txDate.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 20)); // NOI18N
        txDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout bgHomeLayout = new javax.swing.GroupLayout(bgHome);
        bgHome.setLayout(bgHomeLayout);
        bgHomeLayout.setHorizontalGroup(
            bgHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgHomeLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addGroup(bgHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(bgHomeLayout.createSequentialGroup()
                        .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(50, 50, 50)
                        .addComponent(panel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(50, 50, 50)
                        .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(50, 50, 50)
                        .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(150, 150, 150))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgHomeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txDate, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bgHomeLayout.setVerticalGroup(
            bgHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgHomeLayout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addGroup(bgHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50)
                .addComponent(panel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26)
                .addComponent(txDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menu.add(bgHome, "card3");

        menuBar.setBackground(new java.awt.Color(98, 48, 0));
        menuBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuBar.setPreferredSize(new java.awt.Dimension(70, 95));
        menuBar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                menuBarMouseDragged(evt);
            }
        });
        menuBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuBarMousePressed(evt);
            }
        });

        Home.setBackground(new java.awt.Color(98, 48, 0));
        Home.setForeground(new java.awt.Color(255, 255, 255));
        Home.setText("Home");
        Home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Home.setFont(new java.awt.Font("Bauhaus 93", 1, 35)); // NOI18N
        Home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Home.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Home.setPreferredSize(new java.awt.Dimension(180, 32));
        Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                HomeMouseExited(evt);
            }
        });
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });
        menuBar.add(Home);

        Master.setBackground(new java.awt.Color(98, 48, 0));
        Master.setForeground(new java.awt.Color(255, 255, 255));
        Master.setText("Master");
        Master.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Master.setFont(new java.awt.Font("Bauhaus 93", 1, 35)); // NOI18N
        Master.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Master.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Master.setMinimumSize(new java.awt.Dimension(225, 32));
        Master.setPreferredSize(new java.awt.Dimension(200, 32));

        itemBarang.setBackground(new java.awt.Color(226, 135, 67));
        itemBarang.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        itemBarang.setForeground(new java.awt.Color(0, 0, 0));
        itemBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_new_product_30px.png"))); // NOI18N
        itemBarang.setText("Barang");
        itemBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemBarang.setPreferredSize(new java.awt.Dimension(200, 35));
        itemBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                itemBarangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                itemBarangMouseExited(evt);
            }
        });
        itemBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBarangActionPerformed(evt);
            }
        });
        Master.add(itemBarang);

        itemPelanggan.setBackground(new java.awt.Color(226, 135, 67));
        itemPelanggan.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        itemPelanggan.setForeground(new java.awt.Color(0, 0, 0));
        itemPelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_customer_30px.png"))); // NOI18N
        itemPelanggan.setText("Pelanggan");
        itemPelanggan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPelangganActionPerformed(evt);
            }
        });
        Master.add(itemPelanggan);

        itemPegawai.setBackground(new java.awt.Color(226, 135, 67));
        itemPegawai.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        itemPegawai.setForeground(new java.awt.Color(0, 0, 0));
        itemPegawai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Layoff_30px.png"))); // NOI18N
        itemPegawai.setText("Pegawai");
        itemPegawai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPegawaiActionPerformed(evt);
            }
        });
        Master.add(itemPegawai);

        itemMenuKopi.setBackground(new java.awt.Color(226, 135, 67));
        itemMenuKopi.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        itemMenuKopi.setForeground(new java.awt.Color(0, 0, 0));
        itemMenuKopi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Coffee_30px.png"))); // NOI18N
        itemMenuKopi.setText("Menu");
        itemMenuKopi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemMenuKopi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuKopiActionPerformed(evt);
            }
        });
        Master.add(itemMenuKopi);

        menuBar.add(Master);

        Transaksi.setBackground(new java.awt.Color(98, 48, 0));
        Transaksi.setForeground(new java.awt.Color(255, 255, 255));
        Transaksi.setText("Transactions");
        Transaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Transaksi.setFont(new java.awt.Font("Bauhaus 93", 1, 35)); // NOI18N
        Transaksi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Transaksi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Transaksi.setMinimumSize(new java.awt.Dimension(225, 32));
        Transaksi.setPreferredSize(new java.awt.Dimension(250, 32));

        itemPemesanan.setBackground(new java.awt.Color(226, 135, 67));
        itemPemesanan.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        itemPemesanan.setForeground(new java.awt.Color(0, 0, 0));
        itemPemesanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Coffee_30px.png"))); // NOI18N
        itemPemesanan.setText("Pemesanan");
        itemPemesanan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemPemesanan.setPreferredSize(new java.awt.Dimension(200, 36));
        itemPemesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPemesananActionPerformed(evt);
            }
        });
        Transaksi.add(itemPemesanan);

        itemPembayaran.setBackground(new java.awt.Color(226, 135, 67));
        itemPembayaran.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        itemPembayaran.setForeground(new java.awt.Color(0, 0, 0));
        itemPembayaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Online_Payment_30px.png"))); // NOI18N
        itemPembayaran.setText("Pembayaran");
        itemPembayaran.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemPembayaran.setPreferredSize(new java.awt.Dimension(220, 36));
        itemPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPembayaranActionPerformed(evt);
            }
        });
        Transaksi.add(itemPembayaran);

        menuBar.add(Transaksi);

        Report.setBackground(new java.awt.Color(98, 48, 0));
        Report.setForeground(new java.awt.Color(255, 255, 255));
        Report.setText("Report");
        Report.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Report.setFont(new java.awt.Font("Bauhaus 93", 1, 35)); // NOI18N
        Report.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Report.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Report.setMaximumSize(new java.awt.Dimension(250, 32767));
        Report.setPreferredSize(new java.awt.Dimension(300, 32));

        reportBarang.setBackground(new java.awt.Color(226, 135, 67));
        reportBarang.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        reportBarang.setForeground(new java.awt.Color(0, 0, 0));
        reportBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Report_Card_30px.png"))); // NOI18N
        reportBarang.setText("Laporan Barang");
        reportBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reportBarang.setPreferredSize(new java.awt.Dimension(150, 36));
        reportBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportBarangActionPerformed(evt);
            }
        });
        Report.add(reportBarang);

        reportPelanggan.setBackground(new java.awt.Color(226, 135, 67));
        reportPelanggan.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        reportPelanggan.setForeground(new java.awt.Color(0, 0, 0));
        reportPelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Report_Card_30px.png"))); // NOI18N
        reportPelanggan.setText("Laporan Pelanggan");
        reportPelanggan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reportPelanggan.setPreferredSize(new java.awt.Dimension(150, 36));
        reportPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportPelangganActionPerformed(evt);
            }
        });
        Report.add(reportPelanggan);

        reportMenu.setBackground(new java.awt.Color(226, 135, 67));
        reportMenu.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        reportMenu.setForeground(new java.awt.Color(0, 0, 0));
        reportMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Report_Card_30px.png"))); // NOI18N
        reportMenu.setText("Laporan Menu");
        reportMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reportMenu.setPreferredSize(new java.awt.Dimension(250, 36));
        reportMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportMenuActionPerformed(evt);
            }
        });
        Report.add(reportMenu);

        reportPembayaran.setBackground(new java.awt.Color(226, 135, 67));
        reportPembayaran.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        reportPembayaran.setForeground(new java.awt.Color(0, 0, 0));
        reportPembayaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Report_Card_30px.png"))); // NOI18N
        reportPembayaran.setText("Laporan Pembayaran");
        reportPembayaran.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reportPembayaran.setPreferredSize(new java.awt.Dimension(250, 36));
        reportPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportPembayaranActionPerformed(evt);
            }
        });
        Report.add(reportPembayaran);

        menuBar.add(Report);

        Logout.setBackground(new java.awt.Color(98, 48, 0));
        Logout.setForeground(new java.awt.Color(255, 255, 255));
        Logout.setText("Logout");
        Logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Logout.setFont(new java.awt.Font("Bauhaus 93", 1, 35)); // NOI18N
        Logout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Logout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Logout.setMinimumSize(new java.awt.Dimension(150, 32));
        Logout.setPreferredSize(new java.awt.Dimension(200, 32));
        Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoutMouseClicked(evt);
            }
        });
        menuBar.add(Logout);

        About.setBackground(new java.awt.Color(98, 48, 0));
        About.setForeground(new java.awt.Color(255, 255, 255));
        About.setText("About");
        About.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        About.setFont(new java.awt.Font("Bauhaus 93", 1, 35)); // NOI18N
        About.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        About.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        About.setMinimumSize(new java.awt.Dimension(150, 32));
        About.setPreferredSize(new java.awt.Dimension(200, 32));
        About.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AboutMouseClicked(evt);
            }
        });
        menuBar.add(About);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void itemBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBarangActionPerformed
        menu.removeAll();
        menu.add(new Barang());
        menu.revalidate();
        menu.repaint();
    }//GEN-LAST:event_itemBarangActionPerformed

    private void itemPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPelangganActionPerformed
        menu.removeAll();
        menu.add(new Pelanggan());
        menu.revalidate();
        menu.repaint();
    }//GEN-LAST:event_itemPelangganActionPerformed

    private void itemPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPembayaranActionPerformed
        menu.removeAll();
        menu.add(new Pembayaran());
        menu.revalidate();
        menu.repaint();
    }//GEN-LAST:event_itemPembayaranActionPerformed

    private void reportMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportMenuActionPerformed
        menu.removeAll();
        menu.add(new ReportDaftarMenu());
        menu.revalidate();
        menu.repaint();
    }//GEN-LAST:event_reportMenuActionPerformed

    private void HomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_HomeMouseEntered

    private void HomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_HomeMouseExited

    private void LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseClicked
        int ok = JOptionPane.showConfirmDialog(null, "Apakah anda ingin keluar dari aplikasi?","Konfirmasi Dialog",JOptionPane.YES_NO_CANCEL_OPTION);
        if(ok == 0){        
            dispose();
        Login login = new Login();
        login.setVisible(true);
        login.revalidate();
        login.repaint();
        }
    }//GEN-LAST:event_LogoutMouseClicked

    private void HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeMouseClicked
        menu.removeAll();
        menu.add(bgHome);
        menu.revalidate();
        menu.repaint();
        getJumlahBarang();
        getJumlahCustomer();
        getJumlahMenu();
        getJumlahPegawai();
    }//GEN-LAST:event_HomeMouseClicked

    private void itemBarangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemBarangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_itemBarangMouseEntered

    private void itemBarangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemBarangMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_itemBarangMouseExited

    private void menuBarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBarMousePressed
        X = evt.getX();
        Y = evt.getY();
    }//GEN-LAST:event_menuBarMousePressed

    private void menuBarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBarMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - X, y - Y);
    }//GEN-LAST:event_menuBarMouseDragged

    private void lsMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lsMenuMouseClicked
        menu.removeAll();
        menu.add(new Menu());
        menu.revalidate();
        menu.repaint();
    }//GEN-LAST:event_lsMenuMouseClicked

    private void lsCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lsCustomerMouseClicked
        menu.removeAll();
        menu.add(new Pelanggan());
        menu.revalidate();
        menu.repaint();
    }//GEN-LAST:event_lsCustomerMouseClicked

    private void lsTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lsTransaksiMouseClicked
        menu.removeAll();
        menu.add(new Pegawai());
        menu.revalidate();
        menu.repaint();
    }//GEN-LAST:event_lsTransaksiMouseClicked

    private void itemPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPegawaiActionPerformed
        menu.removeAll();
        menu.add(new Pegawai());
        menu.revalidate();
        menu.repaint();
    }//GEN-LAST:event_itemPegawaiActionPerformed

    private void itemPemesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPemesananActionPerformed
        menu.removeAll();
        menu.add(new Pesan());
        menu.repaint();
    }//GEN-LAST:event_itemPemesananActionPerformed

    private void lsBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lsBarangMouseClicked
        menu.removeAll();
//        menu.add(new Barang());
        menu.add(new BarangCoba());
        menu.revalidate();
        menu.repaint();
    }//GEN-LAST:event_lsBarangMouseClicked

    private void itemMenuKopiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuKopiActionPerformed
        menu.removeAll();
        menu.add(new Menu());
        menu.revalidate();
        menu.repaint();
    }//GEN-LAST:event_itemMenuKopiActionPerformed

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HomeActionPerformed

    private void reportPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportPelangganActionPerformed
        menu.removeAll();
        menu.add(new ReportDataPelanggan());
        menu.revalidate();
        menu.repaint();
    }//GEN-LAST:event_reportPelangganActionPerformed

    private void reportBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportBarangActionPerformed
        menu.removeAll();
        menu.add(new ReportDataBarang());
        menu.revalidate();
        menu.repaint();
    }//GEN-LAST:event_reportBarangActionPerformed

    private void reportPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportPembayaranActionPerformed
        menu.removeAll();
        menu.add(new ReportPembayaran());
        menu.revalidate();
        menu.repaint();
    }//GEN-LAST:event_reportPembayaranActionPerformed

    private void AboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AboutMouseClicked
        menu.removeAll();
        menu.add(new About());
        menu.revalidate();
        menu.repaint();
    }//GEN-LAST:event_AboutMouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Change the background color of the menu bar */
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu About;
    private javax.swing.JMenu Home;
    private javax.swing.JMenu Logout;
    private javax.swing.JMenu Master;
    private javax.swing.JMenu Report;
    private javax.swing.JMenu Transaksi;
    private Custom.BackgroundHome bgHome;
    private javax.swing.JMenuItem itemBarang;
    private javax.swing.JMenuItem itemMenuKopi;
    private javax.swing.JMenuItem itemPegawai;
    private javax.swing.JMenuItem itemPelanggan;
    private javax.swing.JMenuItem itemPembayaran;
    private javax.swing.JMenuItem itemPemesanan;
    private javax.swing.JLabel jBarang;
    private javax.swing.JLabel jCustomer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jMenu;
    private javax.swing.JLabel jPegawai;
    private javax.swing.JLabel lsBarang;
    private javax.swing.JLabel lsCustomer;
    private javax.swing.JLabel lsMenu;
    private javax.swing.JLabel lsTransaksi;
    private javax.swing.JPanel menu;
    private javax.swing.JMenuBar menuBar;
    private Custom.Panel panel1;
    private Custom.Panel panel2;
    private Custom.Panel panel3;
    private Custom.Panel panel4;
    private Custom.Panel panel5;
    private javax.swing.JMenuItem reportBarang;
    private javax.swing.JMenuItem reportMenu;
    private javax.swing.JMenuItem reportPelanggan;
    private javax.swing.JMenuItem reportPembayaran;
    private javax.swing.JLabel txDate;
    // End of variables declaration//GEN-END:variables
}
