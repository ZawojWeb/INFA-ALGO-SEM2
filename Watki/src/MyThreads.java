import java.awt.Color;

/**
 * Creates an Threads
 * 
 * @param R    Shade of red.
 * @param G    Shade of green.
 * @param B    Shade of blue.
 * @param ours Metod of callback write metod.
 * @param x    Index in 2D array.
 * @param y    Index in 2D array.
 */
public class MyThreads extends Thread {
    public int R;
    public int G;
    public int B;
    public Our ours;
    public int x;
    public int y;
    public volatile boolean isActive = true;

    MyThreads(Our ours, int ox, int oy, Color color) {
        this.ours = ours;
        this.x = ox;
        this.y = oy;
        this.R = color.getRed();
        this.G = color.getGreen();
        this.B = color.getBlue();

    }

    /**
     * Run chagning colors base on params. if is active change color if not dont do
     * this
     * 
     * 
     */
    public void run() {
        while (true) {
            if (isActive) {
                // System.out.println("Start" + "X: " + x + " Y: " + y);
                synchronized (ours) {
                    ours.write(x, y);
                }
                double time = (MyGridLayout.rand.nextDouble() + MyGridLayout.k);

                try {
                    Thread.sleep((int) time);
                } catch (InterruptedException e) {

                }
            } else {
                // System.out.println("Stop" + "X: " + x + " Y: " + y);
            }

        }
    }

}
