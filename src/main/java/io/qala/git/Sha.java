package io.qala.git;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

record Sha(String hex) {
    private static final byte[] HEX_ARRAY = "0123456789abcdef".getBytes(StandardCharsets.US_ASCII);

    Sha {
        if(hex.length() != 40)
            throw new IllegalArgumentException("Hex isn't 40 bytes: [" + hex + "]");
    }

    static Sha from(byte[] content) {
        try {
            return new Sha(bytesToHex(MessageDigest.getInstance("SHA-1").digest(content)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getParentDirName() {
        return hex().substring(0, 2);
    }
    public String getFilename() {
        return hex().substring(2);
    }

    private static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }
}
