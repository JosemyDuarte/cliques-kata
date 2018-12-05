package io.josemyduarte.cliques.finder.graph;

public final class AlgorithmsFactory {

    public static CliqueAlgorithm defaultAlgorithm() {
        return DefaultCliqueAlgorithm.newInstance();
    }
}
