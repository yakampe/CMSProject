package co.uk.yktech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.uk.yktech.page.CMSPage;
import co.uk.yktech.page.CMSPageRepository;

@Controller
public class MainController {

	Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	CMSPageRepository CMSPageRepo;

	@GetMapping("/")
	public String home(Model theModel) {
		Iterable<CMSPage> pages = CMSPageRepo.findAll();
		theModel.addAttribute("pages", pages);
		return "homepage";
	}
	

}
