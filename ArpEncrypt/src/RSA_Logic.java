import java.math.BigInteger;


public class RSA_Logic {
	
	
	static BigInteger gcd(BigInteger x,BigInteger y) {
		
		 if (y.compareTo(new BigInteger("0"))==0) 
            return x; 
         return gcd(y, x.mod(y));  
		
	}
	
	/*The security of several public-key cryptography algorithms is based on the fact that big prime numbers are hard to find.
A well known example is the RSA algorithm. During the key generation step, you have to choose 2 primes, p and q, and calculate their product, n = p*q. 
The security of RSA is based on the fact that it’s very hard to find p and q given n.*/
	
	static BigInteger randomPrime() {
		
		BigInteger primeArr[]= {
				
				new BigInteger("547"),
				new BigInteger("587")};

		
		
		
		int random = java.util.concurrent.ThreadLocalRandom.current().nextInt(0, primeArr.length);
		
		return primeArr[random];
	}
	
	static void driver()
	{
		// Two random prime numbers 
		BigInteger p = randomPrime(); 
		BigInteger q = randomPrime(); 
	  
	    // First part of public key: 
		BigInteger n= p.multiply(q); 
	   
	    BigInteger e = new BigInteger("2");
	    
	   // System.out.println("here   "+e);
	    
	    BigInteger phi =  (p.subtract ( new BigInteger("1"))).multiply( q.subtract( new BigInteger("1") ) );  //(p-1)*(q-1); 
	    
	    while (phi.compareTo(e)>0) 
	    { 
	        // e must be co-prime (e and phi must have no common factors) to phi and 
	        // smaller than phi. 
	    	
	        //if (gcd(e, phi).compareTo(new BigInteger("1"))==0)
	    	
	    	if ((n.mod(e)).compareTo(new BigInteger("0"))==0)
	            break; 
	        else
	            e=e.add(new BigInteger("1"));
	        
	       // System.out.println(e);
	        
	       /* if(e.compareTo(phi)>0)
	        {
	        	driver();
	        	return;
	        }*/
	    } 
	    
	    // Private key 
	    BigInteger k = e;  // A constant value 
	    BigInteger d;
	  
	    //The Value of K should be such that the 
	    //d = (1 + (k*phi)) mod e should be equal to Zero
	    
	    while(true)
	    {
	    	BigInteger tmp = k.multiply(phi);
	    	
	    	tmp=tmp.add(new BigInteger("1"));
	    	
	    	if(tmp.mod(e).compareTo(new BigInteger("1"))==0)
	    	{
	    		d = tmp.divide(e);
	    		break;
	    	}
	    	
	    	k=k.add(new BigInteger("1"));
	    }
	    
	    //System.out.println("here");
	    String msg="HI";
	    
	    String numMsg="";
	    
	    for(int i=0;i<msg.length();i++)
	    {
	    	char ch=msg.charAt(i);
	    	if( ((int)ch - 30) <10 )
	    		numMsg+="0";
	    	
	    	numMsg+=(int)((int)ch - 30);
	    }
	    
	    BigInteger x=new BigInteger(numMsg);
	    
	 // Encryption c = (msg ^ e) % n 
	    
	    System.out.println("Value of d is "+d);
	    
	    System.out.println("Value of e is "+e);
	    
	    System.out.println("Value of n is "+n);
	    
	    System.out.println("Original Data is "+x);
	    
	    BigInteger c = x.modPow(e,n);
	    
	    System.out.println("Encrypted Data is "+c);
	    
	    BigInteger m= c.modPow(d,n);
	    
	    System.out.println("Decrypted Data is "+m);
	    
	    System.out.println("--------------------------------");
	    
	    
	    n = new BigInteger("9516311845790656153499716760847001433441357");
	    e = new BigInteger("65537");
	    d = new BigInteger("5617843187844953170308463622230283376298685");
	    
	    
	    System.out.println("Value of d is "+d);
	    
	    System.out.println("Value of e is "+e);
	    
	    System.out.println("Value of n is "+n);
	    
	    System.out.println("Original Data is "+x);
	    
	    c = x.modPow(e,n);
	    
	    System.out.println("Encrypted Data is "+c);
	    
	    m= c.modPow(d,n);
	    
	    System.out.println("Decrypted Data is "+m);
	    
	    
	    
	    
	    
	  
	}
	
	public static void main(String args[])
	{
	    driver();
	}
	
	
	

}
