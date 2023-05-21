package com.solvd.hospital.entities;


import com.solvd.hospital.interfaces.Calculable;
import com.solvd.hospital.utilities.Utils;

public class Disease {
    private int pain;
    private int emergency;
    private int improvementRate;
    private int aggravation;


    public Disease() {

        this.pain = Utils.getrandomInt(1,10);
        this.emergency = Utils.getrandomInt(1,10);
        this.improvementRate = Utils.getrandomInt(20,100);
        this.aggravation = Utils.getrandomInt(1,10);
    }

    @Override
    public String toString() {
        return "Pain "+pain+ " Emergency "+ emergency+" ImprovementRate "+ improvementRate;
    }

    public int getPain() {
        return pain;
    }

    public int getEmergency() {
        return emergency;
    }

    public int getImprovementRate() {
        return improvementRate;
    }

    public int getAggravation() {
        return aggravation;
    }

    public int calculateDisease(Calculable<Disease> calculable){
        return calculable.calculate(this);
    }
}
