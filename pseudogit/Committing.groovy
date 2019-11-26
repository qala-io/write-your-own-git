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
Commit c1 = new Commit('Initial commit', root)
/**
 * This is an empty commit since it references the same tree, so it doesn't change the tree structure:
 * {@code git commit --allow-empty}
 */
Commit c2 = new Commit('Empty commit since it references the same tree as its parent', root, c1)
/**
 * New files were added:
 *
 * echo 'Hello' > dir/a.txt
 * echo 'blah' > dir/d.txt
 * git add -u
 */
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
root = new Tree([
        'dir'  : d1,// new d1 with additional file
        'dir2' : d2,
        'c.txt': new Blob("blah2".bytes)/*additionally changed the file content*/],
)
/**
 * Now new commit references new Tree and prev commit becomes its parent:
 * git commit -m '...'
 */
Commit c3 = new Commit('New commit references new root Tree because it in turn references new sub-Tree', root, c2)