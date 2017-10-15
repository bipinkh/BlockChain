package lecture6_MAC;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class aesCipher {
	
		//generate SecretKey randomly
		public static SecretKey generateKey() throws Exception
		{	
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			return (keyGenerator.generateKey());
		}
		//generate SecretKey if key is provided
		public static SecretKey generateKey(String key) throws Exception
		{	
			return ( new SecretKeySpec(key.getBytes(),"AES"));
		}
		
		//get ivparameterspec
		public static IvParameterSpec generateIV(int ivSize) throws Exception
		{
			byte[] iv = new byte[ivSize];
			SecureRandom random = new SecureRandom();
			random.nextBytes(iv);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
			return ivParameterSpec;
		}
		
		public static byte[] encryption(String message, SecretKey seckey, IvParameterSpec ivparam)
		{
			byte[] result = null;
			try {
				Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
				cipher.init(Cipher.ENCRYPT_MODE, seckey, ivparam );
				result = cipher.doFinal(message.getBytes());
				}
			catch(Exception e){
				System.out.println("Exception Occured during Encryption ::: " +e );
				}
			System.out.println("Encryption Sucessful");
			return result;
		}
		
		
		public static String decryption(byte[]message,SecretKey aesKey, IvParameterSpec ivParameterSpec)
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
			System.out.println("Decryption Sucessful");
			return new String(result);
		}
}
