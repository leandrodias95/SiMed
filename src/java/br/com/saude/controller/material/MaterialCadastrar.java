/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saude.controller.material;

import br.com.saude.dao.MaterialDAO;
import br.com.saude.model.Categoria;
import br.com.saude.model.MaterialHospitalar;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabri
 */
@WebServlet(name = "MaterialCadastrar_1", urlPatterns = {"/MaterialCadastrar_1"})
public class MaterialCadastrar extends HttpServlet {

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
        response.setContentType("text/html;charset=iso-8859-1");
        String mensagem = null;
        
        try{
            int idCategoria = Integer.parseInt(request.getParameter("categoria"));
            MaterialHospitalar oMaterialHospitalar = new MaterialHospitalar();
            oMaterialHospitalar.setIdMaterialHospitalar(Integer.parseInt(request.getParameter("idMaterialHospitalar")));
            oMaterialHospitalar.setCategoria(new Categoria(idCategoria,""));
            oMaterialHospitalar.setNomeMaterial(request.getParameter("nomeMaterial"));
            oMaterialHospitalar.setQtde(Integer.parseInt(request.getParameter("qtde")));
            MaterialDAO dao = new MaterialDAO();
            if(dao.cadastrar(oMaterialHospitalar)){
               //cadastrado
               response.getWriter().write("1");
           }else{
               //nao cadastrado
               response.getWriter().write("0");
           }
        }catch (Exception e){
            System.out.println("Erro"+e.getMessage());
            
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
