package de.unistuttgart.vis.dsass2021.ex05.p1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.unistuttgart.vis.dsass2021.ex05.p1.Point;
import de.unistuttgart.vis.dsass2021.ex05.p1.QuadTree;
import de.unistuttgart.vis.dsass2021.ex05.p1.QuadTreeElement;
import de.unistuttgart.vis.dsass2021.ex05.p1.Rectangle;
import de.unistuttgart.vis.dsass2021.ex05.p1.SimpleQuadTree;


public class SimpleQuadTreeTest {
	@Test
    public void boundingboxTest(){

        Point p1 = new Point(1,1);
        Point p2 = new Point(3,4);
        Point p3 = new Point(4,3);
        Point p4 = new Point(67,3);
        Point p5 = new Point(2,4);
        Point p6 = new Point(6,4);
        Point p7 = new Point(3,2);
        Point p8 = new Point(21,2);
        Point p9 = new Point(23,45);
        Point p10 = new Point(29,29);

        List<Point> elementList = new ArrayList<>();

        elementList.add(p1);
        elementList.add(p2);
        elementList.add(p3);
        elementList.add(p4);
        elementList.add(p5);
        elementList.add(p6);
        elementList.add(p7);
        elementList.add(p8);
        elementList.add(p9);
        elementList.add(p10);

        SimpleQuadTree quadTree = new SimpleQuadTree(elementList,10);
    }
}
