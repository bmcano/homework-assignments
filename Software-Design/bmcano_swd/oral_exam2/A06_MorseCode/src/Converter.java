/**
 * @author Brandon Cano
 */
public class Converter {

    private final static String[] morseCode = {
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
            "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..",
            ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----", "/"
    };

    private final static String[] english = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", " "
    };

    /**
     * This will convert any English message to morse code
     *
     * @param message the current message as a String in English
     * @return as String as the message in morse code
     */
    public static String toMorseCode(String message) {
        message = message.toUpperCase();
        StringBuilder morseCodeMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            int index = findIndex(message.charAt(i));
            if (index == -1) continue; // skip character

            morseCodeMessage.append(morseCode[index]);
            if (i != message.length() - 1) morseCodeMessage.append(" ");
        }

        return morseCodeMessage.toString();
    }

    /**
     * This will convert any morse code message back to the English equivalent
     *
     * @param message the current message as a String in morse code
     * @return as String as the message in English text
     */
    public static String toEnglish(String message) {
        StringBuilder englishMessage = new StringBuilder();
        String[] morseCharacters = message.split(" ");

        for (String morseCharacter : morseCharacters) {
            int index = findMorseIndex(morseCharacter);
            if (index == -1) continue; // skip character

            englishMessage.append(english[index]);
        }

        return englishMessage.toString();
    }

    /**
     * This will find the index of a character so that it can be used for converting to morse code.
     *
     * @param character character to find the index of
     * @return the index of the character
     */
    private static int findIndex(char character) {
        int index = -1;
        for (int i = 0; i < english.length; i++) {
            String c = "" + character; // convert to string
            if (c.equals(english[i])) index = i;
        }
        return index;
    }

    /**
     * This will find the index of a character so that it can be used for converting from morse code.
     *
     * @param morseCharacter character to find the index of
     * @return the index of the character
     */
    private static int findMorseIndex(String morseCharacter) {
        int index = -1;
        for (int i = 0; i < morseCode.length; i++) {
            if (morseCharacter.equals(morseCode[i])) index = i;
        }
        return index;
    }
}
