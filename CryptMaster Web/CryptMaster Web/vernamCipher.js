// Key length should be smaller than plain Text length

class VernamCipher {

  static constraint(){

  return ["Supports encryption/decryption of letters,numbers,spaces and all special characters only",
  "The key specified should be smaller in length than the plain Text that is to be encrypted"];
}
    
     test(){
    	
    	 let plainText="How Are You Arpan. Hello !";
    	
        console.log(plainText);
        
        let tmp=this.Encrypt_Driver(plainText,"Big@@rpKE%^y");
        
        console.log(tmp);
        
        console.log(this.Decrypt_Driver(tmp,"Big@@rpKE%^y"));         
        
    }
    

 Encrypt_Driver( plainText, key) {
		 	 
	 key=this.makeKey(plainText.length,key);	 
	 
	 return this.encrypt(plainText,key);
 }

 Decrypt_Driver(cipherText, key) {
	 
	 key=this.makeKey(cipherText.length,key);	 
	 
	 return this.decrypt(cipherText,key);											
 }
 
 
 encrypt(plainText, key) {
	 
	 let cipherText="";
	 
	 let total;
	 
	 for(let i=0;i<plainText.length;i++) {
		 
		 
		 total= this.getCharCode(plainText.charAt(i)) + this.getCharCode(key.charAt(i));
		 
		 total = (total)>94 ? total-94 : total;
		 
		 cipherText+=this.getCodeChar(total);
		 
		 
	 }
	 
	 return cipherText;
	 
 }
 
 
 decrypt( cipherText, key) {
	 
	 let plainText="";
	 
	 let total;
	 
	 for(let i=0;i<cipherText.length;i++) {
		 
		 
		 total= this.getCharCode(cipherText.charAt(i)) - this.getCharCode(key.charAt(i));
		 
		 total = (total)<0 ? total+94 : total;
		 
		 plainText+=this.getCodeChar(total);
		 
		 
	 }
	 
	 return plainText;
	 
 }
 
     makeKey(len, key) {
   	 
   	 let newKey="";
   	 
   	 let j=0;
   	 
   	 for(let i=0;i<len;i++) {
   	     
   	     if(j>=key.length) 
   			 j=0;
   		 
   		 newKey+=key.charAt(j);	 
   		 j++;
   	 }
   	 
   	 return newKey;
   	 
    }
    
    
    getCharCode(ch) {
   	 return ch.charCodeAt(0)-32;
    }
    
    getCodeChar(x) {
      	 return String.fromCharCode(32+x);
    }
 
}
