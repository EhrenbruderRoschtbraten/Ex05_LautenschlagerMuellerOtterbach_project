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
        Point p2 = new Point(1,30);
        Point p3 = new Point(30,1);
        Point p4 = new Point(30,30);
        Point p5 = new Point(32,32);


        List<Point> elementList = new ArrayList<>();

        elementList.add(p1);
        elementList.add(p2);
        elementList.add(p3);
        elementList.add(p4);
        elementList.add(p5);


        SimpleQuadTree quadTree = new SimpleQuadTree(elementList,1);
        System.out.println(quadTree.bottomRight.leafElements.size());


    }
}
