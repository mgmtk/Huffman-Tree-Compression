
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
 *         Open DSA Internal Node
 */
public class HuffInternalNode implements HuffBaseNode {

    private int weight;
    private HuffBaseNode left;
    private HuffBaseNode right;

    /**
     * Constructor of the internal node
     *
     * @param left
     *            the left node
     * @param left
     *            the right node
     * @param weight
     *            the tree weight
     */
    public HuffInternalNode(HuffBaseNode left, HuffBaseNode right, int weight) {

        this.left = left;
        this.right = right;
        this.weight = weight;

    }

    /**
     * Gets the right child
     * 
     * @return right HuffNode
     */
    public HuffBaseNode getRight() {

        return right;
    }

    /**
     * returns false
     *
     * @return false
     */
    @Override
    public boolean isLeaf() {

        return false;
    }

    /**
     * Returns the weight
     *
     * @return the node weight
     */
    @Override
    public int getWeight() {
        return weight;
    }

    /**
     * Gets the left child
     * 
     * @return left HuffNode
     */
    public HuffBaseNode getLeft() {
        return left;
    }

}
