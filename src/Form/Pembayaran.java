package Form;
 
import example.PemesananKopi;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import koneksi.koneksi;

public class Pembayaran extends javax.swing.JPanel {

    private final Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    
    public Pembayaran() {
        initComponents();
        txIdPembayaran.setEnabled(false);
        dataTable();
        dataTablePembayaran();
        setTime();
        addPlaceholder(txTotalHarga, "0.0");
        addPlaceholder(txBayar, "0.0");
        addPlaceholder(txKembalian, "0.0");
        tabelPemesanan.setRowHeight(30);
        tabelPemesanan.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        tabelPemesanan.setDefaultRenderer(Object.class, new CustomCellRenderer());
        tabelPembayaran.setRowHeight(30);
        tabelPembayaran.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        tabelPembayaran.setDefaultRenderer(Object.class, new CustomCellRenderer());
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
    
    private void dataTable(){
        Object[] Baris = {"ID Pemesanan","Nama Menu","Nama Pelanggan","Jumlah","Harga","Total Harga","Waktu Pesan","Status"};

        for (int i = 0; i < Baris.length; i++){
            Baris[i] = "<html><font face='Sans Serif' size='5' style='font-weight: bold;'>" + Baris[i] + "</font></html>";
        }  
        tabmode = new DefaultTableModel(null,Baris);
        tabelPemesanan.setModel(tabmode);
        String sql = "Select * From pemesanan";
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("idPemesanan");
                String b = hasil.getString("namaMenu");
                String c = hasil.getString("namaPelanggan");
                String d = hasil.getString("jumlah");
                double harga = hasil.getDouble("harga");
                String e = formatRupiah(harga);
                double totalHarga = hasil.getDouble("totalHarga");
                String f = formatRupiah(totalHarga);
                String g = hasil.getString("waktuPesan");
                String h = hasil.getString("status");
                
                String[] data = {a,b,c,d,e,f,g,h};
                tabmode.addRow(data);
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(e);
        }
    }
    
