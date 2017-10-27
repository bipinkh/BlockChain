package lecture8_ElGamal;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;

public class ElGamal {
	
	//constructor consists of adding bouncyc castle provider
	ElGamal(){
	Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}
	
	public static final String Algorithm = "ElGamal/ECB/PKCS1PADDING";
	public static final String Provider = "BC";
	
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
