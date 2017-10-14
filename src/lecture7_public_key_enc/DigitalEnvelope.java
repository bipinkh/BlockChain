package lecture7_public_key_enc;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


//value of iv is appended on first line
//aes key of size 256 bytes is appended on the second line of file

public class DigitalEnvelope {	
	
	//encryption
			public void encryption (String inputFile, String outputFile, PublicKey publicKey) throws Exception
			{
				System.out.println("Encrypting");
				
					//get aes key and ivparameterspec
					SecretKey aesKey = aesCipher.generateKey();
					IvParameterSpec ivParameterSpec = aesCipher.generateIV(16);	//16 byte
					
					System.out.println("Enc key :::" +aesKey);
					System.out.println("Enc iv :::"+ivParameterSpec);
					
					try{
						//open file to read and write
						FileOutputStream  fileOutput = new FileOutputStream(outputFile);
						FileInputStream  fileInput = new FileInputStream(inputFile);
						
						// key and ivspec
						byte[] byteAESkey = aesKey.getEncoded();	
						byte[] encryptedAESkey = rsaCipher.encryption(byteAESkey, publicKey);
						
						//write key and iv
						fileOutput.write(ivParameterSpec.getIV());	//append byte[] of iv in 1st line
						fileOutput.write(encryptedAESkey); //append encrypted aesKey in 2nd line
						
						System.out.println(encryptedAESkey.length);
						System.out.println(ivParameterSpec.getIV().length);

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
				System.out.println("Decrypting");
				
				try{
					//open file to read and write
					FileOutputStream  fileOutput = new FileOutputStream(outputFile);
					FileInputStream  fileInput = new FileInputStream(inputFile);
					
					//read IV
					byte[] ivBytes = new byte[16];	//ivsize 16 bytes
					fileInput.read(ivBytes);
					IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);	

					//read aesKey
					byte[] keyBytes = new byte[256];
					fileInput.read(keyBytes);
					byte[] aesKeyEncrypted = rsaCipher.decryption(keyBytes, prvKey);	//decrypt
					SecretKey aesKey = new SecretKeySpec(aesKeyEncrypted, 0, aesKeyEncrypted.length, "AES");
					
					System.out.println("Dec key :::" +aesKey);
					System.out.println("Dec iv :::"+ivParameterSpec);
					
					// Data Decryption
				    byte[] readBytes = new byte[16];	//read 16 bytes at a time
				    	while ((fileInput.read(readBytes))!= -1){
				    	fileOutput.write(aesCipher.decryption(readBytes, aesKey, ivParameterSpec)); //encrypted and written
				    	}
				    	fileOutput.close();
						fileInput.close();
				     }
				catch(Exception e) { System.out.println("Exception occured ::: " + e);}
			System.out.println("Decryption Successful");
			}
			

}	
