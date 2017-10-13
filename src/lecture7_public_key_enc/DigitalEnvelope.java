package lecture7_public_key_enc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import sun.misc.IOUtils;

//using AES
public class DigitalEnvelope {
	
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
				BufferedWriter writer=null;
				Scanner fileIn;
				
					//AES cipher
					generateKey(16);
					Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
					cipher.init(Cipher.ENCRYPT_MODE, aesKey, ivParameterSpec );

					try{
						//open file reader and writer
						writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile),"utf-8"));
						fileIn = new Scanner(new File(inputFile));
						String line = null;
						
						// Encrypting Data - using AES
						while(fileIn.hasNextLine())
						{   line=fileIn.nextLine();
							byte[] byteResult = cipher.doFinal(line.getBytes());	//encrypt
							String stringResult = new sun.misc.BASE64Encoder().encode(byteResult);	//encode
							writer.write(stringResult+"\n");
			            }
						writer.close();
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
				BufferedWriter writer=null;
				Scanner fileIn;
				
				try{
					//open file reader and writer
					writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile),"utf-8"));
					fileIn = new Scanner(new File(inputFile));
					String line = null;
					
					//AES Cipher
					Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
					cipher.init(Cipher.DECRYPT_MODE,aesKey, ivParameterSpec);
					
					while(fileIn.hasNextLine())
					{
						line=fileIn.nextLine();
						byte[] byteLine = new sun.misc.BASE64Decoder().decodeBuffer(line); //decode
						byte[] byteResult = cipher.doFinal(byteLine);	//decrypt
						String StringResult = new String(byteResult);
						writer.write(StringResult+"\n");
					}	
					writer.close();
				}
				catch(Exception e) { System.out.println("Exception occured ::: " + e);}
			System.out.println("Decryption Successful");
			}
			

}	
