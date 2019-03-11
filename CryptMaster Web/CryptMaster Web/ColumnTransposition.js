
// Simple columnar transposition Technique with 3 passes for stronger encryption

// Plain Text must not contain '!' symbol

// Plain Text must contain more than 6 characters for proper encryption

String.prototype.replaceAll = 
	function(search, replacement) { 
	 var target = this;
 	return target.replace(new RegExp(search, 'g'), replacement); 
 	
 	};

class ColumnTransposition {

  static constraint(){

    return ["Supports encryption/decryption of letters,numbers,spaces and all special characters only",
            "Plain Text must not contain '!' symbol",
            "Plain Text must contain more than 6 characters for proper encryption"];
  }
 
 test(){
 	
 	let plainText="Come Home Tomorrow";
 	
 	console.log(plainText);
     
     let tmp=this.Encrypt_Driver(plainText);
                  
     console.log(tmp);
     
     console.log(this.Decrypt_Driver(tmp));
     
     
 }

Encrypt_Driver(plainText) {
		 
	 let tmp=this.encrypt(plainText,true); // First pass take columns in increasing order
	 
	 tmp=this.encrypt(tmp,false); // Second pass take columns in decreasing order
	 
	 tmp=this.encrypt(tmp,true); // Third pass take columns in increasing order
	 
	 return tmp;
	 
}

Decrypt_Driver( cipherText) {
	 
	 let tmp=this.decrypt(cipherText,true); // First pass take columns in increasing order
	 
	 tmp=this.decrypt(tmp,false); // Second pass take columns in decreasing order
	 
	 tmp=this.decrypt(tmp,true); // Third pass take columns in increasing order 
	 
	 tmp=tmp.replaceAll("!","");
	 
	 return tmp;
																	
}


  decrypt(cipherText , flag) {
	
	let row= Math.ceil(cipherText.length/6.0);
	
	let transpositionMatrix=[];
	
	
	for(let i=0;i<row;i++)
	  transpositionMatrix.push([],[],[],[],[],[]);
	  
	let k=0;
	
	let plainText="";
	
	for(let i=0;i<6;i++) 		
		for(let j=0;j<row;j++) 			
			transpositionMatrix[j][i] = cipherText.charAt(k++);
	
	if(flag) 
		
		for(let i=0;i<row;i++) 		
			for(let j=0;j<6;j++) 
				plainText+=transpositionMatrix[i][j];
	else
		
		for(let i=0;i<row;i++) 		
			for(let j=5;j>=0;j--) 
				plainText+=transpositionMatrix[i][j];
	
	return plainText;
	
	
}



  encrypt( plainText , flag) {
	
	let row= Math.ceil(plainText.length/6.0);
	
	let transpositionMatrix=[];
	for(let i=0;i<row;i++)
	  transpositionMatrix.push([],[],[],[],[],[]);
	let k=0;
	
	for(let i=0;i<row;i++) 		
		for(let j=0;j<6;j++) 			
			transpositionMatrix[i][j] = (k<plainText.length)? plainText.charAt(k++) : '!';
			
	let result="";

			
	if(flag) 		
		
		for(let i=0;i<6;i++) 		
			for(let j=0;j<row;j++) 			
				result+=transpositionMatrix[j][i];			
			
	else
		
		for(let i=5;i>=0;i--) 		
			for(let j=0;j<row;j++) 			
				result+=transpositionMatrix[j][i];			
			
		
	
	return result;
	

}

}
