/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Entre;

/**
 *
 * @author Tsiky Aro
 */
public class EtatdeStock extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EtatdeStock</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EtatdeStock at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Date date1 =Date.valueOf(request.getParameter("date1"));
        Date date2 =Date.valueOf(request.getParameter("date2"));
        String reference = request.getParameter("reference");
        int idMag =Integer.valueOf(request.getParameter("magasin"));
        PrintWriter out = response.getWriter();
        out.println("date1 "+date1+ " date2 "+date2+" reference "+reference+"magasin "+idMag);
        Entre ent = new Entre();
        Entre[] ente = ent.etatPourToutArticle(date1,idMag,reference);
        Entre[] ente1 = ent.etatPourToutArticle(date2,idMag,reference);
        Entre etatFinal1=ent.etatdestockFinal(date1, idMag,reference);
        Entre etatFinal2=ent.etatdestockFinal(date2, idMag,reference);
        request.setAttribute("entre1", ente);
        request.setAttribute("entre2", ente1);
        request.setAttribute("date1", date1);
        request.setAttribute("date2", date2);
        request.setAttribute("eta1", etatFinal1);
        request.setAttribute("eta2", etatFinal2);
        RequestDispatcher dispatch = request.getRequestDispatcher("table.jsp");
        dispatch.forward(request, response);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
