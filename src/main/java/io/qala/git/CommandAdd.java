package io.qala.git;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class CommandAdd {
    private final Path workingDir, gitDir;
    private final FsGitObjects objects;

    CommandAdd(Path workingDir, Path gitDir, FsGitObjects objects) {
        this.workingDir = workingDir;
        this.gitDir = gitDir;
        this.objects = objects;
    }

    GitObject add(String filepath) {
        Path path = Paths.get(filepath);
        Path absolutePath = path.toAbsolutePath();
        if(!absolutePath.startsWith(workingDir.toAbsolutePath()) || absolutePath.startsWith(gitDir))
            throw new RuntimeException("fatal: not a git repository (or any of the parent directories): .git");
        File file = path.toFile();
        if(file.isFile()) {
            Blob object = new Blob(IoUtils.readFully(file));
            return objects.add(object);
        } else {
            List<TreeLine> lines = new ArrayList<>();
            for(File f: Objects.requireNonNull(file.listFiles())) {
                if(f.isDirectory() && f.toPath().endsWith(".git"))
                    continue;
                GitObject child = add(f.getAbsolutePath());
                lines.add(new TreeLine(f.getName(), child));
            }
            return objects.add(new Tree(lines));
        }
    }
}
