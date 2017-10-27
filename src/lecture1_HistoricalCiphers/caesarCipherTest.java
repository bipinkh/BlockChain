package lecture1_HistoricalCiphers;

import static org.junit.Assert.*;

import org.junit.Test;

public class caesarCipherTest {

	@Test
	public void test()
	{
		caesarCipher testObject = new caesarCipher();
		
		String testString = "http:// lorem ipsum";
		String key = "5";

		String encryptedText=testObject.encryption(testString, key);
		System.out.println(encryptedText);
		String decryptedText=testObject.decryption(encryptedText, key);
		System.out.println(decryptedText);
		
		assertEquals(testString,decryptedText);
	}
}
