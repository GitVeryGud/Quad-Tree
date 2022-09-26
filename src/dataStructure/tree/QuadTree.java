package dataStructure.tree;
import dataStructure.list.LinkedList;

import java.awt.geom.Rectangle2D;

public class QuadTree{
    public static class Node
    {
        int x;
        int y;
        public Node(int x, int y)
        {
            this.x  = x;
            this.y  = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    Rectangle2D boundingBox;
    QuadTree topLeft;
    QuadTree topRight;
    QuadTree bottomLeft;
    QuadTree bottomRight;
    Node node;
    int depth = 0;

    public QuadTree(Rectangle2D box)
    {
        boundingBox = box;
    }

    public void add(Node point)
    {
        // bounding box upper left point coordinates
        double x = boundingBox.getX();
        double y = boundingBox.getY();
        // half of the height and width of the bounding
        double halfWidth = boundingBox.getCenterX() - boundingBox.getX();
        double halfHeight = boundingBox.getCenterY() - boundingBox.getY();

        if (point == null) return;

        // if there isn't any other node on that box the current quadtree node becomes the inserted point
        if (node == null && topLeft == null && topRight == null && bottomLeft == null && bottomRight == null) {
            node = point;
            return;
        }

        // checks where the inserted point is and put it into it's appropriate box
        if (boundingBox.contains(point.x, point.y))
        {
            // topLeft
            if (point.x < boundingBox.getCenterX() && point.y <= boundingBox.getCenterY())
            {
                if (topLeft == null)
                {
                    topLeft = new QuadTree(new Rectangle2D.Double(x, y,halfWidth,halfHeight));
                    topLeft.depth = depth + 1;
                }
                topLeft.add(point);
            }

            //topRight
            else if (point.x >= boundingBox.getCenterX() && point.y <= boundingBox.getCenterY())
            {
                if (topRight == null)
                {
                    topRight = new QuadTree(new Rectangle2D.Double(x + halfWidth, y,halfWidth,halfHeight));
                    topRight.depth = depth + 1;
                }
                topRight.add(point);
            }

            //bottomLeft
            else if (point.x < boundingBox.getCenterX() && point.y > boundingBox.getCenterY())
            {
                if (bottomLeft == null)
                {
                    bottomLeft = new QuadTree(new Rectangle2D.Double(x, y + halfHeight,halfWidth,halfHeight));
                    bottomLeft.depth = depth + 1;
                }
                bottomLeft.add(point);
            }

            //bottomRight
            else if (point.x >= boundingBox.getCenterX() && point.y > boundingBox.getCenterY())
            {
                if (bottomRight == null)
                {
                    bottomRight = new QuadTree(new Rectangle2D.Double(x + halfWidth,y + halfHeight,halfWidth,halfHeight));
                    bottomRight.depth = depth + 1;
                }
                bottomRight.add(point);
            }
        }

        // takes the current node and puts it into it's appropriate box if it has children
        if (node != null)
        {
            Node tempNode = node;
            node = null;
            add(tempNode);
        }
    }

    public boolean collisionTest(int x, int y, int radius)
    {
        Node node = boxCollisionTest(x, y);

        if (node == null) return false;

        if (x >= node.getX() - radius && y >= node.getY() - radius && x < node.getX() + radius && y < node.getY() + radius)
        {
            return true;
        }

        return false;
    }

    // checks if there is a node on the box colliding with the inserted coordinates
    public Node boxCollisionTest(int x, int y)
    {
        if (node != null) return node;

        if (boundingBox.contains(x, y))
        {
            // topLeft
            if (x < boundingBox.getCenterX() && y <= boundingBox.getCenterY() && topLeft != null)
            {
                return topLeft.boxCollisionTest(x, y);
            }

            //topRight
            else if (x >= boundingBox.getCenterX() && y <= boundingBox.getCenterY() && topRight != null)
            {
                return topRight.boxCollisionTest(x, y);
            }

            //bottomLeft
            else if (x < boundingBox.getCenterX() && y > boundingBox.getCenterY() && bottomLeft != null)
            {
                return bottomLeft.boxCollisionTest(x, y);
            }

            //bottomRight
            else if (x >= boundingBox.getCenterX() && y > boundingBox.getCenterY() && bottomRight != null)
            {
                return bottomRight.boxCollisionTest(x, y);
            }
        }

        return null;
    }

    public Rectangle2D getBoundingBox() {
        return boundingBox;
    }

    public LinkedList<Rectangle2D> getAllBoundingBoxes(LinkedList<Rectangle2D> list)
    {
        list.add(getBoundingBox());
        // topLeft
        if (topLeft != null)
        {
            topLeft.getAllBoundingBoxes(list);
        }

        //topRight
        if (topRight != null)
        {
            topRight.getAllBoundingBoxes(list);
        }

        //bottomLeft
        if (bottomLeft != null)
        {
            bottomLeft.getAllBoundingBoxes(list);
        }

        //bottomRight
        if (bottomRight != null)
        {
            bottomRight.getAllBoundingBoxes(list);
        }

        return list;
    }

    public LinkedList<Node> getAllNodes(LinkedList<Node> list)
    {
        if (node != null) list.add(node);

        // topLeft
        if (topLeft != null)
        {
            topLeft.getAllNodes(list);
        }

        //topRight
        if (topRight != null)
        {
            topRight.getAllNodes(list);
        }

        //bottomLeft
        if (bottomLeft != null)
        {
            bottomLeft.getAllNodes(list);
        }

        //bottomRight
        if (bottomRight != null)
        {
            bottomRight.getAllNodes(list);
        }

        return list;
    }
}
