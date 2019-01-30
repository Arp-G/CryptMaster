
public class DataAndKey {
	
	private String data;
	
	private javax.crypto.SecretKey secretKey;
	
	DataAndKey(String data,javax.crypto.SecretKey secretKey)
	{
		this.data=data;
		this.secretKey=secretKey;
	}
	
	void setData(String data)
	{
		this.data=data;
	}
	
	void setKey(javax.crypto.SecretKey secretKey)
	{
		this.secretKey=secretKey;
	}
	
	String getData()
	{
		return data;
	}
	
	javax.crypto.SecretKey getKey()
	{
		return secretKey;
	}
	
	

}
