var itemCode="";
var total=0;
var customerId="";
function selectItem(code)
{
    itemCode=code;
    $.ajax("PlaceOrderServlet",{
           accepts: {mycustomtype:"*/*"},
           data:{
               action:"getItem",
               itemCode: code              
           },
           method:"POST"

       }).done(function (responce){
           var message=JSON.parse(responce);
           $("#itemDescription").val(message.description);
           $("#qtyhand").val(message.qtyOnHand);
           $("#itemPrice").val(message.price);

//           alert(message.id+"  "+message.description+" "+message.qtyOnHand);
       });
}


function selectCustomer(code)
{
    this.customerId=code;
    $.ajax("PlaceOrderServlet",{
           accepts: {mycustomtype:"*/*"},
           data:{
               action:"getCustomer",
               custId: code              
           },
           method:"POST"

       }).done(function (responce){
           var message=JSON.parse(responce);
           $("#custName").val(message.name);
           
       });
}


function addTable(code)
{               
    var table = document.getElementById("table");
    var row = table.insertRow(1);
    var code = row.insertCell(0);
    var desc = row.insertCell(1);
    var unnitprice = row.insertCell(2);
    var qty = row.insertCell(3);
    var ammount = row.insertCell(4);
    var qtyV=document.getElementById("itemQty").value;
    var p=document.getElementById("itemPrice").value;
    var ammountValue=qtyV*p;
    total+=ammountValue;
    
    desc.innerHTML = document.getElementById("itemDescription").value;
    unnitprice.innerHTML = document.getElementById("itemPrice").value;
    qty.innerHTML =qtyV ;
    code.innerHTML = itemCode; 
    ammount.innerHTML=ammountValue;
    $("#totalAmount").val(total);
    
}

function getOrderId(){
    $.ajax("PlaceOrderServlet",{
           accepts: {mycustomtype:"*/*"},
           data:{
               action:"getOrderId"                             
           },
           method:"POST"

       }).done(function (responce){
//           alert(responce);
           $("#orderId").val(responce);
       });
}
    function createJson(){
        var rowCount = document.getElementById('table').rows.length;
        //alert(rowCount);
        //for(int i=1;)
    }
$(document).ready(function (){
    getOrderId();
    var d = new Date();
    //alert(d.toDateString());
    $("#date").val(d.toDateString());
    $("#btnSave").click(function (){
        var table = $('#table').tableToJSON();
        alert(JSON.stringify(table)+"\n"+customerId+"\n"+document.getElementById("orderId").value);
        $.ajax("PlaceOrderServlet",{
           accepts: {mycustomtype:"*/*"},
           data:{
               action:"save",
               custId: customerId,
               orderId:document.getElementById("orderId").value,
               date:d,
               orderDetail:JSON.stringify(table)
           },
           method:"POST"

       }).done(function (responce){
            alert(responce);
//           var message=JSON.parse(responce);
//           $("#custName").val(message.name);
           if(responce){
               location.reload();
           }
       });
        
    });
    $("#btnExit").click(function(){
        window.close("http://localhost:8080/ThogakadeWeb/PlaceOrder.jsp");
    });
    
});



