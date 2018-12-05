package io.josemyduarte.cliques.reader;

import io.josemyduarte.cliques.finder.User;

import java.io.IOException;
import java.util.Set;

/**
 * Interface to read users from a file
 */
public interface FileReader {
    Set<User> readFrom(String filePath) throws IOException;
}
