package io.qala.git;

import java.nio.file.Path;

public class Git {
    private final Path workingDir;
    private final Path gitDir;

    public Git(Path workingDir) {
        this.workingDir = workingDir;
        this.gitDir = workingDir.resolve(".git");
    }
    public static Git init(Path path) {
        new CommandInit().init(path);
        return new Git(path);
    }

    public FsGitObjects getObjects() {
        Path objectsDir = gitDir.resolve("objects");
        IoUtils.mkdirsIfDoesNotExist(objectsDir);
        return new FsGitObjects(objectsDir);
    }

}
