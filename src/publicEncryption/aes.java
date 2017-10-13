package publicEncryption;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import lecture7_public_key_enc.symmetricKeyEncDec;

public class aes {
	
	IvParameterSpec ivParameterSpec;
	SecretKey aesKey;
	
	//symmetric aes key generation
			public void generateKey(int ivSize) throws Exception
			{
				byte[] iv = new byte[ivSize];
				SecureRandom random = new SecureRandom();
				random.nextBytes(iv);
				ivParameterSpec = new IvParameterSpec(iv);
				
				KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
				aesKey = keyGenerator.generateKey();
			}
			
	//encryption
			public void encryption (String inputFile, String outputFile, PublicKey publicKey) throws Exception
			{
				System.out.println("Encrypting");
					//AES cipher
					generateKey(16);
					Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
					cipher.init(Cipher.ENCRYPT_MODE, aesKey, ivParameterSpec );

					try{
						//open file reader and writer
						FileReader fileReader = new FileReader(inputFile);
						BufferedReader bufferedReader = new BufferedReader(fileReader);		
						FileWriter fileWriter = new FileWriter(outputFile);
						BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
						String line = null;
						
						// Encrypting Data - using AES
						while((line = bufferedReader.readLine()) != null) 
						{
							byte[] byteResult = cipher.doFinal(line.getBytes());	//encrypt
							String stringResult = new sun.misc.BASE64Encoder().encode(byteResult);	//encode
							bufferedWriter.write(stringResult);	//write to file
							bufferedWriter.newLine();
			            } 
		
						//Encrypting AES key - using RSA
						symmetricKeyEncDec encoder = new symmetricKeyEncDec();
						byte[] key = aesKey.getEncoded(); //get string of SecretKey
						byte[] encryptedKey = encoder.encryption(key, publicKey); // encrypt key
						String finalKey = new sun.misc.BASE64Encoder().encode(encryptedKey); //encode key
						bufferedWriter.write("\n\n\n Encrypted Key for Decoding: ");
						bufferedWriter.newLine();
						bufferedWriter.write(finalKey);	//write encoded encrypted key to file
						
						//closing writer and reader
						bufferedWriter.close();
						fileReader.close();
						bufferedReader.close();
						fileReader.close();
				}
				 catch(FileNotFoundException ex) { System.out.println("Unable to open file '" +  inputFile + "'"); }
			     catch(IOException ex) { System.out.println("Error reading file '"+ inputFile + "'"); }
			System.out.println("Encryption Successful");
			}
			
			
			
	//decryption
			public void decryption (String inputFile, String outputFile, PrivateKey prvKey) throws Exception
			{
				System.out.println("Decrypting");
				try{
					//open file reader and writer
					FileReader fileReader = new FileReader(inputFile);
					BufferedReader bufferedReader = new BufferedReader(fileReader);	
					FileWriter fileWriter = new FileWriter(outputFile);
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
					String line = null;
					
					Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
					cipher.init(Cipher.DECRYPT_MODE,aesKey, ivParameterSpec);
					
					
					while((line = bufferedReader.readLine()) != null) 
					{	
						System.out.println("First Line::: "+line);
						byte[] byteLine = new sun.misc.BASE64Decoder().decodeBuffer(line); //decode
						System.out.println("First Line in Byte :::" +byteLine);
						byte[] byteResult = cipher.doFinal(byteLine);	//decrypt
						
						System.out.println(new String(byteResult));
						String writingLine = new String(byteResult);
						bufferedWriter.write(writingLine);	//write to file
						bufferedWriter.newLine();
		            } 
								
					//closing writer and reader
					bufferedWriter.close();
					fileReader.close();
					bufferedReader.close();
					fileReader.close();
				}
				catch(FileNotFoundException ex) { System.out.println("Unable to open file '" +  inputFile + "'"); }
			    catch(IOException ex) { System.out.println("Error reading file '"+ inputFile + "'"); }
			System.out.println("Decryption Successful");
			}
			

}	
