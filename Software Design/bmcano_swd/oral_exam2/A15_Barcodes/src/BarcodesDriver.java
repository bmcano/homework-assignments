import java.util.Scanner;

/**
 * @author Brandon Cano
 */
public class BarcodesDriver {

    public static void main(String[] args) {
        System.out.println("-- A15_Barcodes --");
        Scanner input = new Scanner(System.in);
        System.out.println("Which Barcode would you like to encode?\n1. POSTNET\n2. UPC-A");
        String selection = input.next();
        if (selection.equals("1")) {
            System.out.println("Enter POSTNET code to encode.");
            EncodePOSTNET encode = new EncodePOSTNET();
            DecodePOSTNET decode = new DecodePOSTNET();
            Scanner value = new Scanner(System.in);
            // loop does not account for invalid inputs, only length to exit
            while (true) {
                String code = value.next();
                if (!(code.length() == 5 || code.length() == 9 || code.length() == 11)) break;
                String encoded = encode.encode(code);
                System.out.println(encoded);

                System.out.println("Enter POSTNET code to decode.");
                code = value.next();
                String decoded = decode.decode(code);
                System.out.println(decoded);
                System.out.println("Enter POSTNET code to encode.");
            }
        } else if (selection.equals("2")) {
            System.out.println("Enter UPC-A code to encode.");
            EncodeUPCA encode = new EncodeUPCA();
            DecodeUPCA decode = new DecodeUPCA();
            Scanner value = new Scanner(System.in);
            // loop does not account for invalid inputs, only length to exit
            while (true) {
                String code = value.next();
                if (code.length() != 11) break;
                String encoded = encode.encode(code);
                System.out.println(encoded);

                System.out.println("Enter UPC-A code to decode.");
                code = value.next();
                String decoded = decode.decode(code);
                System.out.println(decoded);
                System.out.println("Enter UPC-A code to encode.");
            }
        }
    }
}
