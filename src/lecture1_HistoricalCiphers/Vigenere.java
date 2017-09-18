package lecture1_HistoricalCiphers;

public class Vigenere implements CipherInterface{
	
	public static char[][] matrix;
	String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	Integer length;
	
	public Vigenere()
	{
		length=abc.length();
		matrix= new char [length][length];
		for (int i=0;i<length;i++)
				for (int j=0; j<length; j++)
					{
						matrix[i][j]=(char)(abc.charAt(0)+j+i);
						if (matrix[i][j] > abc.charAt(length-1))
							matrix[i][j] -= length;
						
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
				for (int j=0 ; j<length; j++)
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
