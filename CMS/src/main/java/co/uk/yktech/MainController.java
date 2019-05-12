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
	public String home(Model theModel) {
		Iterable<CMSPage> randomList = CMSPageRepo.findByPageTitle("newTitle");
		theModel.addAttribute( "links", randomList);
		return "homepage";
	}
	
	@GetMapping("/add")
	public String testAdd(@RequestParam("title") String title) {
		CMSPage c = new CMSPage();
		c.setContent("test");
		c.setPageTitle(title);
		CMSPageRepo.save(c);
		logger.info("Added page " +c.getContent()+" "+c.getPageTitle());
		return "homepage";
	}
	
}
