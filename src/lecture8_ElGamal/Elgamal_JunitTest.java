package lecture8_ElGamal;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class Elgamal_JunitTest {

	@Test
	public void test() throws Exception {

		//make object
				ElGamal elgml = new ElGamal();
				String text = " This text will be decrypted";
						
				//generate key pair
				KeyPair kp = elgml.generateKeyPair();
				PublicKey pubKey = kp.getPublic();
				PrivateKey prvKey = kp.getPrivate();


				//encrypt
				byte[] encryptedText = elgml.encryption(text, pubKey);
				//decrypt
				String decryptedText = new String (elgml.decryption(encryptedText, prvKey));
			
				//check
				System.out.println("Original Text ::: "+ text);
				System.out.println("Encrypted Cipher Text :::"+ Base64.encodeBase64String(encryptedText));
				System.out.println("Decrypted Cipher Text :::" + decryptedText);
				System.out.println("Size of text ::: "+text.length());
				System.out.println("Size of encrypted cipher :::"+encryptedText.length);
				
	}

}
