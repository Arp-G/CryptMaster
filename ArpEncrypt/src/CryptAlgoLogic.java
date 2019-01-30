
//Tutorial at http://tutorials.jenkov.com/java-cryptography/index.html


import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64; 
import java.security.SecureRandom;

public class CryptAlgoLogic {
	static int key_size=128;   // 128 for AES,Blowfish | 56 for DES
    static int iv_length=16;   // 8 for blowfish,DES | 16 for AES
	static String algo="AES";  // Blowfish | AES | DES
    static SecretKey secretKey;
    static IvParameterSpec iv;  // Block cipher modes need an initialization vector (IV), which is a block of initialization data, usually the same size as the block size of the underlying cipher.
    							// typically have a fixed IV, which is just an arbitrary constant which is included in the hash function specification and is used as the initial hash value before 
    							// any data is fed in
    
    public static void main(String args[]) {
        
       // String e=encryptData("Arpan Ghoshal is a good boy",125,16,"AES");
        //System.out.println(e);
       // System.out.println(decryptData(e,secretKey,"AES"));
    }
    
    public static DataAndKey Encryptdriver(String plainText,String algoToUse)
    {
    	algo=algoToUse;
    	
    	if(algoToUse.compareTo("AES")==0)
    	{	
    		key_size=128;
    		iv_length=16;
    	}
    	else if(algoToUse.compareTo("DES")==0)
    	{    		
    		key_size=56;
    		iv_length=8;
    	}
    	else if(algoToUse.compareTo("BlowFish")==0)
    	{
    		key_size=128;
    		iv_length=8;
    	}
    	
    	DataAndKey ob=encryptData(plainText,key_size,iv_length,algo);
    	
    	return ob;
    }
    
    
    public static String Decryptdriver(DataAndKey x,String algoToUse)
    {
    	algo=algoToUse;
    	
    	if(algoToUse.compareTo("AES")==0)
    	{	
    		key_size=128;
    		iv_length=16;
    	}
    	else if(algoToUse.compareTo("DES")==0)
    	{    		
    		key_size=56;
    		iv_length=8;
    	}
    	else if(algoToUse.compareTo("BlowFish")==0)
    	{
    		key_size=128;
    		iv_length=8;
    	}
    	
    	String ob=decryptData(x.getData(),x.getKey(),algoToUse);
    	
    	return ob;
    }
   static public DataAndKey encryptData(String plaintext,int key_size,int iv_length,String algo) {
   
   try {
       
       
       SecureRandom randomSecureRandom = new SecureRandom();         // Getting "SecureRandom" instance to create a secure random init vector
       byte[] initVector = new byte[iv_length];                      // creating 16 byte array 
       randomSecureRandom.nextBytes(initVector);                     // Getting a random init vector
       iv = new IvParameterSpec(initVector);
       KeyGenerator keyGenerator = KeyGenerator.getInstance(algo);
       keyGenerator.init(key_size);
       secretKey = keyGenerator.generateKey();
       byte[] plaintTextByteArray = plaintext.getBytes("UTF8");
       Cipher cipher = Cipher.getInstance(algo+"/CBC/PKCS5Padding");
       cipher.init(Cipher.ENCRYPT_MODE, secretKey ,iv);
       byte[] cipherText = cipher.doFinal(plaintTextByteArray);
       
       Base64.Encoder encoder = Base64.getEncoder();  
       
       return  new DataAndKey(new String(encoder.encode(cipherText)) , secretKey);
   }
       catch(Exception ex){
           ex.printStackTrace();
       }
       return null;
       
   }
   
   static public String decryptData(String encrypted,SecretKey secretkey,String algo) {
   
   try {
      
       byte[] encryptedTextByteArray = encrypted.getBytes("UTF8");

        
       Cipher cipher = Cipher.getInstance(algo+"/CBC/PKCS5Padding");
       cipher.init(Cipher.DECRYPT_MODE, secretkey ,iv);
       
       Base64.Decoder decoder = Base64.getDecoder();
       byte[] cipherText = cipher.doFinal(decoder.decode(encryptedTextByteArray));
       return new String(cipherText);

       }
        catch(Exception ex){
           ex.printStackTrace();
       }
       
       return null;
   }
   
  
}
