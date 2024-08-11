package Form;
 
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

public class Barang extends javax.swing.JPanel {

    private final Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    
    public Barang() {
        initComponents();
        txkode.setEnabled(false);
        init();
        dataTableBarang();
    }

    private void init(){
        tabelBarang.setRowHeight(30);
        tabelBarang.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        tabelBarang.setDefaultRenderer(Object.class, new CustomCellRenderer());
    }
    
    private void clearBarang(){
        txkode.setEnabled(false);
        txkode.setText("");
        txnama.setText("");
        txharga.setText("");
        txstok.setText("");
        txkode.requestFocus();
        cbbSatuan.setSelectedIndex(0);
        txCari.setText("");
        cbbKategori.setSelectedIndex(0);
    }
    
    private void dataTableBarang(){
        Object[] Baris = {"Kode Barang","Detail / Merek","Stok","Harga","Satuan","Kategori"};

        // Mengubah Huruf pada Baris Tabel  
        for (int i = 0; i < Baris.length; i++){
            Baris[i] = "<html><font face='SansSerif' size='5' style='font-weight: bold;'>" + Baris[i] + "</font></html>";
        }

        tabmode = new DefaultTableModel(null,Baris);
        tabelBarang.setModel(tabmode);
        String sql = "Select kode_barang, detail_merek, stok_barang, harga_barang, satuan, kategori_barang From databarang";
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("kode_barang");
                String b = hasil.getString("detail_merek");
                String c = hasil.getString("stok_barang");
                double harga = hasil.getDouble("harga_barang");
                String d = formatRupiah(harga);
                String e = hasil.getString("satuan");
                String f = hasil.getString("kategori_barang");

                String[] data = {a,b,c,d,e,f};
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

        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbbKategori = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txnama = new javax.swing.JTextField();
        txstok = new javax.swing.JTextField();
        txharga = new javax.swing.JTextField();
        txkode = new javax.swing.JTextField();
        txCari = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cbbSatuan = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        Save = new Custom.Button();
        Clear = new Custom.Button();
        Edit = new Custom.Button();
        Delete = new Custom.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelBarang = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(254, 216, 179));

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Kode Barang");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Detail / Merek");

        cbbKategori.setBackground(new java.awt.Color(255, 255, 255));
        cbbKategori.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        cbbKategori.setForeground(new java.awt.Color(0, 0, 0));
        cbbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kategori", "Food", "Drink" }));
        cbbKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbKategoriActionPerformed(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText(" D A T A  B A R A N G");

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Stok Barang");

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Pencarian");

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Kategori");

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Harga Barang");

        txnama.setBackground(new java.awt.Color(255, 255, 255));
        txnama.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txnama.setForeground(new java.awt.Color(0, 0, 0));
        txnama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));
        txnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txnamaActionPerformed(evt);
            }
        });

        txstok.setBackground(new java.awt.Color(255, 255, 255));
        txstok.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txstok.setForeground(new java.awt.Color(0, 0, 0));
        txstok.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));
        txstok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txstokActionPerformed(evt);
            }
        });

        txharga.setBackground(new java.awt.Color(255, 255, 255));
        txharga.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txharga.setForeground(new java.awt.Color(0, 0, 0));
        txharga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));
        txharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txhargaActionPerformed(evt);
            }
        });

        txkode.setEditable(false);
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

        txCari.setBackground(new java.awt.Color(255, 255, 255));
        txCari.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txCari.setForeground(new java.awt.Color(0, 0, 0));
        txCari.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));
        txCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txCariKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txCariKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Satuan");

        cbbSatuan.setBackground(new java.awt.Color(255, 255, 255));
        cbbSatuan.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        cbbSatuan.setForeground(new java.awt.Color(0, 0, 0));
        cbbSatuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Satuan", "Pcs", "Kg", "Lainnya" }));

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Rp.");

        Save.setBackground(new java.awt.Color(98, 48, 0));
        Save.setForeground(new java.awt.Color(255, 255, 255));
        Save.setText("SAVE");
        Save.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SaveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SaveMouseExited(evt);
            }
        });
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

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

        tabelBarang.setBackground(new java.awt.Color(255, 255, 255));
        tabelBarang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tabelBarang.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        tabelBarang.setForeground(new java.awt.Color(0, 0, 0));
        tabelBarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelBarang.setSelectionBackground(new java.awt.Color(254, 216, 179));
        tabelBarang.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tabelBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBarangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelBarang);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(404, 404, 404)
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                .addGap(400, 400, 400))
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11)
                    .addComponent(jLabel2)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel10)
                    .addComponent(Save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Clear, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txkode, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txnama, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txharga))
                        .addComponent(txstok, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbbSatuan, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbKategori, javax.swing.GroupLayout.Alignment.LEADING, 0, 170, Short.MAX_VALUE))
                    .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txCari, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane3)))
                .addGap(100, 100, 100))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txkode, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txnama, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txstok, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txharga, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txCari, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3)))
                .addGap(100, 100, 100))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txnamaActionPerformed

    private void txstokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txstokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txstokActionPerformed

    private void txhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txhargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txhargaActionPerformed

    private void cbbKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbKategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbKategoriActionPerformed

    private void txkodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txkodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txkodeActionPerformed

    private void txCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariKeyTyped
        Object[] Baris = {"Kode Barang","Detail / Merek","Stok","Harga","Satuan","Kategori"};
        for (int i = 0; i < Baris.length; i++){
            Baris[i] = "<html><font face='SansSerif' size='5' style='font-weight: bold;'>" + Baris[i] + "</font></html>";
        }
        tabmode = new DefaultTableModel(null, Baris);
        tabelBarang.setModel(tabmode);
        String cari = txCari.getText();
        
        try{
            String sql = "Select * From databarang WHERE kode_barang LIKE '%"+cari+"%' OR "
                    + "detail_merek LIKE '%"+cari+"%' OR stok_barang LIKE '%"+cari+"%' "
                    + "OR harga_barang LIKE '%"+cari+"%' OR satuan LIKE '%"+cari+"%'"
                    + " OR kategori_barang LIKE '%"+cari+"%'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                String kode = rs.getString("kode_barang");
                String detail = rs.getString("detail_merek");
                String stok = rs.getString("stok_barang");
                String harga = rs.getString("harga_barang");
                String satuan = rs.getString("satuan");
                String kategori = rs.getString("kategori_barang");

                Object[] rowData = {kode, detail, stok, harga, satuan, kategori};
                tabmode.addRow(rowData);
            }
            rs.close();
            st.close();
        }catch (Exception e){ 
            e.printStackTrace();
        }
    }//GEN-LAST:event_txCariKeyTyped

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
        try{
            String sql = "Update databarang set detail_merek=?,stok_barang=?,harga_barang=?,satuan=?,kategori_barang=? where kode_barang=?";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txnama.getText());
            stat.setString(2, txstok.getText());
            stat.setString(3, txharga.getText());
            stat.setString(4, cbbSatuan.getSelectedItem().toString());
            stat.setString(5, cbbKategori.getSelectedItem().toString());
            stat.setString(6, txkode.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Ubah");
            clearBarang();
            dataTableBarang();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Data Gagal Di Ubah" + ex);
        }
    }//GEN-LAST:event_EditActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        String sql = "insert into databarang (detail_merek,stok_barang,harga_barang,satuan,kategori_barang) values (?,?,?,?,?)";
        try{
            PreparedStatement stat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, txnama.getText());
            stat.setString(2, txstok.getText());
            stat.setString(3, txharga.getText());
            stat.setString(4, cbbSatuan.getSelectedItem().toString());
            stat.setString(5, cbbKategori.getSelectedItem().toString());

            stat.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data Barang Berhasil Di Simpan");
            // Get the generated kode_barang
            ResultSet rs = stat.getGeneratedKeys();
            if (rs.next()) {
                String kodeBarang = rs.getString(1);
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan\nKode Barang: " + kodeBarang);
            }

            clearBarang();
            dataTableBarang();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Data Gagal Di Simpan: " + e.getMessage());
        }
    }//GEN-LAST:event_SaveActionPerformed

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
        clearBarang();
        dataTableBarang();
    }//GEN-LAST:event_ClearActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Hapus Data Berikut?","Konfirmasi Dialog",JOptionPane.YES_NO_CANCEL_OPTION);
        if(ok == 0){
            String sql =  "Delete from databarang where kode_barang=?";
            try{        
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txkode.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Hapus");
                clearBarang();
                dataTableBarang();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Di Hapus");
            }
        }
    }//GEN-LAST:event_DeleteActionPerformed

    private void SaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveMouseEntered
        Save.setBackground(new Color(210,100,0));
    }//GEN-LAST:event_SaveMouseEntered

    private void SaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveMouseExited
        Save.setBackground(new Color(98,48,0));
    }//GEN-LAST:event_SaveMouseExited

    private void EditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditMouseEntered
        Edit.setBackground(new Color(210,100,0));
    }//GEN-LAST:event_EditMouseEntered

    private void EditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditMouseExited
        Edit.setBackground(new Color(98,48,0));
    }//GEN-LAST:event_EditMouseExited

    private void ClearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClearMouseEntered
        Clear.setBackground(new Color(210,100,0));
    }//GEN-LAST:event_ClearMouseEntered

    private void ClearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClearMouseExited
        Clear.setBackground(new Color(98,48,0));
    }//GEN-LAST:event_ClearMouseExited

    private void DeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteMouseEntered
        Delete.setBackground(new Color(210,100,0));
    }//GEN-LAST:event_DeleteMouseEntered

    private void DeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteMouseExited
        Delete.setBackground(new Color(98,48,0));
    }//GEN-LAST:event_DeleteMouseExited

    private void tabelBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarangMouseClicked
        int bar = tabelBarang.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        txkode.setText(a);
        txnama.setText(b);
        txstok.setText(c); 
        // Menghapus "Rp. ", tanda ribuan (.), dan ",00"
        txharga.setText(d.replace("Rp. ", "").replace(",--", "").replace(".", ""));
        cbbSatuan.setSelectedItem(e);
        cbbKategori.setSelectedItem(f);
    }//GEN-LAST:event_tabelBarangMouseClicked

    private void txCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txCariKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Custom.Button Clear;
    private Custom.Button Delete;
    private Custom.Button Edit;
    private Custom.Button Save;
    private javax.swing.JComboBox<String> cbbKategori;
    private javax.swing.JComboBox<String> cbbSatuan;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tabelBarang;
    private javax.swing.JTextField txCari;
    private javax.swing.JTextField txharga;
    private javax.swing.JTextField txkode;
    private javax.swing.JTextField txnama;
    private javax.swing.JTextField txstok;
    // End of variables declaration//GEN-END:variables
}