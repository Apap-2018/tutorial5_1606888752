package com.apap.tutorial4.service;

import java.util.Optional; 

import com.apap.tutorial4.model.CarModel;

/**
 * CarService
 * @author Amira Taliya
 *
 */
public interface CarService {
	void addCar(CarModel car);

	CarModel getCarDetailById(Long id);
	
	void updateCar(Long carId, CarModel newCar);

	void deleteCarById(Long carId);
	
	void deleteCar(Long car);
}
