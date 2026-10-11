
import java.sql.*;

public class SanPham {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:nhanvien.db";

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();

            // Tao bang SanPham
            String sql = "CREATE TABLE IF NOT EXISTS SanPham ("
                    + "maSP INTEGER PRIMARY KEY, "
                    + "tenSP TEXT NOT NULL, "
                    + "gia REAL, "
                    + "soluong INTEGER)";

            stmt.executeUpdate(sql);
            System.out.println("Tao bang SanPham thanh cong!");

            // Them 3 san pham
            stmt.executeUpdate(
                    "INSERT OR IGNORE INTO SanPham VALUES (1, 'But bi', 5000, 100)"
            );

            stmt.executeUpdate(
                    "INSERT OR IGNORE INTO SanPham VALUES (2, 'Vo hoc sinh', 12000, 50)"
            );

            stmt.executeUpdate(
                    "INSERT OR IGNORE INTO SanPham VALUES (3, 'Thuoc ke', 8000, 30)"
            );

            System.out.println("Them san pham thanh cong!");


            // UPDATE: Sua gia san pham co maSP = 1
            String sqlUpdate = "UPDATE SanPham SET gia = 7000 WHERE maSP = 1";

            int soDong = stmt.executeUpdate(sqlUpdate);

            System.out.println("Da cap nhat " + soDong + " san pham!");


            // DELETE: Xoa san pham co maSP = 3
            String sqlDelete = "DELETE FROM SanPham WHERE maSP = 3";

            int soDongXoa = stmt.executeUpdate(sqlDelete);

            System.out.println("Da xoa " + soDongXoa + " san pham!");


            // Hien thi danh sach
            ResultSet rs = stmt.executeQuery("SELECT * FROM SanPham");

            System.out.println("\nDANH SACH SAN PHAM:");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("maSP") + " | "
                                + rs.getString("tenSP") + " | "
                                + rs.getDouble("gia") + " | "
                                + rs.getInt("soluong")
                );
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
