


function load_Contraint(){

  let choice=document.getElementById("Strategy").value;
  let constraint=document.getElementById("constraint");

  switch(choice){

    case "AES":
      var c = AES.constraint();
      document.getElementById("key").disabled = false;
      break;

    case "Blowfish":
      var c = BF.constraint();
      document.getElementById("key").disabled = false;
      break;

    case "MASC":
      var c = Masc.constraint();
      document.getElementById("key").disabled="disabled";
      break;

    case "PSC":
      var c = Psc.constraint();
      document.getElementById("key").disabled = false;
      break;

    case "PlayFair":
      var c = PlayFairCipher.constraint();
      document.getElementById("key").disabled = false;
      break;

    case "HillCipher":
      var c = HillCipher.constraint();
      document.getElementById("key").disabled = false;
      document.getElementById("key").value = "LIHH";
      break;

    case "RailFence":
      var c = RailFence.constraint();
      document.getElementById("key").disabled="disabled";
      break;

    case "sft":
      var c = ColumnTransposition.constraint();
      document.getElementById("key").disabled="disabled";
      break;

    case "vernam":
      var c = VernamCipher.constraint();
      document.getElementById("key").disabled = false;
      break;


  } 
  let constraintStr="<h3>Limitations :</h3><ul>";
   for(i of c)
      constraintStr+= "<li> "+i+" </li><br>";
  constraintStr+="</ul>";

  constraint.innerHTML=constraintStr;
      

}


function encrypt_Driver(){

  let choice=document.getElementById("Strategy").value;
  let toEncrypt=document.getElementById("toEncrypt").value;
  let key=document.getElementById("key").value;
  let cipherText;

  switch(choice){

    case "AES":

      if(key.length!=16 && key.length!=32 && key.length!=24){
        alert("Key size must be 16, 24 or 32 bytes");
        return;
      }
      let aes=new AES();
      cipherText = aes.Encrypt_Driver(toEncrypt,key);
      document.getElementById("encryptedText").value=cipherText;
      break;

    case "Blowfish":
      let bf=new BF();
      cipherText = bf.Encrypt_Driver(toEncrypt,key);
      document.getElementById("encryptedText").value=cipherText;
      break;

   
    case "MASC":
      let masc=new Masc();
      cipherText = masc.Encrypt_Driver(toEncrypt);
      document.getElementById("encryptedText").value=cipherText[0];
      document.getElementById("key").value=cipherText[1];
      break;


    case "PSC":
      let psc=new Psc();
      cipherText = psc.Encrypt_Driver(toEncrypt,key);
      document.getElementById("encryptedText").value=cipherText;
      break;


    case "PlayFair":
      let playFair=new PlayFairCipher();
      cipherText = playFair.Encrypt_Driver(toEncrypt,key);
      document.getElementById("encryptedText").value=cipherText;
      break;

    case "HillCipher":
      if(key.length==4){
        for(let i=0;i<4;i++){

          if(key.charAt(i)<'A'|| key.charAt(i)>'Z'){
            alert("The key specified must have exactly 4 letters in uppercase !")
            return;
          }
       
          }

       let hillCipher=new HillCipher();
      cipherText = hillCipher.Encrypt_Driver(toEncrypt,key);
      document.getElementById("encryptedText").value=cipherText;
      
      }
      else{
        alert("The key specified must have exactly 4 letters in uppercase !")
        return;
      }
      break;
      

    case "RailFence":
      let railFence=new RailFence();
      cipherText = railFence.Encrypt_Driver(toEncrypt);
      document.getElementById("encryptedText").value=cipherText;
      break;

    case "sft":
      let sft=new ColumnTransposition();
      cipherText =  sft.Encrypt_Driver(toEncrypt);
      document.getElementById("encryptedText").value=cipherText;
      break;

    case "vernam":
      if(toEncrypt.length<key.length){
        alert("The key specified should be smaller in length than the plain Text that is to be encrypted !")
        return;
      }
      let vernam=new VernamCipher();
      cipherText = vernam.Encrypt_Driver(toEncrypt,key);
      document.getElementById("encryptedText").value=cipherText;
      break;
  }
}

function decrypt_Driver(){

  let key=document.getElementById("key").value;
  let toDecrypt=document.getElementById("toDecrypt").value;
  let choice=document.getElementById("Strategy").value;
  let plainText;

  switch(choice){

    

    case "AES":

       if(key.length!=16 && key.length!=24 && key.length!=32){
        alert("Key size must be 16, 24 or 32 bytes");
        return;
      }
      let aes=new AES();
      plainText = aes.Decrypt_Driver(toDecrypt,key);
      document.getElementById("decryptedText").value=plainText;
      break;

    case "Blowfish":

      let bf=new BF();
      plainText = bf.Decrypt_Driver(toDecrypt,key);
      document.getElementById("decryptedText").value=plainText;
      break;

    case "MASC":
      let masc=new Masc();
      plainText = masc.Decrypt_Driver(toDecrypt,key);
      document.getElementById("decryptedText").value=plainText;
      break;

    case "PSC":
      let psc=new Psc();
      plainText = psc.Decrypt_Driver(toDecrypt,key);
      document.getElementById("decryptedText").value=plainText;
      break;


    case "PlayFair":
      let playFair=new PlayFairCipher();
      plainText = playFair.Decrypt_Driver(toDecrypt,key);
      document.getElementById("decryptedText").value=plainText;
      break;

    case "HillCipher":

      if(key.length==4){
        for(let i=0;i<4;i++){

          if(key.charAt(i)<'A'|| key.charAt(i)>'Z'){
            alert("The key specified must have exactly 4 letters in uppercase !")
            return;
          }
       
          }

      let hillCipher=new HillCipher();
      plainText = hillCipher.Decrypt_Driver(toDecrypt,key);
      document.getElementById("decryptedText").value=plainText;
     
    }
     else{
            alert("The key specified must have exactly 4 letters in uppercase !")
            return;
          }
           break;

    case "RailFence":
      let railFence=new RailFence();
      plainText = railFence.Decrypt_Driver(toDecrypt);
      document.getElementById("decryptedText").value=plainText;
      break;

    case "sft":
      let sft=new ColumnTransposition();
      plainText = sft.Decrypt_Driver(toDecrypt);
      document.getElementById("decryptedText").value=plainText;
      break;

    case "vernam":
      if(toDecrypt.length<key.length){
        alert("The key specified should be smaller in length than the cipher Text that is to be decrypted !")
        return;
      }
      let vernam=new VernamCipher();
      plainText = vernam.Decrypt_Driver(toDecrypt,key);
      document.getElementById("decryptedText").value=plainText;
      break;

  }
}


