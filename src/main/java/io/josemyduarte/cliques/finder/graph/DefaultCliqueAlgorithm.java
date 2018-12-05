package io.josemyduarte.cliques.finder.graph;

import io.josemyduarte.cliques.Clique;
import io.josemyduarte.cliques.finder.SocialUser;
import io.josemyduarte.cliques.finder.User;
import org.jgrapht.Graph;
import org.jgrapht.alg.clique.PivotBronKerboschCliqueFinder;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Default implementation of CliqueAlgorithm that
 * use Bron-Kerbosch algorithm with pivot to find cliques
 */
class DefaultCliqueAlgorithm implements CliqueAlgorithm {

    private static final int MIN_CONNECTIONS = 2;

    private DefaultCliqueAlgorithm() {
    }

    public static DefaultCliqueAlgorithm newInstance() {
        return new DefaultCliqueAlgorithm();
    }

    public Set<Clique> findCliquesIn(final Set<SocialUser> socialUsers) {

        Graph<User, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        for (SocialUser user1 : socialUsers) {
            for (SocialUser user2 : socialUsers) {
                if (theyFollowEachOther(user1, user2) && theyHaveAnOrganizationInCommon(user1, user2)) {
                    graph.addVertex(user1.getUser());
                    graph.addVertex(user2.getUser());
                    graph.addEdge(user1.getUser(), user2.getUser());
                }
            }
        }
        PivotBronKerboschCliqueFinder<User, DefaultEdge> cliqueFinder = new PivotBronKerboschCliqueFinder<>(graph);
        return filterCliquesWithoutMinConnections(cliqueFinder.iterator(), MIN_CONNECTIONS);
    }

    private boolean theyHaveAnOrganizationInCommon(SocialUser user1, SocialUser user2) {
        // TODO change to a Set before comparing -> https://stackoverflow.com/questions/11796371/check-if-one-list-contains-element-from-the-other#comment59122192_11799240
        return !Collections.disjoint(user1.getGithubOrganizations(), user2.getGithubOrganizations());
    }

    private boolean theyFollowEachOther(SocialUser user1, SocialUser user2) {
        return user1.isFollowedBy(user2) && user2.isFollowedBy(user1);
    }

    private static Set<Clique> filterCliquesWithoutMinConnections(final Iterator<Set<User>> source, final int minConnections) {
        Set<Set<User>> target = new HashSet<>();
        source.forEachRemaining(target::add);
        return target.stream()
                .filter(e -> e.size() > minConnections)
                .map(users -> Clique.newInstance().addUsers(users))
                .collect(Collectors.toSet());
    }
}
