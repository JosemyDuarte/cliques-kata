package io.josemyduarte.cliques.writer;

public class CliqueWritersFactory {

    private CliqueWritersFactory() {
    }

    public static CliqueWriter newDefaultFileWriter() {
        return DefaultCliqueWriter.newInstance();
    }
}
