package class1;

public class caesarCipher implements CipherInterface{
	
	String alphabets;
	Integer length;
	
	public caesarCipher()
	{
		alphabets="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		length=alphabets.length();
	}

	@Override
	public String encryption(String plainText, String keyString) {
		int key = Integer.parseInt(keyString);
		String cipherText = new String();					//result variable
		char check; 										
		for (int i=0; i<plainText.length(); i++)
		{
			check = plainText.charAt(i); 					
			if (Character.isAlphabetic(check))
			{	
				check+=key;									//converted to cipher character
				if (Character.toUpperCase(check) > alphabets.charAt(length-1)) 
				{	check-=length;								//overflow should be deducted by 26 alphabets
				}
			}
			cipherText+=check;
		}
		return cipherText;
	}

	
	@Override
	public String decryption(String cipherText, String keyString) {
		int key = Integer.parseInt(keyString);
		String plainText = new String();
		char check;
		for (int i=0; i<cipherText.length(); i++)
		{
			check = cipherText.charAt(i); 					
			if (Character.isAlphabetic(check))
			{	
				check-=key;									//converted to plain character
				if (Character.toUpperCase(check) < alphabets.charAt(0)) 
				{	check+=length;								//underflow should be increased by 26 alphabets
				}
			}
			plainText+=check;
		}
		return plainText;
	}
}
