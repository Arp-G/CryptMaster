import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class EncryptionGUI {
	
	static JTextArea src;
	
	static JTextArea encrypted;
	
	public static void main(String args[]) {
		
		display();
	}
	
	static void display() {
		
		JFrame f= new JFrame("Encryption"); 
		    
		javax.swing.border.Border border = BorderFactory.createLineBorder(Color.BLACK);
		
	    src=new JTextArea();   
	    
	    src.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
	    
	    src.setBounds(40,75,700,250); 
	    
	    JLabel label1 = new JLabel();
	   
	    label1.setText("Enter text to encrypt here :");
	    
	    label1.setFont(new Font("SansSerif Bold",Font.BOLD,20));
	    
	    label1.setBounds(250, 0, 300, 50);
	    
	    
	    JLabel label2 = new JLabel();
		   
	    label2.setText("Encrypted Text here : ");
	    
	    label2.setFont(new Font("SansSerif Bold",Font.BOLD,20));
	    
	    label2.setBounds(260, 400, 10000, 50);
	    
	    encrypted=new JTextArea();  
	    
	    encrypted.setBorder(BorderFactory.createCompoundBorder(border,
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
	    
	    encrypted.setBounds(40,450,700,250);
	    
	    encrypted.setEditable(false);
	    
	    
	    JButton b=new JButton("Encrypt !"); 
	    
	    b.setBounds(325,350,120,30); 
	    
	    JLabel label3=new JLabel();
	    
	    label3.setBounds(50,700,700,30);
	    
	    b.addActionListener(new ActionListener() 
	    {
	  		      public void actionPerformed(ActionEvent arg0) 
	  		      {
	  		    	 String input=src.getText();
	  		    	 
	  		    	 if(input.length()>0)
	  		    	 {
	  		    		label3.setText("\"Key file was generated and stored in "+System.getProperty("user.dir")+"\\key\"");
	  		    		encrypted.setText(ArpEncrypt.Encrypt_Driver(input));
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
	  	    
	    f.add(label1);	
	    
	    f.add(label2);
	    
	    f.add(label3);
	    
	    f.add(src);
	    
	    f.add(b); 
	    
	    f.add(src);
	    
	    f.add(encrypted);
	    	    
	    f.setSize(800,800);  
	    
	    f.setLayout(null); 
	    
	    f.setVisible(true);  
	}

}