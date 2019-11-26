import objects.*

/**
 * Initial dir structure:
 * <pre>
 *     dir
 *       |_ a.txt
 *     dir2
 *       |_ b.txt
 *     c.txt
 * </pre>
 */
Blob b1 = new Blob("Hello".bytes),
     b2 = new Blob("World".bytes)
Tree d1 = new Tree(['a.txt': b1]),
     d2 = new Tree(['b.txt': b2])
Tree root = new Tree([
        'dir'  : d1,
        'dir2' : d2,
        'c.txt': b1])
Commit c1 = new Commit(root)
/**
 * This is an empty commit since it references the same tree, so it doesn't change the tree structure
 */
Commit c2 = new Commit(root, c1) // same tree, so it's an empty commit

d1 = new Tree([
        'a.txt': b1,
        'd.txt': new Blob('blah'.bytes)])
/**
 * This changes the tree structure to:
 * <pre>
 *     dir
 *       |_ a.txt
 *       |_ d.txt
 *     dir2
 *       |_ b.txt
 *     c.txt  // new content!
 * </pre>
 */
Commit c3 = new Commit(new Tree([// new root
        'dir': d1,// new d1 with additional file
        'dir2': d2,
        'c.txt': new Blob("blah2".bytes)/*file content changed*/],
), c2)