package io.qala.git;

class Blob implements GitObject {
    private final byte[] data;
    
    public Blob(byte[] data) {
        this.data = data;
    }

    public Sha getSha() {
        return Sha.from(getFileContent());
    }
    public byte[] getFileContent() {
        byte[] header = ("blob " + data.length + "\0").getBytes();
        byte[] result = new byte[header.length + data.length];
        System.arraycopy(header, 0, result, 0, header.length);
        System.arraycopy(data, 0, result, header.length, data.length);
        return result;
    }
    public byte[] getData() {
        return data;
    }
}
