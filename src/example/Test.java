package example;

import java.awt.BorderLayout;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Test extends JFrame {
    
    private final Connection conn;

    public Test() {
        conn = new koneksi().connect(); // Pastikan koneksi diinisialisasi di konstruktor
        showReport();
    }
    
    private void showReport() {
        try {
            String namaFile = "src/Report/MenuReport.jasper";
            HashMap<String, Object> parameter = new HashMap<>();
            File reportFile = new File(namaFile);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, conn);

            // Membuat frame baru
            JFrame frame = new JFrame("Laporan Pasien");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Menampilkan laporan dalam tampilan maksimal
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            frame.getContentPane().add(viewer.getContentPane(), BorderLayout.CENTER);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);

            JasperViewer.setDefaultLookAndFeelDecorated(true);        
        } catch (JRException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error menampilkan laporan: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Menjalankan aplikasi
        javax.swing.SwingUtilities.invokeLater(() -> {
            Test test = new Test();
            test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            test.setSize(800, 600);
            test.setVisible(true);
        });
    }
}
