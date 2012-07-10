import java.awt.RenderingHints.Key;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class CreateKey {
	
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException{
		File f=new File("c:\\temp\\agentService\\plugins\\CA.ico"); 
		f.createNewFile(); 
		
		KeyGenerator keyGen=KeyGenerator.getInstance("AES"); 
		SecretKey 	 key=keyGen.generateKey();
		
		byte[] data=key.getEncoded();
		
		FileOutputStream fos=new FileOutputStream(f); 
		fos.write(data); 
		fos.flush(); 
		fos.close();
		
		
	}
}
