package Form;
 
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

public class Pegawai extends javax.swing.JPanel {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    
    public Pegawai() {
        initComponents();
        txpegawai.setEnabled(false);
        dataTablePegawai();
        tabelPegawai.setRowHeight(30);
//        setTableHeaderRenderer();
//        setTableCellRenderer();
        tabelPegawai.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        tabelPegawai.setDefaultRenderer(Object.class, new CustomCellRenderer());
    }
    
    private void clear(){
        txpegawai.setEnabled(false);
        txpegawai.setText("");
        txnama.setText("");
        txTempat.setText("");
//        tanggalLahir.setDate(null);
        txalamat.setText("");
        txtelep.setText("");
        txemail.setText("");
        cbbJabatan.setSelectedIndex(0);
        cbbShiftKerja.setSelectedIndex(0);
        txRekening.setText("");
        txCari.setText("");
        txpegawai.requestFocus();
    }
    
    private void dataTablePegawai(){
        Object[] Baris = {"ID Pegawai","Nama Pegawai","Tempat","Tanggal Lahir","Alamat","Telepon","Email","Jabatan","Shift Kerja","No.Rekening"};

//      Mengubah Huruf pada Baris Tabel  
        for (int i = 0; i < Baris.length; i++){
            Baris[i] = "<html><font face='SansSerif' size='5' style='font-weight: bold;'>" + Baris[i] + "</font></html>";
        }
        
        tabmode = new DefaultTableModel(null,Baris);
        tabelPegawai.setModel(tabmode);
        String sql = "Select * From pegawai";
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("id");
                String b = hasil.getString("nama");
                String c = hasil.getString("Tempat");
                String d = hasil.getString("tanggalLahir");
                String e = hasil.getString("alamat");
                String f = hasil.getString("telepon");
                String g = hasil.getString("email");
                String h = hasil.getString("jabatan");
                String i = hasil.getString("shiftKerja");
                String j = hasil.getString("rek");
                
                String[] data = {a,b,c,d,e,f,g,h,i,j};
                tabmode.addRow(data);
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(e);
        }
    }
    private void cariDataPegawai(){
        Object[] Baris = {"ID Pegawai","Nama Pegawai","Tempat","Tanggal Lahir","Alamat","Telepon","Email","Jabatan","Shift Kerja","No.Rekening"};

//      Mengubah Huruf pada Baris Tabel  
        for (int i = 0; i < Baris.length; i++){
            Baris[i] = "<html><font face='SansSerif' size='5' style='font-weight: bold;'>" + Baris[i] + "</font></html>";
        }
        
        tabmode = new DefaultTableModel(null,Baris);
        tabelPegawai.setModel(tabmode);
        String cari = txCari.getText();
        
        String sql = "Select * From pegawai WHERE id LIKE '%"+cari+"%' OR "
            + "nama LIKE '%"+cari+"%' OR Tempat LIKE '%"+cari+"%' OR tanggalLahir LIKE '%"+cari+"%' "
            + "OR alamat LIKE '%"+cari+"%' OR telepon LIKE '%"+cari+"%'"
            + " OR email LIKE '%"+cari+"%' OR jabatan LIKE '%"+cari+"%'"
            + "OR shiftKerja LIKE '%"+cari+"%' OR rek LIKE '%"+cari+"%'";
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("id");
                String b = hasil.getString("nama");
                String c = hasil.getString("Tempat");
                String d = hasil.getString("tanggalLahir");
                String e = hasil.getString("alamat");
                String f = hasil.getString("telepon");
                String g = hasil.getString("email");
                String h = hasil.getString("jabatan");
                String i = hasil.getString("shiftKerja");
                String j = hasil.getString("rek");
                
                String[] data = {a,b,c,d,e,f,g,h,i,j};
                tabmode.addRow(data);
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(e);
        }
    }
    
    private void setTableHeaderRenderer() {
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabelPegawai.getColumnCount(); i++) {
            tabelPegawai.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }

    private void setTableCellRenderer() {
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabelPegawai.getColumnCount(); i++) {
            tabelPegawai.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
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

        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txCari = new javax.swing.JTextField();
        Save = new Custom.Button();
        Clear = new Custom.Button();
        Edit = new Custom.Button();
        Delete = new Custom.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelPegawai = new javax.swing.JTable();
        txpegawai = new javax.swing.JTextField();
        txRekening = new javax.swing.JTextField();
        cbbJabatan = new javax.swing.JComboBox<>();
        txnama = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txalamat = new javax.swing.JTextArea();
        cbbShiftKerja = new javax.swing.JComboBox<>();
        txtelep = new javax.swing.JTextField();
        txemail = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txTempat = new javax.swing.JTextField();
        tanggalLahir = new com.toedter.calendar.JDateChooser();

        setBackground(new java.awt.Color(254, 216, 179));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText(" D A T A  P E G A W A I");

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Pencarian");

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

        tabelPegawai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tabelPegawai.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        tabelPegawai.setForeground(new java.awt.Color(0, 0, 0));
        tabelPegawai.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelPegawai.setSelectionBackground(new java.awt.Color(254, 216, 179));
        tabelPegawai.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tabelPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPegawaiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelPegawai);

        txpegawai.setEditable(false);
        txpegawai.setBackground(new java.awt.Color(255, 255, 255));
        txpegawai.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txpegawai.setForeground(new java.awt.Color(0, 0, 0));
        txpegawai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));
        txpegawai.setCaretColor(new java.awt.Color(255, 255, 255));
        txpegawai.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txpegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txpegawaiActionPerformed(evt);
            }
        });

        txRekening.setBackground(new java.awt.Color(255, 255, 255));
        txRekening.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txRekening.setForeground(new java.awt.Color(0, 0, 0));
        txRekening.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));

        cbbJabatan.setBackground(new java.awt.Color(255, 255, 255));
        cbbJabatan.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        cbbJabatan.setForeground(new java.awt.Color(0, 0, 0));
        cbbJabatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Jabatan", "Manager", "Barista", "Kasir", "Pelayan", "Supervisor", "Kitchen", "Cleaning" }));
        cbbJabatan.setPreferredSize(new java.awt.Dimension(153, 40));

        txnama.setBackground(new java.awt.Color(255, 255, 255));
        txnama.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txnama.setForeground(new java.awt.Color(0, 0, 0));
        txnama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));

        jScrollPane1.setForeground(new java.awt.Color(0, 0, 0));

        txalamat.setBackground(new java.awt.Color(255, 255, 255));
        txalamat.setColumns(20);
        txalamat.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txalamat.setRows(5);
        txalamat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));
        jScrollPane1.setViewportView(txalamat);

        cbbShiftKerja.setBackground(new java.awt.Color(255, 255, 255));
        cbbShiftKerja.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        cbbShiftKerja.setForeground(new java.awt.Color(0, 0, 0));
        cbbShiftKerja.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Shift Kerja", "Pagi", "Siang", "Malam" }));
        cbbShiftKerja.setPreferredSize(new java.awt.Dimension(153, 40));

        txtelep.setBackground(new java.awt.Color(255, 255, 255));
        txtelep.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txtelep.setForeground(new java.awt.Color(0, 0, 0));
        txtelep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));

        txemail.setBackground(new java.awt.Color(255, 255, 255));
        txemail.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txemail.setForeground(new java.awt.Color(0, 0, 0));
        txemail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Id Pegawai");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nama Lengkap");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("TTL");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Alamat");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("No Telepon");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Alamat Email");

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Jabatan");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Shift Kerja");

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("No Rekening");

        txTempat.setBackground(new java.awt.Color(255, 255, 255));
        txTempat.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txTempat.setForeground(new java.awt.Color(0, 0, 0));
        txTempat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));

        tanggalLahir.setForeground(new java.awt.Color(255, 255, 255));
        tanggalLahir.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(404, 404, 404)
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
                .addGap(400, 400, 400))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(1102, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(txCari, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbbShiftKerja, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbbJabatan, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txemail, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtelep, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txnama, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txpegawai, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txTempat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(txRekening)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(Clear, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                                    .addComponent(Save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Delete, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))))
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)))
                .addGap(100, 100, 100))
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txCari, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txpegawai)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txnama)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(txTempat, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtelep)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txemail)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbJabatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbShiftKerja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txRekening, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3))
                .addGap(100, 100, 100))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariKeyTyped
