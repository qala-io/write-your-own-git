package io.qala.git;

import java.util.ArrayList;
import java.util.List;

class Tree implements ObjectPayload {
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

    public ObjectType getType() {
        return ObjectType.TREE;
    }
    public byte[] toBytes() {
        StringBuilder s = new StringBuilder();
        for (TreeLine line : lines)
            s.append(line).append('\n');
        return s.toString().getBytes();
    }
}
