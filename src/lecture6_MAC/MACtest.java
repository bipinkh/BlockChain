package lecture6_MAC;

import static org.junit.Assert.*;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.junit.Test;
import org.apache.commons.codec.binary.Base64;


public class MACtest {

	@Test
	public void test()throws Exception {
		
		Integer ivSize = 16;
		String text = "This is the string to be decrypted and then authenticated using HMAC";
		String passwordMAC = "passw0rd kEy";
		String passwordAES = "passAES8834 sfhy";
		String macAlgorithm="HmacSha1";
		
		SecretKey aesKey = aesCipher.generateKey(passwordAES);
		IvParameterSpec ivparam = aesCipher.generateIV(ivSize);
		
		HMAC mac = new HMAC();
		mac.initilialize(passwordMAC, macAlgorithm);
		
		//get mac value of original text
		String sentMAC = Base64.encodeBase64String(mac.getMAC(text));
		
		//encryption
		byte[] encryptedText = aesCipher.encryption(text, aesKey, ivparam);
		String encryptedTextString = Base64.encodeBase64String(encryptedText);
			
		//decryption
		String decryptedTextString = aesCipher.decryption(encryptedText, aesKey, ivparam);
		
		//get mac value of received text
		String receivedMAC = Base64.encodeBase64String(mac.getMAC(decryptedTextString));

		assertEquals(text,decryptedTextString);
		assertEquals(sentMAC,receivedMAC);
		
		System.out.println("Original Text ::: " + text);
		System.out.println("Encrypted Text ::: " + encryptedTextString);
		System.out.println("Recieved Text ::: " + decryptedTextString);
		System.out.println("Appended MAC value ::: " +sentMAC);
		System.out.println("Recieved MAC value ::: " +receivedMAC);
	}
}
