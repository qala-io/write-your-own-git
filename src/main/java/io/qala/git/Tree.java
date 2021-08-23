package io.qala.git;

import java.util.ArrayList;
import java.util.List;

class Tree implements GitObject {
    private List<TreeLine> lines;

    public Tree(byte[] payload) {
        String s = new String(payload);
        this.lines = new ArrayList<>();
        for(String line: s.split("\n"))
            lines.add(TreeLine.parse(line));
    }

    public List<TreeLine> getLines() {
        return lines;
    }

    public byte[] getFileContent() {
        return null;
//        byte[] header = ("tree " + data.length() + "\0").getBytes();
//        byte[] result = new byte[header.length + data.length()];
//        System.arraycopy(header, 0, result, 0, header.length);
//        System.arraycopy(data.getBytes(), 0, result, header.length, data.length());
//        return result;
    }
    public byte[] getPayload() {
        return null;
    }
}
