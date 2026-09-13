import java.io.File;

public class deleteFileInForder {
    public boolean deleteListFileInfolder(String source) {
        File folder = new File(source);
// folder tồn tại
        if (folder.exists()) {
// danh sách file
            File[] listFile = folder.listFiles();
            if (listFile.length != 0) {
                for (File f : listFile) {
// file thì xóa
                    if (f.isFile()) {
                        f.delete();
                    }
                }
            }
            folder.delete();
            System.out.println("Delete folder thành công!");
            return true;
        } else {
            System.out.println("folder không tồn tại");
            return false;
        }
    }
}
