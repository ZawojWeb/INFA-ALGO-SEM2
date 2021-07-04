import java.io.Serializable;
import java.util.ArrayList;
import java.awt.*;

/**
 * @author Kacper Zawojski
 * @param value value which we typed in
 * @param left  left way of tree
 * @param right right way of tree
 */

public class MyTree<T extends Comparable<T>> implements Serializable {
    public T value;
    MyTree<T> left, right;

    /**
     * Adding to the right place
     */
    public void add(T n) {
        if (value == null) {
            value = n;
        } else {
            if (value.compareTo(n) >= 0) {
                if (left == null) {
                    left = new MyTree<T>();
                }
                left.add(n);
            } else {
                if (right == null) {
                    right = new MyTree<T>();
                }
                right.add(n);
            }
        }
    }

    public int length() {
        if (left == null && right == null) {
            return 1;
        }
        if (left == null) {
            return 1 + right.length();
        }
        if (right == null) {
            return 1 + left.length();
        }
        return Math.max(1 + left.length(), 1 + right.length());
    }

    /**
     * Checnikg of existing typed vaule
     */
    public boolean contains(T n) {
        if (value == null) {
            return false;
        }
        if (n.compareTo(value) == 0) {
            return true;
        }
        if (value.compareTo(n) >= 0) {
            if (left == null) {
                return false;
            } else {
                return left.contains(n);
            }
        } else {
            if (right == null) {
                return false;
            } else {
                return right.contains(n);
            }
        }
    }

    /**
     * Drawing a suitable view
     */
    public void view(Graphics2D g2d, int y, double x, int w, int h, double lastx, int lasty) {
        if (value != null) {
            if (left != null) {
                left.view(g2d, y + 1, x - 1 / (Math.pow(2, y)), w, h, x, y);
            }
            g2d.setColor(Color.blue);
            g2d.drawLine((int) (x * w), y * h, (int) (lastx * w), lasty * h);
            g2d.setColor(Color.black);
            g2d.drawString(value.toString(), (int) (x * w), y * h);
            if (right != null) {
                right.view(g2d, y + 1, x + 1 / (Math.pow(2, y)), w, h, x, y);
            }
        }
    }

    public ArrayList<T> values() {
        ArrayList<T> table = new ArrayList<>();
        table.add(value);
        if (left != null) {
            ArrayList<T> leftval = left.values();
            table.addAll(leftval);
        }
        if (right != null) {
            ArrayList<T> rightval = right.values();
            table.addAll(rightval);
        }
        return table;
    }

    /**
     * remove and destroy is using to remove eleement of tree
     */
    public void remove(T i) {
        if (value != null) {
            if (value.compareTo(i) == 0) {
                destroy();
            } else {
                if (value.compareTo(i) > 0) {
                    if (left != null) {
                        left.remove(i);
                        if (left.value == null) {
                            left = null;
                        }
                    }
                } else {
                    if (right != null) {
                        right.remove(i);
                        if (right.value == null) {
                            right = null;
                        }
                    }
                }
            }
        }
    }

    public void destroy() {
        ArrayList<T> vals = new ArrayList<>();
        if (left != null) {
            vals.addAll(left.values());
        }
        if (right != null) {
            vals.addAll(right.values());
        }
        this.value = null;
        this.left = null;
        this.right = null;
        for (T i : vals) {
            add(i);
        }
    }
}
