
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:sqlite:nhanvien.db";

        try {
            Class.forName("org.sqlite.JDBC");

            Connection conn = DriverManager.getConnection(url);
            System.out.println("Ket noi SQLite thanh cong!");

            // B2: Tao Statement
            Statement stmt = conn.createStatement();

            // B3.1: Tao bang NhanVien
            String sql = "CREATE TABLE IF NOT EXISTS NhanVien ("
                    + "id INTEGER PRIMARY KEY, "
                    + "ten TEXT NOT NULL, "
                    + "chuc_vu TEXT)";

            stmt.executeUpdate(sql);
            System.out.println("Tao bang NhanVien thanh cong!");

            // B3.2: Them 3 nhan vien
            stmt.executeUpdate(
                    "INSERT OR IGNORE INTO NhanVien VALUES (1, 'Nguyen Van A', 'Giam doc')"
            );

            stmt.executeUpdate(
                    "INSERT OR IGNORE INTO NhanVien VALUES (2, 'Tran Van B', 'Nhan vien')"
            );

            stmt.executeUpdate(
                    "INSERT OR IGNORE INTO NhanVien VALUES (3, 'Le Thi C', 'Ke toan')"
            );

            System.out.println("Them 3 nhan vien thanh cong!");


        // B4: Truy van danh sach nhan vien
                    String sqlSelect = "SELECT * FROM NhanVien";

                    ResultSet rs = stmt.executeQuery(sqlSelect);

                    System.out.println("\nDANH SACH NHAN VIEN:");

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String ten = rs.getString("ten");
                        String chucVu = rs.getString("chuc_vu");

                        System.out.println(id + " | " + ten + " | " + chucVu);
                    }

        // B5: Dong ResultSet
                    rs.close();


            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
