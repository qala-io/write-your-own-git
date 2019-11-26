package objects

class Commit {
    Tree root
    Commit parent

    Commit(Tree root, Commit parent = null) {
        this.root = root
        this.parent = parent
    }
}