//        Object[] Baris = {"ID Pegawai","Nama Pegawai","TTL","Alamat","Telepon","Email","Jabatan","Shift Kerja","No.Rekening"};
//        for (int i = 0; i < Baris.length; i++){
//            Baris[i] = "<html><font face='SansSerif' size='5' style='font-weight: bold;'>" + Baris[i] + "</font></html>";
//        }
//        tabmode = new DefaultTableModel(null, Baris);
//        tabelPegawai.setModel(tabmode);
//        String cari = txCari.getText();
//        
//        try{
//            String sql = "Select * From pegawai WHERE id LIKE '%"+cari+"%' OR "
//            + "nama LIKE '%"+cari+"%' OR Tempat LIKE '%"+cari+"%' OR tanggalLahir LIKE '%"+cari+"%' "
//            + "OR alamat LIKE '%"+cari+"%' OR telepon LIKE '%"+cari+"%'"
//            + " OR email LIKE '%"+cari+"%' OR jabatan LIKE '%"+cari+"%'"
//            + "OR shiftKerja LIKE '%"+cari+"%' OR rek LIKE '%"+cari+"%'";
//            Statement st = conn.createStatement();
//            ResultSet hasil = st.executeQuery(sql);
//
//            while(hasil.next()){
//                String a = hasil.getString("id");
//                String b = hasil.getString("nama");
//                String c = hasil.getString("Tempat");
//                String d = hasil.getString("tanggalLahir");
//                String e = hasil.getString("alamat");
//                String f = hasil.getString("telepon");
//                String g = hasil.getString("email");
//                String h = hasil.getString("jabatan");
//                String i = hasil.getString("shiftKerja");
//                String j = hasil.getString("rek");
//                
//                String[] data = {a,b,c,d,e,f,g,h,i,j};
//                tabmode.addRow(data);
//            }
//            hasil.close();
//            st.close();
//        }catch (SQLException e){ 
//            e.printStackTrace();
//        }

        cariDataPegawai();

    }//GEN-LAST:event_txCariKeyTyped

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
        try{
                String sql = "Update pegawai set nama=?, Tempat=?,tanggalLahir=?,alamat=?,telepon=?,email=?,"
                        + " jabatan=?, shiftKerja=?, rek=? where id=?";
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txnama.getText());
                stat.setString(2, txTempat.getText());
                String date = dateFormat.format(tanggalLahir.getDate());
                stat.setString(3, date);
                stat.setString(4, txalamat.getText());
                stat.setString(5, txtelep.getText());
                stat.setString(6, txemail.getText());
                stat.setString(7, cbbJabatan.getSelectedItem().toString());
                stat.setString(8, cbbShiftKerja.getSelectedItem().toString());
                stat.setString(9, txRekening.getText());
                stat.setString(10, txpegawai.getText());

                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Ubah");
                clear();
                txpegawai.requestFocus();
                dataTablePegawai();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Data Gagal Di Ubah" + ex);
            }
    }//GEN-LAST:event_EditActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        String sql = "insert into pegawai (nama,Tempat,tanggalLahir, alamat,telepon,email,jabatan,shiftKerja,rek) values (?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement stat = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
