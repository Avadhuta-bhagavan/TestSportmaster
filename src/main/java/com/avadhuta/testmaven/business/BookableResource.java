package com.avadhuta.testmaven.business;

import com.avadhuta.testmaven.model.*;

/**
 * Generic interface for objects, which could be booked for loading process.
 */
public interface BookableResource
        extends IdentifiedResource {

    public LoadingProcess getBookedForProcess();

    public void setBookedForProcess(LoadingProcess bookedForProcess);

}