package co.uk.yktech.article;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ArticleTag {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	private String tagName;
	@ManyToMany(mappedBy = "articleTags")
	private Set<CMSArticle> Articles;

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Set<CMSArticle> getArticles() {
		return Articles;
	}

	public void setArticles(Set<CMSArticle> articles) {
		Articles = articles;
	}

	@Override
	public String toString() {
		return this.getTagName();
	}
	
	
	

}
