package lecture7_public_key_enc;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.junit.Test;

public class TestFile {
	


	@Test
	public void test() throws Exception {
		
	//files location
		String inputFilepath = "src\\lecture7_public_key_enc\\text-files\\originalText.txt";
		String outputFilepath = "src\\lecture7_public_key_enc\\text-files\\encrypted.txt";
		String decryptedFile = "src\\lecture7_public_key_enc\\text-files\\decrypted.txt";
		
	//public key and private key
		rsaCipher rsa = new rsaCipher();
		KeyPair keypair = rsa.generateKeyPair(2048);
		PublicKey pubKey = keypair.getPublic();
		PrivateKey prvKey = keypair.getPrivate();
		
	//encryption and decryption
		DigitalEnvelope de = new DigitalEnvelope();
		de.encryption(inputFilepath, outputFilepath, pubKey);
		de.decryption(outputFilepath,decryptedFile, prvKey);
	}
}
