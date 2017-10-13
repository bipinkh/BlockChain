package publicEncryption;

import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class rsa {

	

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
