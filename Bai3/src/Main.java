//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        SanPham sp1 = new SanPham("SP01", "Ban phim", 500000, 10);
        SanPham sp2 = new SanPham("SP02", "Chuot", 250000, 20);

        System.out.println("=== BAN DAU ===");
        sp1.hienThiThongTin();

        System.out.println("\n=== NHAP THEM 5 SAN PHAM ===");
        sp1.nhapHang(5);
        sp1.hienThiThongTin();

        System.out.println("\n=== BAN 3 SAN PHAM ===");
        boolean ketQua = sp1.banHang(3);

        System.out.println("Ban hang: " + ketQua);
        sp1.hienThiThongTin();

        System.out.println("\n=== THU BAN 100 SAN PHAM ===");
        ketQua = sp1.banHang(100);

        System.out.println("Ban hang: " + ketQua);
        sp1.hienThiThongTin();

        System.out.println("\n=== SAN PHAM 2 ===");
        sp2.hienThiThongTin();

        // Tạo 2 sinh viên
        SinhVien sv1 = new SinhVien(
                "Nguyen Van An",
                2005,
                "TP.HCM",
                "SV001",
                "Cong nghe thong tin",
                8.7
        );

        SinhVien sv2 = new SinhVien(
                "Tran Thi Binh",
                2004,
                "Dong Nai",
                "SV002",
                "Thiet ke do hoa",
                6.8
        );

        // Tạo 2 giảng viên
        GiangVien gv1 = new GiangVien(
                "Le Van Cuong",
                1985,
                "TP.HCM",
                "GV001",
                "Lap trinh Java",
                5000000,
                3.5
        );

        GiangVien gv2 = new GiangVien(
                "Pham Thi Dung",
                1990,
                "Binh Duong",
                "GV002",
                "Co so du lieu",
                6000000,
                3.2
        );

        // Hiển thị sinh viên
        System.out.println("========== SINH VIEN 1 ==========");
        sv1.hienThiThongTin();
        System.out.println("Xep loai sinh vien: " + sv1.xepLoai());

        System.out.println();

        System.out.println("========== SINH VIEN 2 ==========");
        sv2.hienThiThongTin();
        System.out.println("Xep loai sinh vien: " + sv2.xepLoai());

        System.out.println();

        // Hiển thị giảng viên
        System.out.println("========== GIANG VIEN 1 ==========");
        gv1.hienThiThongTin();
        System.out.println("Luong giang vien: " + gv1.tinhLuong());

        System.out.println();

        System.out.println("========== GIANG VIEN 2 ==========");
        gv2.hienThiThongTin();
        System.out.println("Luong giang vien: " + gv2.tinhLuong());
    }
}
