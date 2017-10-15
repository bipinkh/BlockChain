package lecture6_MAC;

import static org.junit.Assert.*;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

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
		
		//encrypt and get mac value
		String sentMAC = new BASE64Encoder().encode(mac.getMAC(text));
		byte[] encryptedText = aesCipher.encryption(text, aesKey, ivparam);
		String encryptedTextString = new BASE64Encoder().encode(encryptedText);
			
		//decrypt and get mac value
		byte [] decryptedText = new BASE64Decoder().decodeBuffer(encryptedTextString); 
		String decryptedTextString = aesCipher.decryption(encryptedText, aesKey, ivparam);
		String receivedMAC = new BASE64Encoder().encode(mac.getMAC(decryptedTextString));

		assertEquals(text,decryptedTextString);
		assertEquals(sentMAC,receivedMAC);
		
		System.out.println("Original Text ::: " + text);
		System.out.println("Encrypted Text ::: " + encryptedTextString);
		System.out.println("Recieved Text ::: " + decryptedTextString);
		System.out.println("Appended MAC value ::: " +sentMAC);
		System.out.println("Recieved MAC value ::: " +receivedMAC);
	}
}
