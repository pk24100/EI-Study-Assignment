import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

class JPEGCompression implements CompressionStrategy {
    private static final Random random = new Random();

    @Override
    public void compress(Image image) {
        BufferedImage bufferedImage = image.getImage();
        String outputDirPath = "compressed_images"; // Directory to save compressed images
        File outputDir = new File(outputDirPath);

        // Create the directory if it doesn't exist
        if (!outputDir.exists()) {
            outputDir.mkdir();
        }

        // Generate a random number for the filename
        int randomNumber = random.nextInt(10000); // Adjust the range as needed
        File compressedImageFile = new File(outputDirPath + File.separator + "compressed_" + randomNumber + ".jpeg");

        try {
            ImageIO.write(bufferedImage, "jpg", compressedImageFile);
            System.out.println("Compressed image saved as: " + compressedImageFile.getPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
