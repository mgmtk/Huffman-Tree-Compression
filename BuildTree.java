import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Builds the Hufftree
 *
 * @author Mitchell Mesecher mesechmg@dukes.jmu.edu
 *
 *         HONOR CODE: This work complies with the JMU Honor Code. References
 *         and Acknowledgments: I received no outside help with this programming
 *         assignment.
 */
public class BuildTree {

    private PriorityQueue<HuffTree> queue;

    /**
     * Constructor to add the map to the queue
     *
     * @param map the map to add to the queue
     */
    public BuildTree(HashMap<Byte, Integer> map) {
        queue = new PriorityQueue<HuffTree>();
        Set<Byte> keys = map.keySet();
        for (Byte key : keys) {
            HuffTree tmp = new HuffTree(key, map.get(key));
            queue.add(tmp);
        }

    }

    /**
     * Builds the Hufftree from the queue
     *
     *@return the tree
     */
    public HuffTree buildTree() {
        HuffTree tree1 = null;
        HuffTree tree2 = null;
        HuffTree tree3 = null;

        while (queue.size() > 1) {

            tree1 = queue.remove();
            tree2 = queue.remove();
            tree3 = new HuffTree(tree1.root(), tree2.root(),
                    tree1.weight() + tree2.weight());
            queue.add(tree3);
        }
        return tree3;

    }

}
