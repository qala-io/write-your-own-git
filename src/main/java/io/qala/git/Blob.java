package io.qala.git;

class Blob implements ObjectPayload {
    private final byte[] data;

    public Blob(byte[] data) {
        this.data = data;
    }

    public byte[] toBytes() {
        return data;
    }

    public ObjectType getType() {
        return ObjectType.BLOB;
    }
}
