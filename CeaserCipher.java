import java.util.Scanner;
public class Main{
    public static String encrypt(String text, int shift) {
        int i;
        char ch;
        String result="";
        for(i=0;i<text.length();i++) {
            ch = text.charAt(i);

            if(Character.isUpperCase(ch)) {
                ch = (char) (((ch - 'A' + shift) % 26) + 'A');
            }else if(Character.isLowerCase(ch)) {
                ch = (char) (((ch - 'a' + shift) % 26) + 'a');
            }
            result += ch;
        }
        return result;
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text,26-(shift%26));
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the text :");
        String text = sc.nextLine();
        System.out.println("Enter the shift value :");
        int shift = Integer.parseInt(sc.nextLine());

        String encrypted = encrypt(text, shift);
        String decrypted = decrypt(encrypted, shift);

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}
