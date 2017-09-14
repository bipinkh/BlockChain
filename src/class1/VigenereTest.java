package class1;

import static org.junit.Assert.*;

import org.junit.Test;

public class VigenereTest {

	@Test
	public void test() {
		Vigenere testObject = new Vigenere();
		
		String testString = "TOBEO RNOTT OBE";
		String key = "RELAT IONSR ELA";
		
		String encryptedText=testObject.encryption(testString, key);
		System.out.println(encryptedText);
		String decryptedText=testObject.decryption(encryptedText, key);
		System.out.println(decryptedText);
		
		assertEquals(testString,decryptedText);
	}

}
