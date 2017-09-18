package lecture1_HistoricalCiphers;

public class monoSubstitution implements CipherInterface{
	
	String UpperAlphabets;
	String LowerAlphabets;
	Integer length;
	
	public monoSubstitution()
	{
		UpperAlphabets="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		LowerAlphabets="abcdefghijklmnopqrstuvwxyz";
		length=UpperAlphabets.length();
	}
	
	@Override
	public String encryption(String plainText, String key) {
			String cipherText = new String();			//result variable
			char check;
			for (int i=0; i < plainText.length(); i++)
			{	
				check = plainText.charAt(i);
				if (Character.isAlphabetic(check))
				{
					if (Character.isUpperCase(check))	
						{	int index = check - UpperAlphabets.charAt(0);
							check = Character.toUpperCase(key.charAt(index));
						}	
					else
						{	int index = check - LowerAlphabets.charAt(0);
							check = Character.toLowerCase(key.charAt(index));
						}
				}
				cipherText+=check;
			}
			return cipherText;
	}

	
	@Override
	public String decryption(String cipherText, String key) {
		key = key.toUpperCase();
		String plainText = new String();			//result variable
		char check;
		for (int i=0; i < cipherText.length(); i++)
		{	
			check = cipherText.charAt(i);			
			if (Character.isAlphabetic(check))
			{
				int index = key.indexOf(Character.toUpperCase(check));
				if (Character.isUpperCase(check))	{ check = (char) (index + UpperAlphabets.charAt(0));	}
				else								{check = (char) (index + LowerAlphabets.charAt(0));	}
			}
			plainText+=check;
	}
		return plainText;
	}

}
