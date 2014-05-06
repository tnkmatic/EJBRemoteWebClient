/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package begining.ejbremote.web.client;

import java.io.IOException;
import begining.ejbremote.lib.EjbUtil;
import begining.ejbremote.lib.EjbInterface;
import begining.ejbremote.lib.EjbRemoteInterface;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eiichi Tanaka
 */
@WebServlet(name = "EjbServletClient", urlPatterns = {"/EjbServletClient"})
public class EjbServletClient extends HttpServlet {
    private static final java.util.logging.Logger logger = 
    java.util.logging.Logger.getLogger(EjbServletClient.class.getName());

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
        
        /***********************************************************************
         * Remoteアノテーションのみ付与しているEJBの呼び出し
         **********************************************************************/
        logger.log(Level.INFO, "Call Remote EJB EjbService");
        EjbInterface ejb = (EjbInterface) EjbUtil.ejbLookupFormContainer("ejbService");
        final String retName = ejb.getName("EJB Web Client");
        logger.log(Level.INFO, retName);
        
        /***********************************************************************
         * Local,Remoteの両方のアノテーションを付与したEJBをLookupをしたい場合は、
         * java:global/<アプリケーション名>/<モジュール名>/<Bean名>/<!完全修飾インターフェース名>
         * で、Lookupする必要あり
         * →GlassFishの外部リソースJNDIに登録
         **********************************************************************/
        logger.log(Level.INFO, "Call Remote EJB EjbService2");
        EjbRemoteInterface ejb2 = 
                (EjbRemoteInterface) EjbUtil.ejbLookupFormContainer("ejbService2");
        final String retName2 = ejb2.getName("EJB Web Client");
        logger.log(Level.INFO, retName2);
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
        processRequest(request, response);
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
