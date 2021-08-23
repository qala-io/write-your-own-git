package io.qala.git;

public record TreeLine(ObjectType type, Sha sha, String filename) {
    static TreeLine parse(String objectFileLine) {
        String objectType = objectFileLine.substring(7, 11);
        String sha = objectFileLine.substring(12, 52);
        String filename = objectFileLine.substring(56);
        return new TreeLine(ObjectType.parse(objectType), new Sha(sha), filename);
    }

    @Override
    public String toString() {
        String filemode = type == ObjectType.BLOB ? "100644" : "040000";
        return filemode + " " + type.officialName + " " + sha + "    " + filename;
    }
}
