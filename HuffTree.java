
/**
 * HuffInternalNode builds an internal hufftree node implements the huff base
 * node interface
 *
 * @author Mitchell Mesecher mesechmg@dukes.jmu.edu
 *
 *         HONOR CODE: This work complies with the JMU Honor Code. References
 *         and Acknowledgments: I received no outside help with this programming
 *         assignment.
 * 
 *         Open DSA huff tree
 */
public class HuffTree implements Comparable {

    private HuffBaseNode root;

    /**
     * Constructs a lone huff tree
     *
     * @param el
     *            the element of the tree
     * @param wt
     *            the weight of the tree
     */
    HuffTree(byte el, int wt) {
        root = new HuffLeafNode(el, wt);

    }

    /**
     * Constructs a new huff tree combined from two passed in huff trees
     *
     * @param l
     *            the left huff tree
     * @param r
     *            the right huff tree
     * @param wt
     *            the weight of the tree
     */
    HuffTree(HuffBaseNode l, HuffBaseNode r, int wt) {
        root = new HuffInternalNode(l, r, wt);

    }

    /**
     * gets the root node of the tree
     *
     * @return the root
     */
    HuffBaseNode root() {
        return root;
    }

    /**
     * gets the tree weight
     *
     * @return the weight
     */
    public int weight() {
        return root.getWeight();

    }

    /**
     * Compares two trees and returns 1 if this tree has a higher weight and -1
     * if the other does
     *
     * @param the
     *            tree to compare
     */
    @Override
    public int compareTo(Object t) {

        HuffTree that = (HuffTree) t;
        if (root.getWeight() < that.weight()) {
            return -1;

        } else if (root.getWeight() == that.weight()) {

            int min1 = findMin(this.root(), 0);
            int min2 = findMin(that.root(), 0);
            if (min1 > min2) {

                return 1;
            } else {

                return -1;
            }

        } else {

            return 1;
        }

    }

    /**
     * helper method for finding the min byte value in a tree
     *
     * @param node
     *            the root node to start at
     * @param min
     *            the min byte
     * 
     * @return the min byte
     */
    private int findMin(HuffBaseNode node, int min) {
        if (node.isLeaf()) {
            return ((HuffLeafNode) node).getElement();

        } else {
            if (findMin((((HuffInternalNode) node).getLeft()), min) < (findMin(
                    (((HuffInternalNode) node).getRight()), min))) {
                return findMin((((HuffInternalNode) node).getLeft()), min);

            } else {

                return findMin((((HuffInternalNode) node).getRight()), min);
            }

        }
    }

}
