public class SanPham {
    private String maSanPham;
    private String tenSanPham;
    private double donGia;
    private int soLuong;

    public SanPham(String maSanPham, String Tensanpham, double dongia, int soLuong) {
        this.maSanPham = maSanPham;
        this.tenSanPham = Tensanpham;
        this.donGia = dongia;
        this.soLuong = soLuong;
    }

    public double tinhThanhTien() {
        return donGia * soLuong;
    }

    public void nhapHang(int soLuongNhap) {
        if(soLuongNhap > 0) {
            soLuong += soLuongNhap;
        }
    }

    public boolean banHang(int soLuongBan) {
        if (soLuongBan > 0 && soLuongBan <= soLuong) {
            soLuong -=soLuongBan;
            return true;
        }
        return false;
    }

    public void hienThiThongTin() {
        System.out.println("Ma san pham: " + maSanPham);
        System.out.println("Ten san pham: " +tenSanPham );
        System.out.println("Don gia: " + donGia);
        System.out.println("So luong: " + soLuong);
        System.out.println("Thanh tien: " + tinhThanhTien());
    }
}
