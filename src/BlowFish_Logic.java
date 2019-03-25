public class BlowFish_Logic {
	
	static String p[];
			
	static String k[];
					
	static String s_box[][];
	
	public static void main(String args[])
	{
		blowFish_Encrypt();
	}
	
	static int getRandom(int min,int max)
	{
		return java.util.concurrent.ThreadLocalRandom.current().nextInt(min,max+1);
	}
	
	static String xor(String str1,String str2)
	{
		String str="";
		for(int i=0;i<str1.length();i++)
		{
			if(str1.charAt(i)!=str2.charAt(i))
				str+="1";
			else
				str+="0";
		}
		
		return str;
	}
	
	
	static void blowFish_Encrypt()
	{
		p=new String[18];
		
		for(int i=0;i<18;i++)
		{
			p[i]=random32BitString();
		}
		
		int n=getRandom(1,14);
		
		k=new String[n];
	    
	    for(int i=0;i<k.length;i++)
		{
			k[i]=random32BitString();
		}
	    
	    s_box=new String[4][256];
	    
	    for(int i=0;i<4;i++)		
	    	for(int j=0;j<256;j++)
	    		s_box[i][j]=random32BitString();
	    
	    
	    for(int i=0,j=0;i<18;i++,j++)
	    {
	    	if(j>k.length-1)
	    		j=0;
	    	
	    	p[i]=xor(p[i],k[j]);
	    }    
	    
	    String chiperText="0001000000000001000000000000000011000000000000000001000000000000";
	    
	    String st1,st2;
	    
	    String pt1,pt2;
	    
	    System.out.println("Plain text : "+chiperText);
	    
	    
	    for(int i=0;i<18;i++)
	    {
	    	st1=chiperText.toString().substring(0,32);
	    	
	    	st2=chiperText.toString().substring(32,64);
	    	
	    	pt1=new String(st1);
	    	
	    	pt2=new String(st2);
	    	
	    	String tmp = xor(p[i],pt1) ;
	    	
	    	String tmp1 = xor(( func (tmp) ),pt2);
	    	
	    	pt2=tmp;
	    	
	    	pt1=tmp1;
	    	
	    	chiperText =pt1+ pt2;
	    	    	
	    }
	    
	    System.out.println("Chiper text : "+chiperText);
	    
	    for(int i=17;i>=0;i--)
	    {
	    	st1=chiperText.substring(0,32);
	    	
	    	st2=chiperText.substring(32,64);
	    	
	    	pt1=new String(st1);
	    	
	    	pt2=new String(st2);
	    	
	    	String tmp = xor(p[i],pt1) ;
	    	
	    	String tmp1 = xor(( func (tmp) ),pt2);
	    	
	    	pt2=tmp;
	    	
	    	pt1=tmp1;
	    	
	    	chiperText = new String( pt2+ pt2);
	    	    	
	    }
	    
	    System.out.println("Original text : "+chiperText);
	}
	
	static String func(String x)
	{
		String str=x.toString();
		
		String s1=str.substring(0,8);
		
		String s2=str.substring(8,16);
		
		String s3=str.substring(16,24);
		
		String s4=str.substring(24,32);
		
		String ss1 = substitute(s1);
		
		String ss2 = substitute(s2);
		
		String ss3 = substitute(s3);
		
		String ss4 = substitute(s4);
		
		String res = xor(ss1,ss2);
		
		res = xor(ss3,res);
		
		res = xor(ss4,res);
		
		return res;
		
	}
	
	static String substitute(String x)
	{
		String outer=""+x.charAt(0)+x.charAt(7);
		
		int row=Integer.parseInt(outer,2);
		
		int col=Integer.parseInt(x.substring(1,6),2);
		
		return s_box[row][col];		
	}
	
	
	
	
	static String random32BitString()
	{
		String str="";
		for(int i=1;i<=32;i++) {
			str=str+ getRandom(0,1);
		}
		
		return new String(str);
	}

}
