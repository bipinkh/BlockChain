package lecture6_MAC;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMAC {
	SecretKeySpec signingKey;
	Mac mac;
	
	public void initilialize(String password, String macAlgorithm){
		signingKey = new SecretKeySpec(password.getBytes(), macAlgorithm);
		try{
		mac = Mac.getInstance(macAlgorithm);
		mac.init(signingKey);
		}
		catch(Exception e){
			System.out.println("Exception occured while initializing Mac ::: "+ e);
		}
	}
	
	//generate MAC
	public byte[] getMAC(String message){
			return ( mac.doFinal(message.getBytes()));
		}
	}
	
