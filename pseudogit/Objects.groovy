class Tree {
    Map<String, Blob> blobs
    Map<String, Tree> trees

    Tree(Map<String, Object> blobsAndTrees) {}
}
class Blob {
    Blob(byte[] content) { this.content = content }
    byte[] content
}
class Commit1 {
    Tree root
    Commit1 parent

    Commit1(Tree root, Commit1 parent = null) {
        this.root = root
        this.parent = parent
    }
}