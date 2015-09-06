package components;

import classes.Drawings;
import classes.MyPoint;
import classes.SimpleLinkedList;

/**
 * Created by Sergey on 07.09.2015.
 */
public class CardioPanel extends DrawingPanel {
    @Override
    protected SimpleLinkedList<SimpleLinkedList<MyPoint>> getShapes() {
        SimpleLinkedList<SimpleLinkedList<MyPoint>> result = new SimpleLinkedList<>();

        result.addLast(Drawings.getCardioid(getCenterX(), getCenterY(), RADIUS, N));

        return result;
    }
}
