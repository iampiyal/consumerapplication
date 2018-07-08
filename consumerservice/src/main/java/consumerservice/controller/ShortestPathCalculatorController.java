package consumerservice.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import consumerservice.service.CityServices;

@Controller
public class ShortestPathCalculatorController {
	
	@Autowired
	private CityServices cityService;
	
	private final Logger logger = LoggerFactory.getLogger(ShortestPathCalculatorController.class);
	
	@RequestMapping(value = "cities/{chosencity}", method = RequestMethod.GET)
	public String getShortestDistance(Model model, @PathVariable("chosencity") String chosencity) throws IOException  {
		logger.debug("listAllCities()");
		model.addAttribute("route", cityService.getShortestDistance(chosencity));
		return "route";

	}

}
