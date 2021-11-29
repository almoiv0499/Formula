package ua.com.foxminded.formulaone;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Formula1 {

    private static final String DOT = ".";
    private static final String NEW_LINE = "\n";
    private static final String SPLIT_LINE = "_";
    private static final String EXCEPTION_MESSAGE = "File not found -> ";
    private static final String FORMAT_SEPARATOR_LINE = "%-3s %-20s| %-30s | %s%n";
    private static final String DASHES_SEPARATOR = "-";
    private static final char ÑHANGING_TIME_FORMAT_FROM = '_';
    private static final char ÑHANGING_TIME_FORMAT_TO = 'T';
    private static final int FIRST_INDEX_ABBREVIATIONS = 0;
    private static final int LAST_INDEX_ABBREVIATIONS = 3;
    private static final int VALUE_SIZE_OF_TIME = 4;
    private static final int NUMBER_OF_FIRST_LOSING_RACER = 16;
    private static final int INDEX_RACER_ABBREVIATION = 0;
    private static final int INDEX_RACER_NAME = 1;
    private static final int INDEX_CAR_TEAM = 2;
    private static final int INDEX_RACER_POSITION = 0;
    private static final int ADD_ONE_TO_RACER_INDEX = 1;
    private static final int MAX_AMOUNT_DASHES_SEPARATOR = 67;

    public String createTableResults(String abbreviations, String startTimeLog, String endTimeLog) throws IOException {
        Path abbreviationsPath = getPathToFile(abbreviations);
        Path startTimePath = getPathToFile(startTimeLog);
        Path endTimePath = getPathToFile(endTimeLog);

        List<Racer> racersList = new ArrayList<>();
        try (Stream<String> racersStream = Files.lines(abbreviationsPath)) {
            racersList = racersStream.map(this::getRacer).collect(Collectors.toList());
        }

        Map<String, String> startTimes = getLapInfo(startTimePath);
        Map<String, String> endTimes = getLapInfo(endTimePath);
        List<Racer> sortedByTimeLap = getRacerInfo(racersList, startTimes, endTimes);

        return createTableRacers(sortedByTimeLap);
    }

    private String createTableRacers(List<Racer> racerInfo) {
        int counter = INDEX_RACER_POSITION;
        StringBuilder resultTable = new StringBuilder();
        for (Racer racer : racerInfo) {
            String line = String.format(FORMAT_SEPARATOR_LINE, counter++ + ADD_ONE_TO_RACER_INDEX + DOT, racer.getNameRacer(),
                    racer.getCarTeam(), racer.getLapTime());
            if (counter == NUMBER_OF_FIRST_LOSING_RACER) {
                resultTable.append(Stream.generate(() -> DASHES_SEPARATOR).limit(MAX_AMOUNT_DASHES_SEPARATOR)
                        .collect(Collectors.joining())).append(NEW_LINE);
            }
            resultTable.append(line);
        }
        return resultTable.toString();
    }

    private List<Racer> getRacerInfo(List<Racer> abbreviations, Map<String, String> startTime,
            Map<String, String> endTime) {
        Map<String, String> lapTime = new LinkedHashMap<>();
        startTime.forEach(
                (abbreviation, time) -> lapTime.put(abbreviation, calculateLapTime(time, endTime.get(abbreviation))));
        abbreviations.stream().forEach(
                abbreviationRacer -> abbreviationRacer.setLapTime(lapTime.get(abbreviationRacer.getAbbreviation())));
        return abbreviations.stream().sorted((abbreviationRacer1, abbreviationRacer2) -> abbreviationRacer1.getLapTime()
                .compareTo(abbreviationRacer2.getLapTime())).collect(Collectors.toList());
    }

    private Racer getRacer(String line) {
        String[] racerData = line.split(SPLIT_LINE);

        String abbreviation = racerData[INDEX_RACER_ABBREVIATION];
        String nameRacer = racerData[INDEX_RACER_NAME];
        String carTeam = racerData[INDEX_CAR_TEAM];

        return new Racer(abbreviation, nameRacer, carTeam);
    }

    private String calculateLapTime(String start, String end) {
        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime finishTime = LocalDateTime.parse(end);
        Duration durationLap = Duration.between(startTime, finishTime);
        String racerLapTime = LocalTime.ofNanoOfDay(durationLap.toNanos()).toString();
        return racerLapTime.substring(VALUE_SIZE_OF_TIME);

    }

    private Map<String, String> getLapInfo(Path path) throws IOException {
        Map<String, String> lapInfo = new LinkedHashMap<>();
        try (Stream<String> fileStream = Files.lines(path)) {
            lapInfo = fileStream.collect(Collectors.toMap(
                    abbreviation -> abbreviation.substring(FIRST_INDEX_ABBREVIATIONS, LAST_INDEX_ABBREVIATIONS),
                    time -> time.substring(LAST_INDEX_ABBREVIATIONS, time.length()).replace(ÑHANGING_TIME_FORMAT_FROM,
                            ÑHANGING_TIME_FORMAT_TO)));
        }
        return lapInfo;
    }

    private Path getPathToFile(String file) throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        if (classLoader.getResource(file) == null) {
            throw new FileNotFoundException(EXCEPTION_MESSAGE + file);
        }
        File resource = new File(classLoader.getResource(file).getFile());
        return Paths.get(resource.getAbsolutePath());
    }

}

