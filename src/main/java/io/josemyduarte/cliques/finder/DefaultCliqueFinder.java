package io.josemyduarte.cliques.finder;

import io.josemyduarte.cliques.Clique;
import io.josemyduarte.cliques.finder.graph.CliqueAlgorithm;
import io.josemyduarte.cliques.finder.graph.ConnectionBuilder;

import java.util.Set;

/**
 * CliqueFinder implementation that given ConnectionBuilder and a CliqueAlgorithm
 * Will find Cliques on a given set of Users
 */
class DefaultCliqueFinder implements CliqueFinder {

    private final ConnectionBuilder connectionBuilder;
    private final CliqueAlgorithm cliqueAlgorithm;

    private DefaultCliqueFinder(ConnectionBuilder connectionBuilder, CliqueAlgorithm cliqueAlgorithm) {
        this.connectionBuilder = connectionBuilder;
        this.cliqueAlgorithm = cliqueAlgorithm;
    }

    public static CliqueFinder newInstance(ConnectionBuilder connectionBuilder, CliqueAlgorithm cliqueAlgorithm) {
        return new DefaultCliqueFinder(connectionBuilder, cliqueAlgorithm);
    }

    @Override
    public Set<Clique> findCliquesOn(Set<User> users) {
        Set<SocialUser> socialUsers = connectionBuilder.build(users);
        return cliqueAlgorithm.findCliquesIn(socialUsers);
    }

}
