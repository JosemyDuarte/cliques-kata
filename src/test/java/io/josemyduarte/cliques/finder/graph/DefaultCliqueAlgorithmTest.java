package io.josemyduarte.cliques.finder.graph;

import io.josemyduarte.cliques.Clique;
import io.josemyduarte.cliques.MotherClazz;
import io.josemyduarte.cliques.finder.SocialUser;
import io.josemyduarte.cliques.finder.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultCliqueAlgorithmTest {

    @Test
    public void givenAListOfUsers_shouldReturnAListOfCliques() {
        User adam = new User("Adam");

        List<User> users = Collections.singletonList(adam);
        Clique clique = Clique.newInstance().addUsers(users);
        Set<Clique> expectedCliques = Stream.of(clique)
                .collect(Collectors.toSet());

        Set<SocialUser> socialUsersWithAClique = users.stream()
                .map(MotherClazz::socialUserBuilder)
                .collect(Collectors.toSet());

        CliqueAlgorithm cliqueAlgorithm = mock(CliqueAlgorithm.class);
        when(cliqueAlgorithm.findCliquesIn(anySet())).thenReturn(expectedCliques);

        Set<Clique> actualCliques = cliqueAlgorithm.findCliquesIn(socialUsersWithAClique);

        Assert.assertThat("Should return a list of cliques", actualCliques, is(equalTo(expectedCliques)));

    }

    @Test
    public void givenAListOfUsersWithOneClique_shouldReturnAListOfOneClique() {
        User adam = new User("Adam");
        User juan = new User("Juan");
        User bob = new User("Bob");
        User jose = new User("Jose");

        Set<User> users = Stream.of(adam, juan, bob, jose).collect(Collectors.toSet());

        Clique clique = Clique.newInstance().addUsers(users);

        Set<Clique> expectedCliques = Stream.of(clique)
                .collect(Collectors.toSet());

        Set<SocialUser> socialUsersWithAClique = MotherClazz.createListWithCliques(users);

        assertThat("Users list has more elements that default initialization", users.size(), is(not(equalTo(socialUsersWithAClique.size()))));

        CliqueAlgorithm cliqueAlgorithm = DefaultCliqueAlgorithm.newInstance();

        Set<Clique> actualCliques = cliqueAlgorithm.findCliquesIn(socialUsersWithAClique);

        Assert.assertThat("Should return a list of cliques", actualCliques, is(equalTo(expectedCliques)));

    }

    @Test
    public void givenAListOfUsersWithSomeFormingAClique_shouldReturnAListOfCliques() {
        Set<User> usersToCreateClique1 = MotherClazz.createRandomUsers(3).stream()
                .map(SocialUser::getUser).collect(Collectors.toSet());
        Set<User> usersToCreateClique2 = MotherClazz.createRandomUsers(3).stream()
                .map(SocialUser::getUser).collect(Collectors.toSet());

        Set<SocialUser> usersWithAClique1 = MotherClazz.createListWithCliques(usersToCreateClique1);
        Set<SocialUser> usersWithAClique2 = MotherClazz.createListWithCliques(usersToCreateClique2);
        Set<SocialUser> mergedUsers = Stream.concat(usersWithAClique1.stream(), usersWithAClique2.stream())
                .collect(Collectors.toSet());

        Clique clique1 = Clique.newInstance().addUsers(usersToCreateClique1);
        Clique clique2 = Clique.newInstance().addUsers(usersToCreateClique2);

        Set<Clique> expectedCliques = Stream.of(clique1, clique2)
                .collect(Collectors.toSet());

        assertThat("Users list 1 has more elements that default initialization", usersToCreateClique1.size(), is(not(equalTo(usersWithAClique1.size()))));
        assertThat("Users list 2 has more elements that default initialization", usersToCreateClique2.size(), is(not(equalTo(usersWithAClique2.size()))));

        CliqueAlgorithm cliqueAlgorithm = DefaultCliqueAlgorithm.newInstance();

        Set<Clique> actualCliques = cliqueAlgorithm.findCliquesIn(mergedUsers);

        Assert.assertThat("Should return a list of cliques", actualCliques, is(equalTo(expectedCliques)));

    }
}