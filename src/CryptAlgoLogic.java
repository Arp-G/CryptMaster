import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64; 
import java.security.SecureRandom;

//Good tutorial at http://tutorials.jenkov.com/java-cryptography/index.html

public class CryptAlgoLogic {
	static int key_size;          // 128 for AES,Blowfish | 56 for DES
    static int iv_length;         // 8 for blowfish,DES | 16 for AES
	static String algo;           // Blowfish | AES | DES
    static SecretKey secretKey;
    static IvParameterSpec iv;    // Block cipher modes need an initialization vector (IV), which is a block of initialization data
    							  // IV is just an arbitrary constant which is included in the hash function specification and is used 
    							  // as the initial hash value before any data is fed in
    
    public static DataAndKey Encryptdriver(String plainText,String algoToUse) { // 'plaintext' is the text to encrypt and 'algoToUse' tells the encryption algorithm to use  
    	
    	algo=algoToUse;
    	 
    	// Set key-size and initialization vector(iv) length according to algorithm being used
    	
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
    	
    	DataAndKey ob=encryptData(plainText,key_size,iv_length,algo); //Encrypt plain text and store encrypted text and key as an object of DataAndKey class
    	
    	return ob; // return the object containing the encrypted text and the key
    }
    
    
    public static String Decryptdriver(DataAndKey x,String algoToUse) // 'DataAndKey x' contains the encrypted text and the secret key and 'algoToUse' tells the decryption algorithm to use 
    {
    	algo=algoToUse;
    	
    	// Set key-size and initialization vector(iv) length according to algorithm being used
    	
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
    	
    	String ob=decryptData(x.getData(),x.getKey(),algoToUse); // Decrypt encrypted text   	
    	return ob; // return decrypted text
    }
   static public DataAndKey encryptData(String plaintext,int key_size,int iv_length,String algo) {
   
   try {
              
       SecureRandom randomSecureRandom = new SecureRandom();         // Getting "SecureRandom" instance to create a secure random initialization vector vector
       byte[] initVector = new byte[iv_length];                      // creating 16 byte array to hold the random initialization vector generated
       randomSecureRandom.nextBytes(initVector);                     // use the instance of SecureRandom to generate a random initialization vector
       iv = new IvParameterSpec(initVector);						 // creating IvParameterSpec with initialization vector
       KeyGenerator keyGenerator = KeyGenerator.getInstance(algo);   // create an instance of key generator for specified encryption algorithm
       keyGenerator.init(key_size);									 // set key generator instance's key size
       secretKey = keyGenerator.generateKey();						 // generate the secret key using the instance of key generator
       byte[] plaintTextByteArray = plaintext.getBytes("UTF8");		 // convert the plain text to be encrypted into a byte array with encoding as 'UTF8'
       Cipher cipher = Cipher.getInstance(algo+"/CBC/PKCS5Padding"); // get an instance of Cipher to encrypt the data, specify algorithm/mode/padding, 
       																 // CBC - Cipher block chaining , PKCS5Padding is the padding specification
       cipher.init(Cipher.ENCRYPT_MODE, secretKey ,iv);				 // set cipher mode to ENCRYPT and set secret key and initialization vector to cipher
       byte[] cipherText = cipher.doFinal(plaintTextByteArray);      // encrypt the plain text byte array using the cipher instance      
       Base64.Encoder encoder = Base64.getEncoder();  				 // create an instance of Base64.Encoder       
       return  new DataAndKey(new String(encoder.encode(cipherText)) , secretKey);	// use the encoder instance to encode generated encrypted byte array to string
   }																				// return the encrypted string and secret key as a 'DataAndKey' instance
       catch(Exception ex){
           ex.printStackTrace();
       }
       return null;	//return 'null' in case of an Exception      
   }
   
   static public String decryptData(String encrypted,SecretKey secretkey,String algo) { 
   
   try {
	   
       byte[] encryptedTextByteArray = encrypted.getBytes("UTF8"); 					// create a byte array from the encrypted string using 'UTF8' encoding        
       Cipher cipher = Cipher.getInstance(algo+"/CBC/PKCS5Padding"); 				// get an instance of Cipher to encrypt the data, specify algorithm/mode/padding, 
       																				// CBC - Cipher block chaining , PKCS5Padding is the padding specification
       cipher.init(Cipher.DECRYPT_MODE, secretkey ,iv); 							// set cipher mode to DECRYPT and set secret key and initialization vector to cipher      
       Base64.Decoder decoder = Base64.getDecoder();   								// create an instance of Base64.Decoder
       byte[] cipherText = cipher.doFinal(decoder.decode(encryptedTextByteArray));  // decode the encrypted byte array to Base64 and drcypt it using the decoder instance
       return new String(cipherText); 												// make a String from decrypted byte array and return it
       }
        catch(Exception ex){
           ex.printStackTrace();
       }       
       return null; //return 'null' in case of an Exception
   }  
}
