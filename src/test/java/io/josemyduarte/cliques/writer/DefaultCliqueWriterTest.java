package io.josemyduarte.cliques.writer;

import io.josemyduarte.cliques.Clique;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultCliqueWriterTest {

    @Test
    public void givenAListOfCliques_shouldWriteAFile() throws IOException {
        CliqueWriter cliqueWriter = mock(CliqueWriter.class);

        when(cliqueWriter.writeCliques(any()))
                .thenReturn(Files.createTempFile("anyPrefix", "anySuffix").toFile());

        assertThat(cliqueWriter.writeCliques(Stream.of(new Clique()).collect(Collectors.toSet())), instanceOf(File.class));

    }

}