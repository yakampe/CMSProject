package co.uk.yktech.page;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CMSPage {

	private int ID;
	private int pageOrder;
	private String pageTitle;
	private String content;
	private boolean isDisplayOnMainPage;
	private String headerImage;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	
	@Column(columnDefinition="MEDIUMTEXT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPageOrder() {
		return pageOrder;
	}

	public void setPageOrder(int pageOrder) {
		this.pageOrder = pageOrder;
	}

	public boolean isDisplayOnMainPage() {
		return isDisplayOnMainPage;
	}

	public void setDisplayOnMainPage(boolean isDisplayOnMainPage) {
		this.isDisplayOnMainPage = isDisplayOnMainPage;
	}

	public String getHeaderImage() {
		return headerImage;
	}

	public void setHeaderImage(String headerImage) {
		this.headerImage = headerImage;
	}

	
	
}
