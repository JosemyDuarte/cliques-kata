package io.josemyduarte.cliques.writer;

import io.josemyduarte.cliques.Clique;
import io.josemyduarte.cliques.finder.User;

import java.util.stream.Collectors;

/**
 * Helper class to parse from a Clique to a String
 */
final class OutputParser {

    static String parse(Clique clique) {
        return clique.getUsersInAClique()
                .stream()
                .map(User::getUsername)
                .collect(Collectors.joining(" "));
    }

}
