/*
 * this class is the object version of the table User.
 */

package webapp.model;

import java.util.Date;

/**
 *
 * @author Nina Fu
 */
public class User
{
//........................ D A T A   F I E L D S ............................//
    private int    id;
    private String userName;
    private String userPass;
    private String first_name;
    private String last_name;
    private String email;
    private String lastUpdatedBy;
    private Date   lastUpdateDate;

    private static final int NO_ID = -1;

//........................ C O N S T R U C T O R S ..........................//

     public User()
    {
        id        = NO_ID;
        userName  = "";
        userPass  = "";
        first_name = "";
        last_name = "";
        email     = "";
        lastUpdatedBy = "";
        lastUpdateDate = null;
    } // end of the constructor


    public User(String userName, String userPass, String email)
    {
		this.userName = userName;
		this.userPass = userPass;
		this.email    = email;
	} // end of the constructor


 
    public User(int id, String userName, String userPass, String email)
    {
        this.id        = id;
		this.userName  = userName;
		this.userPass  = userPass;
		this.email     = email;
	} // end of the constructor


//...................... P U B L I C   M E T H O D S ........................//

	@Override
    public String toString()
    {
        return                 "[id        = " +
            this.id        + "] [userName  = " +
            this.userName  + "] [userPass  = " +
            this.userPass  + "] [email     = " +
            this.email     + "]";
    } // end of the method


    /**
	 * @return the userId
	 */
	public int getUserId() {
		return this.id;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.id = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userPass
	 */
	public String getUserPass() {
		return this.userPass;
	}

	/**
	 * @param userPass the userPass to set
	 */
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

  

} // end of the class