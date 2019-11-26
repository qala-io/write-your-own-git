package objects

class Commit {
    String message
    Tree root
    Commit parent

    Commit(String message, Tree root, Commit parent = null) {
        this.message = message
        this.root = root
        this.parent = parent
    }
}