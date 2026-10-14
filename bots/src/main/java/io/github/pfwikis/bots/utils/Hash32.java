package io.github.pfwikis.bots.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash32 {
	
	private static final MessageDigest HASH;
	static {
		try {
			HASH = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new Error(e);
		}
	}

	public static final char[] ALPHABET = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V'
	};
	public static String hash32(String input, Integer length) {
        byte[] hash = HASH.digest(input.getBytes(StandardCharsets.UTF_8));

        StringBuilder result = new StringBuilder(52);

        int buffer = 0;
        int bits = 0;

        for (byte b : hash) {
            buffer = (buffer << 8) | (b & 0xFF);
            bits += 8;

            while (bits >= 5) {
                bits -= 5;
                result.append(ALPHABET[(buffer >> bits) & 31]);
            }
        }

        if (bits > 0) {
            result.append(ALPHABET[(buffer << (5 - bits)) & 31]);
        }

        if (length != null) {
            if (length < 1 || length > 52) {
                throw new IllegalArgumentException(
                    "Length must be between 1 and 52"
                );
            }
            result.setLength(length);
        }

        return result.toString();
	}
}
