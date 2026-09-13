import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteImages {

    public static void saveFile(File path, String tfile, byte[] bfile) {
        try {
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(bfile));
            ImageIO.write(img, tfile, path);

        } catch (IOException ex) {
            Logger.getLogger(WriteImages.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}