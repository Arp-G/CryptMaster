// Key length should be smaller than plain Text length

public class VernamCipher {
    
    public static void main(String args[]){
    	
    	String plainText="How Are You Arpan. Hello !";
    	
        System.out.println(plainText);
        
        String tmp=Encrypt_Driver(plainText,"key");
        
        System.out.println(tmp);
        
        System.out.println(Decrypt_Driver(tmp,"key"));         
        
    }
    

 static String Encrypt_Driver(String plainText,String key) {
		 	 
	 key=makeKey(plainText.length(),key);	 
	 
	 return encrypt(plainText,key);
 }

 static String Decrypt_Driver(String cipherText,String key) {
	 
	 key=makeKey(cipherText.length(),key);	 
	 
	 return decrypt(cipherText,key);											
 }
 
 
 
 static String encrypt(String plainText,String key) {
	 
	 String cipherText="";
	 
	 int total;
	 
	 for(int i=0;i<plainText.length();i++) {
		 
		 
		 total= getCharCode(plainText.charAt(i)) + getCharCode(key.charAt(i));
		 
		 total = (total)>94 ? total-94 : total;
		 
		 cipherText+=getCharCode(total);
		 
		 
	 }
	 
	 return cipherText;
	 
 }
 
 
 static String decrypt(String cipherText,String key) {
	 
	 String plainText="";
	 
	 int total;
	 
	 for(int i=0;i<cipherText.length();i++) {
		 
		 
		 total= getCharCode(cipherText.charAt(i)) - getCharCode(key.charAt(i));
		 
		 total = (total)<0 ? total+94 : total;
		 
		 plainText+=getCharCode(total);
		 
		 
	 }
	 
	 return plainText;
	 
 }
 
    static String makeKey(int len, String key) {
   	 
   	 String newKey="";
   	 
   	 int j=0;
   	 
   	 for(int i=0;i<len;i++) {
   	     
   	     if(j>=key.length())
   			 j=0;
   		 
   		 newKey+=key.charAt(j);	 
   		 j++;
   	 }
   	 
   	 return newKey;
   	 
    }
    
    
    static int getCharCode(char ch) {
   	 return (int)ch-32;
    }
    
    static char getCharCode(int x) {
      	 return (char)(x+32);
    }
    
 
 
}
