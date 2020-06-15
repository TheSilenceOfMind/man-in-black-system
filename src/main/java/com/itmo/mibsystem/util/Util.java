package com.itmo.mibsystem.util;

/**
 * @author BaladKV
 * @since 16.06.2020
 */
public class Util {

    // todo switch to using properties file
    public static String getDecodedCategoryName(int categoryNum, String categoryKey) {
        switch (categoryNum) {
            // category1
            case 1:
                return category1(categoryKey);
            case 2:
                return category2(categoryKey);
            case 3:
                return category3(categoryKey);
            default:
                return "";
        }
    }

    private static String category1(String key) {
        switch (key) {
            case "green":
                return "Зелёный";
            case "red":
                return "Красный";
            case "yellow":
                return "Жёлтый";
            case "blue":
                return "Синий";
            default:
                return "";
        }
    }

    private static String category2(String key) {
        switch (key) {
            case "horns":
                return "Рожки";
            case "gills":
                return "Жабры";
            case "fur":
                return "Мех";
            default:
                return "";
        }
    }

    private static String category3(String key) {
        switch (key) {
            case "reptioid":
                return "Рептилоид";
            case "humanoid":
                return "Гуманоид";
            case "daedra":
                return "Даэдра";
            default:
                return "";
        }
    }
}
