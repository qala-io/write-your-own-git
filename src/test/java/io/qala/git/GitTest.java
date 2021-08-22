package io.qala.git;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GitTest {
    private final Path projectDir = Paths.get("target/git", UUID.randomUUID().toString());
    private final Git git = Git.init(projectDir);

    @Test public void canAddDirectory() {
//        git.add();
    }
}