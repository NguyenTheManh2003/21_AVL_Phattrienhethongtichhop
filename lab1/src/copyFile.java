import java.io.*;

public class copyFile {
    public boolean copyFile(String source, String dest) throws FileNotFoundException, IOException {
// file nguồn
        File sourceFile = new File(source);
// file đích
        File destFile = new File(dest);
// kiem tra file nguồn có tồn tại không
        if (sourceFile.exists()) {
// luồng đọc file
            FileInputStream fis = new FileInputStream(sourceFile);
// luồng ghi file
            FileOutputStream fos = new FileOutputStream(destFile);
            byte[] arr = new byte[1024];
            while ((fis.read(arr)) != -1) {
                fos.write(arr);
                fos.flush();
            }
            fis.close();
            fos.close();
            System.out.println("copy thành công");
            return true;
        } else {
            System.out.println("file nguồn không tồn tại");
            return false;
        }
    }
}
