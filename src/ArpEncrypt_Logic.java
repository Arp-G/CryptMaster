import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.Map;


public class ArpEncrypt_Logic {

 static String Encrypt_Driver(String src) {

  Set < Character > charsUsed = usedChars(src);                  // send source text to usedChars() to find the distinct character used in the text the encrypt
  Map < Integer, Integer > charMap = createMap(charsUsed);		 // create map containing mapping between original and encrypted characters, this will serve as key
  storeKey(charMap);											 // store the map as a key in file
  String coded = encrypt(src, charMap);							 // encrypt the source string using the map/key
  return coded;												     // return the encrypted string
 }

 static String Decrypt_Driver(String coded) {
	 
  Map < Integer, Integer > key = getKey(System.getProperty("user.dir") + "\\key");	// read the map/key stored in file
  String decoded = decrypt(coded, key);												// decrypt encrypted text using map/key
  return decoded;																	// return decryptes text
 }


 static Set < Character > usedChars(String src) {
	 
  Set < Character > charsUsed = new HashSet < > ();		// create a set to find distinct characters used in the source
  char ch;
  for (int i = 0; i < src.length(); i++) {				// read the source string character by character
   ch = src.charAt(i);
   if (ch >= 32 && ch <= 126)							// if the source character is in the ascii range 32 to 126 add it to the set	
    charsUsed.add(ch);
  }
  return charsUsed;										// return the distinct characters used as a set
 }


 static Map < Integer, Integer > createMap(Set < Character > usedChars) {
	 
  List < Integer > asciiList = new ArrayList < > ();	 // create a list to hold all characters in the ascii range 32 to 126
  
  for (int i = 32; i <= 126; i++)						
   asciiList.add(i);									 // add all the ascii characters in the range 32 to 126

  Map < Integer, Integer > charMap = new HashMap < > (); // create map to hold the mapping between original and encrypted characters, this will serve as key

  for (int x: usedChars) {								 // loop through all the distinct characters used
   Collections.shuffle(asciiList);						 // shuffle the array of ascii characters to get a random character in the beginning of the array
   charMap.put(x, asciiList.remove(0));					 // map the character used to a random character from the shuffled array and removed the mapped character from the array
  }

  return charMap;										// return the map 
 }

 static String encrypt(String src, Map < Integer, Integer > charMap) {
  String coded = "";
  int ch;
  Integer code;

  for (int i = 0; i < src.length(); i++) {	// loop through the text to encrypt
   ch = src.charAt(i);						// get a character from the source text
   code = charMap.get(ch);					// get the mapped character for this character

   if (code != null)						// if no mapped character was found for the character in the map		
    coded += (char) code.intValue();		// insert the character without replacing it
   else
    coded += (char) ch;						// otherwise replace the character with mapped character
  }

  return coded;								// return the encrypted String
 }



 static String decrypt(String coded, Map < Integer, Integer > charMap) {
  String decoded = "";
  Map < Integer, Integer > key = new HashMap < > ();			

  if (charMap == null) return null;

  charMap.entrySet().stream().forEach(e -> key.put(e.getValue(), e.getKey()));	// reverse the map to get original character from encrypted character as key

  int ch;
  Integer code;

  for (int i = 0; i < coded.length(); i++) {  // loop through the encrypted string
   ch = coded.charAt(i);					  // get a character from the encrypted string
   code = key.get(ch);						  // get the original character for this mapped character

   if (code != null)						 // if no original character was found for this mapped character in the map	
    decoded += (char) code.intValue();       // insert the character without replacing it as this is the original character
   else
    decoded += (char) ch;					 // otherwise replace the character with original character
  }

  return decoded;							// return the decrypted String
 }

 static void storeKey(Map < Integer, Integer > charMap) { // write the map as a key in a file
  try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("key"))) {
   objectOut.writeObject(charMap);
  } catch (IOException e) {
   e.getStackTrace();
  }

 }

 @SuppressWarnings("unchecked")
 static Map < Integer, Integer > getKey(String path) { // get the map as a key from a file
  Map < Integer, Integer > key = null;

  try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(path))) {
   key = (Map < Integer, Integer > ) objectIn.readObject();
  } catch (IOException | ClassNotFoundException e) {
   e.getStackTrace();
  }

  return key;
 }

}