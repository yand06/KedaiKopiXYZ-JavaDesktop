package Form;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReportDataBarang extends javax.swing.JPanel {

    private final  Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;  
    
    public ReportDataBarang() {
        initComponents();
        dataTableBarang();
        tabelBarang.setRowHeight(30);
        tabelBarang.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        tabelBarang.setDefaultRenderer(Object.class, new CustomCellRenderer());
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
//            
//            setHorizontalAlignment(SwingConstants.CENTER);
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
                String d = hasil.getString("harga_barang");
                String e = hasil.getString("satuan");
                String f = hasil.getString("kategori_barang");

                String[] data = {a,b,c,d,e,f};
                tabmode.addRow(data);
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    
    public void showReport(Connection connection, String reportPath) {
        try {
            // Load the report design
            JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
            // Compile the report
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            // Create parameters map (if needed)
            Map<String, Object> parameters = new HashMap<String, Object>();
            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

            // Create JasperViewer
            JasperViewer viewer = new JasperViewer(jasperPrint, false);

            // Set the viewer to maximized state
            viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);

            // Alternative: Set the size to screen size manually
            // Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            // viewer.setSize(screenSize.width, screenSize.height);

            // Show the viewer
            viewer.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBarang = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel42 = new javax.swing.JLabel();
        btnPrint = new Custom.Button();

        setBackground(new java.awt.Color(254, 216, 179));

        tabelBarang.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
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
        jScrollPane1.setViewportView(tabelBarang);

        jLabel42.setBackground(new java.awt.Color(98, 48, 0));
        jLabel42.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("L A P O R A N  B A R A N G");

        btnPrint.setBackground(new java.awt.Color(52, 152, 219));
        btnPrint.setForeground(new java.awt.Color(0, 0, 0));
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Print_30px.png"))); // NOI18N
        btnPrint.setText(" Print");
        btnPrint.setFont(new java.awt.Font("Script MT Bold", 1, 26)); // NOI18N
        btnPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPrintMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPrintMouseExited(evt);
            }
        });
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, 1265, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1))
                        .addGap(80, 80, 80)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                .addGap(80, 80, 80))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
//        try{
//            JasperPrint jasperPrint = JasperFillManager.fillReport("src/Laporan/BarangReport.jasper",null,conn);
//            JasperViewer viewer = new JasperViewer(jasperPrint, false);
//            viewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
//            viewer.setVisible(true);
//        }catch (JRException ex){
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        } 

        String reportPath = "src/Laporan/BarangReport.jrxml";
        showReport(conn, reportPath);
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnPrintMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintMouseEntered
        btnPrint.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btnPrintMouseEntered

    private void btnPrintMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintMouseExited
        btnPrint.setBackground(new Color(52,152,219));
    }//GEN-LAST:event_btnPrintMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Custom.Button btnPrint;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tabelBarang;
    // End of variables declaration//GEN-END:variables
}
