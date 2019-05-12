package co.uk.yktech.page;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CMSPageRepository extends CrudRepository<CMSPage, Long> {
	    public List<CMSPage> findByPageTitle(String pageTitle);
}
