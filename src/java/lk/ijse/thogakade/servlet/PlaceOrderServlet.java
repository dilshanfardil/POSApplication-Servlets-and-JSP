/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lk.ijse.thogakade.dto.CustomerDTO;
import lk.ijse.thogakade.dto.ItemDTO;
import lk.ijse.thogakade.dto.OrderDTO;
import lk.ijse.thogakade.dto.OrderDetailDTO;
import lk.ijse.thogakade.service.custom.CustomerService;
import lk.ijse.thogakade.service.custom.ItemService;
import lk.ijse.thogakade.service.custom.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.json.*;

/**
 *
 * @author Lahiru-PC
 */
public class PlaceOrderServlet extends HttpServlet {

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
        CustomerService customerService = (CustomerService) ctx.getBean("customerService");
        OrderService orderService=(OrderService) ctx.getBean("orderService");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(new Date());
        
        response.setContentType("text/html;charset=UTF-8");
//        RequestDispatcher rd=request.getRequestDispatcher("/index.html");
        switch (request.getParameter("action")) {
            case "save":
                Date date=parseDate(formattedDate);
                OrderDTO o=new OrderDTO(
                        request.getParameter("orderId"),
                        date ,
                        request.getParameter("custId")
                );
                String a="hi\n";
                //a+=request.getParameter("orderDetail")+"\n\n"+date;
                ArrayList<OrderDetailDTO> orderDetailDTOs=new ArrayList<>();
                JSONArray array=new JSONArray(request.getParameter("orderDetail"));
                for(int i=0;i<array.length();i++){
                    JSONObject ob=array.getJSONObject(i);
                    OrderDetailDTO orderDetailDTO=new OrderDetailDTO(
                            request.getParameter("orderId"), 
                            ob.getString("Code"), 
                            ob.getInt("Qty"), 
                            ob.getDouble("Unit Price")
                    );
                    //a+=ob.getString("Code")+"    "+ob.getString("Description")+"    "+ob.getDouble("Unit Price")+"    "+ob.getString("Amount")+"    "+ob.getInt("Qty")+"\n";
                    orderDetailDTOs.add(orderDetailDTO);                            
                }
                boolean add = orderService.add(o, orderDetailDTOs);
                response.getOutputStream().print(add);
                break;
            case "getItem":
                ItemDTO dto = new ItemDTO(request.getParameter("itemCode"));
                ItemDTO get = itemService.get(dto);                
                response.getOutputStream().print("{\"id\": \""+get.getCode()+"\",\"description\":\""+get.getDescription()+"\",\"qtyOnHand\":\""+get.getQtyOnHand()+"\",\"price\":\""+get.getUnitPrice()+"\"}");          
                break;
            case "getCustomer":
                CustomerDTO customerDTO=new CustomerDTO(request.getParameter("custId"));
                CustomerDTO get1 = customerService.get(customerDTO);
                response.getOutputStream().print("{\"id\": \""+get1.getId()+"\",\"name\":\""+get1.getName()+"\"}");
                break;
            case "getOrderId":
                OrderDTO orderDTO=new OrderDTO();
                orderDTO=orderService.get(orderDTO);
                response.getOutputStream().print(orderDTO.getId());
                break;
            default:
                System.out.println("Something went wrong...search");
        }
    
    }
    
    private Date parseDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(date);
        } catch (ParseException ex) {
            
        }
        return null;
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
