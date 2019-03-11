class Psc{

  Encrypt_Driver(plainText,key) {

  let matrix = this.createSubstitutionMatrix();

  let newKey = this.makeKey(plainText.length, key);

  let cipher = this.encrypt(plainText, newKey, matrix);

  return cipher;
}

 Decrypt_Driver(cipherText,key) {

  let matrix = [];

  for (let i = 0; i < 94; i++) //declare the 2nd dimension of the matrix
    matrix[i] = [];

  matrix = this.createSubstitutionMatrix();

  let newKey = this.makeKey(cipherText.length, key);

  let plainText = this.decrypt(cipherText, newKey, matrix);

  return plainText;

}

static constraint(){

  return ["Supports characters are all english alphabets,numbers and special characters."];
}

 makeKey(len, key) {

  let newKey = "";

  let j = 0;

  for (let i = 0; i < len; i++) {

    if (j >= key.length)
      j = 0;

    newKey += key.charAt(j);
    j++;
  }

  return newKey;

}

 createSubstitutionMatrix() {

  let ascii = 32;

  let matrix = [];

  for (let i = 0; i < 94; i++) //declare the 2nd dimension of the matrix
    matrix[i] = [];

  for (let i = 0; i < 94; i++) {

    ascii = 32 + i;

    for (let j = 0; j < 94; j++) {

      matrix[j][i] = String.fromCharCode(ascii);

      ascii++;

      if (ascii > 126)
        ascii = 32;

    }
  }

  return matrix;

}



 encrypt(src, newKey, matrix) {

  let cipherText = "";

  for (let i = 0; i < src.length; i++)
    cipherText += matrix[(newKey.charAt(i)).charCodeAt(0) - 32][(src.charAt(i)).charCodeAt(0) - 32];


  return cipherText;
}



 decrypt(cipherText, newKey, matrix) {

  let plainText = "";

  for (let i = 0; i < newKey.length; i++) {

    let row = (newKey.charAt(i)).charCodeAt(0) - 32;
    let ch = cipherText.charAt(i);

    for (let j = 0; j < 94; j++) {
      if (matrix[row][j] == ch)
        plainText += matrix[0][j];
    }

  }

  return plainText;

}

}