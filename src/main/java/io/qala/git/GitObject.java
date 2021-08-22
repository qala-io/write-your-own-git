package io.qala.git;

interface GitObject {
    default Sha getSha() {
        return Sha.from(getFileContent());
    }
    byte[] getFileContent();
    byte[] getPayload();
}
