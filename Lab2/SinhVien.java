public class SinhVien extends Nguoi {

    private String maSinhVien;
    private String nganhHoc;
    private double diemTrungBinh;

    // Constructor
    public SinhVien(String hoTen, int namSinh, String diaChi,
                    String maSinhVien, String nganhHoc, double diemTrungBinh) {

        super(hoTen, namSinh, diaChi);

        this.maSinhVien = maSinhVien;
        this.nganhHoc = nganhHoc;
        this.diemTrungBinh = diemTrungBinh;
    }

    // Getter
    public String getMaSinhVien() {
        return maSinhVien;
    }

    public String getNganhHoc() {
        return nganhHoc;
    }

    public double getDiemTrungBinh() {
        return diemTrungBinh;
    }

    // Setter
    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public void setNganhHoc(String nganhHoc) {
        this.nganhHoc = nganhHoc;
    }

    public void setDiemTrungBinh(double diemTrungBinh) {
        this.diemTrungBinh = diemTrungBinh;
    }

    // Xếp loại sinh viên
    public String xepLoai() {
        if (diemTrungBinh >= 8.5) {
            return "Gioi";
        } else if (diemTrungBinh >= 7.0) {
            return "Kha";
        } else if (diemTrungBinh >= 5.0) {
            return "Trung binh";
        } else {
            return "Yeu";
        }
    }

    // Ghi đè phương thức hiển thị
    @Override
    public void hienThiThongTin() {
        super.hienThiThongTin();

        System.out.println("Ma sinh vien: " + maSinhVien);
        System.out.println("Nganh hoc: " + nganhHoc);
        System.out.println("Diem trung binh: " + diemTrungBinh);
        System.out.println("Xep loai: " + xepLoai());
    }
}