import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GUI {
	
	public static void main(String args[]) {
	
	display();
    
	}
	
	
	static void display() {
		
		JFrame frame=new JFrame();
		
	    frame.setResizable(false);

	    frame.setSize(500,500);
		
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    frame.setTitle("ArpEncrypt");         
	    
	    JButton button_Encrypt = new JButton("Encryption");
	    
	    JButton button_Decrypt = new JButton("Decryption");
	     
	    JButton button_Exit = new JButton("Exit");
	                     
	    //Mouse click event
	    
	    button_Encrypt.addActionListener(new ActionListener() 
	    {
	  		      public void actionPerformed(ActionEvent arg0) 
	  		      {
	  		    	  frame.dispose();
	  		    	  Encryption_Menu.display();
	  		      }
	  		      
	    });
	    
	    button_Decrypt.addActionListener(new ActionListener() 
	    {
	  		      public void actionPerformed(ActionEvent arg0) 
	  		      {
	  		    	  frame.dispose();
	  		    	  Decryption_Menu.display();
		
	  		      }
	  		      
	    });
	    
	    button_Exit.addActionListener(new ActionListener() 
	    {
	  		      public void actionPerformed(ActionEvent arg0) 
	  		      {
	  		    	  System.exit(0);
	  		      }
	  		      
	    });
	    	    
	    
	    // Button Hover actions
	  	    
	    
	    button_Encrypt.addMouseListener(new java.awt.event.MouseAdapter()
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
	  	    	
	  	    	
	  	    	button_Encrypt.setBackground(Color.WHITE);
	  	    }

	  	    public void mouseExited(java.awt.event.MouseEvent evt) {
	  	    	button_Encrypt.setBackground(UIManager.getColor("control"));
	  	    }
	  	});
	    
	    button_Decrypt.addMouseListener(new java.awt.event.MouseAdapter()
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
	  	    	
	  	    	
	  	    	button_Decrypt.setBackground(Color.WHITE);
	  	    }

	  	    public void mouseExited(java.awt.event.MouseEvent evt) {
	  	    	button_Decrypt.setBackground(UIManager.getColor("control"));
	  	    }
	  	});
	    
	    button_Exit.addMouseListener(new java.awt.event.MouseAdapter()
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
	  	    	
	  	    	
	  	    	button_Exit.setBackground(Color.WHITE);
	  	    }

	  	    public void mouseExited(java.awt.event.MouseEvent evt) {
	  	    	button_Exit.setBackground(UIManager.getColor("control"));
	  	    }
	  	});
	    
	    
	    
	    JLabel heading=new JLabel(new ImageIcon("images/Heading.gif"),JLabel.CENTER);

	    heading.setBounds(170,0,150,50);
	    
	    
	    JLabel buttons=new JLabel(new ImageIcon("images/Background.gif"),JLabel.CENTER);

	    buttons.setBorder(new EmptyBorder(new Insets(150, 0, 0, 0))); //Set location of label
	    
	    buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS)); //Use Box Layout to order buttons vertically
	    
	    
	    button_Encrypt.setFont(button_Encrypt.getFont().deriveFont(Font.BOLD, 20)); //Set button size by changing fonts
	    
	    button_Decrypt.setFont(button_Decrypt.getFont().deriveFont(Font.BOLD, 20));
	    
	    button_Exit.setFont(button_Exit.getFont().deriveFont(Font.BOLD, 20));
	    
	    
	    button_Encrypt.setAlignmentX(Component.CENTER_ALIGNMENT); //Set button alignment to center
	    
	    button_Decrypt.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    button_Exit.setAlignmentX(Component.CENTER_ALIGNMENT);
	    	    
	    
	    buttons.add(button_Encrypt);
	    
	    buttons.add(Box.createRigidArea(new Dimension(0, 60)));  //Add an invisible component to create a vertical spacing between two adjacent buttons in the panel
	   
	    buttons.add(button_Decrypt);
	    
	    buttons.add(Box.createRigidArea(new Dimension(0, 60)));
	    
	    buttons.add(button_Exit);
	    
	    frame.add(heading);
	    
	    frame.add(buttons);

	    frame.setVisible(true);
	}
}
