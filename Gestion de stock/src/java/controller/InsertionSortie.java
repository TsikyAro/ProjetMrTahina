package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Article;
import model.Entre;
import model.Magasin;
import model.Sortie;
import model.Unite;
public class InsertionSortie extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertionSortie</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertionSortie at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         Magasin m = new Magasin();
        Unite unit = new Unite();
        Article art = new Article();
        Article[] article = art.findAllArticle();
        Unite [] unite = unit.findAllUnite();
        Magasin[] magasins = m.findAllMagasin();
        request.setAttribute("magasin", magasins);
        request.setAttribute("unite", unite);
        request.setAttribute("article", article);
        RequestDispatcher dispatch = request.getRequestDispatcher("sortie.jsp");
        dispatch.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            Date date = Date.valueOf(request.getParameter("date"));
            int idarticle = Integer.valueOf(request.getParameter("idarticle"));
            int idmagasin = Integer.valueOf(request.getParameter("idmagasin"));
            int idunite = Integer.valueOf(request.getParameter("idunite"));
            double quantite = Double.valueOf(request.getParameter("quantite"));
            Article arte = new Article().getAllArticlebyId(idarticle);
            Entre entre = new Entre();
            Sortie sotie = new Sortie();
            Entre[] entrer = entre.getEntrerbyDate(date, arte, idmagasin);
            Sortie sortie = sotie.getSortiebyDate(date, arte, idmagasin);
            Entre result = entre.etatFinal(entrer, sortie);
            sortie = new Sortie(idarticle,quantite, idmagasin, idunite, date);
            entrer = entre.getEntrerbyDate(date, arte, idmagasin);
            sortie.sortieValide(entrer,result, sortie);
        } catch (Exception ex) {
            out.println(ex.getMessage());
            ex.printStackTrace();
        }
        
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
