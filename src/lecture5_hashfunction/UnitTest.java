package lecture5_hashfunction;

import org.junit.Test;

public class UnitTest {

	@Test
	public void test() {
		String text = "this text is being hashed .";
		System.out.println("Original Text ::: "+ text);
		System.out.println("Hashed using SHA-256 ::: "+ hasher.hash(text, "SHA-256"));
		System.out.println("Hashed using SHA-512 ::: "+ hasher.hash(text, "SHA-512"));
		System.out.println("Hashed using SHA-224 ::: "+ hasher.hash(text, "SHA-224"));
		System.out.println("Hashed using SHA-384 ::: "+ hasher.hash(text, "SHA-384"));
	}

}
