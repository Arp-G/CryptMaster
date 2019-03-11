class keyValue{
	constructor(key,value){
		this.key=key;
		this.value=value;
	}	
	
}


class Masc{
	
	test(){
		
		let plainText = "Hello this is a Test..!!!";
		let cipherTextAndKey=this.Encrypt_Driver(plainText);
		let cipherText=cipherTextAndKey[0];
		let key=cipherTextAndKey[1];
		console.log("Plain Text : "+plainText);
		console.log("Cipher Text : "+cipherText);
		console.log("Key :"+key);
		let decryptedText=this.Decrypt_Driver(cipherText,key);
		console.log("Decrypted Text : "+decryptedText);
		
		}

  static constraint(){

    return ["Supports encryption/decryption of letters,numbers,spaces and all special characters only"];
  } 
		
	restoreMap(key){
		
		let map=[];
		
		for(let i=0;i<key.length;i=i+2)
			map.push(new keyValue(key.charAt(i),key.charAt(i+1)));
		
		return map;
		
	}
	
	makeKey(map){
		
		let key="";
		for(let ob of map)
		  key+=ob.key+ob.value;
		
		return key;
	}
	
	Encrypt_Driver(plainText){
		
		let distinctChars= this.usedChars(plainText);
		
		let map = this.createMap(distinctChars);
				
		let key = this.makeKey(map);
		
		return [this.encrypt(plainText,map),key];
		
	}
	
	Decrypt_Driver(cipherText,map){
		
		map=this.restoreMap(map);
		
		return this.decrypt(cipherText,map);
	}
	
	
	usedChars(plainText){
		
		let arr=plainText.split("");
		
		let distinctChars=[];
		
		for(let i=32;i<=126;i++){
			let ch=String.fromCharCode(i);
			for(let j of arr){
				if(ch==j){
					distinctChars.push(ch);
					break;
					}
				}
			}
		return distinctChars;
	}
	
	getRandom(min, max) {
    return Math.random() * (max - min) + min;
}
	
	createMap(usedChars){
		
		let map=[];
		
		let charPool=[];
		
		for(let i=32;i<=126;i++)
			charPool.push(String.fromCharCode(i));
			
			
		
		for(let j of usedChars){
			let randomIndex=this.getRandom(0,charPool.length);
			randomIndex=Math.floor(randomIndex);
			map.push( new keyValue(j,charPool[randomIndex]));
			charPool.splice(randomIndex,1);
			}
			
			return map;
		}
		
		encrypt(plainText,map){
			
			let cipherText="";
			
			for(let i=0;i<plainText.length;i++){
				let ch=plainText.charAt(i);
				for (let ob of map){
					if(ob.key==ch){
						cipherText+=ob.value;
						break;
						}
					}
				}
				return cipherText;
			}
			
			decrypt(cipherText,map){
			
			let plainText="";
			
			for(let i=0;i<cipherText.length;i++){
				let ch=cipherText.charAt(i);
				for (let ob of map){
					if(ob.value==ch){
						plainText+=ob.key;
						break;
						}
					}
				}
				return plainText;
			}		
}


  