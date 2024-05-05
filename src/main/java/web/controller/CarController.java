package web.controller;

import org.springframework.ui.Model;
import web.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.service.CarService;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

	private final CarService carService;

	@Autowired
	public CarController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping
	public String getCarByCount(@RequestParam(value = "count", required = false) Integer count, Model model) {
		List<Car> cars = carService.getCars(count);
		model.addAttribute("cars", cars);
		return "cars";
	}

	
}