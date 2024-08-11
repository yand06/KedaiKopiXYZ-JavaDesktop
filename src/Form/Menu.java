package Form;
import java.io.FileInputStream;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
public class Menu extends javax.swing.JPanel {
    private String currentImagePath;
    private final  Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;  
//    String path2 = null;
    public Menu() {
        initComponents();
        txkode.setEnabled(false);
        dataTableMenu();
        tabelMenu.setRowHeight(150);
        setTableRenderer();
//        setTableHeaderRenderer();
//        setTableCellRenderer();
        tabelMenu.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        tabelMenu.setDefaultRenderer(Object.class, new CustomCellRenderer());
    }
    
    private void clear(){
        txkode.setEnabled(false);
        txkode.setText("");
        txnama.setText("");
        txHarga.setText("");
        txStok.setText("");
        cbbKategori.setSelectedIndex(0);
        gambarMenu.setIcon(null);
        txCari.setText("");
        txkode.requestFocus();  
    }
    
    private void dataTableMenu() {
        Object[] Baris = {"ID Menu","Nama Menu","Harga","Kategori","Gambar","Stok"};

//      Mengubah Huruf pada Baris Tabel  
        for (int i = 0; i < Baris.length; i++){
            Baris[i] = "<html><font face='SansSerif' size='6' style='font-weight: bold;'>" + Baris[i] + "</font></html>";
        }
        
        tabmode = new DefaultTableModel(null,Baris);
        tabelMenu.setModel(tabmode);
        String sql = "Select * From menu";
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("idMenu");
                String b = hasil.getString("namaMenu");
                double harga = hasil.getDouble("harga");
                String c = formatRupiah(harga); // Format harga untuk tampilan tabel
                String d = hasil.getString("kategori");
                byte[] imageData = hasil.getBytes("gambarMenu");
                String e = hasil.getString("stok");
                ImageIcon image = new ImageIcon(imageData);
                Object[] data = {a,b,c,d,image,e};
                tabmode.addRow(data);
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    
    private String formatRupiah(double number) {
        // Membuat objek Locale untuk Indonesia dengan kode bahasa "in" dan kode negara "ID"
        Locale localeID = new Locale("in", "ID");

        // Mendapatkan instance NumberFormat untuk format mata uang sesuai dengan locale Indonesia
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        // Memformat angka menjadi format mata uang Rupiah
        String formattedNumber = formatRupiah.format(number);

        // Mengganti "Rp" dengan "Rp. " agar sesuai dengan konvensi penulisan di Indonesia
        formattedNumber = formattedNumber.replace("Rp", "Rp. ");

        // Mengganti ",00" dengan ",--" untuk format yang diinginkan
        formattedNumber = formattedNumber.replace(",00", ",--");

        return formattedNumber;
    }

    private void setTableRenderer(){
        class CustomRenderer extends DefaultTableCellRenderer{

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if(value instanceof ImageIcon){
                    ImageIcon icon = (ImageIcon) value;
                    Image originalImage = icon.getImage();
                    int desireWidth = 120;
                    int desireHeight = 120;
                    Image resizedImage = originalImage.getScaledInstance(desireWidth, desireHeight, Image.SCALE_SMOOTH);
                    ImageIcon resizedIcon = new ImageIcon(resizedImage);
                    
                    JLabel label = new JLabel(resizedIcon);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    return label;
                    
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
            
        }
        tabelMenu.getColumnModel().getColumn(4).setCellRenderer(new CustomRenderer());
    }
    
    class ImageRender extends DefaultTableCellRenderer{

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if(value instanceof ImageIcon){
                    ImageIcon icon = (ImageIcon) value;
                    JLabel label = new JLabel(icon);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    return label;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
            
        }

//    private void setTableHeaderRenderer() {
//        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
//        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//        for (int i = 0; i < tabelMenu.getColumnCount(); i++) {
//            tabelMenu.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
//        }
//    }
//
//    private void setTableCellRenderer() {
//        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
//        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//        for (int i = 0; i < tabelMenu.getColumnCount(); i++) {
//            if (i != 4) { // Kecuali kolom gambar (indeks 4)
//                tabelMenu.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
//            }
//        }
//    }
    
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
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (column == 2 && value != null) { // Kolom harga
                try {
                    double harga = Double.parseDouble(value.toString());
                    setText(formatRupiah(harga));
                } catch (NumberFormatException e) {
                    // Jika tidak bisa di-parse sebagai double, tampilkan apa adanya
                    setText(value.toString());
                }
            }

            setHorizontalAlignment(SwingConstants.CENTER);
            if (row % 2 == 0) {
                setBackground(new Color(255, 236, 217));
            } else {
                setBackground(Color.WHITE);
            }

            if (isSelected) {
                setBackground(new Color(173, 216, 230));
            }

            return c;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();
        tambahMenu = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        txkode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txnama = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        cbbKategori = new javax.swing.JComboBox<>();
        gambarMenu = new javax.swing.JLabel();
        btnGambar = new Custom.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelMenu = new javax.swing.JTable();
        jLabel42 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txHarga = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        txCari = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        Clear = new Custom.Button();
        Edit = new Custom.Button();
        Delete = new Custom.Button();
        save = new Custom.Button();
        jLabel47 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        txStok = new javax.swing.JTextField();

        mainPanel.setLayout(new java.awt.CardLayout());

        tambahMenu.setBackground(new java.awt.Color(254, 216, 179));
        tambahMenu.setForeground(new java.awt.Color(0, 0, 0));
        tambahMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahMenuMouseClicked(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("ID Menu");

        txkode.setBackground(new java.awt.Color(255, 255, 255));
        txkode.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txkode.setForeground(new java.awt.Color(0, 0, 0));
        txkode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));
        txkode.setCaretColor(new java.awt.Color(255, 255, 255));
        txkode.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txkode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txkodeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nama Menu");

        txnama.setBackground(new java.awt.Color(255, 255, 255));
        txnama.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txnama.setForeground(new java.awt.Color(0, 0, 0));
        txnama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));
        txnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txnamaActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Kategori");

