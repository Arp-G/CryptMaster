/*
 * 
 * The decrypted text will contain a trailing 'X' if the original plain text had odd number of characters
 * Can only work for a single word containing letters (A to Z)
 * Fails to maintain letter case, decrypted text is always in upper case
 * The key given must have exactly 4 letters in caps
 * The determinant of the key specified must be non-zero
 *
 * References:
 * 
 * https://crypto.interactive-maths.com/hill-cipher.html
 * http://practicalcryptography.com/ciphers/hill-cipher/
 * https://www.geeksforgeeks.org/hill-cipher/
 * https://en.wikipedia.org/wiki/Hill_cipher
 * 
*/

class HillCipher {

  static constraint(){

    return ["Supports encryption/decryption of a single word containing letters (A to Z)",
            "The decrypted text will contain a trailing 'X' if the original plain text had odd number of characters",
            "Fails to maintain letter case, decrypted text is always in upper case",
            "The key specified must have exactly 4 letters in uppercase",
            "The determinant of the key specified must be non-zero",
            "It is recommended to use the key already given"];
  }
 
    
        test(){
    	
    	let plainText="TheAttackWillBeAtMidNightToday";
    	
    	let key="LIHH";

    	plainText=plainText.toUpperCase();
        
        let tmp=this.Encrypt_Driver(plainText,key);
        
        if(tmp==null) {
        	console.log("Wrong key,try a different key, cannot Encrypt !");
        	return;
        }
        
        console.log("Plain text:     "+plainText);
        
        console.log("Cipher text:    "+tmp);
        
        tmp=this.Decrypt_Driver(tmp,key);
        
        if(tmp==null) {
        	console.log("Wrong key,try a different key, cannot Decrypt !");
        	return;
        }
        
        console.log("Decrypted text: "+tmp);  
        
        
    }
    
        Encrypt_Driver(plainText,key) {
    	
    	if(this.calcInverse(this.createKeyMatrix(key))==null)
    		return null; //Wrong key
    	
    	if(plainText.length%2!=0)
    		plainText+="X";
    	
    	let cipherText="";
    	
    	for(let i=0;i<plainText.length-1;i=i+2) {
    		
    		let tmp=this.Part_Encrypt_Driver( plainText.substring(i,i+2) , key);
    		cipherText+= tmp;
    	}
    	
    	
    	return cipherText;
    			
    }
    
         Decrypt_Driver(cipherText,key) {
    	
    	if(this.calcInverse(this.createKeyMatrix(key))==null)
    		return null; //Wrong key
    	
    	let plainText="";
    	
    	for(let i=0;i<cipherText.length-1;i=i+2) {
    		
    		let tmp=this.Part_Decrypt_Driver( cipherText.substring(i,i+2) , key);
    		plainText+= tmp;
    	}
    	
    	return plainText;
    			
    }
    
    

         Part_Encrypt_Driver(plainText,key) {
	 
	     let keyMatrix=this.createKeyMatrix(key);
	 
    	 let msgMatrix=this.createMsgMatrix(plainText);
	 
	     let cipherText=this.encrypt(msgMatrix,keyMatrix);
	 
	     return cipherText;
	     
          }

         Part_Decrypt_Driver(cipherText,key) {	 
	 
	    let keyMatrix=this.createKeyMatrix(key);
	 
	    let cipherMatrix=this.createMsgMatrix(cipherText);
	 
	    let plainText=this.decrypt(cipherMatrix,keyMatrix);
	 
	    return plainText;
 																	
         }
 
          getCharCode(ch) {
	      return ch.charCodeAt(0)-65;
 		}
 
 		getChar(x) {	 
	 	return String.fromCharCode(x+65);
 		}
 
 		calcInverse(keyMatrix) {
	 
	 	let det=keyMatrix[0][0]*keyMatrix[1][1]-keyMatrix[0][1]*keyMatrix[1][0];

	 	det=(det%26+26)%26;  // If det is negative +26%26 is needed is get a positive number between 1 and 25
						  // Now we need to find (det)^-1  , use the formula (det)(det^-1) = 1 mod 26 , we need to find (det^-1) we doo this using... (det*i) % 26 == 1 here i = (det^-1)
		 if(det==0)
			 return null;
	
	 let di=0;
     for(let i=0;i<26;i++)
    	 if((det*i)%26 == 1) 
    		 di = i; 
    
     if(di==0) 
    	return null; // Wrong key try another key
	
	 return di;
 }


 createKeyMatrix( key){
	
	 let matrix=[[],[]];
	 let k=0;
	
	for(let i=0;i<2;i++)
		for(let j=0;j<2;j++)
			matrix[i][j]=this.getCharCode(key.charAt(k++));
	
	return matrix;
	
	
}
 
createMsgMatrix(plainText) {
	
	let matrix=[[],[]];
	
	for(let i=0;i<2;i++) 
		matrix[i]=this.getCharCode(plainText.charAt(i));
	
	return matrix;
			
}

 
 encrypt(msgMatrix,keyMatrix) {
	 
	 let sum=0;
	 
	 let cipherText="";
	 
	 for(let i=0;i<2;i++) {
		 
		 sum=0;
		 
		 for(let j=0;j<2;j++) 
			 sum+=msgMatrix[j]*keyMatrix[i][j];
		 		 
		 cipherText+= this.getChar(sum%26);		 
	 }
	 
	 return cipherText;

 }
 
 
decrypt(cipherMatrix,keyMatrix) {	
	
	let di=this.calcInverse(keyMatrix);

    //Find the Adjugate Matrix
    
    let tmp=keyMatrix[0][0];
    
    keyMatrix[0][0]=(keyMatrix[1][1]+26)%26;
    
    keyMatrix[1][1]=(tmp+26)%26;
    
    keyMatrix[0][1]=(-keyMatrix[0][1]+26)%26;
    
    keyMatrix[1][0]=(-keyMatrix[1][0]+26)%26;
    
    //Multiply the Multiplicative Inverse of the Determinant by the Adjugate Matrix
    //Then we take each of these answers modulo 26    
    
    for(let i=0;i<2;i++)
    	for(let j=0;j<2;j++)
    		keyMatrix[i][j] = (keyMatrix[i][j]*di)%26;
    	
	 let sum=0;
	 
	 let plainText="";
	 
	 for(let i=0;i<cipherMatrix.length;i++) {
		 
		 sum=0;
		 
		 for(let j=0;j<cipherMatrix.length;j++) 
			 sum+=keyMatrix[i][j]*cipherMatrix[j];
		 		 
		 plainText+= this.getChar(sum%26);		 
	 }
	 
	 return plainText;	
 }

}


