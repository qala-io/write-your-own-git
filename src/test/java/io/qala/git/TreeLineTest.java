package io.qala.git;

import org.junit.Test;

import static org.junit.Assert.*;

public class TreeLineTest {
    @Test public void canParseBlobLine() {
        String line = "100644 blob 84758536eb7316cde2de3a3a2f6da6240d517c0a    README.md";
        TreeLine parsed = TreeLine.parse(line);
        assertEquals(ObjectType.BLOB, parsed.type());
        assertEquals(new Sha("84758536eb7316cde2de3a3a2f6da6240d517c0a"), parsed.sha());
        assertEquals("README.md", parsed.filename());

        assertEquals(line, parsed.toString());
    }
    @Test public void canParseTreeLine() {
        String line = "040000 tree 6e0275aefdc866a7f89e05a38a4bed4e51ef0a6d    src";
        TreeLine parsed = TreeLine.parse(line);
        assertEquals(ObjectType.TREE, parsed.type());
        assertEquals(new Sha("6e0275aefdc866a7f89e05a38a4bed4e51ef0a6d"), parsed.sha());
        assertEquals("src", parsed.filename());

        assertEquals(line, parsed.toString());
    }
}