        jLabel44.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Gambar");

        cbbKategori.setBackground(new java.awt.Color(255, 255, 255));
        cbbKategori.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        cbbKategori.setForeground(new java.awt.Color(0, 0, 0));
        cbbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kategori", "Minuman", "Makanan" }));

        gambarMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gambarMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60), 3));
        gambarMenu.setPreferredSize(new java.awt.Dimension(265, 270));

        btnGambar.setBackground(new java.awt.Color(98, 48, 0));
        btnGambar.setForeground(new java.awt.Color(255, 255, 255));
        btnGambar.setText("Import");
        btnGambar.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnGambar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGambarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGambarMouseExited(evt);
            }
        });
        btnGambar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGambarActionPerformed(evt);
            }
        });

        tabelMenu.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        tabelMenu.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelMenu.setSelectionBackground(new java.awt.Color(254, 216, 179));
        tabelMenu.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tabelMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMenuMouseClicked(evt);
            }
        });
        tabelMenu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabelMenuKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tabelMenu);

        jLabel42.setBackground(new java.awt.Color(98, 48, 0));
        jLabel42.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("D A F T A R  M E N U");

        jLabel45.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Harga");

        txHarga.setBackground(new java.awt.Color(255, 255, 255));
        txHarga.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txHarga.setForeground(new java.awt.Color(0, 0, 0));
        txHarga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));
        txHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txHargaActionPerformed(evt);
            }
        });

        txCari.setBackground(new java.awt.Color(255, 255, 255));
        txCari.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txCari.setForeground(new java.awt.Color(0, 0, 0));
        txCari.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));
        txCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txCariActionPerformed(evt);
            }
        });
        txCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txCariKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txCariKeyTyped(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Pencarian");

        Clear.setBackground(new java.awt.Color(98, 48, 0));
        Clear.setForeground(new java.awt.Color(255, 255, 255));
        Clear.setText("CLEAR");
        Clear.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ClearMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ClearMouseExited(evt);
            }
        });
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });

        Edit.setBackground(new java.awt.Color(98, 48, 0));
        Edit.setForeground(new java.awt.Color(255, 255, 255));
        Edit.setText("EDIT");
        Edit.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                EditMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                EditMouseExited(evt);
            }
        });
        Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditActionPerformed(evt);
            }
        });

        Delete.setBackground(new java.awt.Color(98, 48, 0));
        Delete.setForeground(new java.awt.Color(255, 255, 255));
        Delete.setText("DELETE");
        Delete.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DeleteMouseExited(evt);
            }
        });
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        save.setBackground(new java.awt.Color(98, 48, 0));
        save.setForeground(new java.awt.Color(255, 255, 255));
        save.setText("SAVE");
        save.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveMouseExited(evt);
            }
        });
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Rp.");

        jLabel49.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Stok");

        txStok.setBackground(new java.awt.Color(255, 255, 255));
        txStok.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txStok.setForeground(new java.awt.Color(0, 0, 0));
        txStok.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));
        txStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txStokActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tambahMenuLayout = new javax.swing.GroupLayout(tambahMenu);
        tambahMenu.setLayout(tambahMenuLayout);
        tambahMenuLayout.setHorizontalGroup(
            tambahMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tambahMenuLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(tambahMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(tambahMenuLayout.createSequentialGroup()
                        .addGroup(tambahMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Clear, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tambahMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(tambahMenuLayout.createSequentialGroup()
                        .addGroup(tambahMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel40)
                            .addComponent(jLabel2)
                            .addComponent(jLabel43)
                            .addComponent(jLabel44)
                            .addComponent(jLabel45)
                            .addComponent(jLabel49))
                        .addGap(20, 20, 20)
                        .addGroup(tambahMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txkode)
                            .addComponent(txnama)
                            .addComponent(gambarMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbKategori, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGambar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tambahMenuLayout.createSequentialGroup()
                                .addComponent(jLabel47)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txHarga))
                            .addComponent(txStok))
                        .addGap(11, 11, 11)))
                .addGap(50, 50, 50)
                .addGroup(tambahMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tambahMenuLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel46)
                        .addGap(18, 18, 18)
                        .addComponent(txCari, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE))
                .addGap(62, 62, 62))
            .addGroup(tambahMenuLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
        tambahMenuLayout.setVerticalGroup(
            tambahMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tambahMenuLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(tambahMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txCari, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tambahMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tambahMenuLayout.createSequentialGroup()
                        .addGroup(tambahMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txkode, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tambahMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txnama, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tambahMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tambahMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txStok, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tambahMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbKategori, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tambahMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gambarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tambahMenuLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGambar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(tambahMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(tambahMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3))
                .addGap(100, 100, 100))
        );

        mainPanel.add(tambahMenu, "card2");

        jScrollPane1.setViewportView(mainPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1296, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txkodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txkodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txkodeActionPerformed

    private void txnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txnamaActionPerformed

    private void btnGambarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGambarActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File f = fileChooser.getSelectedFile();
        if (f != null) {
            String path = f.getAbsolutePath();
            try {
                BufferedImage bi = ImageIO.read(new File(path));
                Image img = bi.getScaledInstance(284,270,Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(img);
                gambarMenu.setIcon(icon);
                currentImagePath = path;  // Simpan path gambar saat ini
            } catch (IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnGambarActionPerformed

    private void tabelMenuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelMenuKeyReleased

    }//GEN-LAST:event_tabelMenuKeyReleased

    private void txHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txHargaActionPerformed

    private void txCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txCariActionPerformed

    private void ClearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClearMouseEntered
        Clear.setBackground(new Color(210,100,0));
    }//GEN-LAST:event_ClearMouseEntered

    private void ClearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClearMouseExited
        Clear.setBackground(new Color(98,48,0));
    }//GEN-LAST:event_ClearMouseExited

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
        clear();
        dataTableMenu();
        setTableRenderer();
    }//GEN-LAST:event_ClearActionPerformed

    private void EditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditMouseEntered
        Edit.setBackground(new Color(210,100,0));
    }//GEN-LAST:event_EditMouseEntered

    private void EditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditMouseExited
        Edit.setBackground(new Color(98,48,0));
    }//GEN-LAST:event_EditMouseExited

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
        try {
            String sql;
            PreparedStatement stat;

            if (currentImagePath != null && !currentImagePath.isEmpty()) {
                sql = "UPDATE menu SET namaMenu=?, harga=?, stok=?, kategori=?, gambarMenu=? WHERE idMenu=?";
                stat = conn.prepareStatement(sql);

                File imageFile = new File(currentImagePath);
                if (imageFile.exists()) {
                    FileInputStream fis = new FileInputStream(imageFile);
                    stat.setBinaryStream(5, fis, (int) imageFile.length());
                } else {
                    JOptionPane.showMessageDialog(null, "File gambar tidak ditemukan!");
                    return;
                }
                stat.setString(6, txkode.getText());
            } else {
                // If no new image is selected, don't update the image
                sql = "UPDATE menu SET namaMenu=?, harga=?, stok=?, kategori=? WHERE idMenu=?";
                stat = conn.prepareStatement(sql);
                stat.setString(5, txkode.getText());
            }

            stat.setString(1, txnama.getText());
            stat.setString(2, txHarga.getText());
            stat.setString(3, txStok.getText());
            stat.setString(4, cbbKategori.getSelectedItem().toString());
            stat.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Ubah");
            clear();
            txkode.requestFocus();
            dataTableMenu();
            setTableRenderer();
            tabelMenu.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
            tabelMenu.setDefaultRenderer(Object.class, new CustomCellRenderer());
            
//            int rowsAffected = stat.executeUpdate();
//            if (rowsAffected > 0) {
//                JOptionPane.showMessageDialog(null, "Data Berhasil Di Ubah");
//                clear();
//                txkode.requestFocus();
//                dataTableMenu();
//                setTableRenderer();
//                tabelMenu.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
//                tabelMenu.setDefaultRenderer(Object.class, new CustomCellRenderer());
//            } else {
//                JOptionPane.showMessageDialog(null, "Tidak ada data yang diubah");
//            }
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Di Ubah: " + ex.getMessage());
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "File gambar tidak ditemukan: " + ex.getMessage());
            ex.printStackTrace();
        }
    }//GEN-LAST:event_EditActionPerformed

    private void DeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteMouseEntered
        Delete.setBackground(new Color(210,100,0));
    }//GEN-LAST:event_DeleteMouseEntered

    private void DeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteMouseExited
        Delete.setBackground(new Color(98,48,0));
    }//GEN-LAST:event_DeleteMouseExited

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Hapus Data Berikut?","Konfirmasi Dialog",JOptionPane.YES_NO_CANCEL_OPTION);
        if(ok == 0){
            String sql =  "Delete from menu where idMenu=?";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txkode.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Hapus");
                clear();
                txkode.requestFocus();
                dataTableMenu();
                setTableRenderer();
                tabelMenu.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
                tabelMenu.setDefaultRenderer(Object.class, new CustomCellRenderer());
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Di Hapus");
            }
        }
    }//GEN-LAST:event_DeleteActionPerformed

    private void saveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMouseEntered
        save.setBackground(new Color(210,100,0));
    }//GEN-LAST:event_saveMouseEntered

    private void saveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMouseExited
        save.setBackground(new Color(98,48,0));
    }//GEN-LAST:event_saveMouseExited

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        String sql = "INSERT INTO menu (namaMenu, harga, stok, kategori, gambarMenu) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, txnama.getText());
            stat.setString(2, txHarga.getText());
            stat.setString(3, txStok.getText());
            stat.setString(4, cbbKategori.getSelectedItem().toString());

            if (currentImagePath != null && !currentImagePath.isEmpty()) {
                File imageFile = new File(currentImagePath);
                if (imageFile.exists()) {
                    FileInputStream fis = new FileInputStream(imageFile);
                    stat.setBinaryStream(5, fis, (int) imageFile.length());
                } else {
                    JOptionPane.showMessageDialog(null, "File gambar tidak ditemukan!");
                    return;
                }
            } else {
                stat.setNull(5, java.sql.Types.BLOB);
            }
            
            // Get the generated idMenu
            ResultSet rs = stat.getGeneratedKeys();
            if (rs.next()) {
                String idMenu = rs.getString(1);
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan\nId Menu: " + idMenu);
            }
            
            int rowsAffected = stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            if (rowsAffected > 0) {
                clear();
                txkode.requestFocus();
                dataTableMenu();
                setTableRenderer();
                tabelMenu.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
                tabelMenu.setDefaultRenderer(Object.class, new CustomCellRenderer());

            } else {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan");
            }

            stat.close(); // Close PreparedStatement
        } catch(SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan: " + e.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "File gambar tidak ditemukan: " + e.getMessage());
        }

    }//GEN-LAST:event_saveActionPerformed

    private void tabelMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMenuMouseClicked
        int bar = tabelMenu.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        ImageIcon icon = (ImageIcon) tabmode.getValueAt(bar, 4);
        String e = tabmode.getValueAt(bar, 5).toString();
