/*
 * this class is in charge to paginate the result of a search. It analyze a
 * result set and simulates the pagination.
 */

package webapp.model;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Nina
 */
public class Cursor extends DataAccessLayer
{
//........................ D A T A   F I E L D S ............................//

    /* hold the default page size. */
    public static final int DEFAULT_PAGE_SIZE = 5;

    /* hold the whole array of Recipes. */
    private Book[] arr;

    /* hold the array size. */
    private int size;

    /* hold the page size. */
    private int pageSize;

    /* hold the current page numbre. */
    private int pageNum;

    /* hold the number of pages this object has. */
    private int numOfPages;

//........................ C O N S T R U C T O R S ..........................//

    public Cursor(ResultSet rs)
    {
        ArrayList<Book> arrL = resultSetPacker(rs, Book.class);
        this.size = arrL.size();
        this.arr = arrL.toArray(new Book[size]);
        this.pageNum = -1;
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.numOfPages = (int) Math.ceil((double) size / (double) pageSize);

    } // end of the constructor


    public
    Cursor()
    {
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.pageNum = -1;
        this.numOfPages = 0;
        this.size = 0;
        this.arr = null;

    } // end of the constructor

//...................... P U B L I C   M E T H O D S ........................//

    @Override
    public String toString()
    {
        return                     "\n[size       = " +
                this.size       + "]\n[pageSize   = " +
                this.pageSize   + "]\n[pageNum    = " +
                this.pageNum    + "]\n[numOfPages = " +
                this.numOfPages + "]\n";
    } // end of the method

    public
    int getNumOfPages()
    {
        return numOfPages;
    }
//.............................. G E T T E R S ...............................//
    public
    int getPageNum()
    {
        return pageNum;
    }

    public
    Book[] getArr()
    {
        return arr;
    }

    public
    int getPageSize()
    {
        return pageSize;
    }

    public
    int getSize()
    {
        return size;
    }

   
//.............................. S E T T E R S ...............................//
     public
    void setPageSize(int pageSize)
    {
        this.pageSize = pageSize; //this will change numOfPages; need to update
    }

// ...................... P U B L I C   M E T H O D S ........................//

    /**
     * check if there is next page?
     * @return true if yes and false otherwise.
     */
    public boolean hasNextPage()
    {
        if (size == 0 || pageNum + 1 == numOfPages) return false;
        else                                        return true;

    } // end of the method


    /**
     * check if there is previous page?
     * @return true if yes and false otherwise.
     */
    public boolean hasPreviousPage()
    {
        if (size == 0 || pageNum == 0) return false;
        else                           return true;

    } // end of the method
    

    /**
     * check if the cursor has next record?
     * @return true if yes and false otherwise.
     */
    public Book[] nextPage()
    {
        if (!hasNextPage()) return null;

        pageNum++;

        ArrayList<Book> arrLst = new ArrayList<Book>();
        int offset = pageNum * pageSize;

        for (int index = 0; index < pageSize && offset + index < size; index++)
             arrLst.add(arr[offset + index]);

        return arrLst.toArray(new Book[arrLst.size()]);

    } // end of the method


    /**
     * check if the cursor has next record?
     * @return true if yes and false otherwise.
     */
    public Book[] previousPage()
    {
        if (!hasPreviousPage()) return null;

        pageNum--;

        ArrayList<Book> arrLst = new ArrayList<Book>();
        int offset = pageNum * pageSize;

        for (int index = 0; index < pageSize && offset + index < size; index++)
             arrLst.add(arr[offset + index]);

        return arrLst.toArray(new Book[arrLst.size()]);

    } // end of the method


    private void testPages()
    {
        try
        {
            /* ..........  Prepare the environemetn  .................*/
            String stmnt = String.format("SELECT * FROM recipe WHERE user_id = %d", 2);
            Connection con = getCon();
            PreparedStatement pstmt =
                    con.prepareCall(stmnt, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs2 = pstmt.executeQuery();
            /* .......................................................*/

            Cursor cur = new Cursor(rs2);
            showObject(cur.getArr());

            System.out.println(cur.toString());

            System.out.println("   NEXT PAGE   ");

            Book[] arrrr = cur.nextPage();
            showObject(arrrr);

            System.out.println("   NEXT PAGE   ");
            arrrr = cur.nextPage();
            showObject(arrrr);

            System.out.println("   NEXT PAGE   ");
            arrrr = cur.nextPage();
            showObject(arrrr);

            System.out.println("   PREVIOUS PAGE   ");
            arrrr = cur.previousPage();
            showObject(arrrr);

            System.out.println("   PREVIOUS PAGE   ");
            arrrr = cur.previousPage();
            showObject(arrrr);

            System.out.println(cur.toString());
        }
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
        }

    } // end of the method.


    private void showObject(Book[] arr2) {
		// TODO Auto-generated method stub
		
	}


	/**
     */
//........................ M A I N   M E T H O D ............................//
    /**
     * This main method is just for testing this class.
     * @param args the arguments
     */
    public static void main(String[] args)
    {
        Cursor app = new Cursor();
        /* .............................................*/

        app.testPages();
        /* .............................................*/
        app.releaseConnection();

    } // end of the main method.

} // end of the class