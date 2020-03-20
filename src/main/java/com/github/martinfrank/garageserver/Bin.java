package com.github.martinfrank.garageserver;

import com.google.common.io.BaseEncoding;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Bin {

    private static final String BIN = "bin";
    private static final String PATH = "src/main/resources";

    public static String unBin() throws IOException {
        return new String(BaseEncoding.base64().decode(new String(Files.readAllBytes(Paths.get(PATH, BIN)))));
    }

    public static void main(String[] args) throws IOException {
        System.out.println(unBin());
    }

}
