package de.unistuttgart.vis.dsass2021.ex05.p1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The class SimpleQuad
 */

public class SimpleQuadTree<T extends QuadTreeElement> extends QuadTree<T> {

    /**
     * a constructor of a SimpleQuadTree with 2 parameters. A bounding box will be
     * computed by the algorithm.
     *
     * @param elements,          a list of elements to be saved in the quadtree.
     * @param maxElementsInLeaf, the maximum limit of elements, in which a leaf node
     *                           can save.
     * @throws IllegalArgumentException, if parameter is missing, or element ==
     *                                   null, or the maxElementsInLeaf < 1.
     */
    public SimpleQuadTree(final List<T> elements, final int maxElementsInLeaf) throws IllegalArgumentException {
        if (elements == null || maxElementsInLeaf < 1) {
            throw new IllegalArgumentException();
        }
        this.boundingBox = computeBoundingBox(elements);
        this.maxLeafElements = maxElementsInLeaf;
        createQuadTree(elements);

    }

    /**
     * a constructor to create a SimpleQuadTree with 3 parameters. The bounding box
     * is predefined.
     *
     * @param elements,          a list of elements to be saved in the quadtree.
     * @param maxElementsInLeaf, the maximum limit of elements, in which a leaf node
     *                           can save.
     * @param boundingBox,       the smallest rectangle, in which all elements can
     *                           be included.
     * @throws IllegalArgumentException, if parameter is missing, or element ==
     *                                   null, or the maxElementsInLeaf < 1.
     */
    private SimpleQuadTree(final List<T> elements, final int maxElementsInLeaf, final Rectangle boundingBox)
            throws IllegalArgumentException {
        if (elements == null || maxElementsInLeaf < 1) {
            throw new IllegalArgumentException();
        }
        this.boundingBox = boundingBox;
        this.maxLeafElements = maxElementsInLeaf;
        createQuadTree(elements);
    }

    /**
     * This returns a bounding box, which is smallest of all possible bounding boxes
     * that include all elements of the list.
     *
     * @param elements, a list of elements to be saved in the quadtree, must not be
     *                  null, must not contain null
     * @return a bounding box in type Rectangle
     */
    private Rectangle computeBoundingBox(final List<T> elements) {
        int xMinRectangle = (int) elements.get(0).getPosition().getXValue();
        int yMinRectangle = (int) elements.get(0).getPosition().getYValue();
        int xMaxRectangle = (int) elements.get(0).getPosition().getXValue();
        int yMaxRectangle = (int) elements.get(0).getPosition().getYValue();
        int width;
        int height;

        for (T element : elements) {
            if (element.getPosition().getXValue() < xMinRectangle) {
                xMinRectangle = (int) element.getPosition().getXValue();
            }
            if (element.getPosition().getYValue() < yMinRectangle) {
                yMinRectangle = (int) element.getPosition().getYValue();
            }
            if (element.getPosition().getXValue() > xMaxRectangle) {
                xMaxRectangle = (int) element.getPosition().getXValue();
            }
            if (element.getPosition().getYValue() > yMaxRectangle) {
                yMaxRectangle = (int) element.getPosition().getYValue();
            }
        }
        width = xMaxRectangle - xMinRectangle;
        height = yMaxRectangle - yMinRectangle;
        return new Rectangle(xMinRectangle, yMinRectangle, width, height);
    }

    /**
     * This creates a quadtree using a recursive algorithm. If the size of the list is
     * smaller than the maximum limit of leaf elements, a split is not necessary.
     * All elements will be saved directly in the current node. Otherwise, a splitting
     * into four child nodes is necessary. A linked list for each subnode will be
     * initialized. The size of the bounding box of each subnode is the half of its
     * width and height. This calls the Rectangle() function. Elements contained
     * in the bounding box will be added into the linked list. With the list of
     * elements and the bounding box as well as the maxLeafElements, it creates a
     * SimpleQuadTree. This again includes the createQuadTree() function, which will
     * be called recursively.
     *
     * @param list, a list of elements in type T
     * @throws IllegalArgumentException, when parameter is missing.
     */
    void createQuadTree(final List<T> list) throws IllegalArgumentException {
        if (list.size() > maxLeafElements) {
            topLeft = createSubTree(list, new Rectangle(this.boundingBox.getX(), this.boundingBox.getY(), this.boundingBox.getWidth() / 2, this.boundingBox.getHeight() / 2));
            bottomLeft = createSubTree(list, new Rectangle(this.boundingBox.getX(), this.boundingBox.getY() + (this.boundingBox.getHeight() / 2), this.boundingBox.getWidth() / 2, this.boundingBox.getHeight() / 2));
            topRight = createSubTree(list, new Rectangle(this.boundingBox.getX() + (this.boundingBox.getWidth() / 2), this.boundingBox.getY(), this.boundingBox.getWidth() / 2, this.boundingBox.getHeight() / 2));
            bottomRight = createSubTree(list, new Rectangle(this.boundingBox.getX() + (this.boundingBox.getWidth() / 2), this.boundingBox.getY() + (this.boundingBox.getHeight() / 2), this.boundingBox.getWidth() / 2, this.boundingBox.getHeight() / 2));
        }
        this.leafElements.addAll(list);
    }

    /**
     * Creates a sub QuadTree with the elements contained in the bounding box.
     * The QuadTree is empty if no elements are contained in the bounding box.
     *
     * @param allElements all elements of the parent node
     *                    (must be != null, must not contain null)
     * @param boundingBox the boundingBox to check if an element should be
     *                    inserted into the sub QuadTree (must be != null)
     * @return the SimpleQuadTree that contains all elements within the bounding box
     */
    private SimpleQuadTree<T> createSubTree(final List<T> allElements, final Rectangle boundingBox) {
        assert allElements != null : "allElements is null";
        assert !allElements.contains(null) : "allElements contains null";
        assert boundingBox != null : "boundingBox is null";

        final List<T> elements = new LinkedList<>();
        for (final T item : allElements) {
            if (boundingBox.containsPoint(item.getPosition())) {
                elements.add(item);
            }
        }
        return new SimpleQuadTree<>(elements, this.maxLeafElements, boundingBox);
    }

    /**
     * This inserts all elements located in the requested range in resultList. It
     * first checks whether the query intersects the whole quadtree. After that, the
     * elements will be directly saved in resultList if this is a leaf node. Else it
     * needs to go down to the subnodes recursively until the leaf node and then get
     * the elements. The resultList only save the element, when it locates inside
     * the requested range and is not already part of the list.
     *
     * @param resultList: List that is used to store the elements that are contained
     *                    in the searching area, must be != null it is allowed that
     *                    this list already contains elements, they are not removed,
     *                    however this also does not make any sense
     * @param query:      the searching area, must be != null
     * @throws IllegalArgumentException if any parameter is invalid
     */
    @Override
    public void rangeQuery(final List<T> resultList, final Rectangle query) {
        if (this.getBoundingBox().intersects(query)) {

        }
    }
}
