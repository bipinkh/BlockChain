package publicEncryption;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class keyPair {
	//key pair generation for key encryption and decryption
			public KeyPair generateKeyPair(int keySize) throws NoSuchAlgorithmException
			{
				KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
				keyPairGenerator.initialize(keySize);
				KeyPair keyPair = keyPairGenerator.genKeyPair();
				return keyPair;
			}

}
