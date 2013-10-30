/*
 * This class is responsible for all data access for the application and contains
 * the very general purpose methods that are used in the bean managers class.
 * Each bean managers contain specific methods to deal with the corresponding
 * tables.
 */
package webapp.model;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nina
 */
public class DataAccessLayer {
//........................ D A T A   F I E L D S ............................//
//............. G L O B A L   P R I V A T E   C O N S T A N T S .............//

    /** hold default database name. */
    private static final String DATABASE_NAME = "booksea";
    /** hold default field size. */
    private static final int DEFAULT_FIELD_SIZE = 20;
    /** hold default null value. */
    private static final String DEFAULT_NULL_VALUE = " ";
// ................. G L O B A L   P R I V A T E   V A R S ...................//
    /** hold a pointer to the database connection. */
    private Connection con;
    /** hold a pointer to the MySQLConnection object. */
    private MySQLConnection sqlCon;

// ........................ C O N S T R U C T O R S ..........................//
    DataAccessLayer() {
        sqlCon = new MySQLConnection(DATABASE_NAME);
        this.con = sqlCon.getDBConnection();

    } // end of the constructor


//...................... P R I V A T E   M E T H O D S ......................//
//...................... P U B L I C   M E T H O D S ........................//

    public
    Connection getCon()
    {  return con;  }
    

    /**
     * close the database connection.
     */
    public void releaseConnection() {
        sqlCon.disconnectFromDB();

    } // end of the method
    
    
    /**
    * execute a SQL statement (query)
    * @param statement - the SQL statement
    * @return the result set.
    */
   public ResultSet executeQuery(String stmnt) {
       try {
           PreparedStatement pstmt = con.prepareStatement(stmnt);
           ResultSet rs = pstmt.executeQuery();
           return rs;

       } catch (SQLException e) {
           System.out.println(e.getMessage());
           return null;
       }

   } // end of the method
   
   /**
   * make an arrayList and put the result set contents into it and return it.
   * @param rs - the result set
   * @param c - the class of the been
   * @return - the arrayList
   */
  public <E> ArrayList<E> resultSetPacker(
          ResultSet rs,
          Class<? extends E> c) {
      try {
          ArrayList<E> arr = new ArrayList<E>();

          while (rs.next()) {
              E e = c.newInstance();
              beanMakerEngine(rs, e);
              arr.add(e);

          } // end of the for-loop

          return arr;

      } catch (SQLException ex) {
          System.err.println(ex.getMessage());
          return null;
      } catch (InstantiationException ex) {
          System.err.println(ex.getMessage());
          return null;
      } catch (IllegalAccessException ex) {
          System.err.println(ex.getMessage());
          return null;
      }

  } // end of the method
  
  /**
  * make a new bean and put the result set contents into it and return it.
  * @param rs - the result set
  * @param e - the bean object that will return the contents of the result set.
  */
 private <E> void beanMakerEngine(ResultSet rs, E e) {
     Field[] f = e.getClass().getDeclaredFields();
     int index = 1;

     for (Field field : f) {
         field.setAccessible(true);
         try {
             String type = field.getType().toString();

             // private fields has modifer = 2
             if (field.getModifiers() != 2) {
                 continue;
             }

             if (type.equals("int")) {
                 field.set(e, new Integer(rs.getInt(index++)));
             } else if (type.equals("class java.lang.String")) {
                 field.set(e, rs.getString(index++));
             } else if (type.equals("class java.sql.Timestamp")) {
                 field.set(e, rs.getTimestamp(index++));
             }

         } catch (IllegalAccessException ex) {
             System.out.println(ex.getMessage());
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }

     } // end of the for-loop

 } // end of the method
 
