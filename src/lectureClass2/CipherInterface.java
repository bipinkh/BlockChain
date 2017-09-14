package lectureClass2;

public interface CipherInterface {
	public String encryption(String plainText, String key);
	public String decryption(String cipherText, String key);
}
