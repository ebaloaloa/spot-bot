package com.lickhunter.spotbot.utils;

@FunctionalInterface
public interface FileReader<FILE> {
    public abstract FILE read(String path, String filename, Class classType);
}
