
package webapp.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import webapp.model.*;

/**
 * Enable registered users to add comments for books, and update model with user's inputted comments.
 * @author Nina
 */
public class addCommentByBookIdServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        String url = "/feedback.jsp";
        request.setAttribute("current_page", "mybook");
        Integer rating = null;
        boolean addRateSuccess = false;

        SessionManager sm = new SessionManager(request);
        boolean user_status = sm.isLoggedIn();
        if (user_status)
        {
            int user_id = sm.getUserID();

            int bookId = Integer.valueOf(request.getParameter("book_id"));
            String commentText = request.getParameter("comment_text");
            String rateString = request.getParameter("rating");
            if (!rateString.equals(""))
            {
                rating = Integer.valueOf(rateString);

                BookManager rm = new BookManager();
                Book r = rm.getBookById(bookId);
                if (r != null)
                {
                    //int currentRateSum = r.getRateSum();

                    if (rating >= 0 && rating <= 5)
                    {
                            if (rm.updateRatingByBookId(rateString, bookId))
                                addRateSuccess = true;
                    }
                }
            }

            CommentManager commentmanager = new CommentManager();
            Comment c = new Comment(bookId, commentText, user_id);

            if (commentmanager.addComment(c))
            {
                request.setAttribute("title", "Success");
                request.setAttribute("feedback_message", "Comment was successfully added.");
                request.setAttribute("feedback_redirect", "viewbook?book_id=" + bookId);
                if (addRateSuccess) {
                    request.setAttribute("title", "Success");
                    request.setAttribute("feedback_message", "Comment and rate were successfully added.");
                    request.setAttribute("feedback_redirect", "viewbook?book_id=" + bookId);
                } else {
                    if (!rateString.equals("")) {
                        request.setAttribute("title", "Ooops");
                        request.setAttribute("feedback_message", "Comment was successfully added, but your rate is invalid.");
                    }
                }
            } else {
                request.setAttribute("title", "Ooops");
                request.setAttribute("feedback_message", "Sorry, we found a problem with your comment.");
            }
        } else {
            request.setAttribute("title", "Ooops");
            request.setAttribute("feedback_message", "You must be logged in to view this page.");
        }
        
       
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
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
            throws ServletException,
            IOException {
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
            throws ServletException,
            IOException {
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
} // end of the class
