package com.z.dodo.signe;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SignUtil {
	
	public static String makeSignWithSHA1(String s) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(s.getBytes());
			byte[] messageDigest = digest.digest();
			return HexUtil.byte2hex(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public static String makeSignWithSHA256(String s) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(s.getBytes());
			byte[] messageDigest = digest.digest();
			return HexUtil.byte2hex(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

}
