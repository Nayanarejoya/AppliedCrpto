import java.util.Scanner;

public class RC4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the plain text: ");
        String text = sc.nextLine();

        System.out.print("Enter the key: ");
        String key = sc.nextLine();

        byte[] cipher = rc4(key.getBytes(), text.getBytes());
        System.out.println("Cipher (hex): " + toHex(cipher));


        byte[] plain = rc4(key.getBytes(), cipher);
        System.out.println("Plain : " + new String(plain));
    }

    public static byte[] rc4(byte[] key, byte[] data) {
        int[] S = new int[256];


        for (int i = 0; i < 256; i++) {
            S[i] = i;
        }

        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + (key[i % key.length] & 0xFF)) & 0xFF;
            int tmp = S[i];
            S[i] = S[j];
            S[j] = tmp;
        }

        byte[] out = new byte[data.length];
        int i = 0;
        j = 0;
        for (int k = 0; k < data.length; k++) {
            i = (i + 1) & 0xFF;
            j = (j + S[i]) & 0xFF;
            int tmp = S[i];
            S[i] = S[j];
            S[j] = tmp;
            int rnd = S[(S[i] + S[j]) & 0xFF];
            out[k] = (byte)(data[k] ^ rnd);
        }
        return out;
    }

    public static String toHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%02X", b & 0xFF));
        }
        return sb.toString();
    }
}
