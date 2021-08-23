package io.qala.git;

class Blob implements GitObject {
    private final byte[] data;

    public Blob(byte[] data) {
        this.data = data;
    }

    public byte[] getFileContent() {
        byte[] payload = getPayload();
        byte[] header = ("blob " + payload.length + "\0").getBytes();
        byte[] result = new byte[header.length + payload.length];
        System.arraycopy(header, 0, result, 0, header.length);
        System.arraycopy(payload, 0, result, header.length, payload.length);
        return result;
    }
    public byte[] getPayload() {
        return data;
    }
}
