/*
 * this class is the object version of the table Comment.
 */

package webapp.model;
import java.text.SimpleDateFormat;
import java.sql.*;

/**
 *
 * @author Nina
 */
public class Comment
{
//........................ D A T A   F I E L D S ............................//
    private int       review_id;
    private int       book_id;
    private int       user_id;
    private String    review;
    private double    rating;
    private Date      date;

//........................ C O N S T R U C T O R S ..........................//
    public Comment()
    {
    	  this.review_id = -1;;
    	  this.book_id= -1;;
    	  this.user_id = -1;;
    	  this.review = "";
    	  this.rating = 0.0;
    	  this.date = null;
    } // end of the constructor


    public Comment(int book_id, String comment_text, int user_id)
    {
        this.book_id = book_id;
        this.review = comment_text;
        this.user_id = user_id;
        // the postedDate would be filled by the auto-increament feature of MySQL
    } // end of the constructor

//...................... P U B L I C   M E T H O D S ........................//
    @Override
    public String toString()
    {
        return                       "[recipe_id   = " +
                this.book_id   + "] [posteddate  = " +
                this.date  + "] [reviewText = " +
                this.review + "] [user_id     = " +
                this.user_id     + "]";
    } // end of the method


	/**
	 * @return the review_id
	 */
	public int getReview_id() {
		return review_id;
	}


	/**
	 * @param review_id the review_id to set
	 */
	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}


	/**
	 * @return the book_id
	 */
	public int getBook_id() {
		return book_id;
	}


	/**
	 * @param book_id the book_id to set
	 */
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}


	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}


	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	/**
	 * @return the review
	 */
	public String getReview() {
		return review;
	}


	/**
	 * @param review the review to set
	 */
	public void setReview(String review) {
		this.review = review;
	}


	/**
	 * @return the rating
	 */
	public double getRating() {
		return rating;
	}


	/**
	 * @param rating the rating to set
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}


	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

  

} // end of the class
