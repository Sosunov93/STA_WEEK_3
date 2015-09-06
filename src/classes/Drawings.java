package classes;

import java.awt.geom.Point2D;

/**
 * Created by Sergey on 02.09.2015.
 */
public class Drawings {
    public static SimpleLinkedList<MyPoint> getCircle(double x, double y, double radius, int N) {
        SimpleLinkedList<MyPoint> result = new SimpleLinkedList<>();
        //Using formulas from presentation!
        for (int t = 0; t < N; t++) {
            double angl = 2 * Math.PI * t / N;
            double x1 = x + radius * Math.cos(angl);
            double y1 = y + radius * Math.sin(angl);
            result.addLast(new MyPoint(x1, y1));
        }
        return result;
    }

    public static SimpleLinkedList<MyPoint> getCardioid(double x, double y, double radius, int N) {
        SimpleLinkedList<MyPoint> result = new SimpleLinkedList<>();
        //Using formulas from presentation!
        for (int t = 0; t < N; t++) {
            double angl = 2 * Math.PI * t / N;
            double x1 = x + radius * (2 * Math.cos(angl) - Math.cos(2 * angl));
            double y1 = y + radius * (2 * Math.sin(angl) - Math.sin(2 * angl));
            result.addLast(new MyPoint(x1, y1));
        }
        return result;
    }

    /**
     * Checks if there is an intersection between line ab and cd using Kramer's rule
     *
     * @param a -- beginning point of ab
     * @param b -- ending point of ab
     * @param c -- beginning point of cd
     * @param d -- ending point of cd
     * @return true if there is an intersection between ab and cd and false otherwise
     */
    public static boolean intersects(Point2D a, Point2D b, Point2D c, Point2D d) {

        // We describe the section AB as A+(B-A)*u and CD as C+(D-C)*v
        // then we solve A + (B-A)*u = C + (D-C)*v
        // let's use Kramer's rule to solve the task (Ax = B) were x = (u, v)^T
        // build a matrix for the equation
        double[][] A = new double[2][2];
        A[0][0] = b.getX() - a.getX();
        A[1][0] = b.getY() - a.getY();
        A[0][1] = c.getX() - d.getX();
        A[1][1] = c.getY() - d.getY();
        // calculate determinant
        double det0 = A[0][0] * A[1][1] - A[1][0] * A[0][1];
        // substitute columns and calculate determinants
        double detU = (c.getX() - a.getX()) * A[1][1] - (c.getY() - a.getY()) * A[0][1];
        double detV = A[0][0] * (c.getY() - a.getY()) - A[1][0] * (c.getX() - a.getX());
        // calculate the solution
        // even if det0 == 0 (they are parallel) this will return NaN and comparison will fail -> false
        double u = detU / det0;
        double v = detV / det0;
        return u > 0 && u < 1 && v > 0 && v < 1;
    }

    /**
     * Checks if @param point lies within @param polygon
     *
     * @param polygon your
     * @param point
     * @return
     */
    public static boolean inside(SimpleLinkedList<MyPoint> polygon, MyPoint point) {
        /**
         * Here calculate the number of intersections using function intersects
         */
        int n = polygon.getSize();
        // There must be at least 3 vertices in polygon[]
        if (n < 3) return false;

        // Create a point for line segment from p to infinite
        Point2D extreme = new MyPoint(10000, point.getY());

        // Count intersections of the above line with sides of polygon
        int count = 0, i = 0;
        do {
            int next = (i + 1) % n;
            // Check if the line segment from 'p' to 'extreme' intersects
            // with the line segment from 'polygon[i]' to 'polygon[next]'
            if (intersects(polygon.get(i), polygon.get(next), point, extreme)) {
                count++;
            }
            i = next;
        } while (i != 0);

        // Return true if count is odd, false otherwise
        return count % 2 == 1;
    }

}
