package io.josemyduarte.cliques.finder;

import io.josemyduarte.cliques.finder.graph.AlgorithmsFactory;
import io.josemyduarte.cliques.finder.graph.CliqueAlgorithm;
import io.josemyduarte.cliques.finder.graph.ConnectionBuilder;
import io.josemyduarte.cliques.finder.graph.ConnectorsFactory;

public class CliqueFindersFactory {

    private CliqueFindersFactory() {
    }

    public static CliqueFinder newDefaultFinder(ConnectionBuilder connectionBuilder, CliqueAlgorithm cliqueAlgorithm) {
        return DefaultCliqueFinder.newInstance(connectionBuilder, cliqueAlgorithm);
    }

    public static CliqueFinder newDefaultFinder() {
        return DefaultCliqueFinder.newInstance(ConnectorsFactory.defaultConnectionBuilder(), AlgorithmsFactory.defaultAlgorithm());
    }
}
