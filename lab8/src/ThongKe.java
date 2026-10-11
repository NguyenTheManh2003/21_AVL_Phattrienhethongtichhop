
import java.sql.*;

public class ThongKe {
    public static void main(String[] args) {

        String url = "jdbc:sqlite:nhanvien.db";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // Them cot luong neu chua co
            boolean coCotLuong = false;

            try (ResultSet rs = stmt.executeQuery(
                    "PRAGMA table_info(NhanVien)")) {
                while (rs.next()) {
                    if ("luong".equalsIgnoreCase(rs.getString("name"))) {
                        coCotLuong = true;
                    }
                }
            }

            if (!coCotLuong) {
                stmt.executeUpdate(
                        "ALTER TABLE NhanVien ADD COLUMN luong REAL"
                );
            }

            // Cap nhat luong nhan vien
            stmt.executeUpdate(
                    "UPDATE NhanVien SET luong = 20000000 WHERE id = 1"
            );
            stmt.executeUpdate(
                    "UPDATE NhanVien SET luong = 10000000 WHERE id = 2"
            );
            stmt.executeUpdate(
                    "UPDATE NhanVien SET luong = 12000000 WHERE id = 3"
            );

            // Them nhan vien thu 4
            stmt.executeUpdate(
                    "INSERT OR IGNORE INTO NhanVien " +
                            "(id, ten, chuc_vu, luong) VALUES " +
                            "(4, 'Pham Van D', 'Nhan vien', 11000000)"
            );

            // SUM, AVG, MAX, MIN
            String sql = "SELECT " +
                    "SUM(luong) AS tong, " +
                    "AVG(luong) AS trungbinh, " +
                    "MAX(luong) AS caonhat, " +
                    "MIN(luong) AS thapnhat " +
                    "FROM NhanVien";

            try (ResultSet rs = stmt.executeQuery(sql)) {
                if (rs.next()) {
                    System.out.println("THONG KE LUONG:");

                    System.out.println("Tong luong: " +
                            rs.getDouble("tong"));

                    System.out.println("Luong trung binh: " +
                            rs.getDouble("trungbinh"));

                    System.out.println("Luong cao nhat: " +
                            rs.getDouble("caonhat"));

                    System.out.println("Luong thap nhat: " +
                            rs.getDouble("thapnhat"));
                }
            }

            // COUNT va GROUP BY
            String sqlCount =
                    "SELECT chuc_vu, COUNT(*) AS soluong " +
                            "FROM NhanVien GROUP BY chuc_vu";

            try (ResultSet rs = stmt.executeQuery(sqlCount)) {
                System.out.println("\nSO NHAN VIEN THEO CHUC VU:");

                while (rs.next()) {
                    System.out.println(
                            rs.getString("chuc_vu") + ": " +
                                    rs.getInt("soluong")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Loi SQL: " + e.getMessage());
        }
    }
}
