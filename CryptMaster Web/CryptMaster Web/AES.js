class AES{

static constraint(){

  return ["Key size must be 16, 24 or 32 bytes"];
}

Encrypt_Driver(plainText,key){

 key = this.convertBinaryStringToUint8Array(key);
 let textBytes = aesjs.utils.utf8.toBytes(plainText);
 let aesCtr = new aesjs.ModeOfOperation.ctr(key, new aesjs.Counter(5));
 let encryptedBytes = aesCtr.encrypt(textBytes);
 return this.convertUint8ArrayToBinaryString(encryptedBytes);
}

Decrypt_Driver(cipherText,key){

  key = this.convertBinaryStringToUint8Array(key);
  let encryptedBytes =  this.convertBinaryStringToUint8Array(cipherText);
  let aesCtr = new aesjs.ModeOfOperation.ctr(key, new aesjs.Counter(5));
  let decryptedBytes = aesCtr.decrypt(encryptedBytes);
 return this.convertUint8ArrayToBinaryString(decryptedBytes);
}


convertUint8ArrayToBinaryString(u8Array) {
  var i, len = u8Array.length, b_str = "";
  for (i=0; i<len; i++) {
    b_str += String.fromCharCode(u8Array[i]);
  }
  return b_str;
}

convertBinaryStringToUint8Array(bStr) {
  var i, len = bStr.length, u8_array = new Uint8Array(len);
  for (var i = 0; i < len; i++) {
    u8_array[i] = bStr.charCodeAt(i);
  }
  return u8_array;
}

}

