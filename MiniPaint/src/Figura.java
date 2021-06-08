import java.awt.*;
import java.awt.geom.*;
import java.io.Serializable;

/**
 * This class has figure constructors
 *
 * @param figureActive Checks if the figure is active
 * @param r,b,g        colour variables
 * @param rec          A rectangle specified in double coordinates.
 * @param elip         The Double class defines an ellipse specified in double
 *                     precision.
 * @param trian        The Polygon class encapsulates a description of a closed,
 *                     two-dimensional region within a coordinate space. This
 *                     region is bounded by an arbitrary number of line
 *                     segments, each of which is one side of the polygon.
 *                     Internally, a polygon comprises of a list of (x,y)
 *                     coordinate pairs, where each pair defines a vertex of the
 *                     polygon, and two successive pairs are the endpoints of a
 *                     line that is a side of the polygon. The first and final
 *                     pairs of (x,y) points are joined by a line segment that
 *                     closes the polygon. This Polygon is defined with an
 *                     even-odd winding rule. See WIND_EVEN_ODD for a definition
 *                     of the even-odd winding rule. This class's hit-testing
 *                     methods, which include the contains, intersects and
 *                     inside methods, use the insideness definition described
 *                     in the Shape class comments.
 * @param scala        for scaling figures
 * 
 */

public class Figura implements Serializable {
    public boolean figureActive = false;
    public int r, g, b;
    public Rectangle2D.Double rec;
    public Ellipse2D.Double elip;
    public Polygon trian;
    public double scala = 1;

    /**
     * Construktor for rectangle
     */
    Figura(int ox, int oy, int width, int height, int r, int g, int b) {
        rec = new Rectangle2D.Double();
        rec.setFrame(ox, oy, width, height);
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Construktor for elipse
     */
    Figura(int ox, int oy, int radius, int r, int g, int b) {
        elip = new Ellipse2D.Double();
        elip.setFrame(ox, oy, radius, radius);
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Construktor for triangle
     */
    Figura(int ox1, int oy1, int ox2, int oy2, int ox3, int oy3, int r, int g, int b) {
        trian = new Polygon();
        trian.addPoint(ox1, oy1);
        trian.addPoint(ox2, oy2);
        trian.addPoint(ox3, oy3);
        trian.invalidate();
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Boolean contains(int x, int y) {
        if (rec != null) {
            return rec.contains(x, y);
        } else if (elip != null) {
            return elip.contains(x, y);
        } else {
            return trian.contains(x, y);
        }
    }

}
