package io.qala.git;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShaTest {
    @Test public void emptyContentStillGetsSha() {
        assertEquals("e69de29bb2d1d6434b8b29ae775ad8c2e48c5391", new Blob("".getBytes()).getSha().hex());
    }

}