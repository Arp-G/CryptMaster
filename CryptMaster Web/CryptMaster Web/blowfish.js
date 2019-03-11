//https://github.com/egoroof/blowfish

class BF{

  static constraint(){
    return ["All input data including key, IV, plaintext and ciphertext should be a String or ArrayBuffer / Buffer. Strings support all unicode including emoji",
    "The algorithm may fail for plain text lengths which are not multiple of 8 or for very big plain texts",
    "Use simple small keys only"];
  }

Encrypt_Driver(plainText,key){

  const bf = new Blowfish(key, Blowfish.MODE.ECB, Blowfish.PADDING.NULL); 
  bf.setIv('abcdefgh');
  let encoded = bf.encode(plainText);
  let encodedString = this.convertUint8ArrayToBinaryString(encoded);
  return encodedString;
}

Decrypt_Driver(cipherText,key){

const bf = new Blowfish(key, Blowfish.MODE.ECB, Blowfish.PADDING.NULL); 
let decoded= this.convertBinaryStringToUint8Array(cipherText);
let decodedString = bf.decode(decoded, Blowfish.TYPE.STRING); // type is optional
return decodedString;
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

