import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class EncryptionGUI {
	
	static JTextArea src;
	
	static JTextArea encrypted;
	
	
	static void display(String algo) {
		
		JFrame f= new JFrame("Encryption"); 
		    
		javax.swing.border.Border border = BorderFactory.createLineBorder(Color.BLACK); //Creating border for text areas
		
	    src=new JTextArea();   //Text area to enter source text
	    
	    src.setLineWrap(true); //Text in text area will wrap to the next line when text goes out of visible region
	    
	    src.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10))); //Apply border to source text area
	    
	    src.setBounds(40,75,700,250); //Set text area position,width and height as (x,y,width,height)
	    
	    JLabel label1 = new JLabel();
	   
	    label1.setText("Enter text to encrypt here :"); //Create a label holding the text "Enter text to encrypt here :"
	    
	    label1.setFont(new Font("SansSerif Bold",Font.BOLD,20)); //Set font style for label
	    
	    label1.setBounds(250, 0, 300, 50); //Set label position
	    
	    
	    JLabel label2 = new JLabel();
		   
	    label2.setText("Encrypted Text here : "); //Create a label holding the text "Encrypted Text here : "
	    
	    label2.setFont(new Font("SansSerif Bold",Font.BOLD,20)); //Set font style for label
	    
	    label2.setBounds(260, 400, 10000, 50); //Set label position and size
	    
	    encrypted=new JTextArea(); //Create the text area for holding encrypted text
	    
	    encrypted.setLineWrap(true); //Text in text area will wrap to the next line when text goes out of visible region
	    
	    encrypted.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10))); //Apply border to source text area
	    
	    encrypted.setBounds(40,450,700,250); //Set text area position,width and height as (x,y,width,height)
	    
	    encrypted.setEditable(false); //Encrypted text area should not be editable by user
	    
	    
	    JButton b=new JButton("Encrypt !");  //Create a button to encrypt the text
	    
	    b.setBounds(325,350,120,30); //Set button position and size
	    
	    JLabel label3=new JLabel(); //This label will hold the text about the key file generated
	    
	    label3.setFont(new Font("SansSerif Bold",Font.BOLD,13)); //Set font style for label
	    
	    label3.setBounds(50,700,700,30); //Set label position and size
	    
	    b.addActionListener(new ActionListener() 
	    {
	  		      public void actionPerformed(ActionEvent arg0) 
	  		      {
	  		    	try {
	    			      File file = new File("sounds/Button Click.wav");
	    			      AudioInputStream stream = AudioSystem.getAudioInputStream(file);
	    			      Clip clip = AudioSystem.getClip();
	    			      clip.open(stream);
	    			      clip.start();
	    			      stream.close();
	    			 
	    			    } catch (Exception ex) {
	    			      System.out.println(ex.getMessage());
	    			    } 
	  		    	  
	  		    	 String input=src.getText();
	  		    	 
	  		    	 if(input.length()>0) //If user entered some text
	  		    	 {
	  		    		DataAndKey ob=null;
	  		    		 
	  		    		 if(algo.compareTo("ArpEncrypt")==0) //If the user pressed button for "ArpEncrypt" algorithm
	  		    		 {
	  		    			String chiperText=ArpEncrypt_Logic.Encrypt_Driver(input); //Encrypt text
	  		    			label3.setText("Key file was generated and stored in \""+System.getProperty("user.dir")+"\\key\""); //Show label with key path
		  		    		encrypted.setText(chiperText); //Show encrypted text in text area
		  		    		return;
	  		    		 }
	  		    		 else if(algo.compareTo("AES")==0) //If the user pressed button for "AES" algorithm
	  		    		 {
	  		    			ob=CryptAlgoLogic.Encryptdriver(input, "AES"); //Encrypt text using AES
	  		    		 }
	  		    		 else if(algo.compareTo("DES")==0)
	  		    		 {
	  		    			ob=CryptAlgoLogic.Encryptdriver(input, "DES");
	  		    		 }
	  		    		 else if(algo.compareTo("BlowFish")==0)
	  		    		 {
	  		    			ob=CryptAlgoLogic.Encryptdriver(input, "BlowFish");
	  		    		 }
	  		    		
	  		    		storeKey(ob.getKey()); //Write the generated key file
	  		    		label3.setText("Key file was generated and stored in \""+System.getProperty("user.dir")+"\\key\""); //Show label with key path
	  		    		encrypted.setText(ob.getData()); //Show encrypted text in text area
	  		    	 }
		
	  		      }
	  		      
	    });
	    
	    b.addMouseListener(new java.awt.event.MouseAdapter()
	    {
	  	    public void mouseEntered(java.awt.event.MouseEvent evt) 
	  	    {
	  	    	try {
	  			      File file = new File("sounds/Button Hover.wav");
	  			      AudioInputStream stream = AudioSystem.getAudioInputStream(file);
	  			      Clip clip = AudioSystem.getClip();
	  			      clip.open(stream);
	  			      clip.start();
	  			      stream.close();
	  			 
	  			    } catch (Exception ex) {
	  			      System.out.println(ex.getMessage());
	  			    }
	  	    	
	  	    	
	  	    	b.setBackground(Color.WHITE);
	  	    }
	  	    
	  	  public void mouseExited(java.awt.event.MouseEvent evt) {
	  	    	b.setBackground(UIManager.getColor("control"));
	  	    }
	    });
	    
	    JButton button_Back = new JButton("Back");
	    
	    button_Back.setBounds(325,750,120,30);
	    
	    button_Back.addActionListener(new ActionListener() 
	    {
	  		      public void actionPerformed(ActionEvent arg0) 
	  		      {
	  		    	try {
	    			      File file = new File("sounds/Button Click.wav");
	    			      AudioInputStream stream = AudioSystem.getAudioInputStream(file);
	    			      Clip clip = AudioSystem.getClip();
	    			      clip.open(stream);
	    			      clip.start();
	    			      stream.close();
	    			 
	    			    } catch (Exception ex) {
	    			      System.out.println(ex.getMessage());
	    			    }
	  		    	  
	  		    	  f.dispose();
	  		    	  GUI.display();
	  		      }
	  		      
	    });
	    
	    button_Back.addMouseListener(new java.awt.event.MouseAdapter()
	    {
	  	    public void mouseEntered(java.awt.event.MouseEvent evt) 
	  	    {
	  	    	try {
	  			      File file = new File("sounds/Button Hover.wav");
	  			      AudioInputStream stream = AudioSystem.getAudioInputStream(file);
	  			      Clip clip = AudioSystem.getClip();
	  			      clip.open(stream);
	  			      clip.start();
	  			      stream.close();
	  			 
	  			    } catch (Exception ex) {
	  			      System.out.println(ex.getMessage());
	  			    }
	  	    	
	  	    	
	  	    	button_Back.setBackground(Color.WHITE);
	  	    }

	  	    public void mouseExited(java.awt.event.MouseEvent evt) {
	  	    	button_Back.setBackground(UIManager.getColor("control"));
	  	    }
	  	});
	  	    
	    
	  	    
	    f.add(label1);	
	    
	    f.add(label2);
	    
	    f.add(label3);
	    
	    f.add(src);
	    
	    f.add(b);
	    
	    f.add(button_Back);
	    
	    f.add(encrypted);
	    	    
	    f.setSize(800,900);  
	    
	    f.setLayout(null); 
	    
	    f.setVisible(true);  
	}
	
	static void storeKey(javax.crypto.SecretKey key) { //Writing key to file 
		  try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("key"))) {
		   objectOut.writeObject(key);
		  } catch (IOException e) {
		   e.getStackTrace();
		  }

		 }

		

}
