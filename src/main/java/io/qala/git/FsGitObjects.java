package io.qala.git;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

class FsGitObjects {
    private final Path dir;

    public FsGitObjects(Path gitObjects) {
        this.dir = gitObjects;
    }

    public GitObject get(Sha sha) {
        Path objectFile = dir.resolve(sha.getParentDirName()).resolve(sha.getFilename());
        if(!objectFile.toFile().exists())
            return null;
        try (FileInputStream in = new FileInputStream(objectFile.toFile());
             InflaterInputStream input = new InflaterInputStream(in)){
            byte[] data = input.readAllBytes();
            if(ByteUtils.startsWith(data, "blob".getBytes()))
                return new Blob(ByteUtils.extractObjectPayload(data));
            throw new UnsupportedOperationException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void add(GitObject o) {
        Sha sha = o.getSha();
        if(get(sha) != null)
            return;
        Path hashDir = dir.resolve(sha.getParentDirName());
        IoUtils.mkdirIfDoesNotExist(hashDir);
        File objectFile = hashDir.resolve(sha.getFilename()).toFile();
        touch(objectFile);
        try(FileOutputStream fileOutput = new FileOutputStream(objectFile);
            DeflaterOutputStream compressing = new DeflaterOutputStream(fileOutput)) {
            compressing.write(o.getFileContent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void touch(File file) {
        try {
            if(!file.createNewFile())
                throw new RuntimeException("Couldn't create file " + file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}