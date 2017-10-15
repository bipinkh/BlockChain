package lecture5_hashfunction;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

public class hasher {
	
	public static String hash(String message, String algorithm){
		String encodedHashString = null;
		try{
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			byte[] hashValue = digest.digest(message.getBytes());
			encodedHashString = new BASE64Encoder().encode(hashValue);
			return encodedHashString;
		}
		catch(Exception e){
			System.out.println("Exception occured during hashing ::: "+e);
		}
	return encodedHashString;
	}
	
}
