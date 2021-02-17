package com.company.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.company.entities.Employee;
import com.company.entities.FixedRateEmployee;
import com.company.entities.HourlyRateEmployee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonImporter {

    private static final String fileName = "./employees.json";

    /**
     * Метод, пореобразующий JSON, полученный из файла в список сущностей
     * класса {@link HourlyRateEmployee} или {@link FixedRateEmployee} в зависимости от типа.
     *
     * @return список работников
     * @throws IOException если произошла ошибка при чтении файла.
     */
    public static List<Employee> getEmployeeFromJson() throws IOException {
        List<Employee> employees = new ArrayList<>();
        String json = readJSONFile();

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, String>> list = objectMapper.readValue(json, new TypeReference<List<Map<String, String>>>() {});

        for (Map<String, String> data : list) {
            if (data.get("type").equals("hourly_rate")) {
                employees.add(new HourlyRateEmployee(Integer.parseInt(data.get("id")),
                        Double.parseDouble(data.get("rate")),
                        data.get("name")));
            } else {
                employees.add(new FixedRateEmployee(Integer.parseInt(data.get("id")),
                        Double.parseDouble(data.get("rate")),
                        data.get("name")));
            }
        }
        return employees;
    }

    /**
     * Метод, читающий JSON из файла {@value fileName}.
     *
     * @return файл, преобразованный в строковый тип.
     * @throws IOException если произошла ошибка при чтении файла.
     */
    private static String readJSONFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String lines = reader.lines().collect(Collectors.joining());
        reader.close();
        return lines;
    }
}
