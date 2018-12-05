package io.josemyduarte.cliques.reader;

import io.josemyduarte.cliques.finder.User;

import java.util.Set;

/**
 * Interface to read users from a source
 */
public interface UsersReader {
    Set<User> getUsers();
}
