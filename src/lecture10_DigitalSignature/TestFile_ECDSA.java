package lecture10_DigitalSignature;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class TestFile_ECDSA {

	@Test
	public void test() throws Exception {

		String Algorithm = "SHA256withECDSA";
	    String Curve = "secp256k1";
	    String message = "This will be the signed msg";
	    String FilePath = "src\\lecture10_DigitalSignature\\textFiles\\gutenberg.txt";
	    
	    
	    ECDSA ecdsa = new ECDSA(Algorithm, Curve);
	    KeyPair kp = ecdsa.generateKeyPair();
	    PrivateKey prvkey = kp.getPrivate();
	    PublicKey pubKey = kp.getPublic();
	    
	    
	    //signing msg and verifying
	    byte [] mySignature = ecdsa.signMessage(prvkey, message.getBytes());
	    boolean isMsgVerified = ecdsa.verify(pubKey, message.getBytes(), mySignature);
	    
	    
	    //signing file and verifying
	    FileInputStream fis = new FileInputStream(FilePath);
	    byte [] FileSignature = ecdsa.signMessage(prvkey, fis);
	    System.out.println("File Signature :: "+ Base64.encodeBase64String(FileSignature));
	    fis  = new FileInputStream(FilePath);
	    boolean isFileVerified = ecdsa.verify(pubKey, fis, FileSignature);
	    
	    //assert true
	    assertTrue(isFileVerified);
	    assertTrue(isMsgVerified);
	    
	    //check for string 
	    System.out.println("Original text :: "+ message);
	    System.out.println("Message Signature :: "+ Base64.encodeBase64String(mySignature));
	    System.out.println("Message Signature Verification status :: "+isMsgVerified);
	    
	    //check for file
	    System.out.println("File Signature :: "+ Base64.encodeBase64String(FileSignature));
	    System.out.println("File Signature Verification status :: "+isFileVerified);

	}

}
