/*
 * this class is the object version of the table TempUser.
 */

package webapp.model;

/**
 *
 * @author Nina
 */
public class TempUser
{
//........................ D A T A   F I E L D S ............................//
    private String userName;
    private String userPass;
    private String email;
    private String image_url;
    private String actKey;


//........................ C O N S T R U C T O R S ..........................//

     public TempUser()
    {
        userName  = "";
        userPass  = "";
        email     = "";
        image_url = "";
        actKey    = "";
    } // end of the constructor


    public TempUser(String userName, String userPass, String email,
            String image_url, String actKey)
    {
		this.userName  = userName;
		this.userPass  = userPass;
		this.email     = email;
        this.image_url = image_url;
        this.actKey    = actKey;
	} // end of the constructor

//...................... P U B L I C   M E T H O D S ........................//

	@Override
    public String toString()
    {
        return                    "[" +
                this.userName  + "] [" +
                this.userPass  + "] [" +
                this.email     + "] [" +
                this.image_url + "] [" +
                this.actKey       + "]";
    } // end of the method

    public
    String getActKey()
    {
        return actKey;
    }

    public
    void setActKey(String actKey)
    {
        this.actKey = actKey;
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

    /**
	 * @return the email
	 */
	public String getImage_url() {
		return this.image_url;
	}

	/**
	 * @param email the email to set
	 */
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

} // end of the class