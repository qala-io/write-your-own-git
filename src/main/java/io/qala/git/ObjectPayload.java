package io.qala.git;

interface ObjectPayload {
    ObjectType getType();
    byte[] toBytes();
}
