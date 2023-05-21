package com.solvd.hospital.entities;

import com.solvd.hospital.interfaces.IClean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Bed extends HospitalMaterialResource implements IClean {
    private static final Logger logger = LogManager.getLogger("Bed");

    public Bed(Integer id) {
        super(id);
    }

    public Bed() {
        super();
    }

    public boolean isAvailability() {
        return super.getAvailable();
    }

    public void setAvailability(boolean availability) {
        super.setAvailable(availability);
    }

    public Integer getId() {
        return super.getId();
    }

    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String toString() {
        return "id: "+super.getId()+" availability: "+super.getAvailable();
    }

    @Override
    public void clean() {
        logger.info("The bed is clean");
    }
}
