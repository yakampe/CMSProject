package co.uk.yktech.article;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnTransformer;

@Entity
public class ArticleAuthor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	@Column(columnDefinition = "BLOB")
	@ColumnTransformer(read = "AES_DECRYPT(display_name, '${eakey}')", write = "AES_ENCRYPT(?, '${eakey}')")
	private String displayName;
	private String imgLocation;
	@OneToMany(mappedBy = "theAuthor")
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getImgLocation() {
		return imgLocation;
	}

	public void setImgLocation(String imgLocation) {
		this.imgLocation = imgLocation;
	}

}
