package io.qala.git;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

class IoUtils {
    static byte[] readFully(File file) {
        try(InputStream input = new FileInputStream(file)) {
            return input.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
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
    static void touch(Path file) {
        touch(file.toFile());
    }
    static void touch(File file) {
        try {
            if(!file.createNewFile())
                throw new RuntimeException("Couldn't create file " + file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
