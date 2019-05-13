package co.uk.yktech.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.uk.yktech.page.CMSPage;
import co.uk.yktech.page.CMSPageRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

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
	
}
