package dataStructure;

import dataStructure.list.LinkedList;
import dataStructure.tree.QuadTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class Main {

    public static void main(String[] args) {
        // Starting quad tree
        QuadTree quad = new QuadTree(new Rectangle(0,0,400,400));
        quad.add(new QuadTree.Node(20,20));
        quad.add(new QuadTree.Node(230,20));
        quad.add(new QuadTree.Node(20,230));
        quad.add(new QuadTree.Node(140,210));
        quad.add(new QuadTree.Node(140,109));

        createQuadTreeGUI(quad);
    }

    private static void createQuadTreeGUI(QuadTree quadTree) {
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new QuadTreeGUI(quadTree));
        f.pack();
        f.setResizable(false);
        f.setVisible(true);
    }
}

class QuadTreeGUI extends JPanel {

    private int nodeRadius = 3;
    private boolean onNode = false;

    private QuadTree quad;
    private LinkedList<Rectangle2D> boxList;
    private LinkedList<QuadTree.Node> nodeList;
    private QuadTree.Node currentNode;

    public QuadTreeGUI(QuadTree quadTree) {
        quad = quadTree;
        boxList = new LinkedList<>();
        boxList = quad.getAllBoundingBoxes(boxList);
        nodeList = new LinkedList<>();
        nodeList = quad.getAllNodes(nodeList);

        // Checks for mouse clicks
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                addNode(e.getX(),e.getY());
            }
        });

        // Checks for mouse movement
        addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                checkNodeCollision(e.getX(),e.getY());
            }
        });
    }

    private void addNode(int x, int y) {
        QuadTree.Node newNode = new QuadTree.Node(x, y);
        quad.add(newNode);
        boxList = quad.getAllBoundingBoxes(boxList);
        nodeList.add(newNode);
        currentNode = quad.boxCollisionTest(x, y);
        repaint();
    }

    private void checkNodeCollision(int x, int y)
    {
        // check which node, if any, is inside the box containing the mouse
        QuadTree.Node tempNode = quad.boxCollisionTest(x, y);

        // turns red the node inside the box currently containing the mouse;
        if (currentNode != tempNode)
        {
            if (currentNode != null)
            {
                repaint(currentNode.getX() - nodeRadius, currentNode.getY() - nodeRadius, 2 * nodeRadius, 2 * nodeRadius);
            }

            currentNode = tempNode;

            if (currentNode != null)
            {
                repaint(currentNode.getX() - nodeRadius, currentNode.getY() - nodeRadius, 2 * nodeRadius, 2 * nodeRadius);
            }
        }

        // checks if mouse is colliding with the node and turns the node blue if it is and back to red if it isn't
        if (quad.collisionTest(x, y, nodeRadius))
        {
            onNode = true;
            repaint(currentNode.getX() - nodeRadius, currentNode.getY() - nodeRadius, 2 * nodeRadius, 2 * nodeRadius);
        }

        else if (onNode && currentNode != null)
        {
            onNode = false;
            repaint(currentNode.getX() - nodeRadius, currentNode.getY() - nodeRadius, 2 * nodeRadius, 2 * nodeRadius);
        }
    }

    // Sets window size
    public Dimension getPreferredSize() {
        return new Dimension((int) quad.getBoundingBox().getWidth(), (int) quad.getBoundingBox().getHeight());
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draws all existing bounding boxes
        for (Rectangle2D box: boxList)
        {
            g.drawRect((int) box.getX(),(int) box.getY(),(int) box.getWidth(),(int) box.getHeight());
        }

        // Draws all existing nodes and changes their color if necessary
        for (QuadTree.Node node: nodeList)
        {
            if (currentNode == node)
            {
                g.setColor(Color.RED);
                if (onNode) g.setColor(Color.BLUE);
                g.fillOval(node.getX() - nodeRadius, node.getY() - nodeRadius, 2*nodeRadius, 2*nodeRadius);
            }

            g.setColor(Color.BLACK);
            g.drawOval(node.getX() - nodeRadius, node.getY() - nodeRadius, 2*nodeRadius, 2*nodeRadius);
        }
    }
}
