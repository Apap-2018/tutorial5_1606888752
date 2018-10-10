package com.apap.tutorial4.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial4.model.CarModel;
import com.apap.tutorial4.model.DealerModel;
import com.apap.tutorial4.service.CarService;
import com.apap.tutorial4.service.DealerService;

/**
 * DealerController
 * @author Amira Taliya
 *
 */
@Controller
public class DealerController {
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private CarService carService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value="/dealer/add", method=RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("dealer", new DealerModel());
		return "addDealer";
	}
	
	@RequestMapping(value="/dealer/add", method=RequestMethod.POST)
	private String addDealerSubmit(@ModelAttribute DealerModel dealer) {
		dealerService.addDealer(dealer);
		return "add";
	}
	
	@RequestMapping(value="/dealer/view", method=RequestMethod.GET)
	private String viewDealer(@RequestParam ("dealerId") long dealerId, Model model) {
		DealerModel archieve = dealerService.getDealerDetailById(dealerId).get();
		
		List<CarModel> cars = archieve.getListCar();
		Collections.sort(cars, comparePrice);
		
		archieve.setListCar(cars);
		model.addAttribute("dealer", archieve);
		return "view-dealer";
	}
	
	@RequestMapping(value = "/dealer/view-all", method = RequestMethod.GET)
	private String viewAll (Model model) {
		List<DealerModel> cars = dealerService.getAllDealer();
		model.addAttribute("listDealer", cars);
		return "view-all-dealer";
	}
	
	@RequestMapping(value = "/dealer/delete{dealerId}", method = RequestMethod.GET)
	private String updateDealerSubmit(@PathVariable(value = "dealerId") Long dealerId) {
		dealerService.deleteDealerById(dealerId);
		return "delete";
	}
	
/*	@RequestMapping(value = "/dealer/update/{id}", method = RequestMethod.GET)
	private String updateDealer(@PathVariable(value = "id") long id, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(id).get();
		model.addAttribute("dealer",dealer);
		return "update-dealer";
	}
	
	@RequestMapping(value = "/dealer/update/", method = RequestMethod.POST)
	private String updateDealerSubmit(@PathVariable (value = "id") long id, @ModelAttribute Optional<DealerModel> dealer) {
		if(dealer.isPresent()) {
			dealerService.updateDealer(id, dealer);
			return "update";
		}
		return null;
	}*/
	
	public static Comparator<CarModel> comparePrice = new Comparator<CarModel>() {
		public int compare(CarModel o1, CarModel o2) {
			Long price1 = o1.getPrice();
			Long price2 = o2.getPrice();
			return price1.compareTo(price2);
		}
	};
}
