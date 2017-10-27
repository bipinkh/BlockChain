package lecture7_public_key_enc;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class DigitalEnvelope {	
	
	//encryption
			public void encryption (String inputFile, String outputFile, PublicKey publicKey) throws Exception
			{
				System.out.println("Encrypting");
				
				rsaCipher rsa = new rsaCipher();
				
					//get aes key and ivparameterspec
					SecretKey aesKey = aesCipher.generateKey();
					IvParameterSpec ivParameterSpec = aesCipher.generateIV(16);	//16 byte
					
					try{
						//open file to read and write
						FileOutputStream  fileOutput = new FileOutputStream(outputFile);
						FileInputStream  fileInput = new FileInputStream(inputFile);
						
						// get key and ivspec
						byte[] byteAESkey = aesKey.getEncoded();	
						byte[] encryptedAESkey = rsa.encryption(byteAESkey, publicKey);
						
						//write 16 bytes of iv and then 256 bytes of encrypted aesKey
						fileOutput.write(ivParameterSpec.getIV());
						fileOutput.write(encryptedAESkey);
						
						// Data Encryption
					    byte[] readBytes = new byte[16];	//read 16 bytes at a time
					    	while ((fileInput.read(readBytes))!= -1){
					    	fileOutput.write(aesCipher.encryption(readBytes, aesKey, ivParameterSpec)); //encrypted and written
					    	}
					    	fileOutput.close();
							fileInput.close();
					}
					catch (Exception e){
						System.out.println("Exception Occured :::" + e);
					}
			System.out.println("Encryption Successful");
			}
			

	//decryption
			public void decryption (String inputFile, String outputFile, PrivateKey prvKey) throws Exception
			{
				
				rsaCipher rsa = new rsaCipher();
				
				System.out.println("Decrypting");
				
				try{
					//open file to read and write
					FileOutputStream  fileOutput = new FileOutputStream(outputFile);
					FileInputStream  fileInput = new FileInputStream(inputFile);
					
					//read IV
					byte[] ivBytes = new byte[16];	//ivsize 16 bytes
					fileInput.read(ivBytes);
					IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);	

					//read and decrypt aesKey
					byte[] keyBytes = new byte[256];
					fileInput.read(keyBytes);
					byte[] aesKeyEncrypted = rsa.decryption(keyBytes, prvKey);	//decrypt
					SecretKey aesKey = new SecretKeySpec(aesKeyEncrypted, 0, aesKeyEncrypted.length, "AES");
					
					// Data Decryption
				    byte[] readBytes = new byte[16];	//read 16 bytes at a time
				    	while ((fileInput.read(readBytes))!= -1){
				    	fileOutput.write(aesCipher.decryption(readBytes, aesKey, ivParameterSpec)); //decrypted and written
				    	}
				    	fileOutput.close();
						fileInput.close();
				     }
				catch(Exception e) { System.out.println("Exception occured ::: " + e);}
			System.out.println("Decryption Successful");
			}
}	
