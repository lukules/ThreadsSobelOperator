import java.awt.image.BufferedImage;

public class GrayscaleConverter {
    public static BufferedImage convert(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage grayscaleImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                int rgb = originalImage.getRGB(x, y);

                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                int gray = (int)(0.299*r + 0.587*b + 0.114*b);

                int newRgb = (gray << 16) | (gray << 8) | gray;

                grayscaleImage.setRGB(x, y, newRgb);
            }
        }
        return grayscaleImage;
    }
}
