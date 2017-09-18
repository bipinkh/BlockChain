package lecture1_HistoricalCiphers;

import static org.junit.Assert.*;

import org.junit.Test;

public class monoSubstitutionTest {

	@Test
	public void test() {
		
		monoSubstitution testObject = new monoSubstitution();
		
		String testString="httP:// Lorem 123 ipsumz";
		String key="qwertyuiopasdfghjklzxcvbnm";
		
		String encryptedText=testObject.encryption(testString, key);
		System.out.println(encryptedText);
		String decryptedText=testObject.decryption(encryptedText, key);
		System.out.println(decryptedText);
		
		assertEquals(testString,decryptedText);
		
	}

}
