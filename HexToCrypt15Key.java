import java.io.*;

/**
 * 
 * compile: 
 * javac HexToCrypt15Key.java
 * "C:\Program Files\Java\jdk-21\bin\jar.exe" cfe HexToCrypt15Key.jar HexToCrypt15Key HexToCrypt15Key.class
 * 
 * execute:
 * java -jar HexToCrypt15Key.jar 1f2c7d101dc81fdf4be7223f0c7ef47e52d4b7cb46f6b14cd9ba4b9e25710576
 */

public class HexToCrypt15Key {
    public static void main(String[] args) {
        String outputFilePath = "key";
		String hexKey="";
		
        if(args.length != 1) {
            System.out.println("Usage: java -jar HexToCrypt15Key.jar hex-key-value (eg. java -jar HexToCrypt15Key.jar 1f2c7d101dc81fdf4be7223f0c7ef47e52d4b7cb46f6b14cd9ba4b9e25710576)");
            return;
        }

        hexKey = args[0];

        try {
            byte[] byteArray = hexStringToByteArray(hexKey);

            serializeByteArray(byteArray, outputFilePath);

            System.out.println("Binary file read and serialized successfully to '" + outputFilePath + "'.");

        } catch (IOException e) {
            System.err.println("Error during file reading or writing: " + e.getMessage());
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
