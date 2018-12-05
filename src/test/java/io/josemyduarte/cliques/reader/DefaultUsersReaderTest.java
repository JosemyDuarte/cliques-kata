package io.josemyduarte.cliques.reader;

import io.josemyduarte.cliques.finder.User;
import org.junit.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultUsersReaderTest {

    @Test
    public void givenAListOfUsers_whenLoadIt_shouldKeepItAlphabeticallyOrdered() throws IOException {
        User adam = new User("Adam");
        User alfred = new User("Alfred");
        User bob = new User("Bob");

        Set<User> usersInFile = Stream.of(adam, alfred, bob).collect(Collectors.toSet());

        FileReader fileReader = mock(FileReader.class);
        when(fileReader.readFrom(anyString())).thenReturn(usersInFile);

        UsersReader usersReader = DefaultUsersReader.fromFilePath("SomeFilePath", fileReader);

        Set<User> users = usersReader.getUsers();

        Optional<User> shouldBeAdam = users.stream().findFirst();
        assertEquals("Adam should be first", shouldBeAdam.get(), adam);

        users.remove(adam);
        Optional<User> shouldBeAlfred = users.stream().findFirst();
        assertEquals("Alfred should be second", shouldBeAlfred.get(), alfred);

        users.remove(alfred);
        Optional<User> shouldBeBob = users.stream().findFirst();
        assertEquals("Bob should be third", shouldBeBob.get(), bob);

        users.remove(bob);
        assertTrue("Collection should finish empty", users.isEmpty());
    }

    @Test
    public void givenAListOfUsersWithSomeRepetition_whenGetUsers_shouldNotKeepAnyRepetition() throws IOException {
        User adam = new User("Adam");
        User adamClone = new User("Adam");
        User bob = new User("Bob");

        Set<User> usersInFile = Stream.of(adam, adamClone, bob).collect(Collectors.toSet());

        FileReader fileReader = mock(FileReader.class);
        when(fileReader.readFrom(anyString())).thenReturn(usersInFile);

        UsersReader usersReader = DefaultUsersReader.fromFilePath("SomeFilePath", fileReader);

        Set<User> users = usersReader.getUsers();

        assertEquals("Set doesn't hold repetitions", 2, users.size());

    }

    @Test
    public void givenAFileWithAListOfUsers_whenGetUsers_shouldReturnACliqueReaderWithUsers() throws IOException {

        User adam = new User("Adam");
        User alfred = new User("Alfred");
        User bob = new User("Bob");

        Set<User> users = new TreeSet<>();
        users.add(adam);
        users.add(alfred);
        users.add(bob);

        FileReader fileReader = mock(FileReader.class);
        when(fileReader.readFrom(anyString())).thenReturn(users);

        UsersReader usersReader = DefaultUsersReader.fromFilePath("AFilePathToSomeFile", fileReader);

        assertEquals("Should return list of users in the file", usersReader.getUsers(), users);

    }

}