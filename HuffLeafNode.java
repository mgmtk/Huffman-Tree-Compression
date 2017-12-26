
/**
 * HuffLeafNode builds an leaf hufftree node implements the huff base node
 * interface
 *
 * @author Mitchell Mesecher mesechmg@dukes.jmu.edu
 *
 *         HONOR CODE: This work complies with the JMU Honor Code. References
 *         and Acknowledgments: I received no outside help with this programming
 *         assignment.
 * 
 *         Open DSA leaf Node
 */
public class HuffLeafNode implements HuffBaseNode {
    private byte element;
    private int weight;

    /**
     * Constructor of the leaf node
     *
     * @param element
     *            the element of the node
     * @param weight
     *            the weight of the node
     */
    public HuffLeafNode(byte element, int weight) {

        this.element = element;
        this.weight = weight;
    }

    /**
     * get this element
     *
     * @return the node element
     */
    public byte getElement() {
        return element;

    }

    /**
     * returns true
     *
     * @return true
     */
    @Override
    public boolean isLeaf() {
        return true;
    }

    /**
     * get this nodes weight
     *
     * @return the weight
     */
    @Override
    public int getWeight() {
        return weight;
    }

}
