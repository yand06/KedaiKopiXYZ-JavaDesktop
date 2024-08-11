package example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/kedaikopi";
    private static final String USER = "root";  // Sesuaikan dengan username MySQL Anda
    private static final String PASSWORD = "";  // Sesuaikan dengan password MySQL Anda

    // Menambahkan Produk (Create)
    public void addProduct(Product product) {
        String sql = "INSERT INTO produk_kopi (nama, harga, detail, gambar) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getNama());
            pstmt.setDouble(2, product.getHarga());
            pstmt.setString(3, product.getDetail());
            pstmt.setString(4, product.getGambar());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Membaca Semua Produk (Read)
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM produk_kopi";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setNama(rs.getString("nama"));
                product.setHarga(rs.getDouble("harga"));
                product.setDetail(rs.getString("detail"));
                product.setGambar(rs.getString("gambar"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Memperbarui Produk (Update)
    public void updateProduct(Product product) {
        String sql = "UPDATE produk_kopi SET nama = ?, harga = ?, detail = ?, gambar = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getNama());
            pstmt.setDouble(2, product.getHarga());
            pstmt.setString(3, product.getDetail());
            pstmt.setString(4, product.getGambar());
            pstmt.setInt(5, product.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menghapus Produk (Delete)
    public void deleteProduct(int id) {
        String sql = "DELETE FROM produk_kopi WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

