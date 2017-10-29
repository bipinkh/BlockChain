package lecture10_DigitalSignature;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

/**
 *
 * @author bipin
 */

public class ECDSA {

    public static String Algorithm = null;
    public static String Provider = "BC";
    public static String Curve = null;
    KeyPair kp = null;

      //constructor consists of adding bouncy castle provider, algorithm and curve
        ECDSA(String alg, String crv){
    	Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    	Algorithm=alg;
    	Curve = crv;
    	}
    	
    		//key pair generation
    		public KeyPair generateKeyPair(){
    			try{
    			KeyPairGenerator generator = KeyPairGenerator.getInstance("EC",Provider);
    			generator.initialize(new ECGenParameterSpec(Curve));
    			kp = generator.generateKeyPair();
    			}catch(Exception e){
    				System.out.println("Exception Occured in generating key pair::"+e.toString());
    			}
    			return kp;
    		}
    		
    		// sign the message string
    		public byte[] signMessage(PrivateKey pvk, byte[] msg){
    			try{
	    			Signature signer = Signature.getInstance(Algorithm);
	    			signer.initSign(pvk);
	    			signer.update(msg);
	    			return signer.sign();
    			}catch(Exception e){
    				System.out.println("Error in signing :: "+e);
    				return null;
    			}	
    		}
    		
    		//overloading the signMessage method for signing the whole file
    		public byte[] signMessage(PrivateKey pvk, FileInputStream fis){
    			try{
	    			Signature signer = Signature.getInstance(Algorithm);
	    			signer.initSign(pvk);
	    			
	    			BufferedInputStream bufin = new BufferedInputStream(fis);
	                byte[] buffer = new byte[1024];
	                int len;
	                while ((len = bufin.read(buffer)) >= 0) {
	                    signer.update(buffer, 0, len);
	                }
	                bufin.close();
	                
	    			return signer.sign();
    			}catch(Exception e){
    				System.out.println("Error in signing :: "+e);
    				return null;
    			}	
    		}
            
    		
    		
    		//verify the signature
    		public boolean verify(PublicKey pubKey, byte[] message, byte[] signature) throws Exception {
		        Signature signer = Signature.getInstance(Algorithm);
		        signer.initVerify(pubKey);
		        signer.update(message);
		        return signer.verify(signature);
		    }
    		
    		
    		//overload the verify method to verify file signature
    		public boolean verify(PublicKey pubKey, FileInputStream fis, byte[] signature) throws Exception {
		        Signature signer = Signature.getInstance(Algorithm);
		        signer.initVerify(pubKey);
		        try {
	    			BufferedInputStream bufin = new BufferedInputStream(fis);
			        byte[] buffer = new byte[1024];
	                int len;
	                while ((len = bufin.read(buffer)) >= 0) {
	                    signer.update(buffer, 0, len);
	                }
	                bufin.close();
		        }catch(Exception e){
		        	System.out.println("Exc :: "+e);
		        }
		        return signer.verify(signature);
		    }


    }