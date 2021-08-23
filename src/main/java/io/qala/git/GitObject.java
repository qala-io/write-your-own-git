package io.qala.git;

class GitObject {
    private final ObjectPayload payload;

    GitObject(ObjectPayload payload) {
        this.payload = payload;
    }

    Sha getSha() {
        return Sha.from(getFileContent());
    }
    byte[] getFileContent() {
        byte[] bytes = getPayload().toBytes();
        byte[] header = (getPayload().getType().officialName + " " + bytes.length + "\0").getBytes();

        byte[] result = new byte[header.length + bytes.length];
        System.arraycopy(header, 0, result, 0, header.length);
        System.arraycopy(bytes, 0, result, header.length, bytes.length);
        return result;
    }
    ObjectPayload getPayload() {
        return payload;
    }
}
