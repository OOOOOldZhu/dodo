package com.z.dodo.signe;

public class HexUtil {
	private static char[] c = "0123456789ABCDEF".toCharArray();

	public static String byte2hex(byte[] b) {
		if (b == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xff;
			sb.append(c[v / 0x10]).append(c[v % 0x10]);
		}
		return sb.toString();
	}

	public static byte[] hex2byte(String s) {
		if (s == null) {
			return null;
		}
		byte[] b = s.getBytes();
		if ((b.length % 2) != 0) {
			throw new IllegalArgumentException();
		}
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

}
