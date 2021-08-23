package io.qala.git;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TreeTest {
    @Test public void canParseFileObject() {
        String objectFilePayload = """
                100644 blob 84758536eb7316cde2de3a3a2f6da6240d517c0a    README.md
                100644 blob 8bfe544b2a2edc06c123752bf8ebb634a13cfa73    pom.xml
                040000 tree a1a4a9ea5626ce09f24b7a467a3cbfff8ebee006    pseudogit
                040000 tree 6e0275aefdc866a7f89e05a38a4bed4e51ef0a6d    src
                """;
        Tree tree = new Tree(objectFilePayload.getBytes());
        assertEquals(4, tree.getLines().size());
        assertEquals(
                new TreeLine(ObjectType.BLOB, new Sha("84758536eb7316cde2de3a3a2f6da6240d517c0a"), "README.md"),
                tree.getLines().get(0));
        assertEquals(
                new TreeLine(ObjectType.BLOB, new Sha("8bfe544b2a2edc06c123752bf8ebb634a13cfa73"), "pom.xml"),
                tree.getLines().get(1));
        assertEquals(
                new TreeLine(ObjectType.TREE, new Sha("a1a4a9ea5626ce09f24b7a467a3cbfff8ebee006"), "pseudogit"),
                tree.getLines().get(2));
        assertEquals(
                new TreeLine(ObjectType.TREE, new Sha("6e0275aefdc866a7f89e05a38a4bed4e51ef0a6d"), "src"),
                tree.getLines().get(3));
        assertEquals(objectFilePayload, new String(tree.getPayload()));
    }
}
