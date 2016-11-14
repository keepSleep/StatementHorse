package com.member.model;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class PasswordEncorder {
	public static final String KEY_SHA = "SHA-256";
	public static final String KEY = "birdyshin";

	public static String encrypt(String inputStr) {
		byte[] sha = null;
		byte[] inputData = enc(inputStr);
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
			messageDigest.update(inputData);
			sha = messageDigest.digest();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return byteToString(sha);
	}

	private static byte[] enc(String text) {
		byte[] result = null;
		try {
			DESKeySpec desKey = new DESKeySpec(KEY.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, securekey);
			result = cipher.doFinal(text.getBytes());
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private static String byteToString(byte[] data) {
		StringBuilder sb = new StringBuilder();
		for (byte b : data) {
			sb.append(String.format("0%2x", b));
		}
		return Base64.getEncoder().encodeToString(sb.toString().getBytes());
	}

	public static void main(String args[]) {
		try {
			String inputStr = "a123456";
			String result = encrypt(inputStr);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}