package io.qala.git;

public record TreeLine(ObjectType type, Sha sha, String filename) {
    static TreeLine parse(String objectFileLine) {
        String objectType = objectFileLine.substring(7, 11);
        String sha = objectFileLine.substring(12, 52);
        String filename = objectFileLine.substring(56);
        return new TreeLine(ObjectType.parse(objectType), new Sha(sha), filename);
    }
}
