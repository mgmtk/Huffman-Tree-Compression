/**
 * Base node interface for internal and leaf nodes
 *
 * @author Mitchell Mesecher mesechmg@dukes.jmu.edu
 *
 *         HONOR CODE: This work complies with the JMU Honor Code. References
 *         and Acknowledgments: I received no outside help with this programming
 *         assignment.
 * 
 *         Open DSA base node
 */
interface HuffBaseNode {

    /**
     * returns true if leaf false if not
     *
     * @return true if leaf
     */
    boolean isLeaf();

    /**
     * returns weight of node
     *
     * @return the weight
     */
    int getWeight();
}
