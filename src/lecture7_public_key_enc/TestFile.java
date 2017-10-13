package lecture7_public_key_enc;

import static org.junit.Assert.*;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.junit.Test;

public class TestFile {
	
	//key pair generation for key encryption and decryption
		public KeyPair generateKeyPair(int keySize) throws NoSuchAlgorithmException
		{
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(keySize);
			KeyPair keyPair = keyPairGenerator.genKeyPair();
			return keyPair;
		}

	@Test
	public void test() throws Exception {
		
	//files location
		String inputFilepath = "src\\lecture7_public_key_enc\\originalText.txt";
		String outputFilepath = "src\\lecture7_public_key_enc\\encrypted.txt";
		String decryptedFile = "src\\lecture7_public_key_enc\\decrypted.txt";
		
	//public key and private key
		KeyPair keypair = generateKeyPair(2048);
		PublicKey pubKey = keypair.getPublic();
		PrivateKey prvKey = keypair.getPrivate();
		
	//encryption and decryption
		DigitalEnvelope de = new DigitalEnvelope();
		de.encryption(inputFilepath, outputFilepath, pubKey);
		de.decryption(outputFilepath,decryptedFile, prvKey);
		
	}

}
