package io.josemyduarte.cliques.finder;

import io.josemyduarte.cliques.Clique;

import java.util.Set;

/**
 * Interface that given a Set of Users will return a Set of Cliques (if any is found)
 */
public interface CliqueFinder {
    /**
     * Find cliques on a set of users
     *
     * @param users set of users to search for cliques
     * @return a set of cliques found
     */
    Set<Clique> findCliquesOn(Set<User> users);
}
