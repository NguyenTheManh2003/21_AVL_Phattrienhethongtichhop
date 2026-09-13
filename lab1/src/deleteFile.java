import java.io.File;

public class deleteFile {

    private void deleteFile(String source) {
        File file = new File(source);

        if (file.exists()) {
            System.out.println("file ton tai");

            if (file.delete()) {
                System.out.println("xoa file thanh cong");
            } else {
                System.out.println("xoa file that bai");
            }

        } else {
            System.out.println("file khong ton tai");
        }
    }

    public static void main(String[] args) {
        deleteFile deleteFileIO = new deleteFile();
        deleteFileIO.deleteFile("D:/HocJava/demo.txt");
    }
}