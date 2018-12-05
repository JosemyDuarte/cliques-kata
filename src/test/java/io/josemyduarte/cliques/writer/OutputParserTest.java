package io.josemyduarte.cliques.writer;

import io.josemyduarte.cliques.Clique;
import io.josemyduarte.cliques.finder.User;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class OutputParserTest {

    @Test
    public void givenACliqueWithUsers_shouldReturnAListOfNames() {
        User adam = new User("Adam");
        User bob = new User("Bob");
        User jose = new User("Jose");
        Clique clique = Clique.newInstance()
                .addUser(adam)
                .addUser(bob)
                .addUser(jose);

        Assert.assertThat("Should be a list of names", OutputParser.parse(clique), is(equalTo("Adam Bob Jose")));
    }
}