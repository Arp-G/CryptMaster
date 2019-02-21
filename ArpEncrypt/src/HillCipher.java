/*
 * 
 * The decrypted text will contain a trailing 'X' if the original plain text had odd number of characters
 * Can only work for a single word containing letters (A to Z)
 * Fails to maintain letter case, decrypted text is always in upper case
 * The key given must have exactly 4 letters in caps
 * 
 * References:
 * 
 * https://crypto.interactive-maths.com/hill-cipher.html
 * http://practicalcryptography.com/ciphers/hill-cipher/
 * https://www.geeksforgeeks.org/hill-cipher/
 * https://en.wikipedia.org/wiki/Hill_cipher
 * 
*/

public class HillCipher {
    
    public static void main(String args[]){
    	
    	String plainText="MAGIC";
    	
    	String key="LIHH";

    	plainText=plainText.toUpperCase();
        
        String tmp=Encrypt_Driver(plainText,key);
        
        if(tmp==null) {
        	System.out.println("Wrong key,try a different key, cannot Encrypt !");
        	return;
        }
        
        System.out.println("Plain text:     "+plainText);
        
        System.out.println("Cipher text:    "+tmp);
        
        tmp=Decrypt_Driver(tmp,key);
        
        if(tmp==null) {
        	System.out.println("Wrong key,try a different key, cannot Decrypt !");
        	return;
        }
        
        System.out.println("Decrypted text: "+tmp);  
        
        
    }
    
    static String Encrypt_Driver(String plainText,String key) {
    	
    	if(calcInverse(createKeyMatrix(key))==null)
    		return null; //Wrong key
    	
    	if(plainText.length()%2!=0)
    		plainText+="X";
    	
    	String cipherText="";
    	
    	for(int i=0;i<plainText.length()-1;i=i+2) {
    		
    		String tmp=Part_Encrypt_Driver( plainText.substring(i,i+2) , key);
    		cipherText+= tmp;
    	}
    	
    	
    	return cipherText;
    			
    }
    
    static String Decrypt_Driver(String cipherText,String key) {
    	
    	if(calcInverse(createKeyMatrix(key))==null)
    		return null; //Wrong key
    	
    	String plainText="";
    	
    	for(int i=0;i<cipherText.length()-1;i=i+2) {
    		
    		String tmp=Part_Decrypt_Driver( cipherText.substring(i,i+2) , key);
    		plainText+= tmp;
    	}
    	
    	return plainText;
    			
    }
    
    

 static String Part_Encrypt_Driver(String plainText,String key) {
	 
	 int keyMatrix[][]=createKeyMatrix(key);
	 
	 int msgMatrix[]=createMsgMatrix(plainText);
	 
	 String cipherText=encrypt(msgMatrix,keyMatrix);
	 
	 return cipherText;
 }

 static String Part_Decrypt_Driver(String cipherText,String key) {	 
	 
	 int keyMatrix[][]=createKeyMatrix(key);
	 
	 int cipherMatrix[]=createMsgMatrix(cipherText);
	 
	 String plainText=decrypt(cipherMatrix,keyMatrix);
	 
	 return plainText;
 																	
 }
 
 static int getCharCode(char ch) {
	 return (int)ch-65;
 }
 
 static char getChar(int x) {	 
	 return (char)(x+65);
 }
 
 static Integer calcInverse(int keyMatrix[][]) {
	 
	 int det=keyMatrix[0][0]*keyMatrix[1][1]-keyMatrix[0][1]*keyMatrix[1][0];

	 det=(det%26+26)%26;  // If det is negative +26%26 is needed is get a positive number between 1 and 25
						  // Now we need to find (det)^-1  , use the formula (det)(det^-1) = 1 mod 26 , we need to find (det^-1) we doo this using... (det*i) % 26 == 1 here i = (det^-1)
	 if(det==0)
		 return null;
	
	 int di=0;
     for(int i=0;i<26;i++)
    	 if((det*i)%26 == 1) 
    		 di = i; 
    
     if(di==0) 
    	return null; // Wrong key try another key
	
	 return di;
 }


static int[][] createKeyMatrix(String key){
	
	int matrix[][]=new int[2][2];
	int k=0;
	
	for(int i=0;i<2;i++)
		for(int j=0;j<2;j++)
			matrix[i][j]=getCharCode(key.charAt(k++));
	
	return matrix;
	
	
}
 
static int[] createMsgMatrix(String plainText) {
	
	int matrix[]=new int[2];
	
	for(int i=0;i<2;i++) 
		matrix[i]=getCharCode(plainText.charAt(i));
	
	return matrix;
			
}

 
 static String encrypt(int msgMatrix[],int keyMatrix[][]) {
	 
	 int sum=0;
	 
	 String cipherText="";
	 
	 for(int i=0;i<2;i++) {
		 
		 sum=0;
		 
		 for(int j=0;j<2;j++) 
			 sum+=msgMatrix[j]*keyMatrix[i][j];
		 		 
		 cipherText+= getChar(sum%26);		 
	 }
	 
	 return cipherText;

 }
 
 
static String decrypt(int cipherMatrix[],int keyMatrix[][]) {	
	
	int di=calcInverse(keyMatrix);

    //Find the Adjugate Matrix
    
    int tmp=keyMatrix[0][0];
    
    keyMatrix[0][0]=(keyMatrix[1][1]+26)%26;
    
    keyMatrix[1][1]=(tmp+26)%26;
    
    keyMatrix[0][1]=(-keyMatrix[0][1]+26)%26;
    
    keyMatrix[1][0]=(-keyMatrix[1][0]+26)%26;
    
    //Multiply the Multiplicative Inverse of the Determinant by the Adjugate Matrix
    //Then we take each of these answers modulo 26    
    
    for(int i=0;i<2;i++)
    	for(int j=0;j<2;j++)
    		keyMatrix[i][j] = (keyMatrix[i][j]*di)%26;
    	
	 int sum=0;
	 
	 String plainText="";
	 
	 for(int i=0;i<cipherMatrix.length;i++) {
		 
		 sum=0;
		 
		 for(int j=0;j<cipherMatrix.length;j++) 
			 sum+=keyMatrix[i][j]*cipherMatrix[j];
		 		 
		 plainText+= getChar(sum%26);		 
	 }
	 
	 return plainText;	
 }

}