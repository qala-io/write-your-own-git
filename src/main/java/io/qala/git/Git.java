package io.qala.git;

import java.nio.file.Path;

public class Git {
    public FsGitObjects init(Path path) {
        Path gitDir = path.resolve(".git");
        if(gitDir.toFile().exists())
            throw new RuntimeException("Couldn't init a new Git project since it already exists: " + gitDir.toAbsolutePath());
        IoUtils.mkdirsIfDoesNotExist(gitDir);
        return new FsGitObjects(gitDir);
    }
}
