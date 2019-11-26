package objects

class Tree {
    Map<String, Blob> blobs
    Map<String, Tree> trees

    Tree(Map<String, Object> blobsAndTrees) {}
}

