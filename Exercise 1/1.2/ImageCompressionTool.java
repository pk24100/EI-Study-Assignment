import java.util.Scanner;

public class ImageCompressionTool {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Image Compression Tool");
        System.out.println("------------------------");
        System.out.println("1. Compress using JPEG");
        System.out.println("2. Compress using PNG");
        System.out.print("Choose an option: ");

        int option = scan.nextInt();
        scan.nextLine(); 

        if (option!= 1 && option !=2){
            scan.close();
            throw new RuntimeException("Invalid option");
        }

        System.out.print("Enter image file path (Enter 0 for default image):\n ");
        String filePath = scan.nextLine(); 

        if (filePath.equals("0")){
            filePath="https://wallpapercave.com/wp/wp2506819.jpg";
        }

        Image image = new Image(filePath, getCompressionStrategy(option));

        image.compress();

        scan.close();
    }

    private static CompressionStrategy getCompressionStrategy(int option) {
        if (option == 1) {
            return new JPEGCompression();
        } else {
            return new PNGCompression();
        } 
    }
}