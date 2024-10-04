import java.io.*;

public class BinaryFileReader {
    public static void main(String[] args) {
        String outputFilePath = "chiave";
        String hexKey = "1f2c7d101dc81fdf4be7223f0c7ef47e52d4b7cb46f6b14cd9ba4b9e25710576";

        try {
            byte[] byteArray = hexStringToByteArray(hexKey);

            serializeByteArray(byteArray, outputFilePath);

            System.out.println("File binario letto e serializzato con successo in '" + outputFilePath + "'.");

        } catch (IOException e) {
            System.err.println("Errore durante la lettura o scrittura del file: " + e.getMessage());
        }
    }

    public static byte[] hexStringToByteArray(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i+1), 16));
        }
        return data;
    }

    private static void serializeByteArray(byte[] byteArray, String outputFilePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(outputFilePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(byteArray);
        }
    }
}
