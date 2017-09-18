package lecture2_RC4;

import static org.junit.Assert.*;

import org.junit.Test;

public class rc4Test {

	@Test
	public void test() {
		rc4 testObject = new rc4();
		
		String PlainText = "https://Animals.com/Zebra";
		String key="1234567";
		
		String cipher = testObject.encryption(PlainText, key);
		String result = testObject.decryption(cipher, key);
		
		assertEquals(PlainText,result);
				
	}

}
