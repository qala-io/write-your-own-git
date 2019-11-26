import objects.*

Tree t = new Tree()
Commit commit = new Commit(t, new Commit(t, new Commit(t, new Commit())))

for(Commit c = commit; c.parent != null; c = c.parent)
    println c
