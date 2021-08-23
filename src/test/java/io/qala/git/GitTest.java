package io.qala.git;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class GitTest {
    private final Path projectDir = Paths.get("target/git", UUID.randomUUID().toString());
    private final Git git = Git.init(projectDir);

    @Test public void errIfTryingToAddFileOutsideOfWorkingDir() {
        Exception e = assertThrows(Exception.class, ()-> git.add("pom.xml"));
        assertEquals("fatal: not a git repository (or any of the parent directories): .git", e.getMessage());
    }
    @Test public void addingFileCreatesBlobObject() {
        Path file = projectDir.resolve("hello.txt");
        IoUtils.touch(file);
        GitObject blob = git.add(file.toAbsolutePath().toString());

        Sha expectedSha = new Sha("e69de29bb2d1d6434b8b29ae775ad8c2e48c5391");
        assertEquals(expectedSha, blob.getSha());
        assertEquals("", new String(git.getObjects().get(expectedSha).getPayload().toBytes()));
    }
    @Test public void addingFolderAddsAllFilesInside() {
        Path file = projectDir.resolve("hello.txt");
        IoUtils.touch(file);
        GitObject tree = git.add(projectDir.toString());

        Sha expectedSha = new Sha("ac81123ee76ed8e0037419421cecf533260d28ae");
        assertEquals(expectedSha, tree.getSha());
        assertEquals("100644 blob e69de29bb2d1d6434b8b29ae775ad8c2e48c5391    hello.txt\n",
                new String(git.getObjects().get(expectedSha).getPayload().toBytes()));
    }
}