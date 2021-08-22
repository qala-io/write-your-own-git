package io.qala.git;

import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FsGitObjectsTest {
    private final Path projectDir = Paths.get("target/git", UUID.randomUUID().toString());
    private final Path objectsDir = projectDir.resolve(".git/objects");
    private final FsGitObjects objects = new Git().init(projectDir);

    @Test public void whenAddingObjects_ifParentHashDirDoesNotExist_itIsCreated() {
        Blob blob = new Blob("".getBytes());
        objects.add(blob);

        File objectFile = objectsDir.resolve("e6/9de29bb2d1d6434b8b29ae775ad8c2e48c5391").toFile();
        assertTrue("Had to exist " + objectFile.getAbsolutePath(), objectFile.exists());
        assertEquals("blob 0\0", new String(objects.get(new Sha("e69de29bb2d1d6434b8b29ae775ad8c2e48c5391")).getFileContent()));
    }
}