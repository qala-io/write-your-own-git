package io.qala.git;

class ByteUtils {
    static boolean startsWith(byte[] data, byte[] prefix) {
        for (int i = 0; i < prefix.length; i++)
            if(data[i] != prefix[i])
                return false;
        return true;
    }
    static byte[] extractObjectPayload(byte[] objectFileContent) {
        int i;
        for (i = 0; i < objectFileContent.length; i++)
            if(objectFileContent[i] == '\0')
                break;
        return copy(objectFileContent, i + 1);
    }
    static byte[] copy(byte[] objectFileContent, int startWith) {
        byte[] result = new byte[objectFileContent.length - startWith];
        System.arraycopy(objectFileContent, startWith, result, 0, result.length);
        return result;
    }
}
