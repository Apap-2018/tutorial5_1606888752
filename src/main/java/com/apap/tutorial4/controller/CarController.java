package com.apap.tutorial4.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tutorial4.model.CarModel;
import com.apap.tutorial4.model.DealerModel;
import com.apap.tutorial4.service.CarService;
import com.apap.tutorial4.service.DealerService;

/**
 * carController
 * @author Amira Taliya
 *
 */
/**
 * CarController
 * */
@Controller

public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.GET)
	private String add (@PathVariable(value = "dealerId") Long dealerId, Model model) {
		
		
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		ArrayList<CarModel> list = new ArrayList<CarModel>();
		list.add(new CarModel());
		dealer.setListCar(list);
		
		model.addAttribute("title", "Add Car");
		model.addAttribute("dealer", dealer);
		return "addCar";
	}
	
	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.POST, params= {"save"})
	private String addCarSubmit (@ModelAttribute DealerModel dealer) {
		DealerModel dealerr = dealerService.getDealerDetailById(dealer.getId()).get();
		for(CarModel car : dealer.getListCar()) {
			car.setDealer(dealerr);
			carService.addCar(car);
		}
		return "add";
	}
	
	@RequestMapping(value = "/car/add/{dealerId}", params= {"tambah"}, method = RequestMethod.POST)
	private String tambah (@ModelAttribute DealerModel dealer, Model model) {
		dealer.getListCar().add(new CarModel());
		model.addAttribute("dealer", dealer);
		return "addCar";
	}
	
	@RequestMapping(value="/car/add/{dealerId}", method = RequestMethod.POST, params={"hapus"})
	private String hapus (@ModelAttribute DealerModel dealer, final BindingResult bindingResult, final HttpServletRequest req, Model model) {
		final Integer rowId = Integer.valueOf(req.getParameter("hapus"));
		dealer.getListCar().remove(rowId.intValue());
		model.addAttribute("dealer", dealer);
		return "addCar";
	}

	@RequestMapping(value = "/car/delete", method = RequestMethod.POST )
	private String deleteCar(@ModelAttribute DealerModel dealer, Model model) {
		for(CarModel car: dealer.getListCar()) {
			carService.deleteCar(car);
		}
		return "delete";
	}
	
	@RequestMapping(value = "/car/update/{id}", method = RequestMethod.POST)
	private String updateCarSubmit(@PathVariable(value = "id") long id, @ModelAttribute CarModel car) {
		carService.updateCar(id, car);
		return "update";
	}
	
}