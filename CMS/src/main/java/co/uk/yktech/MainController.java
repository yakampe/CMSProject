package co.uk.yktech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.uk.yktech.page.CMSPage;
import co.uk.yktech.page.CMSPageRepository;

@Controller
public class MainController {

	Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	CMSPageRepository CMSPageRepo;

	@GetMapping("/")
	public String home(Model theModel, @RequestParam(value="error", required=false) String error) {
		Iterable<CMSPage> pages = CMSPageRepo.findAllByOrderByPageOrderAsc();
		theModel.addAttribute("pages", pages);
		//if there is an error return single page associated with the page in DB by error name
		if(error != null) {
		theModel.addAttribute("page", CMSPageRepo.findByPageTitle(error).get(0));
		return "singlepage";
		} 
		return "homepage";
		
	}
	

}
