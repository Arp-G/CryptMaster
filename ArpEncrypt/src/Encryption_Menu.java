import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Encryption_Menu {
	

static void display() {
		
	JFrame frame=new JFrame();
	
    frame.setResizable(false);

    frame.setSize(500,600);
	
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setTitle("Encryption Menu");    
    
    JButton button_ArpEncrypt = new JButton("ArpEncrypt");
    
    JButton button_AES = new JButton("    AES    ");
    
    JButton button_DES = new JButton("    DES    ");
    
    JButton button_BlowFish = new JButton("BlowFish ");
     
    JButton button_Back = new JButton("Back");
                     
    //Mouse click event
    
    button_ArpEncrypt.addActionListener(new ActionListener() 
    {
  		      public void actionPerformed(ActionEvent arg0) 
  		      {
  		    	  frame.dispose();
 		    	  EncryptionGUI.display("ArpEncrypt");
  		      }
  		      
    });
    
    button_AES.addActionListener(new ActionListener() 
    {
  		      public void actionPerformed(ActionEvent arg0) 
  		      {
  		    	  frame.dispose();
  		    	EncryptionGUI.display("AES");
  		      }
  		      
    });
    
    button_DES.addActionListener(new ActionListener() 
    {
  		      public void actionPerformed(ActionEvent arg0) 
  		      {
  		    	  frame.dispose();
  		    	  EncryptionGUI.display("DES");
  		      }
  		      
    });
    
    button_BlowFish.addActionListener(new ActionListener() 
    {
  		      public void actionPerformed(ActionEvent arg0) 
  		      {
  		    	  frame.dispose();
  		    	  EncryptionGUI.display("BlowFish");
  		      }
  		      
    });
    
    button_Back.addActionListener(new ActionListener() 
    {
	      public void actionPerformed(ActionEvent arg0) 
	      {
	    	  frame.dispose();
	    	  GUI.display();
	      }
	      
});
    
    button_ArpEncrypt.addMouseListener(new java.awt.event.MouseAdapter()
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
  	    	
  	    	
  	    	button_ArpEncrypt.setBackground(Color.WHITE);
  	    }

  	    public void mouseExited(java.awt.event.MouseEvent evt) {
  	    	button_ArpEncrypt.setBackground(UIManager.getColor("control"));
  	    }
  	});
    
    button_AES.addMouseListener(new java.awt.event.MouseAdapter()
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
  	    	
  	    	
  	    	button_AES.setBackground(Color.WHITE);
  	    }

  	    public void mouseExited(java.awt.event.MouseEvent evt) {
  	    	button_AES.setBackground(UIManager.getColor("control"));
  	    }
  	});
    
    button_DES.addMouseListener(new java.awt.event.MouseAdapter()
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
  	    	
  	    	
  	    	button_DES.setBackground(Color.WHITE);
  	    }

  	    public void mouseExited(java.awt.event.MouseEvent evt) {
  	    	button_DES.setBackground(UIManager.getColor("control"));
  	    }
  	});
    
    button_BlowFish.addMouseListener(new java.awt.event.MouseAdapter()
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
  	    	
  	    	
  	    	button_BlowFish.setBackground(Color.WHITE);
  	    }

  	    public void mouseExited(java.awt.event.MouseEvent evt) {
  	    	button_BlowFish.setBackground(UIManager.getColor("control"));
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
    
    
    JLabel heading=new JLabel(new ImageIcon("images/Heading.gif"),JLabel.CENTER); //Heading GIF image

    heading.setBounds(0,0,500,50); // Heading image position (x,y,width,height)
      
    JLabel buttons=new JLabel(new ImageIcon("images/Background.gif"),JLabel.CENTER); //label to hold background image

    buttons.setBorder(new EmptyBorder(new Insets(150, 0, 0, 0))); //Set location of label
    
    buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS)); //Use Box Layout to order buttons vertically
    
    button_ArpEncrypt.setFont(button_AES.getFont().deriveFont(Font.BOLD, 20)); //Set button size by changing fonts
    
    button_AES.setFont(button_AES.getFont().deriveFont(Font.BOLD, 20)); 
    
    button_DES.setFont(button_DES.getFont().deriveFont(Font.BOLD, 20));
    
    button_BlowFish.setFont(button_BlowFish.getFont().deriveFont(Font.BOLD, 20));
    
    button_Back.setFont(button_BlowFish.getFont().deriveFont(Font.BOLD, 20));
    
    
    button_ArpEncrypt.setAlignmentX(Component.CENTER_ALIGNMENT); //Set button alignment to center
    
    button_AES.setAlignmentX(Component.CENTER_ALIGNMENT); 
    
    button_DES.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    button_BlowFish.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    button_Back.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    buttons.add(button_ArpEncrypt);
    
    buttons.add(Box.createRigidArea(new Dimension(0, 60)));  //Add an invisible component to create a vertical spacing between two adjacent buttons in the panel
    
    buttons.add(button_AES);
    
    buttons.add(Box.createRigidArea(new Dimension(0, 60)));  
   
    buttons.add(button_DES);
    
    buttons.add(Box.createRigidArea(new Dimension(0, 60)));
    
    buttons.add(button_BlowFish);
    
    buttons.add(Box.createRigidArea(new Dimension(0, 60)));
    
    buttons.add(button_Back);
    
    frame.add(heading);
    
    frame.add(buttons);

    frame.setVisible(true);
}
}
