

/*
 * 
 * The decrypted text will contain a trailing 'X' if the original plain text had odd number of characters
 * Can only work for a small single word containing letters (A to Z)
 * Fails to maintain letter case, decrypted text is always in upper case
 * The algorithm fails when the plain text has similar consecutive characters Example: Hello, hammer ,etc
 * 
*/

class PlayFairCipher {


  static constraint(){

    return ["Supports encryption/decryption of a small single word containing letters (A to Z)",
            "The decrypted text will contain a trailing 'X' if the original plain text had odd number of characters",
            "Fails to maintain letter case, decrypted text is always in upper case",
            "The algorithm fails when the plain text has similar consecutive characters Example: Hello, hammer ,etc"];
  }
    
    test(){
    	
    	let plainText="HeloWorlds";

    	plainText=plainText.toUpperCase();
        
        let tmp=this.Encrypt_Driver(plainText,"PLAYFAIRENCRYPTION");
        
        console.log("Plain text: "+plainText);
        
        console.log("cipher text: "+tmp);
        
        console.log("decrypted text: "+this.Decrypt_Driver(tmp,"PLAYFAIRENCRYPTION"));  
        
        
    }

    Encrypt_Driver(plainText,key) {
		 
	 let matrix=this.createPopulationMatrix(key);
	 
	 let cipher=this.encrypt(plainText,matrix);
	 
	 return cipher;
 }

    Decrypt_Driver(cipherText,key) {
	 
	 let matrix=this.createPopulationMatrix(key);
	 
	 let plainText=this.decrypt(cipherText,matrix);
	 
	 return plainText;
 																	
 }


    createPopulationMatrix(key) {
	 
	 let populationMatrix=[[],[],[],[],[]];
	 	 
	 let keyArr=key.split('');
	 
	 let distinctKeyChars=[];
	 
	 let alphabets="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	 
	 for(let i=0;i<keyArr.length;i++)
	 {
	 	let flag=true;
	 	
	 	for (let k of distinctKeyChars){
	 		
 			if(k==keyArr[i]){
 				
 				flag=false;
 				break;
 			}
 			
 		}
 		
 		if(flag)
		   distinctKeyChars.push(keyArr[i]);
		 //distinctKeyChars.sort();
		 
		 if(keyArr[i]=='I') 
			 alphabets=alphabets.replace("J","");
		 
		 if(keyArr[i]=='J') 
			 alphabets=alphabets.replace("I","");
			 
	      
		 
	 }
	 
	
	
	for(let ch of distinctKeyChars)
		alphabets=alphabets.replace(""+ch,"");
	
	let k=0;
	
	for(let i=0;i<5;i++) {
		for(let j=0;j<5;j++) {
			
			let ch;
			
			if(distinctKeyChars.length>0){
				
				ch=distinctKeyChars.shift();
				//distinctKeyChars.sort();
				}
			else {				
				ch=alphabets.charAt(k);
				k++;
			}	
			populationMatrix[i][j]=ch;
			
		}
	}
	
	return populationMatrix;
	 
 }
 
 


 
    encrypt(plainText,matrix) {
	 
	 let row1,col1,row2,col2;
	 let ch1,ch2;
	 let result="";
	 row1=col1=row2=col2=0;
	 
 	if(plainText.length%2!=0)
 		plainText+="X";
	
	for(let i=0;i<plainText.length-1;i=i+2) {
		
		ch1=plainText.charAt(i);
		ch2=plainText.charAt(i+1);
		
		for(let j=0;j<5;j++) {
			for(let k=0;k<5;k++) {
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

 
     decrypt(cipherText,matrix) {
	 
	 let row1,col1,row2,col2;
	 let ch1,ch2;
	 let result="";
	 row1=col1=row2=col2=0;
	 
 	if(cipherText.length%2!=0)
 		cipherText+="X";
	
	for(let i=0;i<cipherText.length-1;i=i+2) {
		
		ch1=cipherText.charAt(i);
		ch2=cipherText.charAt(i+1);
		
		for(let j=0;j<5;j++) {
			for(let k=0;k<5;k++) {
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
 
}

