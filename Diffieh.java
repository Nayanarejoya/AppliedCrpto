import java.util.Scanner;

public class dh {


    private static long power(long a, long b, long p) {
        long result = 1;
        a = a % p;

        while (b > 0) {
            if ((b & 1) == 1)
                result = (result * a) % p;

            a = (a * a) % p;
            b >>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a prime number (P): ");
        long P = scanner.nextLong();

        System.out.print("Enter a primitive root modulo P (G): ");
        long G = scanner.nextLong();

        System.out.print("Enter A's private key (a): ");
        long a = scanner.nextLong();

        System.out.print("Enter B's private key (b): ");
        long b = scanner.nextLong();

       
        long x = power(G, a, P);
        long y = power(G, b, P);

     
        long ka = power(y, a, P);
        long kb = power(x, b, P);

        System.out.println("Public key for A: " + x);
        System.out.println("Public key for B: " + y);
        System.out.println("Secret key for A: " + ka);
        System.out.println("Secret key for B: " + kb);

        scanner.close();
    }
}
