package class1;

public class monoSubstitution implements CipherInterface{
	
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
						{	int index = check - 'A';	//0 to 25
							check = Character.toUpperCase(key.charAt(index));
						}	
					else
						{	int index = check - 'a';
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
				int index = key.indexOf(Character.toUpperCase(check));	//0 to 26
				if (Character.isUpperCase(check))	{ check = (char) (index + 'A');	}
				else								{check = (char) (index + 'a');	}
			}
			plainText+=check;
	}
		return plainText;
	}

}