 /**
 * get two beans and update the corresponding record from the table.
 * @param value - the bean object that contain values.
 * @param where - the bean object that contain where clause values.
 * @return  true if the update operation is successful and false otherwise.
 */
public <E> boolean beanUpdateEngine(E value, String whereClause) {
    String tabName = value.getClass().getSimpleName();

    ArrayList<String> nameArr = getBeanFieldName(value);
    ArrayList<String> typeArr = getBeanFieldType(value);
    ArrayList<String> valueArr = getBeanFieldValue(value);

    String stmnt =
            createUPDATEStatement(tabName, nameArr, valueArr, typeArr, whereClause);

    //Test!
    //System.out.println("Statement: \n" + stmnt);

    int result = executeUpdate(stmnt);

    if (result == -1) return false;
    else              return true;

} // end of the method

/**
* get the bean fields Names.
* @param e - the bean object.
*/
private <E> ArrayList<String> getBeanFieldName(E e) {
   ArrayList<String> arr = new ArrayList<String>();

   Field[] f = e.getClass().getDeclaredFields();
   for (Field field : f) {
       field.setAccessible(true);
       String name = field.getName();
       arr.add(name);

   } // end of the for-loop

   return arr;

} // end of the method

/**
* get a bean and insert it into the appropriate table.
* @param e - the bean object.
* @return  true if the insert is successful and false otherwise.
*/
public <E> boolean beanInsertEngine(E e) {
   String tabName = e.getClass().getSimpleName();

   ArrayList<String> nameArr = getBeanFieldName(e);
   ArrayList<String> typeArr = getBeanFieldType(e);
   ArrayList<String> valueArr = getBeanFieldValue(e);

   String stmnt = createINSERTStatement(
           tabName, nameArr, valueArr, typeArr);

   //Test!
   //System.out.println(stmnt);

   int result = executeUpdate(stmnt);

   if (result == -1) {
       return false;
   } else {
       return true;
   }

} // end of the method


public <E> boolean beanInsertEngine_comment(E e) {
	   String tabName = "review";

	   ArrayList<String> nameArr = getBeanFieldName(e);
	   ArrayList<String> typeArr = getBeanFieldType(e);
	   ArrayList<String> valueArr = getBeanFieldValue(e);

	   String stmnt = createINSERTStatement(
	           tabName, nameArr, valueArr, typeArr);

	   //Test!
	   //System.out.println(stmnt);

	   int result = executeUpdate(stmnt);

	   if (result == -1) {
	       return false;
	   } else {
	       return true;
	   }

	} // end of the method

/** create INSERT statement
 * @param tabName - the table name.
 * @param conName - the array of the columns names.
 * @param values - the values array
 * @return the INSERT statement.
 */
private static String createINSERTStatement(
        String tabName, ArrayList<String> nameArr,
        ArrayList<String> valueArr, ArrayList<String> typeArr) {
    String colStr = createCommaSeparatedStr(nameArr, valueArr, typeArr);
    String valStr = createCommaSeparatedStrWrapped(valueArr, typeArr);

    String insertStr = "INSERT INTO %s (%s) \nVALUES (%s);";

    return String.format(insertStr, tabName, colStr, valStr);

} //end of the method

/**
* make a string of all values of an array separated by comma.
* @param valueArr - the input array.
* @return - the string of the array values.
*/
private static String createCommaSeparatedStr(
       ArrayList<String> NameArr,
       ArrayList<String> valueArr, ArrayList<String> typeArr) {
   StringBuilder result = new StringBuilder();

   for (int i = 0; i < NameArr.size(); i++) {
       if (valueArr.get(i).toUpperCase().equals("NULL")) {
           continue;
       } else if (typeArr.get(i).equals("int")
               && Integer.valueOf(valueArr.get(i)) < 0) {
           continue;
       } else {
           result.append(", ").append(NameArr.get(i));
       }
   }

   return result.toString().substring(2);

} // end of the method

/**
* make a string of all values of an array separated by comma. It looks at
* the corresponding numOrStr and if it is 0 means that the value is a
* String. So, it wrapped it in single quotes.
* @param valueArr - the input array.
* @return - the string of the array values.
*/
private static String createCommaSeparatedStrWrapped(
       ArrayList<String> valueArr, ArrayList<String> typeArr) {
   StringBuilder result = new StringBuilder();

   for (int i = 0; i < valueArr.size(); i++) {
       // remove \' from the char types
       valueArr.set(i, valueArr.get(i).replaceAll("[']", "\'"));


       if (valueArr.get(i).toUpperCase().equals("NULL")) {
           continue;
       } else if (typeArr.get(i).equals("int")
               && Integer.valueOf(valueArr.get(i)) < 0) {
           continue;
       } else if (!typeArr.get(i).equals("int")) {
           result.append(", \'").append(valueArr.get(i)).append("\'");
       } else {
           result.append(", ").append(valueArr.get(i));
       }
   }

   return result.toString().substring(2);

} // end of the method



/**
* get the bean fields types.
* @param e - the bean object.
*/
private <E> ArrayList<String> getBeanFieldType(E e) {
   ArrayList<String> arr = new ArrayList<String>();

   Field[] f = e.getClass().getDeclaredFields();
   for (Field field : f) {
       field.setAccessible(true);
       String type = field.getType().toString();
       arr.add(type);

   } // end of the for-loop 

   return arr;

} // end of the method

/**
* get the bean fields Names.
* @param e - the bean object.
*/
private <E> ArrayList<String> getBeanFieldValue(E e) {
   ArrayList<String> arr = new ArrayList<String>();

   Field[] f = e.getClass().getDeclaredFields();
   for (Field field : f) {
       field.setAccessible(true);
       try {
           Object value = field.get(e);

           if (value == null) {
               arr.add("null");
           } else {
               arr.add(value.toString());
           }
       } catch (IllegalAccessException ex) {
           System.out.println(ex.getMessage());
       }

   } // end of the for-loop

   return arr;

} // end of the method

/** create DELET statement
* @param tabName - the table name.
* @param nameArr - the array of the columns names.
* @param valueArr - the values array
* @param typeArr - the type array
* @param whereClause - the where clause string
* @return the delete statement.
*/

private static String createUPDATEStatement(
       String tabName,
       ArrayList<String> nameArr, ArrayList<String> valueArr,
       ArrayList<String> typeArr, String whereClause) {

   String setClauseStr = createSETClause(nameArr, valueArr, typeArr);

   return String.format("UPDATE %s \nSET %s%s",
           tabName, setClauseStr, whereClause);

} //end of method

/**
* execute a SQL statement (update)
* @param statement - the SQL statement
* @return the number of records affected.
*/
public int executeUpdate(String stmnt) {
   try {
       PreparedStatement pstmt = con.prepareStatement(stmnt);
       int result = pstmt.executeUpdate();

       return result;
   } catch (SQLException e) {
       System.out.println(e.getMessage());
       return -1;
   }

} // end of the method
    
