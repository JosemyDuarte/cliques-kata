package io.josemyduarte.cliques.reader;

import java.io.IOException;

public class UsersReadersFactory {

    private UsersReadersFactory() {
    }

    public static UsersReader newDefaultFileReader(String filePathIn) throws IOException {
        return DefaultUsersReader.fromFilePath(filePathIn);
    }

    public static UsersReader newDefaultFileReader(String filePathIn, FileReader fileReader) throws IOException {
        return DefaultUsersReader.fromFilePath(filePathIn, fileReader);
    }
}
