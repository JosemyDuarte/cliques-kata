package io.josemyduarte.cliques;

import io.josemyduarte.cliques.finder.CliqueFinder;
import io.josemyduarte.cliques.finder.CliqueFindersFactory;
import io.josemyduarte.cliques.finder.User;
import io.josemyduarte.cliques.reader.UsersReader;
import io.josemyduarte.cliques.reader.UsersReadersFactory;
import io.josemyduarte.cliques.writer.CliqueWriter;
import io.josemyduarte.cliques.writer.CliqueWritersFactory;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;

public class CliquesHandler {

    private final UsersReader usersReader;
    private final CliqueFinder cliqueFinder;
    private final CliqueWriter cliqueWriter;

    private CliquesHandler(final UsersReader usersReader, final CliqueFinder cliqueFinder, final CliqueWriter cliqueWriter) {
        this.usersReader = usersReader;
        this.cliqueFinder = cliqueFinder;
        this.cliqueWriter = cliqueWriter;
    }

    public static CliquesHandler with(final UsersReader usersReader, final CliqueFinder cliqueFinder, final CliqueWriter cliqueWriter) {
        Objects.requireNonNull(usersReader);
        Objects.requireNonNull(cliqueFinder);
        Objects.requireNonNull(cliqueWriter);
        return new CliquesHandler(usersReader, cliqueFinder, cliqueWriter);
    }

    public static CliquesHandler from(final String filePath) throws IOException {
        Objects.requireNonNull(filePath);
        UsersReader defaultReader = UsersReadersFactory.newDefaultFileReader(filePath);
        CliqueFinder defaultFinder = CliqueFindersFactory.newDefaultFinder();
        CliqueWriter defaultWriter = CliqueWritersFactory.newDefaultFileWriter();
        return new CliquesHandler(defaultReader, defaultFinder, defaultWriter);
    }

    public File findCliquesOn() throws IOException {
        Set<User> users = usersReader.getUsers();
        Set<Clique> cliques = cliqueFinder.findCliquesOn(users);
        return cliqueWriter.writeCliques(cliques);
    }

}
