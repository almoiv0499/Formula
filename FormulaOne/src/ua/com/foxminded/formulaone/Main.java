package ua.com.foxminded.formulaone;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Formula1 formula1 = new Formula1();
        System.out.println(
                formula1.createTableResults("resources/abbreviations.txt", "resources/start.log", "resources/end.log"));
    }

}

