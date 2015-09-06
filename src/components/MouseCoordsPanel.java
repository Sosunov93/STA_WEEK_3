package components;

import classes.Drawings;
import classes.MyPoint;
import classes.SimpleLinkedList;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by Sergey on 07.09.2015.
 */
public class MouseCoordsPanel extends DrawingPanel implements MouseMotionListener {

    public MouseCoordsPanel() {
        super();
        this.addMouseMotionListener(this);
    }

    private MyPoint point = new MyPoint(0,0);
    private SimpleLinkedList<MyPoint> shape = new SimpleLinkedList<>();

    @Override
    protected SimpleLinkedList<SimpleLinkedList<MyPoint>> getShapes() {
        SimpleLinkedList<SimpleLinkedList<MyPoint>> result = new SimpleLinkedList<>();

        shape = Drawings.getCardioid(getCenterX(), getCenterY(), RADIUS, N);
        result.addLast(shape);
        return result;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (point != null){
            point.setLocation(x, y);
        }
        if (Drawings.inside(shape, point)){
            setBackground(Color.GREEN);
        }else{
            setBackground(Color.YELLOW);
        }
    }
}
