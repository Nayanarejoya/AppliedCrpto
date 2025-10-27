import java.util.*; 
public class substitution {
public static void main(String args[]) { Scanner scanner = new Scanner(System.in); 
System.out.print("Enter the plaintext: "); 
String plaintext = scanner.nextLine(); 
System.out.print("Enter the key: "); 
int key = scanner.nextInt(); 
String encrypted = "";
for(int i = 0; i < plaintext.length(); i++) { 
char ch = plaintext.charAt(i); 
if(Character.isLetter(ch)) {
char base = Character.isUpperCase(ch)?'A':'a'; 
ch= (char)((ch - base + key) % 26 + base);
encrypted += ch;
}}
System.out.println("Encrypted message :"); 
System.out.println(encrypted);
System.out.print("Decrypted message :"); 
for(int i = 0; i < encrypted.length(); i++) { 
char ch = encrypted.charAt(i); 
if(Character.isLetter(ch)) {
char base = Character.isUpperCase(ch)?'A':'a'; 
ch = (char)((ch - base - key +26) % 26 + base); 
System.out.print(ch);
}}
System.out.println(" ");
}}
