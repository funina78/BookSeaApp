/*
 * this class contains all operations needed to deal with restaurant. It extends
 * DataAccessLayer class which contains more general methods to handle all
 * kind of beans and tables.
 *
 */
package webapp.model;

import java.sql.*;
import java.util.*;
/**
 *
 * @author Nina
 *
 */
public class BookManager extends DataAccessLayer
{
//........................ D A T A   F I E L D S ............................//
    static final int SEARCH_LIMIT = 100;
    static final int PAGE_LIMIT = 7;

//........................ C O N S T R U C T O R S ..........................//

    public BookManager()
    {  } // end of the constructor

//...................... P U B L I C   M E T H O D S ........................//

/*   *//** addBook
     * Adds a book
     * @param b - book to be added. ID will be overwritten during insertion.
     * @returns the book with a modified ID based on insertion in the DB.
     *//*
    public Book addBook(Book b) {

       
    }
*/



    /**
     * return a Book for a specific book id
     * @param book_id - the book id
     * @return the book.
     */
    public Book getBookById(int book_id)
    {
        String stmnt = String.format("SELECT * FROM book WHERE book_ID = %d LIMIT 1",
        		book_id);

        ResultSet rs = executeQuery(stmnt);
        ArrayList<Book> arr = resultSetPacker(rs, Book.class);

        return arr.get(0);

    } // end of the main method.




    /**
     * get the recommend books.
     * @return - the array of the books
     */
    public Book[] getRecommendBooks()
    {
        String stmnt = String.format(
                "SELECT * FROM book LIMIT %d ",
                PAGE_LIMIT);
        ResultSet rs = executeQuery(stmnt);
        ArrayList<Book> arr = resultSetPacker(rs, Book.class);

        return arr.toArray(new Book[arr.size()]);

    } // end of the method


 





    public Book[] getFavoriteBooks()
    {
        String stmnt = String.format(
                "SELECT * FROM book ORDER BY rating DESC LIMIT %d ",
                PAGE_LIMIT);
        ResultSet rs = executeQuery(stmnt);
        ArrayList<Book> arr = resultSetPacker(rs, Book.class);

        return arr.toArray(new Book[arr.size()]);

    } // end of the method
    
    public boolean updateRatingByBookId (String newRate, int bookId)
    {
        String stmnt = String.format(
                "UPDATE book "
                + "SET rating = %d "
                + "WHERE book_ID = %d", newRate, bookId);

        //Test!
        System.out.println("Statement: \n" + stmnt);

        int result = executeUpdate(stmnt);

        if (result == -1) return false;
        else              return true;

    } // end of the method


public Book[] getRecommendBook(){
	
	  String stmnt = String.format(
              "SELECT * FROM book " );
      ResultSet rs = executeQuery(stmnt);
      ArrayList<Book> arr = resultSetPacker(rs, Book.class);

      return arr.toArray(new Book[arr.size()]);
}


} // end of the class
