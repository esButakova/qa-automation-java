package com.tinkoff.edu.app.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.logger.LoanCalcLogger;
import com.tinkoff.edu.app.model.LoanResponse;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created on 15.08.2021
 *
 * @author Elena Butakova
 */
public class VariableLoanCalcRepository implements LoanCalcRepository {
    private static final Map<UUID, LoanResponse> loans = new HashMap<>();
    //    private File file = new File("loanRepo.txt");
    private final Path path = Path.of("Pepka.txt");


    /**
     * TODO persist request
     *
     * @return Request Id
     */
    @Override
    public LoanResponse save(LoanResponse response) {
        loans.put(response.getId(), response);
        saveToFile();
        return response;
    }

    @Override
    public void update(LoanResponse response) {
        save(response);
    }

    @Override
    public LoanResponse find(UUID uuid) {
        List<LoanResponse> responses = getResponsesFromFile();
        final LoanResponse[] result = {null};
        responses.stream()
                .filter(response -> uuid.equals(response.getId()))
                .findFirst()
                .ifPresent(response -> result[0] = response);
        return result[0];
    }

    @Override
    public List<LoanResponse> findByType(LoanType type) {
        List<LoanResponse> responses = getResponsesFromFile();

        return responses.stream()
                .filter(response -> type.equals(response.getRequest().getType()))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private void saveToFile() {
        List<String> stringsToSave = loans.values().stream()
                .map(this::convertToJson)
                .collect(Collectors.toList());
        Files.write(
                path
                , stringsToSave
                , StandardOpenOption.CREATE
                , StandardOpenOption.TRUNCATE_EXISTING
        );
    }

    @SneakyThrows
    private List<LoanResponse> getResponsesFromFile() {
        List<String> strings = Files.readAllLines(path);

        List<LoanResponse> result = strings.stream()
                .map(this::convertJsonToObject)
                .collect(Collectors.toList());

        LoanCalcLogger.log("repository", result.toString());

        return result;
    }

    @SneakyThrows
    private String convertToJson(LoanResponse response) {
        ObjectWriter ow = new ObjectMapper().writer();
        return ow.writeValueAsString(response);
    }

    @SneakyThrows
    private LoanResponse convertJsonToObject(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, LoanResponse.class);
    }
}