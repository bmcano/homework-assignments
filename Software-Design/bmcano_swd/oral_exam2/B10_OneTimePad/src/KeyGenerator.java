import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * @author Brandon Cano
 */
public class KeyGenerator {

    private final int location;

    public KeyGenerator(int location) {
        this.location = location;
    }

    /**
     * This will generate the key that of a length specified by the input.
     *
     * @param amount the length of the key (number of numbers)
     */
    public void generateKey(int amount) {
        Random random = new Random();
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < amount; i++) {
            int offset = random.nextInt(26);
            key.append(offset);
            if (i != amount - 1) {
                key.append(",");
            }
        }

        createAndWriteToKeyFile(key.toString());
    }

    /**
     * This will create a file to store the key and then write to it.
     *
     * @param key the String that will be written to the file
     */
    private void createAndWriteToKeyFile(String key) {
        try {
            // create file
            File file = new File("oral_exam2/B10_OneTimePad/documents/key.txt");
            if (file.createNewFile()) System.out.println("File created: " + file.getName());

            // write to file
            FileWriter myWriter = new FileWriter("oral_exam2/B10_OneTimePad/documents/key.txt");
            myWriter.write(location + "\n" + key);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error with: key.txt");
            e.printStackTrace();
        }
    }
}
