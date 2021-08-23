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
            byte[] payload = ByteUtils.extractObjectPayload(data);
            ObjectType type = ObjectType.parse(data);
            if(type == ObjectType.BLOB)
                return new GitObject(new Blob(payload));
            else if(type == ObjectType.TREE)
                return new GitObject(new Tree(payload));
            throw new UnsupportedOperationException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public GitObject add(ObjectPayload payload) {
        GitObject o = new GitObject(payload);
        Sha sha = o.getSha();
        if(get(sha) != null)
            return get(sha);
        Path hashDir = dir.resolve(sha.getParentDirName());
        IoUtils.mkdirIfDoesNotExist(hashDir);
        File objectFile = hashDir.resolve(sha.getFilename()).toFile();
        IoUtils.touch(objectFile);
        try(FileOutputStream fileOutput = new FileOutputStream(objectFile);
            DeflaterOutputStream compressing = new DeflaterOutputStream(fileOutput)) {
            compressing.write(o.getFileContent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return o;
    }
}
