
import java.sql.*;

public class ExceptionDemo {
    public static void main(String[] args) {

        String url = "jdbc:sqlite:nhanvien.db";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Ket noi database
            conn = DriverManager.getConnection(url);
            System.out.println("Ket noi thanh cong!");

            // Tao Statement
            stmt = conn.createStatement();

            // Them san pham
            stmt.executeUpdate(
                    "INSERT OR IGNORE INTO SanPham VALUES (4, 'But chi', 3000, 50)"
            );

            // Cap nhat san pham
            stmt.executeUpdate(
                    "UPDATE SanPham SET gia = 4000 WHERE maSP = 4"
            );

            // Hien thi danh sach
//            rs = stmt.executeQuery("SELECT * FROM SanPham");


            // DELETE: Xoa san pham co maSP = 4
            int soDongXoa = stmt.executeUpdate(
                    "DELETE FROM SanPham WHERE maSP = 4"
            );

            System.out.println("So san pham da xoa: " + soDongXoa);


            rs = stmt.executeQuery("SELECT * FROM BangKhongTonTai");

            System.out.println("DANH SACH SAN PHAM:");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("maSP") + " | "
                                + rs.getString("tenSP") + " | "
                                + rs.getDouble("gia") + " | "
                                + rs.getInt("soluong")
                );
            }

        } catch (SQLException e) {
            System.out.println("Co loi SQL: " + e.getMessage());

        } finally {
            // Dong ResultSet
            try {
                if (rs != null) {
                    rs.close();
                    System.out.println("Da dong ResultSet");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Dong Statement
            try {
                if (stmt != null) {
                    stmt.close();
                    System.out.println("Da dong Statement");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Dong Connection
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Da dong Connection");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
