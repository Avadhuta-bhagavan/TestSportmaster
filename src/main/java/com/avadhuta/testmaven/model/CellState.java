package com.avadhuta.testmaven.model;

/**
 * Состояние ячейки контейнера после её загрузки.
 */
public enum CellState {

    EMPTY,
    BOOKED_FOR_LOADING,
    PARTIALLY_LOADED,
    FULLY_LOADED,
    UNUSABLE

}