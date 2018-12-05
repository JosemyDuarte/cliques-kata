package io.josemyduarte.cliques.writer;

import io.josemyduarte.cliques.Clique;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;

/**
 * Default implementation to write cliques on a File
 */
class DefaultCliqueWriter implements CliqueWriter {


    private DefaultCliqueWriter() {
    }

    public static CliqueWriter newInstance() {
        return new DefaultCliqueWriter();
    }

    @Override
    public File writeCliques(Set<Clique> cliques) throws IOException {
        File resultFile = Files.createTempFile("cliques-", "-output").toFile();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(resultFile, true))) {
            cliques.forEach(clique -> writeToFile(bw, OutputParser.parse(clique)));
        }

        return resultFile;
    }

    private void writeToFile(BufferedWriter fw, String line) {
        try {
            fw.write(String.format("%s", line));
        } catch (IOException e) {
            throw new RuntimeException("Problem writing output file", e);
        }
    }
}
