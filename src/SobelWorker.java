import java.awt.image.BufferedImage;

public class SobelWorker implements Runnable{
    private static final int[][] GX = {
            {-1, 0, 1},
            {-2, 0, 2},
            {-1, 0, 1}
    };

    private static final int[][] GY = {
            {-1, -2, -1},
            {0, 0, 0},
            {1, 2, 1}
    };

    private BufferedImage src;
    private BufferedImage dst;
    private int startY;
    private int endY;

    public SobelWorker(BufferedImage src, BufferedImage dst, int startY, int endY) {
        this.src = src;
        this.dst = dst;
        this.startY = startY;
        this.endY = endY;
    }


    @Override
    public void run() {
        int width = src.getWidth();
        int height = src.getHeight();

        int safeStartY = Math.max(1, startY);
        int safeEndY = Math.min(height - 1, endY);

        for(int y = safeStartY; y < safeEndY; y++) {
            for(int x = 1; x < width - 1; x++) {
                int pixelX = 0;
                int pixelY = 0;

                for(int i = -1; i <= 1; i++) {
                    for(int j = -1; j <= 1; j++) {
                        int rgb = src.getRGB(x+j, y+i);

                        int gray = rgb & 0xFF;

                        pixelY += gray*GY[i+1][j+1];
                        pixelX += gray*GX[i+1][j+1];
                    }
                }

                double magnitude = Math.sqrt(Math.pow(pixelX,2) + Math.pow(pixelY, 2));

                int edgeColor = (int) Math.min(255, magnitude);

                int newRgb = (edgeColor << 16) | (edgeColor << 8) | (edgeColor);

                dst.setRGB(x, y, newRgb);



            }
        }
    }
}
