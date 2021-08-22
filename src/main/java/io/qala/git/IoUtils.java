package io.qala.git;

import java.nio.file.Path;

class IoUtils {

    static void mkdirsIfDoesNotExist(Path dir) {
        if(!dir.toFile().exists())
            if(!dir.toFile().mkdirs())
                throw new RuntimeException("Couldn't create directory " + dir.toAbsolutePath());
    }
    static void mkdirIfDoesNotExist(Path dir) {
        if(!dir.toFile().exists())
            if(!dir.toFile().mkdir())
                throw new RuntimeException("Couldn't create directory " + dir.toAbsolutePath());
    }
}
