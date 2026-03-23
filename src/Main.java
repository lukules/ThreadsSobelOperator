import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String inputImagePath = "input.jpg";
        String outputImagePath = "output.png";

        try {
            System.out.println("Loading image...");
            BufferedImage sourceImage = ImageIO.read(new File(inputImagePath));

            System.out.println("Converting to grayscale...");

            BufferedImage grayImage = GrayscaleConverter.convert(sourceImage);

            int width = grayImage.getWidth();
            int height = grayImage.getHeight();

            BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            System.out.println("Available threads: " + Runtime.getRuntime().availableProcessors());
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter number of working threads: ");
            int numberOfThreads = scanner.nextInt();
            System.out.println("Employing " + numberOfThreads + " worker threads");
            scanner.close();

            Thread[] threads = new Thread[numberOfThreads];

            int chunkHeight = height / numberOfThreads;


            long startTime = System.currentTimeMillis();

            for (int i = 0; i < numberOfThreads; i++) {
                int startY = i * chunkHeight;
                int endY = (i == numberOfThreads - 1) ? height : (i + 1) * chunkHeight;

                SobelWorker worker = new SobelWorker(grayImage, resultImage, startY, endY);

                threads[i] = new Thread(worker);
                threads[i].start();
            }

            for (Thread t : threads) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    System.out.println("Thread waiting was interrupted!");
                }
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Processing completed in: " + (endTime - startTime) + " milliseconds.");

            System.out.println("Saving image...");
            ImageIO.write(resultImage, "png", new File(outputImagePath));
            System.out.println("Done! outputImagePath = " + outputImagePath);



        } catch (IOException e) {
            System.err.println("File error!");
            e.printStackTrace();
        }
    }
}