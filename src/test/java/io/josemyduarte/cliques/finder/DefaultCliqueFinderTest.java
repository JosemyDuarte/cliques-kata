package io.josemyduarte.cliques.finder;

import io.josemyduarte.cliques.Clique;
import io.josemyduarte.cliques.finder.graph.CliqueAlgorithm;
import io.josemyduarte.cliques.finder.graph.ConnectionBuilder;
import io.josemyduarte.cliques.finder.social.github.GithubException;
import org.junit.Assert;
import org.junit.Test;
import twitter4j.TwitterException;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

public class DefaultCliqueFinderTest {

    @Test
    public void findCliquesOn_shouldBuildConnections() throws TwitterException, GithubException {
        User adam = new User("Adam");
        User alfred = new User("Alfred");
        User bob = new User("Bob");

        Set<User> users = new TreeSet<>();
        users.add(adam);
        users.add(alfred);
        users.add(bob);

        ConnectionBuilder connectionBuilder = mock(ConnectionBuilder.class);
        CliqueAlgorithm cliqueAlgorithm = mock(CliqueAlgorithm.class);

        when(connectionBuilder.build(users)).thenReturn(any());

        CliqueFinder cliqueFinder = CliqueFindersFactory.newDefaultFinder(connectionBuilder, cliqueAlgorithm);

        cliqueFinder.findCliquesOn(users);

        verify(connectionBuilder).build(users);

    }

    @Test
    public void findCliquesOn_shouldFindCliques() {
        User adam = new User("Adam");
        User alfred = new User("Alfred");
        User bob = new User("Bob");

        Set<User> users = new TreeSet<>();
        users.add(adam);
        users.add(alfred);
        users.add(bob);

        Set<Clique> cliquesExpected = Stream.of(Clique.newInstance().addUsers(users)).collect(Collectors.toSet());

        ConnectionBuilder connectionBuilder = mock(ConnectionBuilder.class);
        CliqueAlgorithm cliqueAlgorithm = mock(CliqueAlgorithm.class);

        when(cliqueAlgorithm.findCliquesIn(anySet())).thenReturn(cliquesExpected);

        CliqueFinder cliqueFinder = CliqueFindersFactory.newDefaultFinder(connectionBuilder, cliqueAlgorithm);

        Set<Clique> cliquesFound = cliqueFinder.findCliquesOn(anySet());

        Assert.assertEquals("Should return cliques", cliquesExpected, cliquesFound);
    }

}