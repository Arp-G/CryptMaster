class RailFence {

  static constraint(){

  return ["Supports encryption/decryption of letters,numbers,spaces and all special characters only"];
}
    
      test(){
    	
    	let plainText="Come Home Tomorrow";
    	
    	console.log(plainText);
        
        let tmp=this.Encrypt_Driver(plainText);
                     
        console.log(tmp);
        
        console.log(this.Decrypt_Driver(tmp));
        
        
    }

 Encrypt_Driver(plainText) {
		 
	 return this.encrypt(plainText);
	 
 }

 Decrypt_Driver(cipherText) {
	 
	 return this.decrypt(cipherText);
 																	
 }
 

 
 encrypt(plainText) {
	 
	 let arr=plainText.split('');
	 
	 let r1,r2;

	 r1=r2="";
	 
	 for(let i=0;i<arr.length;i++) {
		 
		 if(i%2==0)
			 r1+=arr[i];
		 else
			 r2+=arr[i];
		 		 
	 }
	 
	 return r1+r2;

 }



 decrypt(cipherText) {

	let len=Math.ceil(cipherText.length/2.0); //Making sure the first half has greater characters than the 2nd half in case of strings of odd length
	
	let r1=cipherText.substring(0, len);
	
	let r2=cipherText.substring(len,cipherText.length);
	
	let j,k; 
	j=k=0;
	
	let plainText="";
	
	for(let i=0;i<cipherText.length;i++) 
		plainText += (i%2==0) ?  r1.charAt(j++) : r2.charAt(k++);
		
	return plainText;	

 } 

}
