package gov.utcourts.notifications.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * SAPEncriptDecrypt.java
 * 
 * @author Brody Arishita
 * */
public class SAPEncriptDecrypt {

	public static void main(String[] args)  throws Exception {

	String key = "01234567890123456789";
	SAPEncriptDecrypt de = new SAPEncriptDecrypt("DESede", key);
	String temp1 = "Dij7pyezTuHm2JX/k/GvvA==";
	System.out.println("Before: " + temp1 + ", length: " + temp1.length());
	String val = de.encrypt(temp1);
	System.out.println("After: " + val + ", length: " +val.length());
//	String temp2 = de.decrypt(val); //de.decrypt(temp1);
//	System.out.println("Recovered: " + temp2);

	SAPEncriptDecrypt de2 = new SAPEncriptDecrypt("DESede", ";:YkwZ7RgEF+hAJmMdor[V9eOq^PW(pI6aw+}K|{fuia:-wiQ}e8/8D5n4JswLp]");
	System.out.println("Test 1: " + de2.decrypt("UjuVbYMdsZY="));
	System.out.println("Test 2: " + de2.decrypt("Llvv4uXT4FQ="));

//	de = new SAPEncriptDecrypt("DESede", "#-@8pjNKbb'c1p|r<>NJfx5oMnLs3:rZ++s-)Oi`M0\0,%;Iuy->+3lIfGS+}K4J");
//	System.out.println("Recovered again: " + de1.decrypt("lw15kz2+PHwleBVHapcrlD8+AZJeHDtj1VWic43sqiY="));
	}
	
	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	public static final String DES_ENCRYPTION_SCHEME = "DES";
	//DO NOT CHANGE THIS DEFAULT ENCRYPTION KEY..  DO NOT TOUCH.  THIS FILE SHOULD NOT BE MODIFIED
	public static final String DEFAULT_ENCRYPTION_KEY = "181254367894813605424789938745";
	//DO NOT CHANGE THIS DEFAULT ENCRYPTION KEY..  DO NOT TOUCH.  THIS FILE SHOULD NOT BE MODIFIED 
 
	private KeySpec keySpec;
	private SecretKeyFactory keyFactory;
	private Cipher cipher;
 
	private static final String UNICODE_FORMAT = "UTF8";

	public SAPEncriptDecrypt( String encryptionScheme ) throws EncryptionException
	{
		 this( encryptionScheme, DEFAULT_ENCRYPTION_KEY );
	}

	public SAPEncriptDecrypt( String encryptionScheme, String encryptionKey )  throws EncryptionException  {

		 if ( encryptionKey == null )
				   throw new IllegalArgumentException( "Encryption key is null." );
				   
		 if ( encryptionKey.trim().length() < 24 )
				   throw new IllegalArgumentException("Encryption key was less than 24 characters." );

		 try
		 {
			  byte[] keyAsBytes = encryptionKey.getBytes( UNICODE_FORMAT );

			  if ( encryptionScheme.equals( DESEDE_ENCRYPTION_SCHEME) )
			  {
				   keySpec = new DESedeKeySpec( keyAsBytes );
			  }
			  else if ( encryptionScheme.equals( DES_ENCRYPTION_SCHEME ) )
			  {
				   keySpec = new DESKeySpec( keyAsBytes );
			  }
			  else
			  {
				   throw new IllegalArgumentException( "Encryption scheme not supported: "	+ encryptionScheme );
			  }

			  keyFactory = SecretKeyFactory.getInstance( encryptionScheme );
			  cipher = Cipher.getInstance( encryptionScheme );

		 }
		 catch (InvalidKeyException e)
		 {
			  throw new EncryptionException( e );
		 }
		 catch (UnsupportedEncodingException e)
		 {
			  throw new EncryptionException( e );
		 }
		 catch (NoSuchAlgorithmException e)
		 {
			  throw new EncryptionException( e );
		 }
		 catch (NoSuchPaddingException e)
		 {
			  throw new EncryptionException( e );
		 }

	}

	public String encrypt( String unencryptedString ) throws EncryptionException
	{
		 if ( unencryptedString == null || unencryptedString.trim().length() == 0 )
				   throw new IllegalArgumentException("Unencrypted string was null or empty." );
		 try
		 {
			  SecretKey key = keyFactory.generateSecret( keySpec );
			  cipher.init( Cipher.ENCRYPT_MODE, key );
			  byte[] cleartext = unencryptedString.getBytes( UNICODE_FORMAT );
			  byte[] ciphertext = cipher.doFinal( cleartext );

			  BASE64Encoder base64encoder = new BASE64Encoder();
			  return base64encoder.encode( ciphertext );
		 }
		 catch (Exception e)
		 {
			  throw new EncryptionException( e );
		 }
	}

	public String decrypt( String encryptedString ) throws EncryptionException
	{
		 if ( encryptedString == null || encryptedString.trim().length() <= 0 )
				   throw new IllegalArgumentException( "Encrypted string was null or empty" );
		 try
		 {
			  SecretKey key = keyFactory.generateSecret( keySpec );
			  cipher.init( Cipher.DECRYPT_MODE, key );
			  BASE64Decoder base64decoder = new BASE64Decoder();
			  byte[] cleartext = base64decoder.decodeBuffer( encryptedString );
			  byte[] ciphertext = cipher.doFinal( cleartext );

			  return bytes2String( ciphertext );
		 }
		 catch (Exception e)
		 {
			  throw new EncryptionException( e );
		 }
	}

	private static String bytes2String( byte[] bytes )
	{
		 StringBuffer stringBuffer = new StringBuffer();
		 for (int i = 0; i < bytes.length; i++)
		 {
			  stringBuffer.append( (char) bytes[i] );
		 }
		 return stringBuffer.toString();
	}

	public static class EncryptionException extends Exception
	{
		 public EncryptionException( Throwable t )
		 {
			  super( t );
		 }
	}
} 