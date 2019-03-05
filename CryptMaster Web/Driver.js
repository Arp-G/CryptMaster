function load_Contraint(){

  let choice=document.getElementById("Strategy").value;
  let constraint=document.getElementById("contraint");

  switch(choice){

    case "MSC":
      constraint.innerText = Msc.contraint();
      break;
  }

}


function encrypt_Driver(){

  let key=document.getElementById("key").value;

  let plainText=document.getElementById("encryption").value;

  let choice=document.getElementById("Strategy").value;

  switch(choice){

    case "MSC":

      let msc=new Msc();

      document.getElementById("encryptedText").value = msc.encrypt_Driver(plainText,key);

      break;
  }
}

function decrypt_Driver(){

  let key=document.getElementById("key").value;

  let cipherText=document.getElementById("decryption").value;

  let choice=document.getElementById("Strategy").value;

  switch(choice){

    case "MSC":

      let msc=new Msc();

      document.getElementById("decryptedText").value = msc.decrypt_Driver(cipherText,key);

      break;
  }
}


