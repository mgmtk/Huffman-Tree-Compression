import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Unzips a .jmz file given by the user.
 *
 * @author Mitchell Mesecher mesechmg@dukes.jmu.edu
 *
 *         HONOR CODE: This work complies with the JMU Honor Code. References
 *         and Acknowledgments: I received no outside help with this programming
 *         assignment.
 * 
 * 
 */
public class JMUnzip {

    static HuffBaseNode root;

    /**
     * Checks if the argument length is valid and uncompresses the file.
     * 
     * @param the
     *            arguement of the user
     *
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            String compressedFile = args[0];
            String outFile = args[1];
            outPut(compressedFile, outFile);
        } else {

            System.out.println("Must have two file arguments");
        }
    }

    /**
     * Uncompresses the passed in file and writes its contents to the secondary
     * file
     * 
     * @param compressedFile
     *            the file thats compressed
     * @param outFile
     *            the file to write to
     * 
     */
    private static void outPut(String compressedFile, String outFile) {
        ObjectInputStream input;
        try {
            input = new ObjectInputStream(
                    new FileInputStream(new File(compressedFile)));
            HuffmanSave save = (HuffmanSave) input.readObject();
            input.close();

            HashMap<Byte, Integer> map = save.getFrequencies();
            BitSequence sequence = save.getEncoding();
            FileOutputStream writer = new FileOutputStream(new File(outFile));

            if (sequence.length() == 0) {
                writer.close();
                return;
            } else if (sequence.length() == 1) {
                for (Byte key : map.keySet()) {
                    writer.write(key);

                }
                writer.close();
                return;
            }

            BuildTree build = new BuildTree(map);
            HuffTree tree = build.buildTree();
            root = tree.root();

            writeBytes(root, writer, sequence);

            writer.close();

        } catch (Exception e) {

            System.out.println("Input proper file");
            e.printStackTrace();
        }

    }

    /**
     * Helper method to write the bytes to the file corresponding with their bit
     * sequence
     * 
     * @param node
     *            the node to check
     * @param writer
     *            the writer to write the bytes to the file
     * @param sequence
     *            the bit sequence that corresponds to the byte values.
     *
     */
    private static void writeBytes(HuffBaseNode node, FileOutputStream writer,
            BitSequence sequence) throws IOException {

        String bits = sequence.toString();
        int iterations = 0;
        while (iterations < sequence.length()) {
            while (!node.isLeaf()) {

                String bit = bits.substring(0, 1);

                if (bit.equals("0")) {
                    node = ((HuffInternalNode) node).getLeft();
                    iterations++;

                } else if (bit.equals("1")) {
                    node = ((HuffInternalNode) node).getRight();
                    iterations++;

                }
                bits = bits.substring(1, bits.length());

            }

            writer.write(((HuffLeafNode) node).getElement());
            node = root;

        }

    }

}
