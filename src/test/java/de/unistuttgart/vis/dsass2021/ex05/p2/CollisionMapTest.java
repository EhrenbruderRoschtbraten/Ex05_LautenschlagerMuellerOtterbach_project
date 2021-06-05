package de.unistuttgart.vis.dsass2021.ex05.p2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import de.unistuttgart.vis.dsass2021.ex05.p1.Rectangle;
import de.unistuttgart.vis.dsass2021.ex05.p2.CollisionMap;

public class CollisionMapTest {
    @Test
    public void testFillMap() throws CollisionMapOutOfBoundsException {
        Rectangle r1 = new Rectangle(5, 5, 10, 10);
        Rectangle r2 = new Rectangle(10, 10, 10, 10);
        Rectangle r3 = new Rectangle(15, 15, 10, 10);
        Rectangle r4 = new Rectangle(20, 20, 10, 10);

        Rectangle r5 = new Rectangle(50, 50, 5, 5);
        Set<Rectangle> setR = new HashSet<>();

        setR.add(r1);
        setR.add(r2);
        setR.add(r3);
        setR.add(r4);

        CollisionMap collisionMap = new CollisionMap(setR);
        assertFalse(collisionMap.collide(r5));

    }
}
