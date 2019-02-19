import java.util.TreeSet;

/*
 * 
 * The decrypted text will contain a trailing 'X' if the original plain text had odd number of characters
 * Can only work for a single word containing letters (A to Z)
 * Fails to maintain letter case, decrypted text is always in upper case
 * The algorithm fails when the plain text has similar consecutive characters Example: Hello, hammer ,etc
 * 
*/


public class PlayFairCipher {
    
    public static void main(String args[]){
    	
    	String plainText="HeloWorlds";

    	plainText=plainText.toUpperCase();
        
        String tmp=Encrypt_Driver(plainText,"PLAYFAIRENCRYPTION");
        
        System.out.println("Plain text: "+plainText);
        
        System.out.println("cipher text: "+tmp);
        
        System.out.println("decrypted text: "+Decrypt_Driver(tmp,"PLAYFAIRENCRYPTION"));  
        
        
    }

 static String Encrypt_Driver(String plainText,String key) {
		 
	 char matrix[][]=createPopulationMatrix(key);
	 
	 String cipher=encrypt(plainText,matrix);
	 
	 return cipher;
 }

 static String Decrypt_Driver(String cipherText,String key) {
	 
	 char matrix[][]=createPopulationMatrix(key);
	 
	 String cipher=decrypt(cipherText,matrix);
	 
	 return cipher;
 																	
 }


 static char[][] createPopulationMatrix(String key) {
	 
	 char populationMatrix[][]=new char[5][5];
	 	 
	 char keyArr[]=key.toCharArray();
	 
	 TreeSet<Character> distinctKeyChars=new TreeSet<>();
	 
	 String alphabets="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	 
	 for(int i=0;i<keyArr.length;i++)
	 {
		 distinctKeyChars.add(keyArr[i]);
		 
		 if(keyArr[i]=='I') 
			 alphabets=alphabets.replace("J","");
		 
		 if(keyArr[i]=='J') 
			 alphabets=alphabets.replace("I","");
		 
	 }
	 
	
	
	for(Character ch:distinctKeyChars)
		alphabets=alphabets.replace(""+ch,"");
	
	int k=0;
	
	for(int i=0;i<5;i++) {
		for(int j=0;j<5;j++) {
			
			char ch;
			
			if(distinctKeyChars.size()>0)
				ch=distinctKeyChars.pollFirst();
			else {				
				ch=alphabets.charAt(k);
				k++;
			}	
			populationMatrix[i][j]=ch;
			
		}
	}
	
	return populationMatrix;
	 
 }
 
 


 
 static String encrypt(String src,char matrix[][]) {
	 
	 int row1,col1,row2,col2;
	 char ch1,ch2;
	 String result="";
	 row1=col1=row2=col2=0;
	 
 	if(src.length()%2!=0)
		src+="X";
	
	for(int i=0;i<src.length()-1;i=i+2) {
		
		ch1=src.charAt(i);
		ch2=src.charAt(i+1);
		
		for(int j=0;j<5;j++) {
			for(int k=0;k<5;k++) {
				if(ch1==matrix[j][k]) {
					row1=j;
					col1=k;
				}
				
				if(ch2==matrix[j][k]) {
					row2=j;
					col2=k;
				}
			}
		}
		
		if(row1==row2) {
			result+= (col1==4)?matrix[row1][0]:matrix[row1][col1+1];
			result+= (col2==4)?matrix[row2][0]:matrix[row2][col2+1];
		}
		
		else if(col1==col2) {
			result+= (row1==4)?matrix[0][col1]:matrix[row1+1][col1];
			result+= (row2==4)?matrix[0][col2]:matrix[row2+1][col2];
		}
		else
		{	
			result+= matrix[row2][col1];
			result+= matrix[row1][col2];
		}
		
	}
	
	return result;
 }

 
static String decrypt(String src,char matrix[][]) {
	 
	 int row1,col1,row2,col2;
	 char ch1,ch2;
	 String result="";
	 row1=col1=row2=col2=0;
	 
 	if(src.length()%2!=0)
		src+="X";
	
	for(int i=0;i<src.length()-1;i=i+2) {
		
		ch1=src.charAt(i);
		ch2=src.charAt(i+1);
		
		for(int j=0;j<5;j++) {
			for(int k=0;k<5;k++) {
				if(ch1==matrix[j][k]) {
					row1=j;
					col1=k;
				}
				
				if(ch2==matrix[j][k]) {
					row2=j;
					col2=k;
				}
			}
		}
		
		if(row1==row2) {
			result+= (col1==0)?matrix[row1][4]:matrix[row1][col1-1];
			result+= (col2==0)?matrix[row2][4]:matrix[row2][col2-1];
		}
		
		else if(col1==col2) {
			result+= (row1==0)?matrix[4][col1]:matrix[row1-1][col1];
			result+= (row2==0)?matrix[4][col2]:matrix[row2-1][col2];
		}
		else
		{	
			result+= matrix[row2][col1];
			result+= matrix[row1][col2];
		}
		
	}
	
	return result;
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