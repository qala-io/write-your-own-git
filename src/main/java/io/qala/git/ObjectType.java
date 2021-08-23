package io.qala.git;

enum ObjectType {
    TREE("tree"), BLOB("blob"), COMMIT("commit");

    final String name;

    ObjectType(String name) {
        this.name = name;
    }

    static ObjectType parse(byte[] objectFileContent) {
        if(ByteUtils.startsWith(objectFileContent, "blob".getBytes()))
            return BLOB;
        else if(ByteUtils.startsWith(objectFileContent, "tree".getBytes()))
            return TREE;
        throw new UnsupportedOperationException();
    }
    static ObjectType parse(String name) {
        return valueOf(name.toUpperCase());
    }
}
