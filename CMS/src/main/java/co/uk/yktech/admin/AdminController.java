package co.uk.yktech.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.uk.yktech.page.CMSPage;
import co.uk.yktech.page.CMSPageRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	CMSPageRepository CMSPageRepo;
	
	@GetMapping("/home")
	public String home() {
		return "admin-homepage";
	}
	
	@GetMapping("/pages")
	public String showPages(Model theModel) {
		Iterable<CMSPage> pages = CMSPageRepo.findAll();
		theModel.addAttribute("pages", pages);
		return "admin/pages";
	}
	@GetMapping("/editPage/{pageTitle}")
	public String editPage(@PathVariable String pageTitle, Model theModel) {
		logger.info("Loading page for editing with title == " +pageTitle);
		try {
		CMSPage page = CMSPageRepo.findByPageTitle(pageTitle).get(0);
		theModel.addAttribute("page", page);
		} catch(IndexOutOfBoundsException e) {
		logger.warn("Page Not Found");
		}
		return "admin/editpage";
	}
	@PostMapping("/savePage")
	public String savePage(@ModelAttribute("page") CMSPage page) {
		CMSPageRepo.save(page);
		logger.info("Saving page with ID ==" +page.getID());
		return "admin-homepage";
	}
	
}
