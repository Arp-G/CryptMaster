/*
 * 
 * The decrypted text will contain a trailing 'X' if the original plain text had odd number of characters
 * Can only work for a single word containing letters (A to Z)
 * Fails to maintain letter case, decrypted text is always in upper case
 * The algorithm fails when the plain text has similar consecutive characters Example: Hello, hammer ,etc
 * 
*/





public class HillCipher {
    
    public static void main(String args[]){
    	
    	String plainText="Act";

    	plainText=plainText.toUpperCase();
        
        String tmp=Encrypt_Driver(plainText,"GYBNQKURP");
        
        System.out.println("Plain text: "+plainText);
        
        System.out.println("cipher text: "+tmp);
        
        System.out.println("decrypted text: "+Decrypt_Driver(tmp,"GYBNQKURP"));  
        
        
    }

 static String Encrypt_Driver(String plainText,String key) {
	 
	 int keyMatrix[][]=createKeyMatrix(plainText.length(),key);
	 
	 int msgMatrix[]=createMsgMatrix(plainText);
	 
	 String cipherText=encrypt(msgMatrix,keyMatrix);
	 
	 return cipherText;
 }

 static String Decrypt_Driver(String cipherText,String key) {
	 
	 int keyMatrix[][]=createKeyMatrix(cipherText.length(),key);
	 
	 int cipherMatrix[]=createMsgMatrix(cipherText);
	 
	 String plainText=decrypt(cipherMatrix,keyMatrix);
	 
	 return plainText;
 																	
 }
 
 static int getCharCode(char ch) {
	 return (int)ch-65;
 }
 
 static char getChar(int x) {	 
	 return (char)(x+65);
 }


static int[][] createKeyMatrix(int len,String key){
	
	int matrix[][]=new int[len][len];
	int k=0;
	
	for(int i=0;i<len;i++)
		for(int j=0;j<len;j++)
			matrix[i][j]=getCharCode(key.charAt(k++));
	
	return matrix;
	
	
}
 
static int[] createMsgMatrix(String plainText) {
	
	int matrix[]=new int[plainText.length()];
	
	for(int i=0;i<plainText.length();i++) 
		matrix[i]=getCharCode(plainText.charAt(i));
	
	return matrix;
			
}

 
 static String encrypt(int msgMatrix[],int keyMatrix[][]) {
	 
	 int sum=0;
	 
	 String cipherText="";
	 
	 for(int i=0;i<msgMatrix.length;i++) {
		 
		 sum=0;
		 
		 for(int j=0;j<msgMatrix.length;j++) 
			 sum+=keyMatrix[i][j]*msgMatrix[j];
		 		 
		 cipherText+= getChar(sum%26);		 
	 }
	 
	 return cipherText;

 }
 
 static void displayMatrix(double m[][]) {
	 
	 for(int i=0;i<m[0].length;i++) {
		 for(int j=0;j<m[0].length;j++)
			 System.out.print(m[i][j]+" ");
		 System.out.println();
	 }
 }

 
static String decrypt(int cipherMatrix[],int keyMatrix[][]) {
	
	double tmp[][] = new double[keyMatrix[0].length][keyMatrix[0].length];
	
	for(int i=0; i<keyMatrix[0].length; i++) 
		for(int j=0; j<keyMatrix[0].length; j++)
			tmp[i][j]=keyMatrix[i][j];
	
	/*displayMatrix(tmp);
	System.out.println();
	System.out.println();*/
	
	tmp=invert(tmp);
	
	/*displayMatrix(tmp);*/
	
	
	double x[][]= {{7,8},{11,11}};
	displayMatrix(x);
	x=invert(x);
	displayMatrix(x);
	
	for(int i=0; i<tmp[0].length; i++) 
		for(int j=0; j<tmp[0].length; j++)
			tmp[i][j]=tmp[i][j]%26;
	
	 int sum=0;
	 
	 String plainText="";
	 
	 for(int i=0;i<cipherMatrix.length;i++) {
		 
		 sum=0;
		 
		 for(int j=0;j<cipherMatrix.length;j++) 
			 sum+=tmp[i][j]*cipherMatrix[j];
		 		 
		 plainText+= getChar(sum%26);		 
	 }
	 
	 return plainText;
	
	
 }


public static double[][] invert(double a[][]) 
{
    int n = a.length;
    double x[][] = new double[n][n];
    double b[][] = new double[n][n];
    int index[] = new int[n];
    for (int i=0; i<n; ++i) 
        b[i][i] = 1;

// Transform the matrix into an upper triangle
    gaussian(a, index);

// Update the matrix b[i][j] with the ratios stored
    for (int i=0; i<n-1; ++i)
        for (int j=i+1; j<n; ++j)
            for (int k=0; k<n; ++k)
                b[index[j]][k]
                	    -= a[index[j]][i]*b[index[i]][k];

// Perform backward substitutions
    for (int i=0; i<n; ++i) 
    {
        x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
        for (int j=n-2; j>=0; --j) 
        {
            x[j][i] = b[index[j]][i];
            for (int k=j+1; k<n; ++k) 
            {
                x[j][i] -= a[index[j]][k]*x[k][i];
            }
            x[j][i] /= a[index[j]][j];
        }
    }
    return x;
}

//Method to carry out the partial-pivoting Gaussian
//elimination.  Here index[] stores pivoting order.

public static void gaussian(double a[][], int index[]) 
{
    int n = index.length;
    double c[] = new double[n];

// Initialize the index
    for (int i=0; i<n; ++i) 
        index[i] = i;

// Find the rescaling factors, one from each row
    for (int i=0; i<n; ++i) 
    {
        double c1 = 0;
        for (int j=0; j<n; ++j) 
        {
            double c0 = Math.abs(a[i][j]);
            if (c0 > c1) c1 = c0;
        }
        c[i] = c1;
    }

// Search the pivoting element from each column
    int k = 0;
    for (int j=0; j<n-1; ++j) 
    {
        double pi1 = 0;
        for (int i=j; i<n; ++i) 
        {
            double pi0 = Math.abs(a[index[i]][j]);
            pi0 /= c[index[i]];
            if (pi0 > pi1) 
            {
                pi1 = pi0;
                k = i;
            }
        }

// Interchange rows according to the pivoting order
        int itmp = index[j];
        index[j] = index[k];
        index[k] = itmp;
        for (int i=j+1; i<n; ++i) 	
        {
            double pj = a[index[i]][j]/a[index[j]][j];

// Record pivoting ratios below the diagonal
            a[index[i]][j] = pj;

// Modify other elements accordingly
            for (int l=j+1; l<n; ++l)
                a[index[i]][l] -= pj*a[index[j]][l];
        }
    }
}

 
}