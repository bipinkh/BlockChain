package lecture8_ElGamal;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import javax.crypto.Cipher;

import org.bouncycastle.util.encoders.Base64;

public class ElGamal {
	public static final String Algorithm = "ElGamal/ECB/PKCS1PADDING";
	public static final String Provider = "BC";
	

	public static void main(String[] args) throws Exception {

		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		final String text = " This text is used here.";
		
		//generate key pair
		KeyPair kp = generateKeyPair();
		PublicKey pubKey = kp.getPublic();
		PrivateKey prvKey = kp.getPrivate();
		
		//encrypt
		byte[] encryptedText = encryption(text, pubKey);
		//decrypt
		String decryptedText = new String (decryption(encryptedText, prvKey));
	
		//check
		System.out.println("Original Text ::: "+ text);
		System.out.println("Encrypted Cipher Text :::"+ Base64.encode(encryptedText));
		System.out.println("Decrypted Cipher Text :::" + decryptedText);
		System.out.println("Size of text ::: "+text.length());
		System.out.println("Size of encrypted cipher :::"+encryptedText.length);
	}
	
	//key pair generation
		public static KeyPair generateKeyPair() throws Exception{
			System.out.println("Generating Key Pair");
			KeyPairGenerator generator = null;
			generator = KeyPairGenerator.getInstance("ElGamal", Provider);
			SecureRandom random = new SecureRandom();
			generator.initialize(256,random);
			System.out.println("Generated Key Pair");
			return generator.genKeyPair();
		}
		
		//encryption
		public static byte[] encryption(String message, PublicKey pubKey) throws Exception{
			Cipher cipher = null;
			cipher = Cipher.getInstance(Algorithm, Provider);
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			return (cipher.doFinal(message.getBytes()));
		}
		//decryption
		public static byte[] decryption(byte[] message, PrivateKey prvKey) throws Exception{
			Cipher cipher = null;
			cipher = Cipher.getInstance(Algorithm, Provider);
			cipher.init(Cipher.DECRYPT_MODE, prvKey);
			return cipher.doFinal(message);
		}
	
}
