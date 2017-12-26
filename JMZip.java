import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Creates a new compressed .jmz zip file.
 *
 * @author Mitchell Mesecher mesechmg@dukes.jmu.edu
 *
 *         HONOR CODE: This work complies with the JMU Honor Code. References
 *         and Acknowledgments: I received no outside help with this programming
 *         assignment.
 * 
 * 
 */
public class JMZip {

    /**
     * Checks if the argument length is valid and compresses the file.
     * 
     * @param args
     *            the argument of the user
     *
     */

    public static void main(String[] args) {
        if (args.length == 2) {
            String file = args[0];
            String outFile = args[1];
            input(file, outFile);

        } else {
            System.out.println("Must have two file arguments");

        }

    }

    /**
     * Compresses the passed in file and writes the huffman save object to the
     * secondary file
     * 
     * @param file
     *            the file to compress
     * @param outFile
     *            the file to write the compressed values to
     * 
     */
    private static void input(String file, String outFile) {
        try {
            FileInputStream reader = new FileInputStream(new File(file));
            HashMap<Byte, Integer> map = new HashMap<Byte, Integer>();

            while (reader.available() != 0) {
                byte key = (byte) reader.read();
                if (map.containsKey(key)) {

                    map.put(key, (map.get(key) + 1));
                } else {
                    map.put(key, 1);

                }
            }
            reader.close();
            ObjectOutputStream output = new ObjectOutputStream(
                    new FileOutputStream(new File(outFile)));
            if (map.isEmpty()) {

                HuffmanSave blank = new HuffmanSave(new BitSequence(), map);
                output.writeObject(blank);
                output.close();
                return;

            } else if (map.size() == 1) {

                BitSequence bits = new BitSequence();
                bits.appendBit(1);
                HuffmanSave oneByte = new HuffmanSave(bits, map);
                output.writeObject(oneByte);
                output.close();
                return;

            }

            BuildTree builder = new BuildTree(map);
            HuffTree tree = builder.buildTree();

            HashMap<Byte, String> sequences = new HashMap<Byte, String>();

            traverseTree(tree.root(), sequences, "");
            BitSequence sequence = new BitSequence();
            FileInputStream reader1 = new FileInputStream(new File(file));
            while (reader1.available() != 0) {
                byte key = (byte) reader1.read();

                sequence.appendBits(sequences.get(key));

            }
            reader1.close();
            for (int i = 0; i < sequence.length(); i++) {

                System.out.print(sequence.getBit(i));
            }
            HuffmanSave save = new HuffmanSave(sequence, map);

            output.writeObject(save);
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("input proper file");
        }

    }

    /**
     * Helper method to traverse the tree and place bytes with their string
     * values in the hash map.
     * 
     * @param node
     *            the node to check
     * @param map
     *            hash map to add the byte and corresponding bit string to
     * @param sequence
     *            the string of bits to save to the hash map
     *
     */
    private static void traverseTree(HuffBaseNode node,
            HashMap<Byte, String> map, String sequence) {

        if (node.isLeaf()) {
            map.put(((HuffLeafNode) node).getElement(), sequence);

        } else {
            traverseTree(((HuffInternalNode) node).getLeft(), map,
                    sequence.concat("0"));
            traverseTree(((HuffInternalNode) node).getRight(), map,
                    sequence.concat("1"));

        }

    }

}
