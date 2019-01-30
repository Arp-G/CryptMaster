import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class DecryptionGUI {
	
	static JTextArea encrypted;
	
	static JTextArea decrypted;
	
	static void display(String algo) {
		
		JFrame f= new JFrame("Decryption"); 
		    
		javax.swing.border.Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		encrypted=new JTextArea();  
		
		encrypted.setLineWrap(true);
	    
		encrypted.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
	    
		encrypted.setBounds(40,75,700,250); 
	    
	    JLabel label1 = new JLabel();
	   
	    label1.setText("Enter text to decrypt here :");
	    
	    label1.setFont(new Font("SansSerif Bold",Font.BOLD,20));
	    
	    label1.setBounds(250, 0, 300, 50);
	    
	    
	    JLabel label2 = new JLabel();
		   
	    label2.setText("Decrypted Text here : ");
	    
	    label2.setFont(new Font("SansSerif Bold",Font.BOLD,20));
	    
	    label2.setBounds(260, 400, 10000, 50);
	    
	    decrypted=new JTextArea();  
	    
	    decrypted.setLineWrap(true);
	    
	    decrypted.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
	    
	    decrypted.setBounds(40,450,700,250);
	    
	    decrypted.setEditable(false);
	    
	    
	    JButton b=new JButton("Decrypt !"); 
	    
	    b.setBounds(325,350,120,30); 
	    
	    JLabel label3=new JLabel();
	    
	    label3.setBounds(50,700,700,30);
	    
	    label3.setFont(new Font("SansSerif Bold",Font.BOLD,13));
	    
	    b.addActionListener(new ActionListener() 
	    {
	  		      public void actionPerformed(ActionEvent arg0) 
	  		      {
	  		    	 
	  		    	String output=encrypted.getText();  
	  		    	javax.crypto.SecretKey key=null;
	  		    	
	  		    	if(algo.compareTo("ArpEncrypt")!=0)
	  		    	{
	  		    		key= getKey(System.getProperty("user.dir") + "\\key");   		    	
	  		    		
	  		    		if(key==null)
	  		    		{
	  		    			label3.setText("Decryption unsuccessful, Key file was not found in \""+System.getProperty("user.dir")+"\\\"");
	  		    			return;
	  		    		}
	  		    	
	  		    	}
	  		    	  
	  		    	DataAndKey ob=new DataAndKey(output,key);
	  		    	String decryptedString=null;
	  		    	
	  		    	 if(algo.compareTo("ArpEncrypt")==0)
 		    		 {
 		    			decryptedString=ArpEncrypt_Logic.Decrypt_Driver(output);
 		    			
 		    		 }
 		    		 else if(algo.compareTo("AES")==0)
 		    		 {
 		    			decryptedString=CryptAlgoLogic.Decryptdriver(ob, "AES");
 		    		 }
 		    		 else if(algo.compareTo("DES")==0)
 		    		 {
 		    			decryptedString=CryptAlgoLogic.Decryptdriver(ob, "DES");
 		    		 }
 		    		 else if(algo.compareTo("BlowFish")==0)
 		    		 {
 		    			decryptedString=CryptAlgoLogic.Decryptdriver(ob, "BlowFish");
 		    		 }
	  		    	 
	  		    	 if(decryptedString==null)
	  		    		 label3.setText("Decryption unsuccessful");
	  		    	 else
	  		         {
	  		    			 label3.setText("Decryption successfull using key file \""+System.getProperty("user.dir")+"\\key\"");
	  		    			 decrypted.setText(decryptedString);
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
	    
	    f.add(encrypted);
	    
	    f.add(b);
	    
	    f.add(button_Back);
	    
	    f.add(decrypted);
	    	    
	    f.setSize(800,900);  
	    
	    f.setLayout(null); 
	    
	    f.setVisible(true);  
	}
	
	
		static javax.crypto.SecretKey getKey(String path) { //Read key from file at 'path'
		 javax.crypto.SecretKey key = null;

		  try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(path))) {
		   key = (javax.crypto.SecretKey ) objectIn.readObject();
		  } catch (IOException | ClassNotFoundException e) {
		   e.getStackTrace();
		  }

		  return key;
		 }

}
