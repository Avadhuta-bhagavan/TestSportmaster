package com.avadhuta.testmaven.model;

/**
 * Ценность кристалла
 */
public enum CrystalCostClassification {

    QUALITY_0("Мусор", 0, -1),
    QUALITY_1("Сойдёт", 1, 8),
    QUALITY_2("Обычный", 2, 7),
    QUALITY_3("Качественный", 3, 6),
    QUALITY_4("Редкий", 4, 5),
    QUALITY_5("Героический", 5, 4),
    QUALITY_6("Легендарный", 6, 3),
    QUALITY_7("Эпический", 7, 2),
    QUALITY_8("Божественный", 8, 1),;

    private final String description;
    private final int sorterCode;
    private final int logisticsCode;

    private CrystalCostClassification(String description, int sorterCode, int logisticsCode) {
        this.description = description;
        this.sorterCode = sorterCode;
        this.logisticsCode = logisticsCode;
    }

    public String getDescription() {
        return description;
    }

    public int getSorterCode() {
        return sorterCode;
    }

    public int getLogisticsCode() {
        return logisticsCode;
    }

}