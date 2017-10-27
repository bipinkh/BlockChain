package lecture5_hashfunction;

import java.security.MessageDigest;
import org.apache.commons.codec.binary.Base64;

public class hasher {
	
	public static String hash(String message, String algorithm){
		String encodedHashString = null;
		try{
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			byte[] hashValue = digest.digest(message.getBytes());
			encodedHashString = Base64.encodeBase64String(hashValue);
			return encodedHashString;
		}
		catch(Exception e){
			System.out.println("Exception occured during hashing ::: "+e);
		}
	return encodedHashString;
	}
	
}
