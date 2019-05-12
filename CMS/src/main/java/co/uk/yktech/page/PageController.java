package co.uk.yktech.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {
	
    Logger logger = LoggerFactory.getLogger(PageController.class);
	
    @Autowired
	CMSPageRepository CMSPageRepo;

	@GetMapping("/page/{pageTitle}")
	public String getPage(@PathVariable String pageTitle, Model theModel) {
		logger.info("Loading page with title == " +pageTitle);
		try {
		CMSPage cmspage = CMSPageRepo.findByPageTitle(pageTitle).get(0);
		theModel.addAttribute("page", cmspage);
		} catch(IndexOutOfBoundsException e) {
		logger.warn("Page Not Found");
		}
		return "singlepage";
	}
}
