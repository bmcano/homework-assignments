/**
 * @author Brandon Cano
 * A14_FourSquare
 */
public class Cipher {

    private static final String[][] TOP_LEFT = {
            {"a", "b", "c", "d", "e"},
            {"f", "g", "h", "i", "j"},
            {"k", "l", "m", "n", "o"},
            {"p", "r", "s", "t", "u"},
            {"v", "w", "x", "y", "z"}
    };

    private final String[][] topRight = new String[5][5];

    private static final String[][] BOTTOM_RIGHT = {
            {"a", "b", "c", "d", "e"},
            {"f", "g", "h", "i", "j"},
            {"k", "l", "m", "n", "o"},
            {"p", "r", "s", "t", "u"},
            {"v", "w", "x", "y", "z"}
    };

    private final String[][] bottomLeft = new String[5][5];

    // constructor
    Cipher(String keywordOne, String keywordTwo) {
        keywordParser(keywordOne, topRight);
        keywordParser(keywordTwo, bottomLeft);
    }

    /**
     * @param message message to be encrypted.
     * @return the encrypted message.
     */
    public String encrypt(String message) {
        // 'fix' input message
        String formattedMessage = message.toUpperCase();
        formattedMessage = formattedMessage.replace(" ", "").replace("Q", "");
        if (formattedMessage.length() % 2 == 1) {
            formattedMessage = formattedMessage.substring(0, formattedMessage.length() - 1);
        }

        StringBuilder encryptedMessage = new StringBuilder();
        for(int i = 0; i < formattedMessage.length(); i += 2) {
            String firstChar = formattedMessage.substring(i, i + 1);
            String secondChar = formattedMessage.substring(i + 1, i + 2);

            int[] coordinates = findCoordinatesForEncryption(firstChar, secondChar);

            // (row of first char, col of second char) -> (topRight coordinates)
            // (row of second char, col of first char) -> (bottomLeft coordinates)
            encryptedMessage.append(topRight[coordinates[0]][coordinates[3]]);
            encryptedMessage.append(bottomLeft[coordinates[2]][coordinates[1]]);
        }

        return encryptedMessage.toString();
    }

    /**
     * @param encryptedMessaged message that is already encrypted as a String.
     * @return the decrypted message in all caps without spaces between words.
     */
    public String decrypt(String encryptedMessaged) {
        StringBuilder decryptedMessage = new StringBuilder();
        for(int i = 0; i < encryptedMessaged.length(); i += 2) {
            String firstChar = encryptedMessaged.substring(i, i + 1);
            String secondChar = encryptedMessaged.substring(i + 1, i + 2);

            int[] coordinates = findCoordinatesForDecryption(firstChar, secondChar);

            decryptedMessage.append(TOP_LEFT[coordinates[0]][coordinates[3]]);
            decryptedMessage.append(BOTTOM_RIGHT[coordinates[2]][coordinates[1]]);
        }
        return decryptedMessage.toString().toUpperCase();
    }

    /**
     * @param keyword is one of the keywords from the constructor.
     * @param squareArray is the appropriate array to fill in the letters from the keyword.
     *
     * Parses through the keyword and put it into the array and then fills in with the remaining available letters.
     */
    private void keywordParser(String keyword, String[][] squareArray) {
        String alphabet = "ABCDEFGHIJKLMNOPRSTUVWXYZ";
        int keywordCounter = 0;
        int alphabetCounter = 0;
        keyword = keyword.toUpperCase().replace("q","");

        for(int row = 0; row < 5; row++) {
            for(int col = 0; col < 5; col++) {
                if(keywordCounter < keyword.length()) {
                    String character = keyword.substring(keywordCounter, keywordCounter + 1).toUpperCase();

                    // prevents duplicate characters from showing up
                    if(alphabet.contains(character)) {
                        alphabet = alphabet.replace(character, "");
                        squareArray[row][col] = character;
                    } else { col--; } // if a duplicate appears then reset back a position and try the next

                    keywordCounter++;
                } else {
                    squareArray[row][col] = alphabet.substring(alphabetCounter, alphabetCounter + 1);
                    alphabetCounter++;
                }
            }
        }
    }

    /**
     * @param firstLetter first char of the iteration.
     * @param secondLetter second char of the iteration.
     * @return The coordinates of the letters in an integer array.
     */
    private int[] findCoordinatesForEncryption(String firstLetter, String secondLetter) {
        return getCoordinates(firstLetter, secondLetter, TOP_LEFT, BOTTOM_RIGHT);
    }

    /**
     * @param firstLetter first char of the iteration.
     * @param secondLetter second char of the iteration.
     * @return the coordinates of the letters in an integer array.
     */
    private int[] findCoordinatesForDecryption(String firstLetter, String secondLetter) {
        return getCoordinates(firstLetter, secondLetter, topRight, bottomLeft);
    }

    /**
     * @param firstLetter first char of the iteration.
     * @param secondLetter second char of the iteration.
     * @param top specific square on the top with respect to encryption or decryption
     * @param bottom specific square on the bottom with respect to encryption or decryption
     * @return the coordinates in an int array.
     */
    private int[] getCoordinates(String firstLetter, String secondLetter, String[][] top, String[][] bottom) {
        int[] coords = new int[4];

        for(int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if(top[row][col].toUpperCase().equals(firstLetter)) {
                    coords[0] = row;
                    coords[1] = col;
                }
            }

            for (int col = 0; col < 5; col++) {
                if(bottom[row][col].toUpperCase().equals(secondLetter)) {
                    coords[2] = row;
                    coords[3] = col;
                }
            }
        }

        return coords;
    }
}
