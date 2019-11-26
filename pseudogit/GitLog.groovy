import objects.*

Tree t = new Tree()
Commit commit = new Commit('1st', t, new Commit('2nd', t, new Commit('3d', t, new Commit('4th', t))))

for(Commit c = commit; c.parent != null; c = c.parent)
    println c
