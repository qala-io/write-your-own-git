package io.qala.git;

import java.io.*;
import java.nio.file.Path;
import java.util.zip.DeflaterInputStream;
import java.util.zip.DeflaterOutputStream;

class FsGitObjects {
    final Path dir;

    public FsGitObjects(Path gitObjects) {
        this.dir = gitObjects;
    }

    public GitObject get(Sha sha) {
        Path objectFile = dir.resolve(sha.getParentDirName()).resolve(sha.getFilename());
        try {
            DeflaterInputStream input = new DeflaterInputStream(new FileInputStream(objectFile.toFile()));
            System.out.println(new String(input.readAllBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void add(GitObject o) {
        Sha sha = o.getSha();
        if(get(sha) != null)
            return;
        Path hashDir = dir.resolve(sha.getParentDirName());
        IoUtils.mkdirIfDoesNotExist(hashDir);
        File objectFile = hashDir.resolve(sha.getFilename()).toFile();
        touch(objectFile);
        try(FileOutputStream fileOutput = new FileOutputStream(objectFile)) {
            DeflaterOutputStream compressing = new DeflaterOutputStream(fileOutput);
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
