
// Ploy alphabetic substitution Cipher

public class PSC {
    
   /* public static void main(String args[]){
        
        String tmp=Encrypt_Driver("Arpan is a Good boy !","Testing1@");
        
        System.out.println(tmp);
        
        System.out.println(Decrypt_Driver(tmp,"Testing1@"));
        
        
    }*/

 static String Encrypt_Driver(String plainText,String key) {
		 
	 char matrix[][]=createSubstitutionMatrix();
	 
	 String newKey=makeKey(plainText.length(),key);
	 
	 String cipher=encrypt(plainText,newKey,matrix);
	 
	 return cipher;
 }

 static String Decrypt_Driver(String cipherText,String key) {
	 
	 char matrix[][]=createSubstitutionMatrix();
	 
	 String newKey=makeKey(cipherText.length(),key);
	 
	 String plainText=decrypt(cipherText,newKey,matrix);
	 
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


 static char[][] createSubstitutionMatrix() {
	 	 
	 int ascii=32;
	 
	 char matrix[][]=new char[94][94];
	 
	 for (int i = 0; i < 94; i++) {
	     
	    ascii=32+i;
		 
		 for (int j = 0; j < 94; j++) {
			 
			 matrix[j][i]=(char)ascii;
			 
			 ascii++;
			 
			 if(ascii>126)
				 ascii=32;			 
			 
		 }
	 }
	 
	 return matrix;
	 
 }


 
 static String encrypt(String src,String newKey,char matrix[][]) {
	 
	 String cipherText="";
	 
	 for(int i=0;i<src.length();i++) 
	 
	     
		 cipherText+= matrix[  ((int)newKey.charAt(i))-32  ][  ((int)src.charAt(i))-32  ];

	 
	 
	 return cipherText; 

 }



 static String decrypt(String cipherText, String newKey, char matrix[][]) {

	 
	 String plainText="";
	 
	 for(int i=0;i<newKey.length();i++) {
		 
		 int row=(newKey.charAt(i))-32;
		 int ch=cipherText.charAt(i);
		 
		 for(int j=0;j<94;j++) 			 
			 if( matrix[row][j]==ch) 				 
				 plainText+= matrix[0][j];
			 		 		 
	 }
	 
	 return plainText;

 }

 

}