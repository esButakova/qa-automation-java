package com.tinkoff.edu.app.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.model.LoanResponse;
import lombok.SneakyThrows;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created on 15.08.2021
 *
 * @author Elena Butakova
 */
public class VariableLoanCalcRepository implements LoanCalcRepository {
    private static final Path path = Path.of("Pepka.txt");
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final ObjectWriter ow = objectMapper.writer();
    private static final Map<UUID, LoanResponse> loans =
            VariableLoanCalcRepository.getResponsesFromFile();
    /*
     * {id:1,value:A}
     * {id:2, value:B}
     * {id:3, value: A}
     * */

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
        Map<UUID, LoanResponse> responses = getResponsesFromFile();
        return responses != null ? responses.get(uuid) : null;
    }

    @Override
    public List<LoanResponse> findByType(LoanType type) {
        Map<UUID, LoanResponse> responses = getResponsesFromFile();

        return responses != null ? responses.values().stream()
                .filter(response -> type.equals(response.getRequest().getType()))
                .collect(Collectors.toList()) : null;
    }

    @SneakyThrows
    private void saveToFile() {
        try {
            objectMapper.writeValue(path.toFile(), loans);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    private static Map<UUID, LoanResponse> getResponsesFromFile() {
        TypeReference<HashMap<UUID, LoanResponse>> typeRef = new TypeReference<>() {
        };
        try {
            return (objectMapper != null && path != null)
                    ? objectMapper.readValue(path.toFile(), typeRef)
                    : new HashMap<>();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

}