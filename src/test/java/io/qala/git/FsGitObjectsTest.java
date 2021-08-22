package io.qala.git;

import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.junit.Assert.assertTrue;

public class FsGitObjectsTest {
    @Test public void whenAddingObjects_ifParentDirDoesNotExist_itIsCreated() {
        FsGitObjects objects = randomGitObjects();
        Blob blob = new Blob("".getBytes());
        objects.add(blob);

        File objectFile = objects.dir.resolve("e6/9de29bb2d1d6434b8b29ae775ad8c2e48c5391").toFile();
        assertTrue(objectFile.exists());
    }

    static FsGitObjects randomGitObjects() {
        String folder = UUID.randomUUID().toString();
        Path path = Paths.get("target/", folder);
        return new Git().init(path);
    }
}