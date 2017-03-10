<%-- 
    Document   : ThogakadeJSP
    Created on : Feb 21, 2017, 12:30:17 PM
    Author     : Lahiru-PC
--%>

<%@page import="lk.ijse.thogakade.dto.ItemDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="lk.ijse.thogakade.service.custom.ItemService"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="_css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="_css/style.css">
        <script src="_js/jquery.min.js" type="text/javascript"></script>
        <script src="_js/item.js" type="text/javascript"></script>
    </head>
    <body style="background-color: chartreuse" class="container"> 
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
               <ul class="nav nav-pills">
                   <li role="presentation" id="navCustomer" ><a href="index.html" >Customer</a></li>
                   <li role="presentation" id="navItem" class="active"><a href="ItemJSP.jsp">Item</a></li>
                   <li role="presentation" id="navPlaceOrder"><a href="PlaceOrder.jsp" >Place Order</a></li>
              </ul>          
            </div>
        </nav>
        <div class="container-fluid">
        <div class="row" style="margin-top: 100px;">
                
            </div>
            <div class="row" style="background-color: gray;align-content: center;vertical-align: -webkit-baseline-middle;padding-bottom: 10px">
                <h1 style="text-align: center">Item Details</h1>
                <!--<h4 style="text-align: center">Welcome to IJSE Student Management System</h4>-->
                <div class="col-md-1" style="background-color: gray;position: static"></div>

                <div class="col-md-12" style="background-color: gray;position: static">
                    <div class="col-md-5" style="background-color: gray;position: static">
                        <form id="itemForm" action="ItemServlet" enctype="application/x-www-form-urlencoded" method="POST" class="form-group">
                        <div class="form-group">
                            <label for="itemCode">Item ID</label>
                            <input name="itemCode" type="text" class="form-control" id="itemCode" placeholder="ID">

                        </div>
                            
                        <div class="form-group">
                            <label for="itemDescription">Item Description</label>
                            <input name="itemDescription" type="text" class="form-control" id="itemDescription" placeholder="Description">
                        </div>
                        <div class="form-group">
                            <label for="itemQty">Qunty On Hand</label>
                            <input type="text" class="form-control" id="itemQty" placeholder="Qunty On Hand" name="itemQty">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputAmount">Unit Price (in Rupees)</label>
                            <div class="input-group">
                                <div class="input-group-addon">Rs</div>
                                <input type="number" class="form-control" id="itemPrice" placeholder="Unit Price (in Rupees)" name="itemPrice">
                                <div class="input-group-addon">.00</div>
                            </div>
                        </div>
                        <div style="text-align: center">
                            <button id="btnSave" type="button" class="btn btn-success">Save</button>
                            <button id="btnDelete" type="button" class="btn btn-danger">Delete</button>
                            <button id="btnUpdate" type="button" class="btn btn-warning">Update</button>
                            <button id="btnS" type="button" class="btn btn-info">Search</button>


                        </div>
                    </form>
                    </div>
                    <div class="col-md-7" style="background-color: gray;position: static; position: relative" >
                        <!--height: 300px;overflow: hidden;-->
                        <table class="table table-bordered table-striped">
                            <tr style="background-color: red"><th>Item ID</th><th>Description</th><th>Unit price</th><th>Qty on hand</tr>
                            <%
                                ApplicationContext ctx=(ApplicationContext) new ClassPathXmlApplicationContext("lk/ijse/thogakade/spring/AutoWiredSpringXMLConfig.xml");
                                ItemService itemService=(ItemService)ctx.getBean("itemService"); 
                                ArrayList<ItemDTO> all=itemService.getAll();
                                for(ItemDTO dto:all){                                   
                                
                                %>
                            <tr> 
                                <td><%=dto.getCode().toString()%></td>
                                <td><%=dto.getDescription()%></td>
                                <td><%=dto.getUnitPrice()%></td>
                                <td><%=dto.getQtyOnHand()%></td>
                            </tr>
                            <%                                
                                }
                             %>
                            
                        </table>
                    </div>
                </div>
                
                <div class="col-md-1" style="background-color: gray;position: static"></div>

            </div>
            <div class="row ">

            </div>
        </div>
            <footer class="footer" style="background: black;height: 100px;color: wheat;margin-top: 120px;text-align:center ">Developed by : Lahiru Muthumal</footer>
    
    </body>
</html>
