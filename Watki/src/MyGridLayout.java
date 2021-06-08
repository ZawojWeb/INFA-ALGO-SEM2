import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class MyGridLayout {
    /**
     * Creates an button.
     * 
     * @param width      width of surface.
     * @param height     height of surface.
     * @param rand       random number.
     * @param arrayOfRec arrray of buttons.
     * @param thread     arrya of thread.
     * @param p          propabillity.
     * @param k          rapidity of change.
     * @param f          JFrame.
     * @param x          Index in 2D array.
     * @param y          Index in 2D array.
     */
    JFrame f;
    public static int width;
    public static int height;
    public static Random rand = new Random();
    public static JButton[][] arrayOfRec;
    public static MyThreads[][] thread;
    public static double p; // Prawdopodobienswo
    public static double k; // Sleep time

    // Creat Frame with gird of colors
    MyGridLayout(int w, int h, double k, double p) {
        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.width = w;
        this.height = h;
        this.p = p;
        this.k = k;

        int x = 0;
        int y = 0;

        arrayOfRec = new JButton[width][height];
        for (int i = 1; i <= (width * height); i++) {

            CreatBtn Btn = new CreatBtn(i, x, y);
            f.add(Btn);
            arrayOfRec[x][y] = Btn;
            x++;
            if (x == width) {
                y++;
                x = 0;
            }

        }
        Our ours = new Our();
        thread = new MyThreads[width][height];
        for (int oy = 0; oy < height; oy++) {
            for (int ox = 0; ox < width; ox++) {
                thread[ox][oy] = new MyThreads(ours, ox, oy, arrayOfRec[ox][oy].getBackground());
            }
        }
        for (int oy = 0; oy < height; oy++) {
            for (int ox = 0; ox < width; ox++) {
                thread[ox][oy].start();
            }
        }

        f.setLayout(new GridLayout(width, height));
        f.setSize(1000, 1000);
        f.setVisible(true);
    }

    /**
     * Changes colour based on neighbours
     * 
     * @param rm  middle of shade of red
     * @param gm  middle of shade of green
     * @param bm  middle of shade of blue
     * @param num num of neighbours
     * @return new color
     * 
     */
    public static void change(int x, int y) {

        if (p > rand.nextDouble()) {
            thread[x][y].R = rand.nextInt(255);
            thread[x][y].G = rand.nextInt(255);
            thread[x][y].B = rand.nextInt(255);
            arrayOfRec[x][y].setBackground(new java.awt.Color(thread[x][y].R, thread[x][y].G, thread[x][y].B));

        } else {
            int rm = 0;
            int gm = 0;
            int bm = 0;
            int num = 0;
            // Right
            if (thread[x][y].x == 0) {
                if (thread[width - 1][y].isActive) {
                    rm += thread[width - 1][y].R;
                    gm += thread[width - 1][y].G;
                    bm += thread[width - 1][y].B;
                    num++;
                }
            } else if (thread[x - 1][y].isActive) {
                rm += thread[x - 1][y].R;
                gm += thread[x - 1][y].G;
                bm += thread[x - 1][y].B;
                num++;
            }
            // Left
            if (thread[x][y].x == width - 1) {
                if (thread[0][y].isActive) {
                    rm += thread[0][y].R;
                    gm += thread[0][y].G;
                    bm += thread[0][y].B;
                    num++;
                }
            } else if (thread[x + 1][y].isActive) {
                rm += thread[x + 1][y].R;
                gm += thread[x + 1][y].G;
                bm += thread[x + 1][y].B;
                num++;
            }

            // Down
            if (thread[x][y].y == 0) {
                if (thread[x][height - 1].isActive) {
                    rm += thread[x][height - 1].R;
                    gm += thread[x][height - 1].G;
                    bm += thread[x][height - 1].B;
                    num++;
                }
            } else if (thread[x][y - 1].isActive) {
                rm += thread[x][y - 1].R;
                gm += thread[x][y - 1].G;
                bm += thread[x][y - 1].B;
                num++;
            }
            // Up
            if (thread[x][y].y == height - 1) {
                if (thread[x][0].isActive) {
                    rm += thread[x][0].R;
                    gm += thread[x][0].G;
                    bm += thread[x][0].B;
                    num++;
                }
            } else if (thread[x][y + 1].isActive) {
                rm += thread[x][y + 1].R;
                gm += thread[x][y + 1].G;
                bm += thread[x][y + 1].B;
                num++;
            }

            if (num != 0) {
                thread[x][y].R = rm / num;
                thread[x][y].G = gm / num;
                thread[x][y].B = bm / num;
                arrayOfRec[x][y].setBackground(new java.awt.Color(thread[x][y].R, thread[x][y].G, thread[x][y].B));
            }
        }
    }

}