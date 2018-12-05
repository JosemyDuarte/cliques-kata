package io.josemyduarte.cliques.reader;

import io.josemyduarte.cliques.finder.User;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/**
 * Default implementations of a UsersReader that read users from a file
 * and return those users in O(1)
 */
class DefaultUsersReader implements UsersReader {

    private final Set<User> users;

    private DefaultUsersReader() {
        this.users = new TreeSet<>();
    }

    /**
     * Reads users from userFilePath with help of a fileReader
     *
     * @param usersFilePath file path with users
     * @param fileReader    reader of users from files
     * @return UserReader instance with users read
     * @throws IOException when there are problems reading the input file
     */
    static UsersReader fromFilePath(final String usersFilePath, final FileReader fileReader) throws IOException {
        Set<User> usersOnFile = fileReader.readFrom(usersFilePath);
        DefaultUsersReader cliqueReader = new DefaultUsersReader();
        cliqueReader.users.addAll(usersOnFile);
        return cliqueReader;
    }

    /**
     * Reads users from userFilePath using the default fileReader
     *
     * @param usersFilePath file path with users
     * @return UserReader instance with users read
     * @throws IOException when there are problems reading the input file
     */
    static UsersReader fromFilePath(final String usersFilePath) throws IOException {
        return DefaultUsersReader.fromFilePath(usersFilePath, DefaultFileReader.newInstance());
    }

    /**
     * Retrieve users read from file
     *
     * @return users without repetition
     */
    @Override
    public Set<User> getUsers() {
        return new TreeSet<>(users);
    }
}
