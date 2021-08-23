package io.qala.git;

import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FsGitObjectsTest {
    private final Path projectDir = Paths.get("target/git", UUID.randomUUID().toString());
    private final Path objectsDir = projectDir.resolve(".git/objects");
    private final FsGitObjects objects = Git.init(projectDir).getObjects();

    @Test public void whenAddingObjects_createsObjectFile_inGitDatabase() {
        Blob blob = new Blob("".getBytes());
        objects.add(blob);

        File objectFile = objectsDir.resolve("e6/9de29bb2d1d6434b8b29ae775ad8c2e48c5391").toFile();
        assertTrue("Had to exist " + objectFile.getAbsolutePath(), objectFile.exists());
        assertEquals("blob 0\0", new String(objects.get(new Sha("e69de29bb2d1d6434b8b29ae775ad8c2e48c5391")).getFileContent()));
    }
    @Test public void canAddAndGetTreeObject() {
        Tree object = new Tree(List.of(
                new TreeLine(ObjectType.BLOB, new Sha("e69de29bb2d1d6434b8b29ae775ad8c2e48c5391"), "file.txt"),
                new TreeLine(ObjectType.TREE, new Sha("e69de29bb2d1d6434b8b29ae775ad8c2e48c5392"), "file2.txt")
        ));
        objects.add(object);

        File objectFile = objectsDir.resolve("33/4ed4bcdcd6193d7831283a55e0eddf0107f5ed").toFile();
        assertTrue("Had to exist " + objectFile.getAbsolutePath(), objectFile.exists());
        assertEquals("""
                tree 131\000100644 blob e69de29bb2d1d6434b8b29ae775ad8c2e48c5391    file.txt
                040000 tree e69de29bb2d1d6434b8b29ae775ad8c2e48c5392    file2.txt
                """,
                new String(objects.get(new Sha("334ed4bcdcd6193d7831283a55e0eddf0107f5ed")).getFileContent()));
    }
}