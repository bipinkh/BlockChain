package class1;

public class Vigenere implements CipherInterface{
	
	public static char[][] matrix;
	String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public Vigenere()
	{
		 matrix= new char [26][26];
		for (int i=0;i<26;i++)
				for (int j=0; j<26; j++)
					{
						matrix[i][j]=(char)('A'+j+i);
						if (matrix[i][j] > 'Z')
							matrix[i][j] -= 26;
						
					}
	}

	
	@Override
	public String encryption(String plainText, String key) {
		plainText= plainText.toUpperCase();
		key = key.toUpperCase();
		String cipherText=new String();
		for(int i=0; i<plainText.length(); i++)
		{
			char ch = plainText.charAt(i);
			if(Character.isAlphabetic(ch))
				{
					int column = abc.indexOf(ch);
					int row = abc.indexOf(key.charAt(i));
					ch = matrix[row][column];
				}
			
			cipherText+=ch;	
		}
		return cipherText;
	}

	@Override
	public String decryption(String cipherText, String key) {
		cipherText= cipherText.toUpperCase();
		key = key.toUpperCase();
		String plainText=new String();
		for(int i=0; i<cipherText.length(); i++)
		{
			char ch = cipherText.charAt(i);
			if(Character.isAlphabetic(ch))
			{
				int row = abc.indexOf(key.charAt(i));
				for (int j=0 ; j<26; j++)
					if (matrix[row][j]==ch)
					{
						ch=abc.charAt(j);
						break;
					}
			}
			plainText+=ch;
		
		}
		return plainText;
	}

}
