package mypackage; 
import javax.crypto.Cipher; 
import javax.crypto.KeyGenerator; 
import javax.crypto.SecretKey; 
import java.util.Base64; 
public class blowfish { 
private static final String ALGORITHM = "Blowfish"; 
public static byte[] encrypt(String data, SecretKey secretKey) throws Exception { 
Cipher cipher = Cipher.getInstance(ALGORITHM); 
cipher.init(Cipher.ENCRYPT_MODE, secretKey); 
return cipher.doFinal(data.getBytes()); 
} 
public static String decrypt(byte[] encryptedData, SecretKey secretKey) throws 
Exception { 
Cipher cipher = Cipher.getInstance(ALGORITHM); 
cipher.init(Cipher.DECRYPT_MODE, secretKey); 
byte[] decryptedBytes = cipher.doFinal(encryptedData); 
return new String(decryptedBytes); 
} 
public static SecretKey generateBlowfishKey() throws Exception { 
KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM); 
keyGenerator.init(128); 
return keyGenerator.generateKey(); 
} 
public static void main(String[] args) { 
try { 
SecretKey secretKey = generateBlowfishKey(); 
String originalText = "Hello World"; 
System.out.println("Original Text: " + originalText); 
byte[] encryptedBytes = encrypt(originalText, secretKey); 
String encodedEncryptedText =  
Base64.getEncoder().encodeToString(encryptedBytes); 
System.out.println("Encrypted Text (Base64 encoded): " + encodedEncryptedText); 
String decryptedText = decrypt(encryptedBytes, secretKey); 
System.out.println("Decrypted Text: " + decryptedText); 
} catch (Exception e) { 
e.printStackTrace(); 
}  } }
