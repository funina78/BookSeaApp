/*
 * this class contains all operations needed to deal with comments. It extends
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
 */
public class CommentManager extends DataAccessLayer
{
    /**
     * get all comments by a specific recipe ID
     * @param recipe_id - the recipe id
     * @return an array list of the Comment beans.
     */
    public ArrayList<Comment> getCommentByBookId(int book_id)
    {
        String stmnt = String.format(
                  "SELECT   * "
                + "FROM     comment "
                + "WHERE    book_id = %d "
                + "ORDER BY date DESC ", book_id);

        ResultSet rs = executeQuery(stmnt);
        ArrayList<Comment> arr = resultSetPacker(rs, Comment.class);

        return arr;

    } // end of the method.



    /**
     * add a comment to the database
     * @param c - the comment object
     * @return true if the add is successful and false otherwise.
     */
    public boolean addComment(Comment c)
    {
        return beanInsertEngine_comment(c);
    	
    	     
    } // end of the method



} // end of the class



