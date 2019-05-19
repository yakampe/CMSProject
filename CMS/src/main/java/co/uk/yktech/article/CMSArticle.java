package co.uk.yktech.article;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class CMSArticle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ID;
	private String title;
	@Column(columnDefinition = "MEDIUMTEXT")
	private String content;
	@DateTimeFormat(pattern = "dd MMM yyyy")
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
	private boolean pinned;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
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
	
	public boolean isPinned() {
		return pinned;
	}

	public void setPinned(boolean pinned) {
		this.pinned = pinned;
	}

	public String excerpt() {
		String full = this.getContent().replaceAll("<[^>]*>", "");
		String excerpt = (full.length()>300) ? full.substring(0, 300) + "..." : full + "...";
		return excerpt;
	}

}
