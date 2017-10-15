package lecture6_MAC;

import static org.junit.Assert.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.junit.Test;

import com.sun.org.apache.xml.internal.security.utils.Base64;

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
		byte[] encryptedText = aesCipher.encryption(text, aesKey, ivparam);
		byte[] sentMAC = mac.getMAC(text);
		
		//decrypt and get mac value
		String decryptedtext = aesCipher.decryption(encryptedText, aesKey, ivparam);
		byte [] receivedMAC = mac.getMAC(decryptedtext);
		
		//check
		assertEquals(text, new String(decryptedtext));
		assertEquals(new String(sentMAC), new String(receivedMAC));
	}
}
