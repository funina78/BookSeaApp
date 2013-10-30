/*Servlet handles updating user account information.*/

package webapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import java.util.HashMap;
import webapp.model.UserManager;
import webapp.model.User;

/**
 *
 * @author Nina 
 */
public class accountSettingsServlet extends HttpServlet {

    private HashMap<String, String> fields;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        fields = new HashMap<String, String>();
        System.out.println("accountSettings: trying Sessionmanager");
        SessionManager sm = new SessionManager(request);
        boolean user_status = sm.isLoggedIn();
        String url = "/feedback.jsp";

        if (user_status) {
            UserManager mgr = new UserManager();
            User u = mgr.getUserByID(sm.getUserID());

            System.out.println("accountSettings: user_id" + u.getUserId() + " user_status=" + user_status);

            //validate fields are complete.
            //if not valid, tag and reforward to marked fields.
            //if valid, add recipe and forward to "my recipes" servlet.

           // FileUploader f = new FileUploader(this, "users", this);
            //this status flag will not change to success until file is uploaded
           // int status = f.parseRequest(request);
           // System.out.println("addRecipe: Parsed request=" + status);

            if ((fields != null)) { //some fields weres parsed

               if (fields.containsKey("change_password")) { //we are trying to change password
                    String oldPassword = fields.get("old_password");

                    String newPassword = fields.get("new_password");
                    System.out.println("old="+oldPassword);
                    System.out.println("new="+newPassword);

                    if (mgr.validatePass(u.getUserName(), oldPassword)) {
                        System.out.println("here2");

                        u.setUserPass(newPassword);
                        if (mgr.updateUserPassword(u)) {
                            request.setAttribute("title", "Success");
                            request.setAttribute("feedback_message", "Successfully changed password.");
                        } else {
                            request.setAttribute("title", "Oops");
                            request.setAttribute("feedback_message", "Failed to change password.");
                        }
                    }
                    else {
                        request.setAttribute("title", "Oops");
                        request.setAttribute("feedback_message", "Incorrect password.");
                    }
                }
                else {
                    request.setAttribute("title", "Oops");
                    request.setAttribute("feedback_message", "There has been an error processing your request.");
                }
            } else {
                url = "/feedback.jsp";
                request.setAttribute("title", "Ooops");
                request.setAttribute("feedback_message", "Incorrect form submission.");
            }
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    /*
     * handleFormField
     * Required to implement interface FileUploadParseListener
     * Called whenever FileUploader parses a field. This may be non-file fields or
     * the new relative path to the unique name for the file.
     * @param field - the form field
     * @param value - the value for that field
     */
    public void handleFormField(String field, String value) {
        fields.put(field, value);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