//            stat.setString(1, txpegawai.getText());
            stat.setString(1, txnama.getText());
            stat.setString(2, txTempat.getText());
            String date = dateFormat.format(tanggalLahir.getDate());
            stat.setString(3, date);
            stat.setString(4, txalamat.getText());
            stat.setString(5, txtelep.getText());
            stat.setString(6, txemail.getText());
            stat.setString(7, cbbJabatan.getSelectedItem().toString());
            stat.setString(8, cbbShiftKerja.getSelectedItem().toString());
            stat.setString(9, txRekening.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Pegawai Berhasil Di Simpan");
            ResultSet rs = stat.getGeneratedKeys();
            if (rs.next()) {
                String idPegawai = rs.getString(1);
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan\nID Pegawai : " + idPegawai);
            }
            
            clear();
            txpegawai.requestFocus();
            dataTablePegawai();
        }catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Data Gagal Di Simpan");
        }
    }//GEN-LAST:event_SaveActionPerformed

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
        clear();
        dataTablePegawai();
    }//GEN-LAST:event_ClearActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Hapus Data Berikut?","Konfirmasi Dialog",JOptionPane.YES_NO_CANCEL_OPTION);
        if(ok == 0){
            String sql =  "Delete from pegawai where id=?";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txpegawai.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Hapus");
                clear();
                txpegawai.requestFocus();
                dataTablePegawai();
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

    private void tabelPegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPegawaiMouseClicked
        int bar = tabelPegawai.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        String g = tabmode.getValueAt(bar, 6).toString();
        String h = tabmode.getValueAt(bar, 7).toString();
        String i = tabmode.getValueAt(bar, 8).toString();
        String j = tabmode.getValueAt(bar, 9).toString();
        txpegawai.setText(a);
        txnama.setText(b);
        txTempat.setText(c);
                
        try
        {
            Date date = dateFormat.parse(d);
            tanggalLahir.setDate(date);
        } catch (ParseException ex)
        {
            Logger.getLogger(Pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }

        txalamat.setText(e);
        txtelep.setText(f);
        txemail.setText(g);
        cbbJabatan.setSelectedItem(h);
        cbbShiftKerja.setSelectedItem(i);
        txRekening.setText(j);
    }//GEN-LAST:event_tabelPegawaiMouseClicked

    private void txCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txCariKeyPressed

    private void txpegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txpegawaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txpegawaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Custom.Button Clear;
    private Custom.Button Delete;
    private Custom.Button Edit;
    private Custom.Button Save;
    private javax.swing.JComboBox<String> cbbJabatan;
    private javax.swing.JComboBox<String> cbbShiftKerja;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tabelPegawai;
    private com.toedter.calendar.JDateChooser tanggalLahir;
    private javax.swing.JTextField txCari;
    private javax.swing.JTextField txRekening;
    private javax.swing.JTextField txTempat;
    private javax.swing.JTextArea txalamat;
    private javax.swing.JTextField txemail;
    private javax.swing.JTextField txnama;
    private javax.swing.JTextField txpegawai;
    private javax.swing.JTextField txtelep;
    // End of variables declaration//GEN-END:variables
}
