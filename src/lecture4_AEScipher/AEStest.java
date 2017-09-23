package lecture4_AEScipher;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

public class AEStest {

	@Test
	public void test() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {

		String password="thisIs16BitPass.";
		String plainText="A quick brown fox jumps over the lazy dog";
		int ivSize =16;
		
		AES aesObject = new AES (ivSize);
		aesObject.generateKey( password.getBytes());
		//encrypt and decrypt
		byte[] cipherText = aesObject.encryption(plainText.getBytes());
		byte[] originalText = aesObject.decryption(cipherText);
		//test
		assertEquals(plainText,new String(originalText));
	}

}
