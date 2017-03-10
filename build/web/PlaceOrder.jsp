<%-- 
    Document   : PlaceOrder
    Created on : Feb 27, 2017, 9:40:22 AM
    Author     : Lahiru-PC
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="lk.ijse.thogakade.dto.CustomerDTO"%>
<%@page import="lk.ijse.thogakade.service.custom.impl.CustomerServiceImpl"%>
<%@page import="lk.ijse.thogakade.dto.ItemDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="lk.ijse.thogakade.service.custom.impl.ItemServiceImpl"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="_css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="_css/style.css">
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" type="text/javascript"></script>-->
        <script src="_js/jquery.min.js" type="text/javascript"></script>
        <!--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>-->
        <script src="_js/bootstrap.min.js" type="text/javascript"></script>
        <script src="_js/jquery.tabletojson.js" type="text/javascript"></script>
        <script src="_js/place.order.js" type="text/javascript"></script>
        <style type="text/css">
            .colo{
                background-color: #EFE9ED;
            }
        </style>

        <title>JSP Page</title>
    </head>
    <body class="container" style="background-color: chartreuse">
        <%
        
            ApplicationContext ctx = (ApplicationContext) new ClassPathXmlApplicationContext("lk/ijse/thogakade/spring/AutoWiredSpringXMLConfig.xml");
            ItemServiceImpl itemservice = (ItemServiceImpl) ctx.getBean("itemService");
            ArrayList<ItemDTO> itemlist = new ArrayList();
            itemlist = itemservice.getAll();
        
            CustomerServiceImpl custservice =(CustomerServiceImpl) ctx.getBean("customerService");
            ArrayList<CustomerDTO> custlist =new ArrayList();
            custlist = custservice.getAll();
        %>
         <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
               <ul class="nav nav-pills">
                   <li role="presentation" id="navCustomer" ><a href="index.html" >Customer</a></li>
                   <li role="presentation" id="navItem" ><a href="ItemJSP.jsp">Item</a></li>
                   <li role="presentation" id="navPlaceOrder" class="active"><a href="PlaceOrder.jsp" >Place Order</a></li>
              </ul>          
            </div>
        </nav>
        
        <div class="container-fluid">
            <div class="row" style="margin-top: 100px;"></div>
            <div class="row colo" style="align-content: center;vertical-align: -webkit-baseline-middle;padding-bottom: 10px">
                <h1 style="text-align: center ; color:#000000" >Plase Order Form</h1>
                <!--<h4 style="text-align: center">Welcome to IJSE Student Management System</h4>-->
                <div class="col-md-2 colo" style="position: static"></div>
                <div class="col-md-8 colo" style="position: static">
                    <form action="PlaceOrderServlet" enctype="application/x-www-form-urlencoded" method="POST" class="form-group">


                        <div class="col-md-2 colo" style="position: static">
                            <div class="form-group">
                                <label for="customerId">Order ID</label>
                            </div>
                        </div>

                        <div class="col-md-4 colo" style="position: static">
                            <div class="form-group">
                                <input name="orderId" type="text" class="form-control" id="orderId" placeholder="ID">
                            </div>
                        </div>


                        <div class="col-md-2 colo" style="position: static">
                            <div class="form-group">                                
                                <label for="customerId">Date</label>
                            </div>
                        </div>


                        <div class="col-md-4 colo" style="position: static">
                            <div class="form-group">
                                <input name="date" type="text" class="form-control" id="date" placeholder="Date" >
                            </div>
                        </div>






                        <div class="row" style="margin-top: 4px"></div>
                        <div  style="position: static" class="panel panel-primary colo">
                            <div class="panel-heading">Customer Info</div>
                            <div class="panel-body">


                                <div class="col-md-2 colo" style="position: static">
                                    <div class="form-group">
                                        <label for="customerId">ID</label>
                                    </div>
                                </div>

                                <div class="col-md-4 colo" style="position: static">
                                    <div class="form-group">
                                        <select  class="form-control" id="code" onchange="selectCustomer(this.value )">
                                        <%                         
                                                for (CustomerDTO dto : custlist) {
                                                       String b=dto.getId();
                                            %>
                                            <option><%=b%> </option>
                                            <%
                                                }
                                            %>         
                                        </select>
                                        <!--<input name="custId" type="text" class="form-control" id="orderid" placeholder="ID">-->
                                    </div>
                                </div>


                                <div class="col-md-2 colo" style="position: static">
                                    <div class="form-group">
                                        <label for="customerId">Name</label>
                                    </div>
                                </div>


                                <div class="col-md-4 colo" style="position: static">
                                    <div class="form-group">
                                        <input name="custName" type="text" class="form-control" id="custName" placeholder="Name">
                                    </div>
                                </div>


                            </div>

                        </div>








                        <div class="row" style="margin-top: 4px"></div>
                        <div  style="position: static" class="colo panel panel-primary">
                            <div class="panel-heading">Item Info</div>
                            <div class="panel-body">




                                <div class="col-md-2 colo" style=" ;position: static">
                                    <div class="form-group">
                                        <label for="customerId">Code</label>
                                    </div>
                                </div>
                                <div class="col-md-4 colo" style="position: static">
                                    <div class="form-group">
                                        <select class="form-control" id="codeval" onchange="selectItem(this.value )">
                                            <%                         

                                                for (ItemDTO dto : itemlist) {
                                                       String a=dto.getCode();
                                            %>
                                            <option><%=a%> </option>
                                            <%
                                                }
                                            %>
                                        </select>

                                    </div>
                                </div>


                                <div class="col-md-2 colo" style="position: static">
                                    <div class="form-group">
                                        <label for="customerId">Description</label>
                                    </div>
                                </div>


                                <div class="col-md-4 colo" style="position: static">
                                    <div class="form-group">
                                        <input name="itemDescription" type="text" class="form-control" id="itemDescription" placeholder="Description"></input>
                                    </div>
                                </div>





                                <div class="col-md-2 colo" style="position: static">
                                    <div class="form-group">
                                        <label for="customerId">Unit Price</label>
                                    </div>
                                </div>

                                <div class="col-md-4 colo" style="position: static">
                                    <div class="form-group">
                                        <input name="itemPrice" type="text" class="form-control" id="itemPrice" placeholder="Unit Price">
                                    </div>
                                </div>


                                <div class="col-md-2 colo" style="position: static">
                                    <div class="form-group">
                                        <label for="customerId">Qty On Hand</label>
                                    </div>
                                </div>


                                <div class="col-md-4 colo" style="position: static">
                                    <div class="form-group">
                                        <input name="qtyhand" type="text" class="form-control" id="qtyhand" placeholder="Qty On Hand">
                                    </div>
                                </div>




                                <div class="col-md-6 colo" style="position: static"></div>
                                <!--                                <div class="col-md-4" style="background-color:#d0e1e1 ;position: static"></div>-->
                                <div class="col-md-2 colo" style="position: static">
                                    <div class="form-group">
                                        <label for="customerId">Qty</label>
                                    </div>
                                </div>
                                <div class="col-md-4 colo" style="position: static">
                                    <div class="form-group">
                                        <input name="itemQty" type="text" class="form-control" id="itemQty" placeholder="Qty">
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="row" style="margin-top: 4px"></div>
                        <div class="col-md-10 colo" style="position: static"></div>
                        <div  class="col-md-1" style="text-align: center">
                            <button type="button" class="btn btn-success" onclick="addTable(document.getElementById('codeval').value)" >ADD</button>
                        </div>
                        <div class="col-md-1" style="text-align: center">
                            <button type="submit" class="btn btn-success">REMOVE</button>
                        </div>





                        <table class="table table-bordered" id="table">
                            <thead>
                                <tr>
                                    <th>Code</th>
                                    <th>Description</th>
                                    <th>Unit Price</th>
                                    <th>Qty</th>
                                    <th>Amount</th>
                                </tr>
                            </thead>
                            <tbody>
