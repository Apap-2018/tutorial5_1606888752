package com.apap.tutorial4.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial4.model.CarModel;
import com.apap.tutorial4.repository.CarDb;

/**
 * DealerServiceImpl
 * @author Amira Taliya
 *
 */
@Service
@Transactional
public class CarServiceImpl implements CarService{
	@Autowired
	private CarDb carDb;
	
	@Override
	public void addCar(CarModel car) {
		carDb.save(car);
	}
	
	@Override
	public CarModel getCarDetailById(Long id) {
		return carDb.findById(id).get();
	}
	
	@Override
	public void updateCar(Long carId, CarModel newCar) {
		CarModel oldCarData = carDb.findById(carId).get();
		oldCarData.setBrand(newCar.getBrand());
		oldCarData.setType(newCar.getType());
		oldCarData.setPrice(newCar.getPrice());
		oldCarData.setAmount(newCar.getAmount());
		carDb.save(oldCarData);
	}
	
	@Override
	public void deleteCarById(Long carId) {
		carDb.delete(this.getCarDetailById(carId));
	}

	@Override
	public void deleteCar(CarModel car) {
		carDb.delete(car);
	}

}
