package lecture9_EllipticCurve;

import static org.junit.Assert.*;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class JunitTest {

	@Test
	public void test() throws Exception {
		
		String algorithm = "ECIES";
//		String algorithm = "ECDSA";

//		String curve = "secp256k1";
//		String curve = "prime256v1";
		String curve = "curve25519";
		
		String text = "A quick brown fox jumps over the lazy dog";
		
				//make object
				EllipticCurve ec = new EllipticCurve(algorithm,curve);
				
						
				//generate key pair
				KeyPair kp = ec.generateKeyPair();
				PublicKey pubKey = kp.getPublic();
				PrivateKey prvKey = kp.getPrivate();


				//encrypt
				byte[] encryptedText = ec.encryption(text, pubKey);
				//decrypt
				String decryptedText = new String (ec.decryption(encryptedText, prvKey));
			
				//check
				System.out.println("Original Text ::: "+ text);
				System.out.println("Encrypted Cipher Text :::"+ Base64.encodeBase64String(encryptedText));
				System.out.println("Decrypted Cipher Text :::" + decryptedText);
				System.out.println("Size of text ::: "+text.length());
				System.out.println("Size of encrypted cipher :::"+encryptedText.length);
				
				assertEquals(decryptedText, text);
	}

}
