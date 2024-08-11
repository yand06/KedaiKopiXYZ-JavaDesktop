package example;

public class Product {
    private int id;
    private String nama;
    private double harga;
    private String detail;
    private String gambar;

    // Constructors, getters, and setters

    public Product() {}

    public Product(int id, String nama, double harga, String detail, String gambar) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.detail = detail;
        this.gambar = gambar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