    /**
    * return username by ID
    * @param userName - the user name
    * @return the user id.
    */
   public String getUserNameByID(int id)
   {
       try
       {
           String stmnt = String.format(
                   "SELECT USER_NAME " +
                   "FROM   user " +
                   "WHERE  USER_ID = %d " +
                   "LIMIT  1", id);

           ResultSet rs = executeQuery(stmnt);
           rs.next();

           if (rs == null) return null;
           else            return rs.getString(1);
       }

       catch (SQLException ex)
       {
           System.err.println(ex.getMessage());
           return null;
       }

   } // end of the method.
   
   /**
   * make a set clause for UPDATE.
   * @param nameArr - the array of the columns names.
   * @param valueArr - the values array
   * @param typeArr - the type array
   * @return the set clause.
   */
  private static String createSETClause(ArrayList<String> nameArr,
          ArrayList<String> valueArr, ArrayList<String> typeArr) {
      StringBuilder result = new StringBuilder();
      String tempStr = "";

      for (int i = 0; i < valueArr.size(); i++) {
          // remove \' from the char types
          valueArr.set(i, valueArr.get(i).replaceAll("[']", "\'"));

//			if (valueArr.get(i).toUpperCase().equals("NULL"))
//				continue;

          if ((typeArr.get(i).equals("int")
                  && Integer.valueOf(valueArr.get(i)) == -1)
                  || (!typeArr.get(i).equals("int")
                  && valueArr.get(i).equals("-1"))) {
              continue;
          } else if (!typeArr.get(i).equals("int")
                  && valueArr.get(i).toUpperCase().equals("NULL")) {
              tempStr = String.format(",   %s = null\n", nameArr.get(i));
          } else if (!typeArr.get(i).equals("int")
                  && !valueArr.get(i).toUpperCase().equals("NULL")) {
              tempStr = String.format(",   %s = \'%s\'\n",
                      nameArr.get(i), valueArr.get(i));
          } else {
              tempStr = String.format(",   %s = %s\n",
                      nameArr.get(i), valueArr.get(i));
          }

          result.append(tempStr);

      } // end of the for-loop

      if (result.toString().equals("")) {
          return null;
      } else {
          return result.toString().substring(4);
      }

  } // end of the method


   
    
    /** return username by ID
    @param id - User id
    @return The User with that id or null.
    */
   public User getUserByID(int id) {
       User u = null;

       try {
           u = getUserByUserName(getUserNameByID(id));
       } catch (Exception e) {
       }
       return u;
   }
   
   /**
   * return all Users beans for  a specific user name
   * @param userName - the user's name
   * @return the array list of the users.
   */
  public User getUserByUserName(String userName) {
      User u = null;
      try {
          String stmnt = String.format(
              "SELECT   * "
              + "FROM     user "
              + "WHERE    USER_NAME = \'%s\' "
              + "LIMIT    1", userName);

      ResultSet rs = executeQuery(stmnt);
      ArrayList<User> arr = resultSetPacker(rs, User.class);
      if (arr.size() > 0) {
          u = arr.get(0);
      }

      }
      catch (Exception e) {
          System.out.println("DAL: Error in getUserByUserName:"+e.toString());
          u = null;
      }
      return u;
  } // end of the method.
  
  /**
  * return user ID by userName
  * @param userName - the user name
  * @return the user id.
  */
 public int getUserIDByUserName(String userName)
         throws ClassNotFoundException, SQLException,
         InstantiationException, IllegalAccessException {
     String stmnt = String.format(
             "SELECT   USER_ID "
             + "FROM     user "
             + "WHERE    USER_NAME = \'%s\' "
             + "LIMIT    1",
             userName);

     ResultSet rs = executeQuery(stmnt);
     rs.next();
     if (rs == null) {
         return -1;
     }

     return rs.getInt(1);

 } // end of the method.
  



    public int insertUser(User user)
            throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException {
        String userName = user.getUserName();
        boolean isOK = beanInsertEngine(user);

        if (isOK) {
            return getUserIDByUserName(userName);
        } else {
            return -1;
        }

    } // end of the method.

    private void testinsertUser()
            throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException {
        User r = new User("User1", "1", "user1@email.com");

        int user_id = insertUser(r);

        System.out.println(user_id);

    } // end of the method.


} // end of the class

