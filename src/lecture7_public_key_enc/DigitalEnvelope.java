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
import java.io.RandomAccessFile;
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
import javax.crypto.spec.SecretKeySpec;

import sun.misc.IOUtils;

public class DigitalEnvelope {
	
	IvParameterSpec ivParameterSpec;
	
	//encryption
			public void encryption (String inputFile, String outputFile, PublicKey publicKey) throws Exception
			{
				System.out.println("Encrypting");
				BufferedWriter writer=null;
				
					//get aes key and ivparameterspec
					SecretKey aesKey = aesCipher.generateKey();
					ivParameterSpec = aesCipher.generateIV(16);	//128 bit
					
					try{
						//open file to read and write
						writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile),"utf-8"));
						File file = new File(inputFile);
						RandomAccessFile data = new RandomAccessFile(file, "r");
						
						// Key Encryption and append to file
						byte[] byteAESkey = aesKey.getEncoded();	
						byte[] encryptedAESkey = rsaCipher.encryption(byteAESkey, publicKey);
						String stringAESkey = new sun.misc.BASE64Encoder().encode(encryptedAESkey); 
						writer.write(Integer.toString(stringAESkey.length())); //keysize on first line
						writer.newLine();
						
						
						writer.write(stringAESkey);//store encrypted key from second line
						
						// Data Encryption
					    byte[] readBytes = new byte[16];	//read 16 bytes at a time from input file and process
					    for (long i = 0, len = data.length() / 16; i < len; i++) {
					          data.readFully(readBytes);
					          byte[] encrypted = aesCipher.encryption(readBytes, aesKey, ivParameterSpec);
					          String stringResult = new sun.misc.BASE64Encoder().encode(encrypted);
					          writer.write(stringResult);
					     }
					    data.close();
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
				
				try{
					//open file reader and writer
					writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile),"utf-8"));
					File file = new File(inputFile);
					RandomAccessFile data = new RandomAccessFile(file, "r");
					
					//Key Decryption
					String keySize = data.readLine().toString();
					Integer intKeySize = Integer.parseInt(keySize); //keysize extracted
					byte[] Key = new byte[intKeySize];
					data.readFully(Key); 							//read byte key
					byte[] byteLine = new sun.misc.BASE64Decoder().decodeBuffer(new String(Key)); //decode
					byte[] Key2 = rsaCipher.decryption(byteLine, prvKey); //decrypt
					SecretKey aesKey = new SecretKeySpec(Key2, 0, Key2.length, "AES");	//aesKey extracted
					System.out.println("Key extracted");
		
					//Data Decryption
					//16 bytes is stored as 24 bytes after being encoded by BASE64. so, read 24 bytes at a time
				    byte[] readBytes = new byte[24];
					for (long i = 0, len = (data.length()-data.getFilePointer()) / 24; i < len; i++) {
				          data.readFully(readBytes);
				          byte[] readbyte = new sun.misc.BASE64Decoder().decodeBuffer(new String(readBytes)); //decode
				          byte[] byteResult = aesCipher.decryption(readbyte, aesKey, ivParameterSpec); //decrypt
				          writer.write(new String(byteResult));
				     }
					data.close();
					writer.close();
				}
				catch(Exception e) { System.out.println("Exception occured ::: " + e);}
			System.out.println("Decryption Successful");
			}
			

}	
