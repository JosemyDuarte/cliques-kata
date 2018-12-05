package io.josemyduarte.cliques.reader;

import io.josemyduarte.cliques.finder.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Default implementation of a fileReader
 * that map lines in a file to Users
 */
class DefaultFileReader implements FileReader {

    private DefaultFileReader() {
    }

    static FileReader newInstance() {
        return new DefaultFileReader();
    }

    @Override
    public Set<User> readFrom(String filePath) throws IOException {
        try (Stream<String> streams = Files.lines(Paths.get(filePath))) {
            return streams.map(User::new)
                    .collect(Collectors.toSet());
        }
    }
}
