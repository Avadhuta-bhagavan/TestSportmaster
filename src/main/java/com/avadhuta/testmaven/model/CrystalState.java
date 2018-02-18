package com.avadhuta.testmaven.model;

/**
 * Состояние и местоположение отдельного кристалла.
 */
public enum CrystalState {

    ON_STORAGE,
    SCHEDULED_FOR_LOADING,
    LOADED_INTO_CONTAINER,
    SHIPPED,
    MISSING

}