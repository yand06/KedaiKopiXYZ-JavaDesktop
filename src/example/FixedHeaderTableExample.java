
package example;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import javax.swing.table.TableCellRenderer;

public class FixedHeaderTableExample {

    private JTable tabelDetailPesan;
    private DefaultTableModel tabmode;

    public FixedHeaderTableExample() {
        // Membuat frame
        JFrame frame = new JFrame("Contoh Tabel dengan Header Tetap Terlihat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Data untuk tabel
        Object[][] data = {
                {1, "Ayam Goreng", 2, 25000, 50000},
                {2, "Nasi Goreng", 3, 30000, 90000},
                {3, "Es Teh Manis", 4, 5000, 20000}
                // Tambahkan lebih banyak baris data jika diperlukan
        };

        // Header untuk tabel
        Object[] baris = {"ID Menuuuuu", "Nama Menuuuuu", "Jumlahhhhh", "Hargaaaaa", "Totalllll"};

        // Mengubah huruf pada baris tabel
        for (int i = 0; i < baris.length; i++) {
            baris[i] = "<html><font face='SansSerif' size='5' style='font-weight: bold;'>" + baris[i] + "</font></html>";
        }

        // Membuat model tabel
        tabmode = new DefaultTableModel(data, baris);

        // Membuat tabel dengan model yang sudah ada
        tabelDetailPesan = new JTable(tabmode);

        // Menyesuaikan lebar kolom agar semua field pada header terlihat
        for (int i = 0; i < tabelDetailPesan.getColumnCount(); i++) {
            TableColumn column = tabelDetailPesan.getColumnModel().getColumn(i);
            int prefWidth = getColumnHeaderWidth(tabelDetailPesan, i);
            column.setPreferredWidth(prefWidth);
        }

        // Menempatkan tabel ke dalam JScrollPane
        JScrollPane scrollPane = new JScrollPane(tabelDetailPesan);

        // Mengatur agar header tetap terlihat
        scrollPane.setColumnHeaderView(tabelDetailPesan.getTableHeader());

        // Menambahkan JScrollPane ke frame
        frame.add(scrollPane, BorderLayout.CENTER);

        // Menampilkan frame
        frame.setVisible(true);
    }

    // Menghitung lebar kolom berdasarkan teks pada header
    private int getColumnHeaderWidth(JTable table, int colIndex) {
        TableColumn column = table.getColumnModel().getColumn(colIndex);
        TableCellRenderer renderer = column.getHeaderRenderer();
        if (renderer == null) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }
        Component comp = renderer.getTableCellRendererComponent(table, column.getHeaderValue(), false, false, 0, 0);
        return comp.getPreferredSize().width;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FixedHeaderTableExample::new);
    }
}
