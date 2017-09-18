package lecture2_RC4;

public interface CipherInterface {
	public String encryption(String plainText, String key);
	public String decryption(String cipherText, String key);
}
