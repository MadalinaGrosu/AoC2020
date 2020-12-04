package com.aoc.day4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.aoc.day4.Validators.validateField;

public class PassportData {
    private HashMap<String, String> data;
    private List<String> mandatoryFields = Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

    public PassportData() {
        data = new HashMap<>();
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public void addData(String key, String value) {
        data.put(key, value);
    }

    public boolean isValid() {
        return mandatoryFields.stream()
                .map(field -> data.containsKey(field))
                .filter(found -> found == false)
                .count() == 0;
    }

    public boolean isStrictlyValid() {
        return mandatoryFields.stream()
                .map(field -> isFieldValid(field))
                .filter(valid -> valid == false)
                .count() == 0;
    }

    private boolean isFieldValid(String field) {
        return data.containsKey(field) && validateField(field, data.get(field));
    }
}
