/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lk.ijse.thogakade.dto.ItemDTO;
import lk.ijse.thogakade.service.custom.ItemService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Lahiru-PC
 */
public class ItemServlet extends HttpServlet {

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
        ApplicationContext ctx = (ApplicationContext) new ClassPathXmlApplicationContext("lk/ijse/thogakade/spring/AutoWiredSpringXMLConfig.xml");
        ItemService itemService = (ItemService) ctx.getBean("itemService");
        response.setContentType("text/html;charset=UTF-8");
//        RequestDispatcher rd=request.getRequestDispatcher("/index.html");
        switch (request.getParameter("action")) {
            case "save":
                ItemDTO cdto = new ItemDTO(request.getParameter("itemCode"),
                        request.getParameter("itemDescription"),
                        Double.valueOf(request.getParameter("itemPrice")),
                        Integer.valueOf(request.getParameter("itemQty"))
                );
                boolean add = itemService.add(cdto);
                response.getOutputStream().print(add);
                System.out.println("Hi im save");
                break;
            case "delete":
                ItemDTO dto = new ItemDTO(request.getParameter("itemCode"));
                System.out.println(dto.getCode()+" Im in servlet"+request.getParameter("itemCode"));
                boolean result = itemService.delete(dto);                
                response.getOutputStream().print(result);          
                break;
            case "update":
                ItemDTO Udto = new ItemDTO(request.getParameter("itemCode"),
                        request.getParameter("itemDescription"),
                        Double.valueOf(request.getParameter("itemPrice")),
                        Integer.valueOf(request.getParameter("itemQty"))
                );
                boolean update = itemService.update(Udto);
                response.getOutputStream().print(update);
                break;
            case "search":
                ItemDTO Sdto = new ItemDTO(request.getParameter("itemCode"));
        //System.out.println(dto.getCode()+" Im in servlet"+request.getParameter("itemCode"));
                ItemDTO get = itemService.get(Sdto);     
                //response.getOutputStream().print(get.getCode()+";"+get.getDescription()+";"+get.getQtyOnHand()+";"+get.getUnitPrice());
                response.getOutputStream().print("{\"id\": \""+get.getCode()+"\",\"description\":\""+get.getDescription()+"\",\"qtyOnHand\":\""+get.getQtyOnHand()+"\",\"price\":\""+get.getUnitPrice()+"\"}");
                                 
                break;
            default:
                System.out.println("Something went wrong...search");
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
