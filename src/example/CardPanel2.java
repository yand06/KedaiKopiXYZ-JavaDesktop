package example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import koneksi.koneksi;

public class CardPanel2 extends JPanel {
    private final Connection conn = new koneksi().connect();
    private JPanel cardPanel;
    private JPanel cartPanel;
    private JTextArea receiptArea;
    private List<MenuItem> cartItems;

    public CardPanel2() {
        setLayout(new BorderLayout());
        setBackground(new Color(254,216,179));

        cartItems = new ArrayList<>();

        // Card Panel
        cardPanel = new JPanel(new GridLayout(0, 3, 30, 30));
        cardPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        cardPanel.setBackground(new Color(254,216,179));
        JScrollPane cardScrollPane = new JScrollPane(cardPanel);
        cardScrollPane.getViewport().setBackground(Color.WHITE);
        cardScrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(cardScrollPane, BorderLayout.CENTER);

        // Cart Panel
        cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        cartPanel.setBorder(new EmptyBorder(20, 10, 20, 20));
        cartPanel.setBackground(new Color(254,216,179));

        JLabel cartLabel = new JLabel("Keranjang Belanja");
        cartLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        cartLabel.setAlignmentX(CENTER_ALIGNMENT);
        cartPanel.add(cartLabel);

        receiptArea = new JTextArea(10, 50);
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
        receiptArea.setBackground(Color.WHITE);
        receiptArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JScrollPane receiptScrollPane = new JScrollPane(receiptArea);
        receiptScrollPane.setBorder(BorderFactory.createEmptyBorder());
        cartPanel.add(receiptScrollPane);

        add(cartPanel, BorderLayout.EAST);

        loadMenuCards();
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
                byte[] imageData = hasil.getBytes("gambarMenu");

                JPanel card = new JPanel(new BorderLayout());
                card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
                card.setBackground(Color.WHITE);

                ImageIcon icon = new ImageIcon(imageData);
                JLabel labelGambar = new JLabel(icon);
                labelGambar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                card.add(labelGambar, BorderLayout.NORTH);

                JPanel detailPanel = new JPanel(new GridLayout(3, 1, 10, 10));
                detailPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                detailPanel.setBackground(Color.WHITE);
                JPanel menuInfoPanel = new JPanel(new GridLayout(2, 1));
                JLabel labelNama = new JLabel(namaMenu);
                labelNama.setFont(new Font("SansSerif", Font.BOLD, 16));
                JLabel labelHarga = new JLabel("Harga: Rp " + harga);
                labelHarga.setFont(new Font("SansSerif", Font.PLAIN, 14));
                menuInfoPanel.add(labelNama);
                menuInfoPanel.add(labelHarga);

                JSpinner spinnerQuantity = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
                spinnerQuantity.setPreferredSize(new Dimension(100, 30));
                JButton buttonPurchase = new JButton("Beli");
                buttonPurchase.setBackground(new Color(52, 152, 219));
                buttonPurchase.setForeground(Color.WHITE);
                buttonPurchase.setFont(new Font("SansSerif", Font.BOLD, 14));
                buttonPurchase.addActionListener(new PurchaseActionListener(idMenu, namaMenu, harga, spinnerQuantity));

                detailPanel.add(menuInfoPanel);
                detailPanel.add(spinnerQuantity);
                detailPanel.add(buttonPurchase);

                card.add(detailPanel, BorderLayout.CENTER);
                cardPanel.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private class PurchaseActionListener implements ActionListener {
        private String idMenu;
        private String namaMenu;
        private String harga;
        private JSpinner spinnerQuantity;

        public PurchaseActionListener(String idMenu, String namaMenu, String harga, JSpinner spinnerQuantity) {
            this.idMenu = idMenu;
            this.namaMenu = namaMenu;
            this.harga = harga;
            this.spinnerQuantity = spinnerQuantity;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int quantity = (int) spinnerQuantity.getValue();
            double totalHargaDouble = Double.parseDouble(harga) * quantity;
            int totalHarga = (int) Math.round(totalHargaDouble);
            MenuItem item = new MenuItem(idMenu, namaMenu, harga, quantity, totalHarga);
            cartItems.add(item);

            updateReceiptArea();
        }
    }

    private void updateReceiptArea() {
        StringBuilder struk = new StringBuilder();
        int totalPembelian = 0;

        for (MenuItem item : cartItems) {
            struk.append("ID Menu: ").append(item.getIdMenu()).append("\n");
            struk.append("Nama Menu: ").append(item.getNamaMenu()).append("\n");
            struk.append("Harga: Rp ").append(item.getHarga()).append("\n");
            struk.append("Quantity: ").append(item.getQuantity()).append("\n");
            struk.append("Total: Rp ").append(item.getTotalHarga()).append("\n\n");
            totalPembelian += item.getTotalHarga();
        }

        struk.append("Total Pembelian: Rp ").append(totalPembelian).append("\n");
        receiptArea.setText(struk.toString());
    }

    private static class MenuItem {
        private String idMenu;
        private String namaMenu;
        private String harga;
        private int quantity;
        private int totalHarga;

        public MenuItem(String idMenu, String namaMenu, String harga, int quantity, int totalHarga) {
            this.idMenu = idMenu;
            this.namaMenu = namaMenu;
            this.harga = harga;
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

        public int getQuantity() {
            return quantity;
        }

        public int getTotalHarga() {
            return totalHarga;
        }
    }
}