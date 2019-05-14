package co.uk.yktech.article;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ArticleCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	private String categoryName;
	@OneToMany(mappedBy="theCategory")
	private List<CMSArticle> Articles;
	
	
	

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public List<CMSArticle> getArticles() {
		return Articles;
	}

	public void setArticles(List<CMSArticle> articles) {
		Articles = articles;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
