package lecture8_ElGamal;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;

import org.bouncycastle.util.encoders.Base64;

public class elgamal2 {
	public static final String Algorithm = "ElGamal/ECB/PKCS1PADDING";
	public static final String Provider = "BC";
	
	
	
	public void main(String[] args) throws Exception {

		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		final String text = " This text";
		
		//generate key pair
		KeyPair kp = generateKeyPair();
		System.out.println("check");
		PublicKey pubKey = kp.getPublic();
		PrivateKey prvKey = kp.getPrivate();
		System.out.println(pubKey);
		System.out.println(prvKey);
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
		public KeyPair generateKeyPair() throws Exception{
			KeyPairGenerator generator = null;
			SecureRandom random = new SecureRandom();
			generator = KeyPairGenerator.getInstance("ElGamal");
			generator.initialize(512,random);
			KeyPair kp = generator.generateKeyPair();
			return kp;
		}
		
		//encryption
		public byte[] encryption(String message, PublicKey pubKey) throws Exception{
			System.out.println(Algorithm);
			Cipher cipher = Cipher.getInstance(Algorithm, Provider);
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			return (cipher.doFinal(message.getBytes()));
		}
		//decryption
		public byte[] decryption(byte[] message, PrivateKey prvKey) throws Exception{
			 Cipher cipher = Cipher.getInstance(Algorithm, Provider);
			cipher.init(Cipher.DECRYPT_MODE, prvKey);
			return cipher.doFinal(message);
		}

}
