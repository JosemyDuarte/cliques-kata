package io.josemyduarte.cliques;

import io.josemyduarte.cliques.finder.User;

import java.util.*;

/**
 * Value class to hold User cliques
 */
// TODO Use generics
public class Clique {

    private final Set<User> usersInAClique;

    private Clique(Set<User> usersInAClique) {
        this.usersInAClique = new TreeSet<>(usersInAClique);
    }

    public Clique() {
        this.usersInAClique = new TreeSet<>();
    }

    public static Clique newInstance() {
        return new Clique();
    }

    public Set<User> getUsersInAClique() {
        return Collections.unmodifiableSet(usersInAClique);
    }

    public Clique addUser(User user) {
        Set<User> newUsersList = new TreeSet<>(usersInAClique);
        newUsersList.add(user);
        return new Clique(newUsersList);
    }

    public Clique addUsers(Collection<User> users) {
        Set<User> newUsersList = new TreeSet<>(usersInAClique);
        newUsersList.addAll(users);
        return new Clique(newUsersList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clique clique = (Clique) o;
        return Objects.equals(usersInAClique, clique.usersInAClique);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersInAClique);
    }

    @Override
    public String toString() {
        return "Clique{" +
                "usersInAClique=" + usersInAClique +
                '}';
    }
}
