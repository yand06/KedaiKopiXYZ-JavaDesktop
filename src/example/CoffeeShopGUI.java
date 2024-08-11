package example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;

class MainFrame extends JFrame {
    private JPanel panelCards;
    private ProductDAO productDAO;

    public MainFrame() {
        productDAO = new ProductDAO();

        setTitle("Kedai Kopi - Daftar Produk");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel untuk menampilkan produk dalam bentuk card
        panelCards = new JPanel();
        panelCards.setLayout(new GridLayout(0, 3, 15, 15));
        panelCards.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(panelCards);
        scrollPane.setPreferredSize(new Dimension(1800, 800));

        // Tombol untuk menambah produk baru
        JButton btnAddProduct = new JButton("Tambah Produk");
        btnAddProduct.setPreferredSize(new Dimension(150, 30));
        btnAddProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputFrame inputFrame = new InputFrame();
                inputFrame.setVisible(true);
            }
        });

        // Panel untuk tombol tambah produk
        JPanel panelAddButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelAddButton.add(btnAddProduct);

        // Menambahkan komponen ke frame
        setLayout(new BorderLayout(10, 10));
        add(scrollPane, BorderLayout.CENTER);
        add(panelAddButton, BorderLayout.SOUTH);

        // Menampilkan produk pada panel cards saat aplikasi dimulai
        loadProducts();

        setVisible(true);
    }

    private void loadProducts() {
        List<Product> products = productDAO.getAllProducts();
        panelCards.removeAll(); // Menghapus kartu lama
        for (Product product : products) {
            panelCards.add(createProductCard(product));
        }
        panelCards.revalidate();
        panelCards.repaint();
    }

    private JPanel createProductCard(Product product) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(10, 10));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        card.setPreferredSize(new Dimension(250, 300));
        card.setBackground(Color.WHITE);
        card.setOpaque(true);

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBackground(Color.WHITE);

        JLabel lblNama = new JLabel(product.getNama());
        lblNama.setFont(new Font("Arial", Font.BOLD, 16));
        lblNama.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblHarga = new JLabel("Harga: Rp " + product.getHarga());
        lblHarga.setFont(new Font("Arial", Font.PLAIN, 14));
        lblHarga.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblDetail = new JLabel("<html><p style='width: 200px;'>" + product.getDetail() + "</p></html>");
        lblDetail.setFont(new Font("Arial", Font.PLAIN, 12));
        lblDetail.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblGambar = new JLabel();
        lblGambar.setHorizontalAlignment(SwingConstants.CENTER);

        if (product.getGambar() != null && !product.getGambar().isEmpty()) {
            try {
                ImageIcon icon = new ImageIcon(new URL(product.getGambar()));
                Image img = icon.getImage();
                Image scaledImg = img.getScaledInstance(200, 120, Image.SCALE_SMOOTH);
                lblGambar.setIcon(new ImageIcon(scaledImg));
            } catch (Exception e) {
                lblGambar.setText("Gambar tidak tersedia");
                lblGambar.setForeground(Color.RED);
            }
        } else {
            lblGambar.setText("Gambar tidak tersedia");
            lblGambar.setForeground(Color.RED);
        }

        panelInfo.add(lblNama);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 5)));
        panelInfo.add(lblHarga);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 5)));
        panelInfo.add(lblDetail);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 5)));
        panelInfo.add(lblGambar);

        card.add(panelInfo, BorderLayout.CENTER);

        // Menambahkan MouseListener untuk menangani klik pada card
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                InputFrame inputFrame = new InputFrame(product);
                inputFrame.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(Color.WHITE);
            }
        });

        return card;
    }
}

// Frame untuk mengelola input produk
class InputFrame extends JFrame {
    private JTextField txtNama, txtHarga, txtDetail, txtGambar, txtId;
    private ProductDAO productDAO;

    // Constructor untuk menambahkan produk baru
    public InputFrame() {
        this(new Product());
    }

    // Constructor untuk mengedit produk yang sudah ada
    public InputFrame(Product product) {
        productDAO = new ProductDAO();

        setTitle(product.getId() == 0 ? "Tambah Produk Baru" : "Edit Produk");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel input data
        JPanel panelInput = new JPanel(new GridLayout(5, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelInput.add(new JLabel("ID:"));
        txtId = new JTextField(String.valueOf(product.getId()));
//        txtId.setEditable(false);
        panelInput.add(txtId);

        panelInput.add(new JLabel("Nama:"));
        txtNama = new JTextField(product.getNama());
        panelInput.add(txtNama);

        panelInput.add(new JLabel("Harga:"));
        txtHarga = new JTextField(String.valueOf(product.getHarga()));
        panelInput.add(txtHarga);

        panelInput.add(new JLabel("Detail:"));
        txtDetail = new JTextField(product.getDetail());
        panelInput.add(txtDetail);

        panelInput.add(new JLabel("Gambar (URL):"));
        txtGambar = new JTextField(product.getGambar());
        panelInput.add(txtGambar);

        // Panel tombol menggunakan FlowLayout
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelButtons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnSave = new JButton(product.getId() == 0 ? "Tambah" : "Update");
        JButton btnDelete = new JButton("Hapus");
        JButton btnClear = new JButton("Bersihkan");

        btnSave.setPreferredSize(new Dimension(100, 30));
        btnDelete.setPreferredSize(new Dimension(100, 30));
        btnClear.setPreferredSize(new Dimension(100, 30));

        panelButtons.add(btnSave);
        if (product.getId() != 0) { // Tampilkan tombol hapus hanya jika mengedit produk
            panelButtons.add(btnDelete);
        }
        panelButtons.add(btnClear);

        // Menambahkan ActionListener ke tombol
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (product.getId() == 0) {
                    addProduct();
                    
                } else {
                    updateProduct();
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProduct();
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInputFields();
            }
        });

        setLayout(new BorderLayout(10, 10));
        add(panelInput, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addProduct() {
        String nama = txtNama.getText();
        double harga = Double.parseDouble(txtHarga.getText());
        String detail = txtDetail.getText();
        String gambar = txtGambar.getText();

        Product product = new Product(0, nama, harga, detail, gambar);
        productDAO.addProduct(product);
        clearInputFields();
        JOptionPane.showMessageDialog(this, "Produk berhasil ditambahkan!");
        this.dispose();
    }

    private void updateProduct() {
        int id = Integer.parseInt(txtId.getText());
        String nama = txtNama.getText();
        double harga = Double.parseDouble(txtHarga.getText());
        String detail = txtDetail.getText();
        String gambar = txtGambar.getText();

        Product product = new Product(id, nama, harga, detail, gambar);
        productDAO.updateProduct(product);
        clearInputFields();
        JOptionPane.showMessageDialog(this, "Produk berhasil diperbarui!");
        this.dispose();
    }

    private void deleteProduct() {
        int id = Integer.parseInt(txtId.getText());
        productDAO.deleteProduct(id);
        clearInputFields();
        JOptionPane.showMessageDialog(this, "Produk berhasil dihapus!");
        this.dispose();
    }

    private void clearInputFields() {
        txtId.setText("");
        txtNama.setText("");
        txtHarga.setText("");
        txtDetail.setText("");
        txtGambar.setText("");
    }
}

public class CoffeeShopGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}