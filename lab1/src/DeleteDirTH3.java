import java.io.File;
import java.io.IOException;

public class DeleteDirTH3 {
    public boolean deleteListFileInfolder(String source) throws IOException {
        File folder = new File(source);
// folder tá»“n táo¡i
        if (folder.exists()) {
// danh sÃ¡ch file
            File[] listFile = folder.listFiles();
            if (listFile.length != 0) {
                for (File f : listFile) {
// náo¿u lÃ file thÃ¬ delete
                    if(f.isFile()){
                        f.delete();
                    }
// new la thu muc thi goi de quy lai
                    if(f.isDirectory()){
                        deleteListFileInfolder(f.getAbsolutePath());
                    }
                }
            }
            folder.delete();
            System.out.println("Delete folder thÃ nh cÃ ́ng!");
            return true;
        } else {
            System.out.println("folder khÃ ́ng tá»“n táo¡i");
            return false;
        }
    }
    public static void main(String[] args) throws IOException {
        DeleteDirTH3 deleteDirTH3 = new DeleteDirTH3();
        deleteDirTH3.deleteListFileInfolder("D:\\HocJava\\TestDeleteDir");
    }
}
