import java.math.BigInteger;
import java.util.Scanner;
public class RSA {
    private BigInteger n;
    private BigInteger e; 
    private BigInteger d; 

    public RSA() {
        BigInteger p = BigInteger.valueOf(61);
        BigInteger q = BigInteger.valueOf(53);
        n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.valueOf(17);
        d = e.modInverse(phi);
    }
    public BigInteger encrypt(BigInteger message) {
        if (message.compareTo(n) >= 0) {
            throw new IllegalArgumentException("Message too large.");
        }
        return message.modPow(e, n);
    }
    public BigInteger decrypt(BigInteger ciphertext) {
        return ciphertext.modPow(d, n);
    }
    public BigInteger getModulus() { return n; }
    public BigInteger getPublicKey() { return e; }
    public BigInteger getPrivateKey() { return d; }

    public static void main(String[] args) {
        RSA rsa = new RSA();
        Scanner scanner = new Scanner(System.in);

        System.out.println("RSA Demo");
        System.out.println("n: " + rsa.n);
        System.out.println("e: " + rsa.e);
        System.out.println("d: " + rsa.d + "\n");

        System.out.print("Enter message (integer < n): ");
        BigInteger message = scanner.nextBigInteger();

        BigInteger ciphertext = rsa.encrypt(message);
        System.out.println("Ciphertext: " + ciphertext);

        BigInteger decrypted = rsa.decrypt(ciphertext);
        System.out.println("Decrypted: " + decrypted);

        System.out.println(message.equals(decrypted) ? "Success!" : "Failed!");

        scanner.close();
    }
}
