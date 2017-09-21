package lecture3_BlockCiphers;

import static org.junit.Assert.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.junit.Test;

public class DEStest {

	@Test
	public void test() throws InvalidKeyException, NoSuchAlgorithmException, 
								NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException 
	{
		
			String password="this8bit";
			String plainText="A quick brown fox jumps over the lazy dog";
			
		//using key generator
			DES desObject1 = new DES();
			//encrypt and decrypt
			byte[] cipherText1 = desObject1.encryption(plainText.getBytes());
			byte[] originalText1 = desObject1.decryption(cipherText1);
			//test
			assertEquals(plainText,new String(originalText1));
			
		//using password
			DES desObject2 = new DES(password.getBytes());
			//encrypt and decrypt
			byte[] cipherText2 = desObject2.encryption(plainText.getBytes());
			byte[] originalText2 = desObject2.decryption(cipherText2);
			//test
			assertEquals(plainText,new String(originalText2));
			
	}
}
