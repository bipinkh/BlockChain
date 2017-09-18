package lecture3_BlockCiphers;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.Scanner;


public class TripleDEScipher {
	
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, 
													InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException
	{
		// getting key and cipher
		SecretKeySpec myKey = new SecretKeySpec("passwordPasswordPassword".getBytes(),"TripleDES"); // need to have 24 bytes of password
		Cipher cipher = Cipher.getInstance("TripleDES/ECB/PKCS5Padding");
		/*
		 * or,
		 * KeyGenerator generator = KeyGenerator.getInstance("TripleDES");
		 *SecretKey myKey = generator.generateKey();
		 */
		
		 
		 //getting input
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a plain text to decrypt");
		String plainText = scan.nextLine();
		scan.close();
		
		
		//encryption
		cipher.init(Cipher.ENCRYPT_MODE, myKey);
		byte[] bytePlainText = plainText.getBytes();
		byte[] byteResult = cipher.doFinal(bytePlainText);
		String encodedResult = new sun.misc.BASE64Encoder().encode(byteResult);
		
		System.out.println("\nOriginal Message: "+ new String(bytePlainText)); //same as plainText
		System.out.println("Encrypted byte Message: "+ new String(byteResult));
		System.out.println("Base64 Encoded Encrypted Message: "+ encodedResult );
		
		
		
		
		//decryption
		cipher.init(Cipher.DECRYPT_MODE,myKey);
		byte[] originalMessage = cipher.doFinal(byteResult);
				/*
				 * or,
				 * byte[] decodedResult = new sun.misc.BASE64Decoder().decodeBuffer(encodedResult);
				 * byte[] originalMessage = cipher.doFinal(decodedResult);
				 */
		System.out.println("\nDecrypted plain Text: "+ new String(originalMessage));
	}
}
