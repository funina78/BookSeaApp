/*
 * this class is the object version of the table Restaurant.
 */

package webapp.model;

import java.text.SimpleDateFormat;
import java.sql.*;

/**
 * 
 * @author Nina
 */
public class Book {
	// ........................ D A T A F I E L D S
	// ............................//
	private int id;
	private String name;
	private String author;
	private String publishDate;
	private String rating;
	private String price;
	private String description;
	private String image_url;

	private static final int NO_ID = -1;

	// ........................ C O N S T R U C T O R S
	// ..........................//

	public Book() {
		id = NO_ID;
		name = "";
		author = "";
		publishDate = "";
		rating = "";
		price = "";
		description = "";
		image_url = null;
	} // end of the constructor

	public Book(int id, String _name, String _author,
			String _publishDate, String _rating, String _price,
			String _description, String image) {
		this.id = id;
		this.name = _name;
		// java.util.Date timenow = new java.util.Date();
		// this.postedDate = new java.sql.Timestamp(timenow.getTime());
		this.author = _author;
		this.publishDate = _publishDate;
		this.rating = _rating;
		this.price = _price;
		this.description = description;
		this.rating = _rating;
		this.image_url = image;
	} // end of the constructor

	// ...................... P U B L I C M E T H O D S
	// ........................//
	

	@Override
	public String toString() {
		return "[" + this.id + "] [" + this.name + "]";
	} // end of the method

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

} // end of the class
