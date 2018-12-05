package io.josemyduarte.cliques.writer;

import io.josemyduarte.cliques.Clique;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * Interface to write cliques and return file in which are the results.
 */
public interface CliqueWriter {
    File writeCliques(Set<Clique> cliques) throws IOException;
}
