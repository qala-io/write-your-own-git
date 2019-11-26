Blob b1 = new Blob("Hello".bytes),
     b2 = new Blob("World".bytes)
Tree d1 = new Tree(['file.txt': b1]),
     d2 = new Tree(['file.txt': b2])
Tree root = new Tree([
        'dir'  : d1,
        'dir2' : d2,
        'a.txt': b1])
Commit1 c1 = new Commit1(root)
Commit1 c2 = new Commit1(root, c1) // same tree, so it's an empty commit
Commit1 c3 = new Commit1(new Tree([
        'dir': new Tree(['file.txt': b1, 'file2.txt': new Blob('blah'.bytes)]),
        'dir2': d2,
        'a.txt': new Blob("blah2".bytes)],
), c2)