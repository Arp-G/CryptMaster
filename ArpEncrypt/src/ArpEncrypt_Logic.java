import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.Map;


public class ArpEncrypt_Logic {



 static String Encrypt_Driver(String src) {


  Set < Character > charsUsed = usedChars(src);

  Map < Integer, Integer > charMap = createMap(charsUsed);

  storeKey(charMap);

  String coded = encrypt(src, charMap);



  return coded;
 }

 static String Decrypt_Driver(String coded) {
  Map < Integer, Integer > key = getKey(System.getProperty("user.dir") + "\\key");

  String decoded = decrypt(coded, key);



  return decoded;
 }


 static Set < Character > usedChars(String src) {
  Set < Character > charsUsed = new HashSet < > ();
  char ch;
  for (int i = 0; i < src.length(); i++) {
   ch = src.charAt(i);
   if (ch >= 32 && ch <= 126)
    charsUsed.add(ch);
  }

  return charsUsed;
 }


 static Map < Integer, Integer > createMap(Set < Character > usedChars) {
  List < Integer > asciiList = new ArrayList < > ();

  for (int i = 32; i <= 126; i++)
   asciiList.add(i);

  Map < Integer, Integer > charMap = new HashMap < > ();

  for (int x: usedChars) {
   Collections.shuffle(asciiList);
   charMap.put(x, asciiList.remove(0));
  }

  return charMap;
 }

 static String encrypt(String src, Map < Integer, Integer > charMap) {
  String coded = "";
  int ch;
  Integer code;

  for (int i = 0; i < src.length(); i++) {
   ch = src.charAt(i);
   code = charMap.get(ch);

   if (code != null)
    coded += (char) code.intValue();
   else
    coded += (char) ch;
  }

  return coded;
 }



 static String decrypt(String coded, Map < Integer, Integer > charMap) {
  String decoded = "";

  Map < Integer, Integer > key = new HashMap < > ();

  if (charMap == null) return null;

  charMap.entrySet().stream().forEach(e -> key.put(e.getValue(), e.getKey()));

  int ch;
  Integer code;

  for (int i = 0; i < coded.length(); i++) {
   ch = coded.charAt(i);
   code = key.get(ch);

   if (code != null)
    decoded += (char) code.intValue();
   else
    decoded += (char) ch;
  }

  return decoded;
 }

 static void storeKey(Map < Integer, Integer > charMap) {
  try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("key"))) {
   objectOut.writeObject(charMap);
  } catch (IOException e) {
   e.getStackTrace();
  }

 }

 @SuppressWarnings("unchecked")
static Map < Integer, Integer > getKey(String path) {
  Map < Integer, Integer > key = null;

  try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(path))) {
   key = (Map < Integer, Integer > ) objectIn.readObject();
  } catch (IOException | ClassNotFoundException e) {
   e.getStackTrace();
  }

  return key;
 }

}