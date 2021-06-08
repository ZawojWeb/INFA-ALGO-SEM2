import java.util.ArrayList;
import java.awt.*;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.color.*;
import java.awt.event.MouseAdapter;
import java.awt.geom.*;
import java.text.NumberFormat;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;

/**
 * Returns an Image object that can then be painted on the screen. The url
 * argument must specify an absolute <a href="#{@link}">{@link URL}</a>. The
 * name argument is a specifier that is relative to the url argument.
 * <p>
 * This method always returns immediately, whether or not the image exists. When
 * this applet attempts to draw the image on the screen, the data will be
 * loaded. The graphics primitives that draw the image will incrementally paint
 * on the screen.
 *
 * @param listaFigur        array list which have all figures on surface
 * @param rec               A rectangle specified in double coordinates.
 * @param elip              The Double class defines an ellipse specified in
 *                          double precision.
 * @param trian             The Polygon class encapsulates a description of a
 *                          closed, two-dimensional region within a coordinate
 *                          space. This region is bounded by an arbitrary number
 *                          of line segments, each of which is one side of the
 *                          polygon. Internally, a polygon comprises of a list
 *                          of (x,y) coordinate pairs, where each pair defines a
 *                          vertex of the polygon, and two successive pairs are
 *                          the endpoints of a line that is a side of the
 *                          polygon. The first and final pairs of (x,y) points
 *                          are joined by a line segment that closes the
 *                          polygon. This Polygon is defined with an even-odd
 *                          winding rule. See WIND_EVEN_ODD for a definition of
 *                          the even-odd winding rule. This class's hit-testing
 *                          methods, which include the contains, intersects and
 *                          inside methods, use the insideness definition
 *                          described in the Shape class comments.
 * @param scala             for scaling figures
 * @param point1,point2     points for creating triangle
 * @param cretedRectan      metod which creat rectangle
 * @param createElipse      metod which creat rectangle circle
 * @param createTriangle    metod which creat rectangle triangle
 * @param movingFigureThis  metod for move figure
 * @param scalingFigureThis metod for scaling figure
 * @param oldColor          stores the old colour
 * 
 */

public class MyPanel extends JPanel {
    public ArrayList<Figura> listaFigur = new ArrayList<>();
    public Rectangle2D.Double rec;
    public Ellipse2D.Double elip;
    public Polygon trian;
    public Rectangle2D.Double point1, point2;
    public createRec cretedRectan = new createRec();
    public createElip createElipse = new createElip();
    public createTrian createTriangle = new createTrian();
    public movingFigures movingFigureThis = new movingFigures();
    public scalingFigure scalingFigureThis = new scalingFigure();
    private JFrame frame;
    public Color oldColor;

    /**
     * Panel Constructor
     */
    MyPanel(JFrame frame) {
        this.setPreferredSize(new Dimension(1000, 800));
        this.frame = frame;
        addMouseListener(movingFigureThis);
        addMouseMotionListener(movingFigureThis);
        addMouseWheelListener(scalingFigureThis);
        repaint();
    }

    /**
     * Fucnition call fucntion paint.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paint(g);
    }

    /**
     * Fucnition which make figures colorful
     */
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (Figura x : listaFigur) {

            g2d.setColor(new Color(x.r, x.g, x.b));// Bedzie wypełniać tym kolorem
            if (x.rec != null) {
                g2d.fill(x.rec);
            } else if (x.elip != null) {
                g2d.fill(x.elip);
            } else {
                AffineTransform tx1 = new AffineTransform();
                tx1.scale(x.scala, x.scala);
                tx1.translate(
                        ((1 / x.scala) - 1) * ((x.trian.xpoints[0] + x.trian.xpoints[1] + x.trian.xpoints[2]) / 3),
                        (((1 / x.scala) - 1) * (x.trian.ypoints[0] + x.trian.ypoints[1] + x.trian.ypoints[2]) / 3));
                // System.out.println((((1 / x.scala) - 1) * x.trian.xpoints[0]));
                g2d.setTransform(tx1);
                g2d.fill(x.trian);
                // Przywracamy starej transworamcji aby inne figury sie dobrze skalowaly
                AffineTransform tx2 = new AffineTransform();
                tx2.scale(1, 1);
                tx2.translate(0, 0);
                g2d.setTransform(tx2);

            }

            if (x.figureActive == true) {
                g2d.setColor(new Color(0, 0, 0));// Bedzie wypełniać tym kolorem
                g2d.setStroke(new BasicStroke(12f));
                if (x.rec != null) {
                    g2d.draw(x.rec);
                } else if (x.elip != null) {
                    g2d.draw(x.elip);
                } else {
                    AffineTransform tx1 = new AffineTransform();
                    tx1.scale(x.scala, x.scala);
                    tx1.translate(
                            ((1 / x.scala) - 1) * ((x.trian.xpoints[0] + x.trian.xpoints[1] + x.trian.xpoints[2]) / 3),
                            (((1 / x.scala) - 1) * (x.trian.ypoints[0] + x.trian.ypoints[1] + x.trian.ypoints[2]) / 3));
                    // System.out.println((((1 / x.scala) - 1) * x.trian.xpoints[0]));
                    g2d.setTransform(tx1);
                    g2d.draw(x.trian);
                    // Przywracamy starej transworamcji aby inne figury sie dobrze skalowaly
                    AffineTransform tx2 = new AffineTransform();
                    tx2.scale(1, 1);
                    tx2.translate(0, 0);
                    g2d.setTransform(tx2);
                }
                // repaint();
            }
        }