//       
        txkode.setText(a);
        txnama.setText(b);
        txHarga.setText(c.replace("Rp. ", "").replace(",--", "").replace(".", ""));
        cbbKategori.setSelectedItem(d);
        txStok.setText(e);
        
        if (icon != null) {
            Image image = icon.getImage();
            Image resizedImage = image.getScaledInstance(gambarMenu.getPreferredSize().width, gambarMenu.getPreferredSize().height, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            gambarMenu.setIcon(resizedIcon);
        } else {
            gambarMenu.setIcon(null);
        }
        
    }//GEN-LAST:event_tabelMenuMouseClicked

    private void btnGambarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGambarMouseEntered
        btnGambar.setBackground(new Color(210,100,0));
    }//GEN-LAST:event_btnGambarMouseEntered

    private void btnGambarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGambarMouseExited
        btnGambar.setBackground(new Color(98,48,0));
    }//GEN-LAST:event_btnGambarMouseExited

    private void txCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariKeyPressed
        
    }//GEN-LAST:event_txCariKeyPressed

    private void txCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariKeyTyped
        Object[] Baris = {"ID Menu","Nama Menu","Harga","Kategori","Gambar","Stok"};
        for (int i = 0; i < Baris.length; i++){
            Baris[i] = "<html><font face='SansSerif' size='6' style='font-weight: bold;'>" + Baris[i] + "</font></html>";
        }
        tabmode = new DefaultTableModel(null, Baris);
        tabelMenu.setModel(tabmode);
        String cari = txCari.getText();
        
        try{
            String sql = "Select * From menu WHERE idMenu LIKE '%"+cari+"%' OR "
                    + "namaMenu LIKE '%"+cari+"%' OR harga LIKE '%"+cari+"%' "
                    + "OR kategori LIKE '%"+cari+"%' OR stok LIKE '%"+cari+"%'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                String a = rs.getString("idMenu");
                String b = rs.getString("namaMenu");
                String c = rs.getString("harga");
                String d = rs.getString("kategori");
                byte[] imageData = rs.getBytes("gambarMenu");
                String e = rs.getString("stok");
                ImageIcon image = new ImageIcon(imageData);
                setTableRenderer();
                Object[] rowData = {a,b,c,d,image,e};
                tabmode.addRow(rowData);
            }
            rs.close();
            st.close();
        }catch (SQLException e){ 
            e.printStackTrace();
        }
    }//GEN-LAST:event_txCariKeyTyped

    private void txStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txStokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txStokActionPerformed

    private void tambahMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahMenuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tambahMenuMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Custom.Button Clear;
    private Custom.Button Delete;
    private Custom.Button Edit;
    private Custom.Button btnGambar;
    private javax.swing.JComboBox<String> cbbKategori;
    private javax.swing.JLabel gambarMenu;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel mainPanel;
    private Custom.Button save;
    private javax.swing.JTable tabelMenu;
    private javax.swing.JPanel tambahMenu;
    private javax.swing.JTextField txCari;
    private javax.swing.JTextField txHarga;
    private javax.swing.JTextField txStok;
    private javax.swing.JTextField txkode;
    private javax.swing.JTextField txnama;
    // End of variables declaration//GEN-END:variables

}
