/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import webapp.controller.SessionManager;
import webapp.model.BookManager;
import webapp.model.Book;
import java.util.ArrayList;

/**
 *
 * @author Nina
 */
public class addCommentServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "/addcomment.jsp";

        SessionManager sm = new SessionManager(request);
        boolean user_status = sm.isLoggedIn();
        if(user_status){
            int book_id = Integer.valueOf(request.getParameter("book_id"));

            BookManager rm = new BookManager();
            Book r = rm.getBookById(book_id);
            if(r != null){
                request.setAttribute("title", "Add Comment");
                request.setAttribute("book", r);
            } else {
                url = "/feedback.jsp";
                request.setAttribute("title", "Ooops");
                request.setAttribute("feedback_message", "Sorry, we cannot find the book.");
            }
        } else {
            url = "/feedback.jsp";
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
