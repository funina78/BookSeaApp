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

/**
 *
 * @author Nina
 */
public class getRecommendBookServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            String url = "/recommend.jsp"; //default
            request.setAttribute("current_page", "recommend");

            BookManager book_manager = new BookManager();
            Book[] recommend = book_manager.getRecommendBook();
            int null_count = 0;
            for(int i = 0; i < recommend.length; i++)
                if(recommend[i] == null)
                    null_count++;

            if(recommend != null && null_count < recommend.length){
                request.setAttribute("recommend", recommend);
                request.setAttribute("title", "Recommend Book");
            } else {
                url = "/feedback.jsp";
                request.setAttribute("title", "Ooops");
                request.setAttribute("feedback_message", "Sorry, we could not find our recommended books.");
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } finally {
            out.close();
        }
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
