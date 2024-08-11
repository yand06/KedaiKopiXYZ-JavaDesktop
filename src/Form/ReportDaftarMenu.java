package Form;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReportDaftarMenu extends javax.swing.JPanel {

    private final  Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;  
    
    public ReportDaftarMenu() {
        initComponents();
        dataTableMenu();
        tabelMenu.setRowHeight(150);
        setTableRenderer();
        tabelMenu.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        tabelMenu.setDefaultRenderer(Object.class, new CustomCellRenderer());
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
                String c = hasil.getString("harga");
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
        tabelMenu = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel42 = new javax.swing.JLabel();
        btnPrint = new Custom.Button();

        setBackground(new java.awt.Color(254, 216, 179));

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
        jScrollPane1.setViewportView(tabelMenu);

        jLabel42.setBackground(new java.awt.Color(98, 48, 0));
        jLabel42.setFont(new java.awt.Font("Segoe UI Black", 1, 50)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("L A P O R A N  M E N U");

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
        String reportPath = "src/Laporan/MenuReport.jrxml";
        showReport(conn, reportPath);
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnPrintMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintMouseExited
        btnPrint.setBackground(new Color(52,152,219));
    }//GEN-LAST:event_btnPrintMouseExited

    private void btnPrintMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintMouseEntered
        btnPrint.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btnPrintMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Custom.Button btnPrint;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tabelMenu;
    // End of variables declaration//GEN-END:variables
}
