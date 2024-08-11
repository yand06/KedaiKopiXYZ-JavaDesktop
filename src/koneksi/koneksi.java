package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {

    public static Connection koneksi;
    public  Connection connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e);
        }
        String url = "jdbc:mysql://localhost/KedaiKopi";
        try{
            koneksi = DriverManager.getConnection(url,"root","");
            System.out.println("Berhasil Koneksi Database");
        }catch (SQLException e){
            System.out.println("Gagal Koneksi Database");
            System.out.println(e);
        }
        return koneksi;
    }
}
