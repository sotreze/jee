package br.com.casadocodigo.loja.daos;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
//import java.io.ByteArrayOutputStream;
//import java.security.AlgorithmParameters;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;

import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.jboss.security.Base64Encoder;

import br.com.casadocodigo.loja.models.SystemUser;

public class SystemUserDao {

	@PersistenceContext
	private EntityManager manager;

	private static String factoryInstance = "PBKDF2WithHmacSHA256";
	private static String cipherInstance = "AES/CBC/PKCS5PADDING";
	private static String secretKeyType = "AES";
	private static byte[] ivCode = new byte[16];
	// private static String secretKey = "yourSecretKey";
	
	private static final int s_saltlen = 32;

	public void salvar(SystemUser systemUser) {

		try {
			transformaSenhaDoUsuarioEmHash(systemUser);
	        
		} catch (Exception e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}
		manager.persist(systemUser);

	}

	public List<SystemUser> listar() {
		String jpql = "select distinct(su) from SystemUser su" + " join fetch su.roles";
		System.out.println("SystemUser + Role: " + jpql);
		return manager.createQuery(jpql, SystemUser.class).getResultList();
	}

	private String transformaSenhaDoUsuarioEmHash(SystemUser systemUser) {
	//private void transformaSenhaDoUsuarioEmHash(SystemUser systemUser) throws NoSuchAlgorithmException, IOException {

		 //funcionando
//		 String senha64 = generate(systemUser.getSenha());
//		 systemUser.setSenha(senha64);
		
		
        // 1. Generate the salt
        
        
//		final SecureRandom randomGen = SecureRandom.getInstance("SHA1PRNG");
//		
//        byte salt[] = new byte[s_saltlen];
//        randomGen.nextBytes(salt);
//		
//		 String senha64 = generate(systemUser.getSenha(), salt);
//		 systemUser.setSenha(senha64);
		 

//		String secretKey = systemUser.getSenha();
//		try {
//			String fSalt = "anySaltYouCanUseOfOn";
//			String plainText = "M0993000353";
//			String cipherText = encrypt(secretKey, fSalt, plainText);
//			System.out.println("Cipher: " + cipherText);
//			String dcrCipherText = decrypt(secretKey, fSalt, cipherText);
//			System.out.println("Decrypted: " + dcrCipherText);
//			System.out.println("secretKey: " + secretKey);
//			systemUser.setSenha(cipherText);
//			return plainText;
//			
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
		
		String secretKey = systemUser.getSenha();
		try {
			String fSalt = "anySaltYouCanUseOfOn";
			String plainText = "M0993000353";
			String cipherText = encrypt(secretKey, fSalt, plainText);
			System.out.println("Cipher: " + cipherText);
			String dcrCipherText = decrypt(secretKey, fSalt, cipherText);
			System.out.println("Decrypted: " + dcrCipherText);
			System.out.println("secretKey: " + secretKey);
			systemUser.setSenha(cipherText);
			return plainText;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
						
	}


//	public String generate(String senhaTexto) {
//
//		try {
//			byte[] digest = MessageDigest.getInstance("sha-256").digest(senhaTexto.getBytes());
//			return Base64Encoder.encode(digest);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//
//	}
	
	public String generate(String senhaTexto, byte[] salt) throws NoSuchAlgorithmException, IOException {
		byte[] passwordBytes = senhaTexto.getBytes("UTF-8");
		byte[] hashSource = new byte[passwordBytes.length + salt.length];
        System.arraycopy(passwordBytes, 0, hashSource, 0, passwordBytes.length);
        System.arraycopy(salt, 0, hashSource, passwordBytes.length, salt.length);
//		try {
//			byte[] digest = MessageDigest.getInstance("sha-256").digest(senhaTexto.getBytes());
//			return Base64Encoder.encode(digest);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
		
        // 2. Hash the password with the salt
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(hashSource);
        byte[] digest = md.digest();
        return new String(Base64.encodeBase64(digest), "UTF-8");

	}
	
	
	public static String encrypt(String secretKey, String salt, String value) throws Exception {
		Cipher cipher = initCipher(secretKey, salt, Cipher.ENCRYPT_MODE);
		        byte[] encrypted = cipher.doFinal(value.getBytes());
		        byte[] cipherWithIv = addIVToCipher(encrypted);
		        return Base64.encodeBase64String(cipherWithIv);
		    }

		public static String decrypt(String secretKey, String salt, String encrypted) throws Exception {
		       Cipher cipher = initCipher(secretKey, salt, Cipher.DECRYPT_MODE);
		       byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
		       byte[] originalWithoutIv = Arrays.copyOfRange(original, 16, original.length);
		        return new String(originalWithoutIv);
		    }

		private static Cipher initCipher(String secretKey, String salt, int mode) throws Exception {
		       SecretKeyFactory factory = SecretKeyFactory.getInstance(factoryInstance);
		       KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
		        SecretKey tmp = factory.generateSecret(spec);
		       SecretKeySpec skeySpec = new SecretKeySpec(tmp.getEncoded(), secretKeyType);
		        Cipher cipher = Cipher.getInstance(cipherInstance);
		        // Generating random IV
		        SecureRandom random = new SecureRandom();
		        random.nextBytes(ivCode);

		        cipher.init(mode, skeySpec, new IvParameterSpec(ivCode));
		        return cipher;
		    }

		private static byte[] addIVToCipher(byte[] encrypted) {
		        byte[] cipherWithIv = new byte[ivCode.length + encrypted.length];
		        System.arraycopy(ivCode, 0, cipherWithIv, 0, ivCode.length);
		        System.arraycopy(encrypted, 0, cipherWithIv, encrypted.length, encrypted.length);
		        return cipherWithIv;
		    }

}
