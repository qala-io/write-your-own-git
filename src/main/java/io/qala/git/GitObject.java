package io.qala.git;

interface GitObject {
    Sha getSha();
    byte[] getFileContent();
}
