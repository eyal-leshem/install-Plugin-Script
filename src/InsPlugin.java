import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;


public class InsPlugin {

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 * @throws IOException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		//mkKey(); 
		
	/*	if(args.length<3){
			System.out.println("not enough prameters");
			return; 
		}
		
		String jarPath=args[0];
		
		String prop=args[1]; 
		String agentPath=args[2]; */
		
	
		/*-----------------------*/
		String agentPath="C:\\temp\\agentService";
		String prop="{\"keyStorePath\":\"C:/temp/keyStore/my.keyStore\",\"password\":\"a10097\"}";
		String jarPath="c:\\temp\\csr\\JksImplemntor.jar";
		/*----------------------*/
		
		
		String name=jarPath.substring(jarPath.lastIndexOf("\\")+1,jarPath.lastIndexOf("."));
		File jarFile=new File(jarPath); 
		File agentNewJar=new File(agentPath+"\\plugins\\"+name+".jar");
		
		if(agentNewJar.exists()){
			System.out.println("already plugin with that name");
			return;  
		}
		
		File keyFile=new File(agentPath+"\\plugins\\CA.ico"); 
		byte[] keyBytes=new byte[(int)keyFile.length()]; 
		FileInputStream in=new FileInputStream(keyFile); 
		in.read(keyBytes); 
		in.close(); 
		
		
		SecretKeySpec keySpec=new SecretKeySpec(keyBytes,"AES"); 
	
		
	    String append="----"+name+"\n"+prop+"\n"; 
	    Cipher c = Cipher.getInstance("AES");
	    c.init(Cipher.ENCRYPT_MODE, keySpec);
	    byte[] encData=c.doFinal(append.getBytes());
	    
	    File propFile=new File(agentPath+"\\prop"); 
	    if (!propFile.exists()){
	    	propFile.createNewFile(); 
		}
	    
	   FileOutputStream fos=new FileOutputStream(propFile,true); 
	   fos.write(encData); 
	   fos.flush(); 
	   fos.close();
	   
	   agentNewJar.createNewFile(); 
	   
	   in=new FileInputStream(jarFile);
	   fos=new FileOutputStream(agentNewJar); 
	   
	   byte[] arr=new byte[1024];  
	   int len; 
	   
	   //copy the jar into the directory path 
	   while ((len = in.read(arr)) > 0){
		   fos.write(arr, 0, len);
	   } 
	  
	   
	   
	   
	   
		
		
		
		
	
		
		
	

	
	}
	


}