        if (rec != null) {
            g2d.setColor(new Color(0, 0, 200));// Bedzie wypełniać tym kolorem
            g2d.fill(rec);
        }
        if (elip != null) {
            g2d.setColor(new Color(0, 200, 0));// Bedzie wypełniać tym kolorem
            g2d.fill(elip);
        }
        if (point1 != null) {
            g2d.setColor(new Color(200, 0, 0));// Bedzie wypełniać tym kolorem
            g2d.fill(point1);
        }
        if (point2 != null) {
            g2d.setColor(new Color(200, 0, 0));// Bedzie wypełniać tym kolorem
            g2d.fill(point2);
        }
    }

    /**
     * Class which creat Rectangle
     */

    public class createRec extends MouseAdapter {
        int OX1, OY1, OX2, OY2;

        /**
         * Call when mouse is pressed on rectangle
         */
        @Override
        public void mousePressed(MouseEvent e) {
            OX1 = e.getX();
            OY1 = e.getY();
        }

        /**
         * Call when you drug rectangle
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            OX2 = e.getX();
            OY2 = e.getY();
            rec = new Rectangle2D.Double(Math.min(OX1, OX2), Math.min(OY1, OY2), Math.abs(OX1 - OX2),
                    Math.abs(OY1 - OY2));
            newKaltka();
        }

        /**
         * Call when you mouse released
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            listaFigur.add(new Figura(Math.min(OX1, OX2), Math.min(OY1, OY2), Math.abs(OX1 - OX2), Math.abs(OY1 - OY2),
                    0, 0, 200));
            rec = null;
            removeMouseListener(this);
            removeMouseMotionListener(this);
            addMouseListener(movingFigureThis);
            addMouseMotionListener(movingFigureThis);

            newKaltka();

        }

    }

    /**
     * Class which creat cricle
     */

    public class createElip extends MouseAdapter {
        int OX1, OY1, OX2, OY2, r;

        /**
         * Call when mouse is pressed on cricle
         */
        @Override
        public void mousePressed(MouseEvent e) {
            OX1 = e.getX();
            OY1 = e.getY();
        }

        /**
         * Call when you drug cricle
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            OX2 = e.getX();
            OY2 = e.getY();
            r = (int) Math.sqrt((OX1 - OX2) * (OX1 - OX2) + (OY1 - OY2) * (OY1 - OY2));
            elip = new Ellipse2D.Double(OX1 - r, OY1 - r, 2 * r, 2 * r);

            newKaltka();
        }

        /**
         * Call when you mouse released
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            listaFigur.add(new Figura((OX1 - r), (OY1 - r), (2 * r), 0, 200, 0));
            elip = null;
            removeMouseListener(this);
            removeMouseMotionListener(this);
            addMouseListener(movingFigureThis);
            addMouseMotionListener(movingFigureThis);

            newKaltka();
        }

    }

    /**
     * Class which creat triangle
     */

    public class createTrian extends MouseAdapter {
        int OX1, OY1, OX2, OY2, OX3, OY3;
        int isClicked = 0;

        /**
         * function making polygone points
         */
        @Override
        public void mousePressed(MouseEvent e) {
            if (isClicked == 0) {
                OX1 = e.getX();
                OY1 = e.getY();
                point1 = new Rectangle2D.Double(OX1 - 5, OY1 - 5, 10, 10);
                isClicked++;
                newKaltka();

            } else if (isClicked == 1) {
                OX2 = e.getX();
                OY2 = e.getY();
                point2 = new Rectangle2D.Double(OX2 - 5, OY2 - 5, 10, 10);
                isClicked++;
                newKaltka();

            } else {
                OX3 = e.getX();
                OY3 = e.getY();
                listaFigur.add(new Figura(OX1, OY1, OX2, OY2, OX3, OY3, 200, 0, 0));
                removeMouseListener(this);
                removeMouseMotionListener(this);
                isClicked = 0;
                point1 = null;
                point2 = null;
                addMouseListener(movingFigureThis);
                addMouseMotionListener(movingFigureThis);

                newKaltka();

            }

        }

    }

    /**
     * Class responsible for moving figures
     */
    public class movingFigures extends MouseAdapter {
        int OX1, OY1;
        boolean checkIsActive;

        /**
         * Call when mouse is pressed on cricle
         */
        @Override
        public void mousePressed(MouseEvent e) {
            OX1 = e.getX();
            OY1 = e.getY();
            for (Figura x : listaFigur) {
                if (x.contains(OX1, OY1)) {
                    x.figureActive = true;
                    checkIsActive = true;
                    repaint();
                    newKaltka();

                } else {
                    x.figureActive = false;
                    repaint();
                    newKaltka();
                }
            }
            if (SwingUtilities.isRightMouseButton(e) && checkIsActive) { // Zmiana koloru
                changeColor();

            }
            checkIsActive = false;
        }

        /**
         * Call when mouse is draged
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            int dx = e.getX() - OX1;
            int dy = e.getY() - OY1;
            for (Figura x : listaFigur) {
                if (x.figureActive) {
                    if (x.rec != null) {
                        x.rec.x += dx;
                        x.rec.y += dy;
                    } else if (x.elip != null) {
                        x.elip.x += dx;
                        x.elip.y += dy;
                    } else {
                        x.trian.translate(dx, dy);
                    }
                }
            }
            OX1 += dx;
            OY1 += dy;
            newKaltka();
        }

        // @Override
        // public void mouseReleased(MouseEvent e) {
        // listaFigur.add(new Figura((OX1 - r), (OY1 - r), (2 * r), 0, 200, 0));
        // elip = null;
        // removeMouseListener(this);
        // removeMouseMotionListener(this);
        // }
    }

    /**
     * Class responsible for scaling figures
     */

    public class scalingFigure extends MouseAdapter {
        /**
         * Call when mouse is wheel moved
         */
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            int WheelRotation = e.getWheelRotation() * 5;
            for (Figura x : listaFigur) {
                if (x.figureActive) {
                    if (x.rec != null) {
                        x.rec.height += (x.rec.height / x.rec.width) * WheelRotation;
                        x.rec.width += WheelRotation;
                        x.rec.x -= WheelRotation / 2;
                        x.rec.y -= (x.rec.height / x.rec.width) * WheelRotation / 2;
                    } else if (x.elip != null) {
                        x.elip.width += WheelRotation;
                        x.elip.height += WheelRotation;
                        x.elip.x -= WheelRotation / 2;
                        x.elip.y -= WheelRotation / 2;
                    } else {
                        x.scala += WheelRotation / 50.0;
                    }
                }
            }
            repaint();
            newKaltka();
        }
    }

    /**
     * Call when you want to change color
     */
    public void changeColor() {

        try {
            Color newColor = JColorChooser.showDialog(this, "Change Color", oldColor);
            if (newColor != null) {
                oldColor = newColor;
                for (Figura x : listaFigur) {
                    if (x.figureActive) {
                        x.r = newColor.getRed();
                        x.g = newColor.getGreen();
                        x.b = newColor.getBlue();
                    }
                }
                repaint();
                newKaltka();
            }
        } catch (NumberFormatException ignored) {
        }
        ;
    }

    /**
     * function responsible for deleting listeners
     */
    public void deleteListener() {
        MouseListener[] arrayMouseL = getMouseListeners();
        MouseMotionListener[] arrayMouseMotions = getMouseMotionListeners();
        for (MouseListener x : arrayMouseL) {
            removeMouseListener(x);
        }
        for (MouseMotionListener x : arrayMouseMotions) {
            removeMouseMotionListener(x);
        }
        frame.repaint();

    }

    /**
     * function refresh frame
     */
    public void newKaltka() {
        frame.repaint();
    }

}
