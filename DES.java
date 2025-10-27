package mypackage; 
import javax.crypto.Cipher; 
import javax.crypto.SecretKey; 
import javax.crypto. SecretKeyFactory; 
import javax.crypto.spec.DESKeySpec; 
import java.util.Base64; 
import java.util.Scanner; 
public class DES{ 
public static void main(String[] args) throws Exception { 
Scanner scanner = new Scanner(System.in); 
System.out.print("Enter message to encrypt: "); 
String message = scanner.nextLine(); 
System.out.print("Enter 8-character key: "); 
String key = scanner.nextLine(); 
DESKeySpec keySpec = new DESKeySpec(key.getBytes("UTF-8")); 
SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(keySpec); 
Cipher cipher = Cipher.getInstance("DES"); 
cipher.init(Cipher.ENCRYPT_MODE,secretKey); 
byte[] encrypted = cipher.doFinal (message.getBytes("UTF-8")); 
String encryptedBase64 = Base64.getEncoder().encodeToString(encrypted); 
System.out.println("Encrypted: " + encryptedBase64); 
cipher.init(Cipher. DECRYPT_MODE, secretKey); 
byte[] decrypted = cipher.doFinal (Base64.getDecoder().decode(encryptedBase64)); 
System.out.println("Decrypted: " + new String(decrypted, "UTF-8")); 
scanner.close(); 
} 
} 
