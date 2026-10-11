
import java.sql.*;

public class TransactionDemo {
    public static void main(String[] args) {

        String url = "jdbc:sqlite:nhanvien.db";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);

            // Tat che do tu dong luu
            conn.setAutoCommit(false);

            Statement stmt = conn.createStatement();


            // Cap nhat gia san pham 1
            stmt.executeUpdate(
                    "UPDATE SanPham SET gia = 10000 WHERE maSP = 1"
            );

            // Cap nhat gia san pham 2
            stmt.executeUpdate(
                    "UPDATE SanPham SET gia = 15000 WHERE maSP = 2"
            );

            // Luu giao dich
            conn.commit();

            System.out.println("Giao dich thanh cong!");
;

            System.out.println("Giao dich thanh cong!");
            stmt.close();

        } catch (SQLException e) {

            System.out.println("Giao dich that bai!");

            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Da rollback!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            e.printStackTrace();

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
