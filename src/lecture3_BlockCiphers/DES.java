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


public class DES {
	
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, 
													InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException
	{
		// getting key
		KeyGenerator generator = KeyGenerator.getInstance("DES");
		SecretKey myKey = generator.generateKey();
		/*
		 * or,
		 * SecretKeySpec myKey = new SecretKeySpec("password".getBytes(),"DES");
		 */
		 Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		
		 
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
