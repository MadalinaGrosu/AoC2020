package com.aoc.day4;

public class Validators {

    public static boolean validateField(String field, String value) {
        switch (field) {
            case "byr": return isByrValid(value);
            case "iyr": return isIyrValid(value);
            case "eyr": return isEyrValid(value);
            case "hgt": return isHgtValid(value);
            case "hcl": return isHclValid(value);
            case "ecl": return isEclValid(value);
            case "pid": return isPidValid(value);
            default: return false;
        }
    }

    private static boolean isByrValid(String byr) {
        Long year = Long.valueOf(byr);
        return year >= 1920 && year <= 2002;
    }

    private static boolean isIyrValid(String iyr) {
        Long year = Long.valueOf(iyr);
        return 2010 <= year && year <= 2020;
    }

    private static boolean isEyrValid(String eyr) {
        Long year = Long.valueOf(eyr);
        return 2020 <= year && year <= 2030;
    }

    private static boolean isHgtValid(String hgt) {
        if (hgt.contains("in")) {
            int indexInch = hgt.indexOf("in");
            Long height = Long.valueOf(hgt.substring(0, indexInch));
            return 59 <= height && height <= 76;
        }

        if (hgt.contains("cm")) {
            int indexCm = hgt.indexOf("cm");
            Long height = Long.valueOf(hgt.substring(0, indexCm));
            return 150 <= height && height <= 193;
        }
        return false;
    }

    private static boolean isHclValid(String hcl) {
        String regex = "#[a-f0-9]{6}";
        return hcl.matches(regex) && hcl.length() == 7;
    }

    private static boolean isEclValid(String ecl) {
        return "amb blu brn gry grn hzl oth".contains(ecl);
    }

    private static boolean isPidValid(String pid) {
        return pid.matches("[0-9]{9}") && pid.length() == 9;
    }
}