<!--                                                           
                            </tbody>
                        </table>






                        <div class="col-md-6" style="background-color:#c2d6d6 ;position: static"></div>
                        <!--                                <div class="col-md-4" style="background-color:#d0e1e1 ;position: static"></div>-->
                        <div class="col-md-2 colo" style="position: static">
                            <div class="form-group">
                                <label for="customerId">Total Amount</label>
                            </div>
                        </div>
                        <div class="col-md-4 colo" style="position: static">
                            <div class="form-group">
                                <input name="totalAmount" type="text" class="form-control" id="totalAmount" placeholder="0.0" style="text-align: right">
                            </div>
                        </div>





                        <div class="col-md-7 colo" style="position: static"></div>
                            <!--<button type="submit" class="btn btn-success">REPORT</button>-->
                        </div>
                        <div class="col-md-1" style="text-align:  center; margin: 10px">
                            <button  type="button" class="btn btn-success" id="btnSave">SAVE</button>
                        </div>
                        <div class="col-md-1" style="text-align: center; margin: 10px">
                            <button type="button" class="btn btn-success" id="btnExit">EXIT</button>
                        </div>
                        
                    </form>
                </div>
                <div class="col-md-2"></div>
            </div>
            <div class="row ">
                
            </div>
        </div>
        <!--<footer class="footer" style="background: black;height: 100px;color: wheat;margin-top: 120px;text-align:center ">Developed by : Lahiru Muthumal</footer>-->    
    </body>
</html>
