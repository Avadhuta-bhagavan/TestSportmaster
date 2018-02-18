package com.avadhuta.testmaven.model;

/**
 * Состояние контейнера после его загрузки.
 */
public enum ContainerState {

    EMPTY,
    BOOKED_FOR_LOADING,
    PARTIALLY_LOADED,
    FULLY_LOADED,
    READY_FOR_TRANSPORTATION,
    UNAVAILABLE,
    UNUSABLE,

}