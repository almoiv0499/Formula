package ua.com.foxminded.formulaone;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Rule;
import org.junit.jupiter.api.Test;

class Formula1Test {

    @Rule
    Formula1 tester = new Formula1();

    @Test
    void createTableBestLapTimesNineteenRacers() throws IOException {
        String expected = "1.  Sebastian Vettel    | FERRARI                        | 1:04.415\r\n"
                        + "2.  Daniel Ricciardo    | RED BULL RACING TAG HEUER      | 1:12.013\r\n"
                        + "3.  Valtteri Bottas     | MERCEDES                       | 1:12.434\r\n"
                        + "4.  Lewis Hamilton      | MERCEDES                       | 1:12.460\r\n"
                        + "5.  Stoffel Vandoorne   | MCLAREN RENAULT                | 1:12.463\r\n"
                        + "6.  Kimi Raikkonen      | FERRARI                        | 1:12.639\r\n"
                        + "7.  Fernando Alonso     | MCLAREN RENAULT                | 1:12.657\r\n"
                        + "8.  Sergey Sirotkin     | WILLIAMS MERCEDES              | 1:12.706\r\n"
                        + "9.  Charles Leclerc     | SAUBER FERRARI                 | 1:12.829\r\n"
                        + "10. Sergio Perez        | FORCE INDIA MERCEDES           | 1:12.848\r\n"
                        + "11. Romain Grosjean     | HAAS FERRARI                   | 1:12.930\r\n"
                        + "12. Pierre Gasly        | SCUDERIA TORO ROSSO HONDA      | 1:12.941\r\n"
                        + "13. Carlos Sainz        | RENAULT                        | 1:12.950\r\n"
                        + "14. Esteban Ocon        | FORCE INDIA MERCEDES           | 1:13.028\r\n"
                        + "15. Nico Hulkenberg     | RENAULT                        | 1:13.065\r\n"
                        + "-------------------------------------------------------------------\n"
                        + "16. Brendon Hartley     | SCUDERIA TORO ROSSO HONDA      | 1:13.179\r\n"
                        + "17. Marcus Ericsson     | SAUBER FERRARI                 | 1:13.265\r\n"
                        + "18. Lance Stroll        | WILLIAMS MERCEDES              | 1:13.323\r\n"
                        + "19. Kevin Magnussen     | HAAS FERRARI                   | 1:13.393\r\n";

        String acrual = tester.createTableResults("resources/abbreviations.txt", "resources/start.log",
                "resources/end.log");

        assertEquals(expected, acrual);
    }

    @Test
    void createTableBestLapTimesFifteenRacersAndCreateSeparatorLine() throws IOException {
        String expected = "1.  Sebastian Vettel    | FERRARI                        | 1:04.415\r\n"
                        + "2.  Daniel Ricciardo    | RED BULL RACING TAG HEUER      | 1:12.013\r\n"
                        + "3.  Valtteri Bottas     | MERCEDES                       | 1:12.434\r\n"
                        + "4.  Lewis Hamilton      | MERCEDES                       | 1:12.460\r\n"
                        + "5.  Stoffel Vandoorne   | MCLAREN RENAULT                | 1:12.463\r\n"
                        + "6.  Kimi Raikkonen      | FERRARI                        | 1:12.639\r\n"
                        + "7.  Fernando Alonso     | MCLAREN RENAULT                | 1:12.657\r\n"
                        + "8.  Sergey Sirotkin     | WILLIAMS MERCEDES              | 1:12.706\r\n"
                        + "9.  Charles Leclerc     | SAUBER FERRARI                 | 1:12.829\r\n"
                        + "10. Sergio Perez        | FORCE INDIA MERCEDES           | 1:12.848\r\n"
                        + "11. Romain Grosjean     | HAAS FERRARI                   | 1:12.930\r\n"
                        + "12. Pierre Gasly        | SCUDERIA TORO ROSSO HONDA      | 1:12.941\r\n"
                        + "13. Carlos Sainz        | RENAULT                        | 1:12.950\r\n"
                        + "14. Esteban Ocon        | FORCE INDIA MERCEDES           | 1:13.028\r\n"
                        + "15. Nico Hulkenberg     | RENAULT                        | 1:13.065\r\n";

        String actual = tester.createTableResults("resources/abbreviationsFifteen.txt", "resources/startFifteen.log",
                "resources/endFifteen.log");

        assertEquals(expected, actual);
    }

    @Test
    void createTableBestLapTimesFourRacers() throws IOException {
        String expected = "1.  Sebastian Vettel    | FERRARI                        | 1:04.415\r\n"
                        + "2.  Daniel Ricciardo    | RED BULL RACING TAG HEUER      | 1:12.013\r\n"
                        + "3.  Valtteri Bottas     | MERCEDES                       | 1:12.434\r\n"
                        + "4.  Lewis Hamilton      | MERCEDES                       | 1:12.460\r\n";

        String actual = tester.createTableResults("resources/abbreviationsFour.txt", "resources/startFour.log",
                "resources/endFour.log");

        assertEquals(expected, actual);
    }

    @Test
    void createTableBestLapTimeOnlyOneRacer() throws IOException {
        String expected = "1.  Lewis Hamilton      | MERCEDES                       | 1:12.460\r\n";

        String actual = tester.createTableResults("resources/abbreviationsOne.txt", "resources/startOne.log",
                "resources/endOne.log");

        assertEquals(expected, actual);
    }

    @Test
    void shouldCreateTableNothingWhenOneFileIsEmpty() throws IOException {
        assertEquals("",
                tester.createTableResults("resources/isEmpty.txt", "resources/start.log", "resources/end.log"));
    }

    @Test
    void returnFileNotFoundException_WhenOneOfFileIsNotFound() {
        assertThrows(FileNotFoundException.class,
                () -> new Formula1().createTableResults("file not found", "resources/start.log", "resources/end.log"));
    }

}

