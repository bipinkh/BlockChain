package lecture3_BlockCiphers;

import static org.junit.Assert.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

public class TripleDEScipherTest {
	
	@Test
	public void test() throws InvalidKeyException, NoSuchAlgorithmException, 
								NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException 
	{
		
			String password="this8bit";
			String plainText="A quick brown fox jumps over the lazy dog";
			
		//using key generator
			TripleDEScipher desObject1 = new TripleDEScipher();
			//encrypt and decrypt
			byte[] cipherText1 = desObject1.encryption(plainText.getBytes());
			byte[] originalText1 = desObject1.decryption(cipherText1);
			//test
			assertEquals(plainText,new String(originalText1));
			
		//using password
			TripleDEScipher desObject2 = new TripleDEScipher(password.getBytes());
			//encrypt and decrypt
			byte[] cipherText2 = desObject2.encryption(plainText.getBytes());
			byte[] originalText2 = desObject2.decryption(cipherText2);
			//test
			assertEquals(plainText,new String(originalText2));
			
	}

}
