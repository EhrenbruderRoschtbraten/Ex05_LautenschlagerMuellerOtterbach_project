package de.unistuttgart.vis.dsass2021.ex05.p1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import de.unistuttgart.vis.dsass2021.ex05.p1.Point;
import de.unistuttgart.vis.dsass2021.ex05.p1.Rectangle;

public class RectangleTest {
	@Test
	public void testExcludeAllPoints() {
		Rectangle r1 = new Rectangle(100, 100, 100, 100);
		ArrayList<Point> pointlist = new ArrayList<>();
		Point pointOne = new Point(180, 150);
		Point pointTwo = new Point(150, 180);
		Point pointThree = new Point(101, 150);
		Point pointFour = new Point(101, 149);

		pointlist.add(pointOne);
		pointlist.add(pointTwo);
		// pointlist.add(pointThree);
		// pointlist.add(pointFour);
		assertEquals(79, (int) r1.excludePoints(pointlist).getWidth());
		assertEquals(79, (int) r1.excludePoints(pointlist).getHeight());
	}
}
