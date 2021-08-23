package io.qala.git;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

class CommandAdd {
    private final Path workingDir, gitDir;
    private final FsGitObjects objects;

    CommandAdd(Path workingDir, Path gitDir, FsGitObjects objects) {
        this.workingDir = workingDir;
        this.gitDir = gitDir;
        this.objects = objects;
    }

    GitObject add(String pattern) {
        Path path = Paths.get(pattern);
        Path absolutePath = path.toAbsolutePath();
        if(!absolutePath.startsWith(workingDir.toAbsolutePath()) || absolutePath.startsWith(gitDir))
            throw new RuntimeException("fatal: not a git repository (or any of the parent directories): .git");
        File file = path.toFile();
        if(file.isFile()) {
            Blob object = new Blob(IoUtils.readFully(file));
            return objects.add(object);
        }
        throw new UnsupportedOperationException();
    }
}
