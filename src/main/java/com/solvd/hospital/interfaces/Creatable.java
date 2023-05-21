package com.solvd.hospital.interfaces;

import com.solvd.hospital.entities.HospitalMaterialResource;

import java.util.List;

@FunctionalInterface
public interface Creatable <T extends HospitalMaterialResource> {

    List<T> create();
}
