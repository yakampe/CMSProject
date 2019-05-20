package co.uk.yktech.page;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PageHelperService {
	
	Logger logger = LoggerFactory.getLogger(PageHelperService.class);
	
	@Autowired
	CMSPageRepository CMSPageRepo;
	
	public void adjustPageUp(Long ID) {
		List<CMSPage> pages = CMSPageRepo.findAllByOrderByPageOrderAsc();
		int order = 0;
		for(CMSPage eachPage:pages) {
			order = order + 10;
			if(eachPage.getID() == ID) {
				eachPage.setPageOrder(order-15);
			} else {
				eachPage.setPageOrder(order);
			}
			CMSPageRepo.save(eachPage);
		}
		
		
	}
	
	public void adjustPageDown(Long ID) {
		List<CMSPage> pages = CMSPageRepo.findAllByOrderByPageOrderAsc();
		int order = 0;
		for(CMSPage eachPage:pages) {
			order = order + 10;
			if(eachPage.getID() == ID) {
				eachPage.setPageOrder(order+15);
			} else {
				eachPage.setPageOrder(order);
			}
			CMSPageRepo.save(eachPage);
		}
		
		
	}
	
	

}
