
// Simple columnar transposition Technique with 3 passes for stronger encryption

// Plain Text must not contain '!' symbol

// Plain Text must contain more than 6 characters for proper encryption



public class ColumnTransposition {
 
 public static void main(String args[]){
 	
 	String plainText="Come Home Tomorrow";
 	
 	System.out.println(plainText);
     
     String tmp=Encrypt_Driver(plainText);
                  
     System.out.println(tmp);
     
     System.out.println(Decrypt_Driver(tmp));
     
     
 }

static String Encrypt_Driver(String plainText) {
		 
	 String tmp=encrypt(plainText,true); // First pass take columns in increasing order
	 
	 tmp=encrypt(tmp,false); // Second pass take columns in decreasing order
	 
	 tmp=encrypt(tmp,true); // Third pass take columns in increasing order
	 
	 return tmp;
	 
}

static String Decrypt_Driver(String cipherText) {
	 
	 String tmp=decrypt(cipherText,true); // First pass take columns in increasing order
	 
	 tmp=decrypt(tmp,false); // Second pass take columns in decreasing order
	 
	 tmp=decrypt(tmp,true); // Third pass take columns in increasing order 
	 
	 tmp=tmp.replaceAll("!","");
	 
	 return tmp;
																	
}


static String decrypt(String cipherText , boolean flag) {
	
	int row= (int)Math.ceil(cipherText.length()/6.0);
	
	char tanspositionMatrix[][]=new char[row][6];
	
	int k=0;
	
	String plainText="";
	
	for(int i=0;i<6;i++) 		
		for(int j=0;j<row;j++) 			
			tanspositionMatrix[j][i] = cipherText.charAt(k++);
	
	if(flag) 
		
		for(int i=0;i<row;i++) 		
			for(int j=0;j<6;j++) 
				plainText+=tanspositionMatrix[i][j];
	else
		
		for(int i=0;i<row;i++) 		
			for(int j=5;j>=0;j--) 
				plainText+=tanspositionMatrix[i][j];
	
	return plainText;
	
	
}



static String encrypt(String plainText , boolean flag) {
	
	int row= (int)Math.ceil(plainText.length()/6.0);
	
	char tanspositionMatrix[][]=new char[row][6];
	
	int k=0;
	
	for(int i=0;i<row;i++) 		
		for(int j=0;j<6;j++) 			
			tanspositionMatrix[i][j] = (k<plainText.length())? plainText.charAt(k++) : '!';
			
	String result="";

			
	if(flag) 		
		
		for(int i=0;i<6;i++) 		
			for(int j=0;j<row;j++) 			
				result+=tanspositionMatrix[j][i];			
			
	else
		
		for(int i=5;i>=0;i--) 		
			for(int j=0;j<row;j++) 			
				result+=tanspositionMatrix[j][i];			
			
		
	
	return result;
	

}

}