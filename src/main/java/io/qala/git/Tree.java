package io.qala.git;

import java.util.ArrayList;
import java.util.List;

class Tree implements GitObject {
    private final List<TreeLine> lines;

    public Tree(byte[] payload) {
        String s = new String(payload);
        this.lines = new ArrayList<>();
        for(String line: s.split("\n"))
            lines.add(TreeLine.parse(line));
    }
    public Tree(List<TreeLine> lines) {
        this.lines = lines;
    }

    public List<TreeLine> getLines() {
        return lines;
    }

    public byte[] getFileContent() {
        byte[] payload = getPayload();
        byte[] header = ("tree " + payload.length + "\0").getBytes();
        byte[] result = new byte[header.length + payload.length];
        System.arraycopy(header, 0, result, 0, header.length);
        System.arraycopy(payload, 0, result, header.length, payload.length);
        return result;
    }
    public byte[] getPayload() {
        StringBuilder s = new StringBuilder();
        for (TreeLine line : lines)
            s.append(line).append('\n');
        return s.toString().getBytes();
    }
}
