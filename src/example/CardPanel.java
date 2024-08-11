package example;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import koneksi.koneksi;
import javax.swing.JButton;

public class CardPanel extends JPanel {
    private final Connection conn = new koneksi().connect();
    private JPanel cardPanel;

    public CardPanel() {
        setLayout(new BorderLayout());

        cardPanel = new JPanel(new GridLayout(0, 3, 10, 10)); // Mengatur layout grid dengan 3 kolom
        JScrollPane scrollPane = new JScrollPane(cardPanel);
        add(scrollPane, BorderLayout.CENTER);

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
                card.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

                ImageIcon icon = new ImageIcon(imageData);
                JLabel labelGambar = new JLabel(icon);
                card.add(labelGambar, BorderLayout.NORTH);

                JPanel detailPanel = new JPanel(new GridLayout(3, 1));
                JLabel labelNama = new JLabel(namaMenu);
                JLabel labelHarga = new JLabel("Harga: " + harga);
                JSpinner spinnerQuantity = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
                JButton buttonPurchase = new JButton("Purchase");
                detailPanel.add(labelNama);
                detailPanel.add(labelHarga);
                detailPanel.add(spinnerQuantity);
                detailPanel.add(buttonPurchase);

                card.add(detailPanel, BorderLayout.CENTER);
                cardPanel.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
