
// A simple transposition cipher

public class RailFence {
    
    public static void main(String args[]){
    	
    	String plainText="Come Home Tomorrow";
    	
    	System.out.println(plainText);
        
        String tmp=Encrypt_Driver(plainText);
                     
        System.out.println(tmp);
        
        System.out.println(Decrypt_Driver(tmp));
        
        
    }

 static String Encrypt_Driver(String plainText) {
		 
	 return encrypt(plainText);
	 
 }

 static String Decrypt_Driver(String cipherText) {
	 
	 return decrypt(cipherText);
 																	
 }
 

 
 static String encrypt(String plainText) {
	 
	 char arr[]=plainText.toCharArray();
	 
	 String r1,r2;

	 r1=r2="";
	 
	 for(int i=0;i<arr.length;i++) {
		 
		 if(i%2==0)
			 r1+=arr[i];
		 else
			 r2+=arr[i];
		 		 
	 }
	 
	 return r1+r2;

 }



 static String decrypt(String cipherText) {

	int len=(int)Math.ceil(cipherText.length()/2.0); //Making sure the first half has greater characters than the 2nd half in case of strings of odd length
	
	String r1=cipherText.substring(0, len);
	
	String r2=cipherText.substring(len,cipherText.length());
	
	int j,k; 
	j=k=0;
	
	String plainText="";
	
	for(int i=0;i<cipherText.length();i++) 
		plainText += (i%2==0) ?  r1.charAt(j++) : r2.charAt(k++);
		
	return plainText;	

 } 

}