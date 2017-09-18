package lecture2_RC4;



public class rc4 implements CipherInterface {

	int len =256;	//length of stream array
	int[] stream;
	int[] keys;
	
	public void initializer(String keyString)
	{
		//key array formation
		keys = new int[keyString.length()];
		for (int i=0; i<keyString.length(); i++)
		{	
			keys[i]=(int)keyString.charAt(i);
		}
		
		//stream array formation
		stream = new int[len];
		for (int i=0; i<len;i++)
			stream[i]=i;
		
		Integer i=0;
		Integer  j=0;
		//initialization
		for (i=0;i<len;i++)
		{
			j = ( j + stream[i] + keys[i % keys.length] )% len ;
			Integer swap = stream[i];
			stream[i]=stream[j];
			stream[j]=swap;
		}
	}
	
	public String generator(String plainText)
	{
		String result = new String();
		//generation
				Integer j=0;
				Integer i=0;
				for (int k=0; k<plainText.length(); k++)
				{
					i = (i+1) % len;
					j = (j+stream[i])%len;
					Integer swap=stream[i];
					stream[i]=stream[j];
					stream[j]=swap;
					int position = (stream[i]+stream[j]) % len;
					char ch = plainText.charAt(k);
					result += (char)( ch ^ position);
				}
				return result;	
	}
	
	@Override
	public String encryption(String plainText, String key) {
		initializer(key);
		String result= generator(plainText);
		System.out.println("Cipher Text: "+result);
		return result;
	}

	@Override
	public String decryption(String cipherText, String key) {
		initializer(key);
		String result= generator(cipherText);
		System.out.println("Plain Text: "+result);
		return result;
	}
}
