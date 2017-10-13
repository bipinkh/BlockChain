package publicEncryption;

import static org.junit.Assert.*;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import lecture7_public_key_enc.DigitalEnvelope;

import org.junit.Test;

public class test {	

	@Test
	public void test() throws Exception {
		
	//files location
		String inputFilepath = "src\\lecture7_public_key_enc\\originalText.txt";
		String outputFilepath = "src\\lecture7_public_key_enc\\encrypted.txt";
		String decryptedFile = "src\\lecture7_public_key_enc\\decrypted.txt";
		
	//public key and private key
		keyPair obj = new keyPair();
		KeyPair keypair = obj.generateKeyPair(16);
		PublicKey pubKey = keypair.getPublic();
		PrivateKey prvKey = keypair.getPrivate();
		
	//encryption and decryption
		DigitalEnvelope de = new DigitalEnvelope();
		de.encryption(inputFilepath, outputFilepath, pubKey);
		de.decryption(outputFilepath,decryptedFile, prvKey);
		
	}


}
