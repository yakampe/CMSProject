package co.uk.yktech.article;

import java.time.LocalDate;

public class CMSArticle {
	
	private int ID;
	private String title;
	private String content;
	private LocalDate datePosted;
	private ArticleAuthor theAuthor;	
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
