package io.qala.git;

import org.junit.Test;

import static org.junit.Assert.*;

public class TreeLineTest {
    @Test public void canParseBlobLine() {
        TreeLine parsed = TreeLine.parse("100644 blob 84758536eb7316cde2de3a3a2f6da6240d517c0a    README.md");
        assertEquals(ObjectType.BLOB, parsed.type());
        assertEquals(new Sha("84758536eb7316cde2de3a3a2f6da6240d517c0a"), parsed.sha());
        assertEquals("README.md", parsed.filename());
    }
    @Test public void canParseTreeLine() {
        TreeLine parsed = TreeLine.parse("040000 tree 6e0275aefdc866a7f89e05a38a4bed4e51ef0a6d    src");
        assertEquals(ObjectType.TREE, parsed.type());
        assertEquals(new Sha("6e0275aefdc866a7f89e05a38a4bed4e51ef0a6d"), parsed.sha());
        assertEquals("src", parsed.filename());
    }
}