package lecture7_public_key_enc;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

//using 
public class rsaCipher {
	
	//key pair generation for key encryption and decryption
	public KeyPair generateKeyPair(int keySize) throws NoSuchAlgorithmException
	{
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(keySize);
		KeyPair keyPair = keyPairGenerator.genKeyPair();
		return keyPair;
	}
	
	public byte[] encryption(byte[] symmetricKey, PublicKey publicKey) throws Exception
	{
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] encryptedKey = cipher.doFinal(symmetricKey);
		return encryptedKey;
	}
	
	public byte[] decryption(byte[] encryptedKey, PrivateKey privateKey) throws Exception
	{
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] symmetricKey = cipher.doFinal(encryptedKey);
		return symmetricKey;
	}

}
