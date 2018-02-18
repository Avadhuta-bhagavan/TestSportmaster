package com.avadhuta.testmaven.business;

/**
 * Интерфейс для объекта, чьё состояние может измениться при изменении некоторых связанных объектов.
 */
public interface CalculableResource
        extends IdentifiedResource {

    boolean isNeedChainedRecalculation();

    void setNeedChainedRecalculation(boolean needChainedRecalculation);

}