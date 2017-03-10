/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lk.ijse.thogakade.dto.CustomerDTO;
import lk.ijse.thogakade.service.custom.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Lahiru-PC
 */
public class WebServlet extends HttpServlet {

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
        ApplicationContext ctx = (ApplicationContext) new ClassPathXmlApplicationContext("lk/ijse/thogakade/spring/AutoWiredSpringXMLConfig.xml");
        CustomerService customerService = (CustomerService) ctx.getBean("customerService");
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd=request.getRequestDispatcher("/index.html");
        switch (request.getParameter("action")) {
            case "save":
                CustomerDTO cdto = new CustomerDTO(request.getParameter("custId"),
                        request.getParameter("custName"),
                        request.getParameter("custAddress"),
                        Double.valueOf(request.getParameter("custSalary"))
                );
                boolean add = customerService.add(cdto);
                rd.include(request, response);  
                break;
            case "delete":
                CustomerDTO dto = new CustomerDTO(request.getParameter("custId"));
                System.out.println(dto.getId()+" Im in servlet"+request.getParameter("custId"));
                boolean result = customerService.delete(dto);                
                rd.include(request, response);                  
                break;
            case "update":
                CustomerDTO dtoUpdate = new CustomerDTO(request.getParameter("custId"),
                        request.getParameter("custName"),
                        request.getParameter("custAddress"),
                        Double.valueOf(request.getParameter("custSalary"))
                );
                boolean update = customerService.update(dtoUpdate);
                rd.include(request, response);  
                break;
            case "search":
                System.out.println("Hi im search");
                CustomerDTO dtoS = new CustomerDTO(request.getParameter("custId"));
                CustomerDTO get = customerService.get(dtoS);
                response.getOutputStream().print(get.getId()+";"+get.getName()+";"+get.getAddress()+";"+get.getSalary());
//                request.setAttribute("custName", dtoS.getName());                
                //rd.include(request, response);                 
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