    private void dataTablePembayaran() {
        DefaultTableModel tabmode = new DefaultTableModel();
        tabmode.addColumn("ID Pembayaran");
        tabmode.addColumn("ID Pemesanan");
        tabmode.addColumn("Nama Pelanggan");
        tabmode.addColumn("Jumlah");
        tabmode.addColumn("Total Harga");
        tabmode.addColumn("Metode Pembayaran");
        tabmode.addColumn("Waktu Bayar");

        try {
            String sql = "SELECT * FROM pembayaran";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);

            while(hasil.next()){
                String a = hasil.getString("idPembayaran");
                String b = hasil.getString("id_pemesanan");
                String c = hasil.getString("namaPelanggan");
                String d = hasil.getString("jumlah");
                double harga = hasil.getDouble("totalHarga");
                String e = formatRupiah(harga);
                String f = hasil.getString("metode_pembayaran");
                String g = hasil.getString("waktuBayar");
                
                String[] data = {a,b,c,d,e,f,g};
                tabmode.addRow(data);
            }

            JTableHeader header = tabelPembayaran.getTableHeader();
            Font boldFont = header.getFont().deriveFont(Font.BOLD);
            header.setFont(boldFont);
            tabelPembayaran.setModel(tabmode);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading payment data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void savePaymentToDatabase() {
        try {
            String sql = "INSERT INTO pembayaran (id_pemesanan, namaPelanggan, jumlah, totalHarga, metode_pembayaran, waktuBayar) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txIdPemesanan.getText());
            stat.setString(2, txNamaPelanggan.getText());
            stat.setInt(3, Integer.parseInt(txJumlah.getText()));
            stat.setDouble(4, Double.parseDouble(txTotalHarga.getText()));
            stat.setString(5, cbbMetode.getSelectedItem().toString());
            stat.setString(6, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            stat.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saving payment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
    private void updatePemesananStatus() {
        try {
            String sql = "UPDATE pemesanan SET status = 'Sudah Bayar' WHERE idPemesanan = ?";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txIdPemesanan.getText());
            stat.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating order status: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
*/
    
    private void updatePemesananStatus() {
        try {
            // First, update the status to "Sudah Bayar"
            String updateSql = "UPDATE pemesanan SET status = 'Sudah Bayar' WHERE idPemesanan = ?";
            PreparedStatement updateStat = conn.prepareStatement(updateSql);
            updateStat.setString(1, txIdPemesanan.getText());
            updateStat.executeUpdate();

            // Then, delete the paid order from the pemesanan table
            String deleteSql = "DELETE FROM pemesanan WHERE idPemesanan = ?";
            PreparedStatement deleteStat = conn.prepareStatement(deleteSql);
            deleteStat.setString(1, txIdPemesanan.getText());
            deleteStat.executeUpdate();

            JOptionPane.showMessageDialog(this, "Status pemesanan diperbarui dan dihapus dari daftar pemesanan.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating order status and deleting from pemesanan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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
//                    txTime.setText(time.split(" ")[0]+" "+time.split(" ")[1]);
//                    txDate.setText(df.format(date));
                }
            }
        }).start();
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
                setBackground(new Color(254,216,179)); // Warna latar belakang baris genap
            } else {
                setBackground(Color.WHITE); // Warna latar belakang baris ganjil
            }
            
            if (isSelected) {
                setBackground(new Color(173, 216, 230)); // Warna saat sel dipilih
            }
            
            return this;
        }
    }

    
    private void clear(){
        txIdPembayaran.setText("");
        txIdPemesanan.setText("");
        txNamaPelanggan.setText("");
        txJumlah.setText("");
        cbbMetode.setSelectedIndex(0);
        cbbStatus.setSelectedIndex(0);
        txTotalHarga.setText("0.0");
        txBayar.setText("0.0");
        txKembalian.setText("0.0");

        // Reset the colors for placeholder text
        txTotalHarga.setForeground(Color.GRAY);
        txBayar.setForeground(Color.GRAY);
        txKembalian.setForeground(Color.GRAY);
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
    
    private boolean getPemesananData(String idPemesanan) {
        try {
            String sql = "SELECT idPemesanan, namaPelanggan, jumlah, totalHarga FROM pemesanan WHERE idPemesanan = ?";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, idPemesanan);
            ResultSet rs = stat.executeQuery();

            if (rs.next()) {
                txIdPemesanan.setText(rs.getString("idPemesanan"));
                txNamaPelanggan.setText(rs.getString("namaPelanggan"));
                txJumlah.setText(rs.getString("jumlah"));
                txTotalHarga.setText(rs.getString("totalHarga"));
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Data pemesanan tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error mengambil data pemesanan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel17 = new javax.swing.JLabel();
        txIdPemesanan = new javax.swing.JTextField();
        txJumlah = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txIdPembayaran = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbbMetode = new javax.swing.JComboBox<>();
        Clear = new Custom.Button();
        Edit = new Custom.Button();
        Delete = new Custom.Button();
        Save = new Custom.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPemesanan = new javax.swing.JTable();
        btnBayar = new Custom.Button();
        btnReset = new Custom.Button();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cbbStatus = new javax.swing.JComboBox<>();
        txTotalHarga = new javax.swing.JTextField();
        txBayar = new javax.swing.JTextField();
        txKembalian = new javax.swing.JTextField();
        txNamaPelanggan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelPembayaran = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        txCari = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(254, 216, 179));
        setForeground(new java.awt.Color(0, 0, 0));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Segoe UI Black", 1, 48)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("P E M B A Y A R A N");

        txIdPemesanan.setBackground(new java.awt.Color(255, 255, 255));
        txIdPemesanan.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txIdPemesanan.setForeground(new java.awt.Color(0, 0, 0));
        txIdPemesanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));
        txIdPemesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txIdPemesananActionPerformed(evt);
            }
        });

        txJumlah.setBackground(new java.awt.Color(255, 255, 255));
        txJumlah.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txJumlah.setForeground(new java.awt.Color(0, 0, 0));
        txJumlah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));
        txJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txJumlahActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("ID Pembayaran");

        txIdPembayaran.setEditable(false);
        txIdPembayaran.setBackground(new java.awt.Color(255, 255, 255));
        txIdPembayaran.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txIdPembayaran.setForeground(new java.awt.Color(0, 0, 0));
        txIdPembayaran.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));
        txIdPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txIdPembayaranActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ID Pemesanan");

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Jumlah ");

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Metode Pembayaran");

        cbbMetode.setBackground(new java.awt.Color(255, 255, 255));
        cbbMetode.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        cbbMetode.setForeground(new java.awt.Color(0, 0, 0));
        cbbMetode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Metode Pembayaran", "Tunai", "QR", "Debit" }));

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

        tabelPemesanan.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        tabelPemesanan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelPemesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPemesananMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelPemesanan);

        btnBayar.setBackground(new java.awt.Color(52, 152, 219));
        btnBayar.setForeground(new java.awt.Color(255, 255, 255));
        btnBayar.setText("Bayar");
        btnBayar.setFont(new java.awt.Font("Segoe UI Black", 1, 20)); // NOI18N
        btnBayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBayarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBayarMouseExited(evt);
            }
        });
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(255, 255, 255));
        btnReset.setForeground(new java.awt.Color(52, 152, 219));
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

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Total");

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Kembalian");

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Bayar");

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Status");

        cbbStatus.setBackground(new java.awt.Color(255, 255, 255));
        cbbStatus.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        cbbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cbbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Status", "Sudah Bayar", "Belum Bayar" }));

        txTotalHarga.setEditable(false);
        txTotalHarga.setBackground(new java.awt.Color(255, 255, 255));
        txTotalHarga.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        txTotalHarga.setForeground(new java.awt.Color(0, 0, 0));
        txTotalHarga.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txTotalHarga.setText("0.0");

        txBayar.setBackground(new java.awt.Color(255, 255, 255));
        txBayar.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        txBayar.setForeground(new java.awt.Color(0, 0, 0));
        txBayar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txBayar.setText("0.0");

        txKembalian.setBackground(new java.awt.Color(255, 255, 255));
        txKembalian.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        txKembalian.setForeground(new java.awt.Color(0, 0, 0));
        txKembalian.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txKembalian.setText("0.0");

        txNamaPelanggan.setBackground(new java.awt.Color(255, 255, 255));
        txNamaPelanggan.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txNamaPelanggan.setForeground(new java.awt.Color(0, 0, 0));
        txNamaPelanggan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));
        txNamaPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNamaPelangganActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nama Pelanggan");

        tabelPembayaran.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        tabelPembayaran.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelPembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPembayaranMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelPembayaran);

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
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

        jLabel47.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Rp.");

        jLabel48.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Rp.");

        jLabel49.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Rp.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txCari, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(jLabel48)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel49)
                                .addGap(1, 1, 1)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txBayar, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txTotalHarga, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Delete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Edit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txIdPemesanan, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txJumlah, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbMetode, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txIdPembayaran)
                                    .addComponent(cbbStatus, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txNamaPelanggan, javax.swing.GroupLayout.Alignment.LEADING))))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(33, 33, 33)
                                .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))))
                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txCari, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIdPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIdPemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbMetode, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txTotalHarga)
                        .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txBayar)
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(txKembalian)
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txIdPemesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txIdPemesananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txIdPemesananActionPerformed

    private void txJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txJumlahActionPerformed

    private void txIdPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txIdPembayaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txIdPembayaranActionPerformed

    private void ClearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClearMouseEntered
        Clear.setBackground(new Color(210,100,0));
    }//GEN-LAST:event_ClearMouseEntered

    private void ClearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClearMouseExited
        Clear.setBackground(new Color(98,48,0));
    }//GEN-LAST:event_ClearMouseExited

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
        clear();
        dataTable();
        dataTablePembayaran();
    }//GEN-LAST:event_ClearActionPerformed

    private void EditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditMouseEntered
        Edit.setBackground(new Color(210,100,0));
    }//GEN-LAST:event_EditMouseEntered

    private void EditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditMouseExited
        Edit.setBackground(new Color(98,48,0));
    }//GEN-LAST:event_EditMouseExited

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
        try {
            String sql = "UPDATE pembayaran SET jumlah=?, totalHarga=?, metode_pembayaran=?, waktuBayar=? WHERE id_pemesanan=?";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1, Integer.parseInt(txJumlah.getText()));
            stat.setDouble(2, Double.parseDouble(txTotalHarga.getText()));
            stat.setString(3, cbbMetode.getSelectedItem().toString());
            stat.setString(4, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            stat.setString(5, txIdPemesanan.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Berhasil Di Ubah");
            clear();
            dataTablePembayaran();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            String sql =  "Delete from pembayaran where idPembayaran=?";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txIdPembayaran.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Hapus");
                clear();
                dataTablePembayaran();
            }catch(SQLException e){
                e.printStackTrace();
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

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        try {
            String sql = "INSERT INTO pembayaran (id_pemesanan, namaPelanggan, jumlah, totalHarga, metode_pembayaran, waktuBayar) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stat = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            stat.setString(1, txIdPemesanan.getText());
            stat.setString(2, txNamaPelanggan.getText());
            stat.setInt(3, Integer.parseInt(txJumlah.getText()));
            stat.setDouble(4, Double.parseDouble(txTotalHarga.getText()));
            stat.setString(5, cbbMetode.getSelectedItem().toString());
            stat.setString(6, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Pembayaran Berhasil Di Simpan");
            ResultSet rs = stat.getGeneratedKeys();
            if (rs.next()) {
                String idPembayaran = rs.getString(1);
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan\nID Pegawai : " + idPembayaran);
            }
            clear();
            dataTablePembayaran();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_SaveActionPerformed

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        try {
        // Validasi input
            if (txIdPemesanan.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID Pemesanan harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Ambil data pemesanan
            if (!getPemesananData(txIdPemesanan.getText())) {
                return; // Jika data tidak ditemukan, hentikan proses
            }

            if (txTotalHarga.getText().isEmpty() || txBayar.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Total harga dan jumlah bayar harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double total = Double.parseDouble(txTotalHarga.getText());
            double bayar = Double.parseDouble(txBayar.getText());

            if (bayar < total) {
                JOptionPane.showMessageDialog(this, "Jumlah bayar kurang dari total harga!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double kembalian = bayar - total;
            txKembalian.setText(String.format("%.2f", kembalian));

            // Update status pembayaran
            cbbStatus.setSelectedItem("Sudah Bayar");

            // Save payment to database
            savePaymentToDatabase();

            // Update status pemesanan
            updatePemesananStatus();

            // Refresh tables
            dataTable();
            dataTablePembayaran();

            JOptionPane.showMessageDialog(this, "Pembayaran berhasil untuk " + txNamaPelanggan.getText() + "!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

            // Clear fields after successful payment
            clear();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid untuk total dan jumlah bayar!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBayarActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clear();
    }//GEN-LAST:event_btnResetActionPerformed

    private void tabelPemesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPemesananMouseClicked
        int bar = tabelPemesanan.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
//        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
//        String f = tabmode.getValueAt(bar, 4).toString();
        String g = tabmode.getValueAt(bar, 5).toString();
//        String h = tabmode.getValueAt(bar, 6).toString();
        String i = tabmode.getValueAt(bar, 7).toString();

        txIdPemesanan.setText(a);
        txNamaPelanggan.setText(c);
        txJumlah.setText(d.replace("Rp. ", "").replace(",--", ""));
        txTotalHarga.setText(g.replace("Rp. ", "").replace(",--", ""));
        cbbStatus.setSelectedItem(i);
        cbbMetode.setSelectedIndex(1);
        // Set foreground color to black for these fields
        txTotalHarga.setForeground(Color.BLACK);
        txBayar.setForeground(Color.BLACK);
        txKembalian.setForeground(Color.BLACK);
    }//GEN-LAST:event_tabelPemesananMouseClicked

    private void btnBayarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBayarMouseEntered
        btnBayar.setBackground(new Color(42, 122, 175));
    }//GEN-LAST:event_btnBayarMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        btnBayar.setBackground(new Color(52,152,219));
    }//GEN-LAST:event_formMouseExited

    private void btnResetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseExited
        btnReset.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btnResetMouseExited

    private void btnResetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseEntered
        btnReset.setBackground(new Color(200,200,200));
    }//GEN-LAST:event_btnResetMouseEntered

    private void txNamaPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txNamaPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txNamaPelangganActionPerformed

    private void tabelPembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPembayaranMouseClicked
        int selectedRow = tabelPembayaran.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tabelPembayaran.getModel();

            txIdPembayaran.setText(model.getValueAt(selectedRow, 0).toString());
            txIdPemesanan.setText(model.getValueAt(selectedRow, 1).toString());
            txNamaPelanggan.setText(model.getValueAt(selectedRow, 2).toString());
            txJumlah.setText(model.getValueAt(selectedRow, 3).toString());
    //        txTotalHarga.setText(model.getValueAt(selectedRow, 4).toString());
            cbbMetode.setSelectedItem(model.getValueAt(selectedRow, 5).toString());

            // Set status to "Sudah Bayar" since it's in the payment table
            cbbStatus.setSelectedItem("Sudah Bayar");

            // Update the colors of the text fields
    //        txTotalHarga.setForeground(Color.BLACK);
    //        txBayar.setForeground(Color.BLACK);
    //        txKembalian.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_tabelPembayaranMouseClicked

    private void btnBayarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBayarMouseExited
        btnBayar.setBackground(new Color(52,152,219));
    }//GEN-LAST:event_btnBayarMouseExited

    private void txCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txCariKeyPressed

    private void txCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariKeyTyped
        String keyword = txCari.getText();
        searchDataTable(keyword);
    }//GEN-LAST:event_txCariKeyTyped

    private void searchDataTable(String keyword) {
        Object[] Baris = {"ID Pemesanan","Nama Menu","Nama Pelanggan","Jumlah","Harga","Total Harga","Waktu Pesan","Status"};

        for (int i = 0; i < Baris.length; i++){
            Baris[i] = "<html><font face='Sans Serif' size='5' style='font-weight: bold;'>" + Baris[i] + "</font></html>";
        }

        tabmode = new DefaultTableModel(null, Baris);
        tabelPemesanan.setModel(tabmode);
        String sql = "Select * From pemesanan WHERE idPemesanan LIKE '%" + keyword + "%' OR namaMenu LIKE '%" + keyword + "%'"
                + " OR namaPelanggan LIKE '%" + keyword + "%' OR jumlah LIKE '%" + keyword + "%' OR harga LIKE '%" + keyword + "%'"
                + " OR totalHarga LIKE '%" + keyword + "%'";

        try {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String a = hasil.getString("idPemesanan");
                String b = hasil.getString("namaMenu");
                String c = hasil.getString("namaPelanggan");
                String d = hasil.getString("jumlah");
                double harga = hasil.getDouble("harga");
                String e = formatRupiah(harga);
                double totalHarga = hasil.getDouble("totalHarga");
                String f = formatRupiah(totalHarga);
                String g = hasil.getString("waktuPesan");
                String h = hasil.getString("status");

                String[] data = {a, b, c, d, e, f, g, h};
                tabmode.addRow(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Custom.Button Clear;
    private Custom.Button Delete;
    private Custom.Button Edit;
    private Custom.Button Save;
    private Custom.Button btnBayar;
    private Custom.Button btnReset;
    private javax.swing.JComboBox<String> cbbMetode;
    private javax.swing.JComboBox<String> cbbStatus;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tabelPembayaran;
    private javax.swing.JTable tabelPemesanan;
    private javax.swing.JTextField txBayar;
    private javax.swing.JTextField txCari;
    private javax.swing.JTextField txIdPembayaran;
    private javax.swing.JTextField txIdPemesanan;
    private javax.swing.JTextField txJumlah;
    private javax.swing.JTextField txKembalian;
    private javax.swing.JTextField txNamaPelanggan;
    private javax.swing.JTextField txTotalHarga;
    // End of variables declaration//GEN-END:variables
}
