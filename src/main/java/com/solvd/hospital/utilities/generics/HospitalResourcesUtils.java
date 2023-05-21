package com.solvd.hospital.utilities.generics;

import com.solvd.hospital.entities.HospitalMaterialResource;

import java.util.List;

public class HospitalResourcesUtils {

    public static <T> T firstResource(List<? extends HospitalMaterialResource> resources){
        for(HospitalMaterialResource item: resources){
            if(item.getAvailable()){
                return (T) item;
            }
        }
        return null;
    }




}
