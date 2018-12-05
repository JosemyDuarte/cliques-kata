package io.josemyduarte.cliques;

import io.josemyduarte.cliques.finder.CliqueFinder;
import io.josemyduarte.cliques.finder.CliqueFindersFactory;
import io.josemyduarte.cliques.finder.SocialUser;
import io.josemyduarte.cliques.finder.User;
import io.josemyduarte.cliques.finder.graph.AlgorithmsFactory;
import io.josemyduarte.cliques.finder.graph.ConnectionBuilder;
import io.josemyduarte.cliques.reader.FileReader;
import io.josemyduarte.cliques.reader.UsersReader;
import io.josemyduarte.cliques.reader.UsersReadersFactory;
import io.josemyduarte.cliques.writer.CliqueWriter;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CliquesHandlerTest {

    @Test
    public void givenAFilePath_findCliquesOn_shouldReturnAResultFile() throws IOException {
        CliquesHandler cliquesHandler = mock(CliquesHandler.class);

        when(cliquesHandler.findCliquesOn())
                .thenReturn(Files.createTempFile("prefix", "suffix").toFile());

        assertTrue("Method returns a file", cliquesHandler.findCliquesOn().isFile());
        assertTrue("A new exists", cliquesHandler.findCliquesOn().exists());

    }

    @Test
    public void whenFindCliquesOn_shouldReadFindAndWriteResult() throws IOException {

        UsersReader usersReader = mock(UsersReader.class);
        CliqueFinder cliqueFinder = mock(CliqueFinder.class);
        CliqueWriter cliqueWriter = mock(CliqueWriter.class);

        Set<User> usersToAnalyze = Stream.of(new User("User1"), new User("User2"), new User("User3")).collect(Collectors.toSet());
        Set<User> usersInClique = Stream.of(new User("User1"), new User("User2")).collect(Collectors.toSet());
        Set<Clique> cliques = Stream.of(
                Clique.newInstance().addUsers(usersInClique)).collect(Collectors.toSet());

        when(usersReader.getUsers()).thenReturn(usersToAnalyze);
        when(cliqueFinder.findCliquesOn(usersToAnalyze)).thenReturn(cliques);
        CliquesHandler.with(usersReader, cliqueFinder, cliqueWriter)
                .findCliquesOn();

        verify(usersReader).getUsers();
        verify(cliqueFinder).findCliquesOn(usersToAnalyze);
        verify(cliqueWriter).writeCliques(cliques);

    }

    @Test
    public void shouldFindRealCliques() throws IOException {

        Set<User> usersToCreateClique1 = MotherClazz.createRandomUsers(3).stream()
                .map(SocialUser::getUser).collect(Collectors.toSet());
        Set<User> usersToCreateClique2 = MotherClazz.createRandomUsers(3).stream()
                .map(SocialUser::getUser).collect(Collectors.toSet());

        Set<SocialUser> usersWithAClique1 = MotherClazz.createListWithCliques(usersToCreateClique1);
        Set<SocialUser> usersWithAClique2 = MotherClazz.createListWithCliques(usersToCreateClique2);
        Set<SocialUser> mergedSocialUsers = Stream.concat(usersWithAClique1.stream(), usersWithAClique2.stream())
                .collect(Collectors.toSet());
        Set<User> mergedUsers = mergedSocialUsers.stream().map(SocialUser::getUser).collect(Collectors.toSet());

        Clique clique1 = Clique.newInstance().addUsers(usersToCreateClique1);
        Clique clique2 = Clique.newInstance().addUsers(usersToCreateClique2);

        Set<Clique> expectedCliques = Stream.of(clique1, clique2)
                .collect(Collectors.toSet());

        FileReader fileReader = mock(FileReader.class);
        when(fileReader.readFrom(anyString())).thenReturn(mergedUsers);

        ConnectionBuilder connectionBuilder = mock(ConnectionBuilder.class);
        when(connectionBuilder.build(mergedUsers)).thenReturn(mergedSocialUsers);

        UsersReader usersReader = UsersReadersFactory.newDefaultFileReader("SomeFilePath", fileReader);

        CliqueFinder cliqueFinder = CliqueFindersFactory.newDefaultFinder(connectionBuilder, AlgorithmsFactory.defaultAlgorithm());
        CliqueWriter cliqueWriter = mock(CliqueWriter.class);

        CliquesHandler cliquesHandler = CliquesHandler.with(usersReader, cliqueFinder, cliqueWriter);

        cliquesHandler.findCliquesOn();

        verify(cliqueWriter).writeCliques(expectedCliques);
    }

}
