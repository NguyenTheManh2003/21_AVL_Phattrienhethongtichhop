
import java.sql.*;

public class PreparedDemo {
    public static void main(String[] args) {

        String url = "jdbc:sqlite:nhanvien.db";

        try (Connection conn = DriverManager.getConnection(url)) {

            // CREATE: Them san pham
            String sqlInsert =
                    "INSERT OR IGNORE INTO SanPham VALUES (?, ?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(sqlInsert)) {
                ps.setInt(1, 5);
                ps.setString(2, "Tay");
                ps.setDouble(3, 5000);
                ps.setInt(4, 40);

                ps.executeUpdate();
                System.out.println("Them san pham thanh cong!");
            }

            // UPDATE: Sua gia
            String sqlUpdate =
                    "UPDATE SanPham SET gia = ? WHERE maSP = ?";

            try (PreparedStatement ps = conn.prepareStatement(sqlUpdate)) {
                ps.setDouble(1, 6000);
                ps.setInt(2, 5);

                ps.executeUpdate();
                System.out.println("Cap nhat thanh cong!");
            }

            // READ: Tim kiem san pham
            String sqlSelect =
                    "SELECT * FROM SanPham WHERE maSP = ?";

            try (PreparedStatement ps = conn.prepareStatement(sqlSelect)) {
                ps.setInt(1, 5);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        System.out.println(
                                rs.getInt("maSP") + " | " +
                                        rs.getString("tenSP") + " | " +
                                        rs.getDouble("gia") + " | " +
                                        rs.getInt("soluong")
                        );
                    }
                }
            }

            // DELETE: Xoa san pham
            String sqlDelete =
                    "DELETE FROM SanPham WHERE maSP = ?";

            try (PreparedStatement ps = conn.prepareStatement(sqlDelete)) {
                ps.setInt(1, 5);

                ps.executeUpdate();
                System.out.println("Xoa san pham thanh cong!");
            }

        } catch (SQLException e) {
            System.out.println("Loi SQL: " + e.getMessage());
        }
    }
}
