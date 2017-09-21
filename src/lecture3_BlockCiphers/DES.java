package lecture3_BlockCiphers;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



public class DES{
	SecretKey key;
	
	//constructor if predefined password is used
	public DES(byte[] password) 
	{
		 key = new SecretKeySpec(password,"DES");
	}
	
	//constructor if keygenerator is used
	public DES() throws NoSuchAlgorithmException 
	{
		KeyGenerator generator = KeyGenerator.getInstance("DES");
		key = generator.generateKey();
	}
	
	//encryption method

		public byte[] encryption(byte[] bytePlainText) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
		{
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] byteResult = cipher.doFinal(bytePlainText);
			String encodedResult = new sun.misc.BASE64Encoder().encode(byteResult);
			//display result
			System.out.println("Encrypted byte Message: "+ new String(byteResult));	//after encryption
			System.out.println("Base64 Encoded Encrypted Message: "+ encodedResult );	//after encryption followed by encoding
			//return encoded encrypted message
			return byteResult;
		}
		
		//decryption method
		public byte[] decryption(byte[] ciphertext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
		{
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE,key);
			byte[] originalMessage = cipher.doFinal(ciphertext);
			/*
			 * or,
			 * byte[] decodedResult = new sun.misc.BASE64Decoder().decodeBuffer(encodedResult);
			 * byte[] originalMessage = cipher.doFinal(decodedResult);
			 */
			System.out.println("\nDecrypted plain Text: "+ new String(originalMessage)+"\n\n\n");
			return originalMessage;
		}			
}
