package lecture7_public_key_enc;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class aesCipher {
	//symmetric aes key generation
	
	public static SecretKey generateKey() throws Exception
	{	
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		SecretKey aesKey = keyGenerator.generateKey();
		return aesKey;
	}
	
	public static IvParameterSpec generateIV(int ivSize) throws Exception
	{
		byte[] iv = new byte[ivSize];
		SecureRandom random = new SecureRandom();
		random.nextBytes(iv);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
		return ivParameterSpec;
	}
	
	public static byte[] encryption(byte[]message,SecretKey aesKey, IvParameterSpec ivParameterSpec)
	{
		byte[] result =null;
		try {
			Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, aesKey, ivParameterSpec );
			result = cipher.doFinal(message);
		}
		catch(Exception e){
			System.out.println("Exception Occured during Encryption ::: " +e );
		}
		return result;
	}
	
	public static byte[] decryption(byte[]message,SecretKey aesKey, IvParameterSpec ivParameterSpec)
	{
		byte[] result =null;
		try {
			Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE,aesKey, ivParameterSpec);
			result = cipher.doFinal(message);
		}
		catch(Exception e){
			System.out.println("Exception Occured during Decryption ::: " +e );
		}
		return result;
	}

}
