/**
 * @author Kacper Zawojski
 * @param args stuff what you input in terminal
 * @return creatin a frame
 */

public class App {
    public static void main(String[] args) {

        new MyGridLayout(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Double.parseDouble(args[2]),
                Double.parseDouble(args[3]));

    }
}