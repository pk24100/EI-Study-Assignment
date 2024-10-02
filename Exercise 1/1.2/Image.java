import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

class Image {
    protected String filePath;
    private CompressionStrategy strategy;

    public Image(String filePath, CompressionStrategy strategy) {
        this.filePath = filePath;
        this.strategy = strategy;
    }

    public void compress() {
        strategy.compress(this);
    }

    public BufferedImage getImage() {
        try {
            // Check if the filePath is a URL
            if (filePath.startsWith("http://") || filePath.startsWith("https://")) {
                return ImageIO.read(new URL(filePath));
            } else {
                return ImageIO.read(new File(filePath));
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read input file!", e);
        }
    }
}