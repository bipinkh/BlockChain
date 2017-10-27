package lecture9_EllipticCurve;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;

import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.jce.spec.ECParameterSpec;

//class can be instantiated for different algorithms and curves

public class EllipticCurve {
	
	public static String Algorithm;
	public static String Curve;
	public static String Provider ="BC";

	
	//constructor consists of adding bouncy castle provider, algorithm and curve
	EllipticCurve(String alg, String crv){
	Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	Algorithm=alg;
	Curve = crv;
	}
	
	//key pair generation
		public KeyPair generateKeyPair(){
			KeyPair kp = null;
			try{
				//get curve parameter in x9.2 format
			X9ECParameters ecP = CustomNamedCurves.getByName(Curve);
			ECParameterSpec ecSpec=new ECParameterSpec(ecP.getCurve(), ecP.getG(),
			        ecP.getN(), ecP.getH(), ecP.getSeed());
				//produce ECIES key as normal curve
			KeyPairGenerator generator = KeyPairGenerator.getInstance("EC",Provider);
			generator.initialize(ecSpec, new SecureRandom());
			kp = generator.generateKeyPair();
			}catch(Exception e){
				System.out.println("Exception Occured in generating key pair::"+e.toString());
			}
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
