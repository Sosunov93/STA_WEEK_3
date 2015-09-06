package components;

import classes.MyPoint;
import classes.SimpleLinkedList;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by kamil on 31/08/15.
 * Panel for drawing polygons
 */
public abstract class DrawingPanel extends JPanel {

    protected static int N = 1000;
    protected static int RADIUS = 40;

    protected SimpleLinkedList<SimpleLinkedList<MyPoint>> shapes;

    protected int getCenterY(){
        return this.getHeight() / 2;
    }
    protected int getCenterX(){
        return this.getWidth() / 2;
    }


      /**
     * Constructor for drawing panel. If you wish you may add your own parameters or create new constructor
     */
    public DrawingPanel() {
        super();
        setBackground(Color.YELLOW); // Just to make it more beautiful:)
    }

    /**
     * Function for drawing polygons within drawing panel
     *
     * @param g -- graphic component of drawing panel
     *          You may derive arrays of x and y coordinates and use g2.drawPolygon method
     *          Do not forget to move center points of your figures to the center of the panel
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (shapes == null)
            shapes = getShapes();
        Graphics2D g2 = (Graphics2D) g; //more modern graphic component



        for (int i = 0; i < shapes.getSize(); i++){
            drawComponent(shapes.get(i), g2);
        }

    }

    abstract protected SimpleLinkedList<SimpleLinkedList<MyPoint>> getShapes();

    protected void drawComponent(SimpleLinkedList<MyPoint> shape, Graphics2D g2){
        int[] xs = new int[shape.getSize()]; // stores x coordinates of polygon points
        int[] ys = new int[shape.getSize()]; // stores y coordinates of polygon points
        for (int i = 0; i < shape.getSize(); i++) {
            Point2D p = shape.get(i);
            xs[i] = (int) p.getX();
            ys[i] = (int) p.getY();
        }
        g2.drawPolygon(xs, ys, shape.getSize());
    }




}
