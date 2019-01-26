import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.Map;


public class ArpEncrypt 
{
	
	/*public static void main(String args[])
	{
		ArpEncrypt.Driver();
	}*/
	
	
	static void Driver()
	{
		System.out.println("------------------ArpEncrypt------------------");
		System.out.println("1)Encryption");
		System.out.println("2)Decryption");
		
		Scanner sc=new Scanner(System.in);
		
		int ch=sc.nextInt();
		switch(ch)
		{
		case 1:
			System.out.println("Enter text to encrypt");
			sc.nextLine();
			String src=sc.nextLine();
			Encrypt_Driver(src);
			break;
			
		case 2:
			System.out.println("Enter encrypted text and place decryption key in current directory");
			sc.nextLine();
			String coded=sc.nextLine();
			Decrypt_Driver(coded);
			break;
			
		default:
			System.out.println("Wrong input...Exiting...");
		}		
	}
	
	static String Encrypt_Driver(String src)
	{
		//String src="This is a Test of my encryption algo. Random text:2143124132%&(&(KJKH";
		
		Set<Character> charsUsed=usedChars(src);
		
		Map<Integer,Integer> charMap=createMap(charsUsed);
		
		storeKey(charMap);
		
		String coded=encrypt(src,charMap);	
		
		//System.out.println("Key file generated and placed in current directory...Encrypted Text is:");
		//System.out.println();
		//System.out.println(coded);
		
		return coded;
	}
	
	static String Decrypt_Driver(String coded)
	{
		Map<Integer,Integer> key=getKey(System.getProperty("user.dir")+"\\key");
		
		String decoded=decrypt(coded,key);	
		
		//System.out.println("Decrypted text:");
		//System.out.println();
		//System.out.println(decoded);
		
		return decoded;
	}
	
	
	static Set<Character> usedChars(String src)
	{
		Set<Character> charsUsed=new HashSet<>();
		char ch;
		for(int i=0;i<src.length();i++)
		{
			ch=src.charAt(i);			
			if(ch>=32 && ch<=126)
				charsUsed.add(ch);
		}
		
		return charsUsed;
	}
	
	
	static Map<Integer,Integer> createMap(Set<Character> usedChars)
	{
		List<Integer> asciiList=new ArrayList<>();
		
		for(int i=32;i<=126;i++)
			asciiList.add(i);
		
		Map<Integer,Integer> charMap=new HashMap<>();
		
		for(int x:usedChars)
		{
			Collections.shuffle(asciiList);
			charMap.put(x, asciiList.remove(0));
		}
		
		return charMap;
	}
	
	static String encrypt(String src,Map<Integer,Integer> charMap)
	{
		String coded="";
		int ch;
		Integer code;
	
		for(int i=0;i<src.length();i++)
		{
			ch=src.charAt(i);
			code=charMap.get(ch);
			
			if(code!=null)
				coded+=(char)code.intValue();
			else
				coded+=(char)ch;
		}
		
		return coded;
	}
	
	
	
	static String decrypt(String coded,Map<Integer,Integer> charMap)
	{
		String decoded="";
		
		Map<Integer,Integer> key=new HashMap<>();
		
		if(charMap==null) return "Key File not found !";
		
		charMap.entrySet().stream().forEach(e -> key.put(e.getValue() , e.getKey()));
		
		int ch;
		Integer code;
			
		for(int i=0;i<coded.length();i++)
		{
			ch=coded.charAt(i);
			code=key.get(ch);
			
			if(code!=null)
				decoded+=(char)code.intValue();
			else
				decoded+=(char)ch;
		}
		
		return decoded;
	}
	
	static void storeKey(Map<Integer,Integer> charMap)
	{
		try(ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("key")))
		{
			objectOut.writeObject(charMap);
		}
		catch(IOException e)
		{
			e.getStackTrace();
		}
		
	}
	
	static Map<Integer,Integer> getKey(String path)
	{
		Map<Integer,Integer> key=null;
		
		try(ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(path)) )
		{		
			key=(Map<Integer,Integer>)objectIn.readObject();	
		}
		catch(IOException | ClassNotFoundException e)
		{
			e.getStackTrace();
		}
		
		return key;
	}
	
}
