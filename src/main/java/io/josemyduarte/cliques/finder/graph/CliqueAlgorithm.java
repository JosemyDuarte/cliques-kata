package io.josemyduarte.cliques.finder.graph;

import io.josemyduarte.cliques.Clique;
import io.josemyduarte.cliques.finder.SocialUser;

import java.util.Set;

/**
 * Algorithm to find Cliques in a Set of social users
 */
public interface CliqueAlgorithm {
    /**
     * Given a set of social users will return a set of cliques
     *
     * @param socialUsers set of social users to check for cliques
     * @return cliques found
     */
    Set<Clique> findCliquesIn(Set<SocialUser> socialUsers);
}
