package co.uk.yktech.article;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class CMSArticle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	private String title;
	private String content;
	private LocalDate datePosted;
	@ManyToOne
	@JoinColumn(name="author_id")
	private ArticleAuthor theAuthor;
	@ManyToOne
	@JoinColumn(name="category_id")
	private ArticleCategory theCategory;
	@ManyToMany
	@JoinColumn(name="article_id")
	private Set<ArticleTag> articleTags;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public ArticleCategory getTheCategory() {
		return theCategory;
	}

	public void setTheCategory(ArticleCategory theCategory) {
		this.theCategory = theCategory;
	}

	
	public Set<ArticleTag> getArticleTags() {
		return articleTags;
	}

	public void setArticleTags(Set<ArticleTag> articleTags) {
		this.articleTags = articleTags;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(LocalDate datePosted) {
		this.datePosted = datePosted;
	}

	public ArticleAuthor getTheAuthor() {
		return theAuthor;
	}

	public void setTheAuthor(ArticleAuthor theAuthor) {
		this.theAuthor = theAuthor;
	}

}
