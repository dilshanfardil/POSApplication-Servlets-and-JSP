$(document).ready(function (){
    
    $("#btnSave").click(function () {          
        $.ajax("ItemServlet",{
            accepts: {mycustomtype:"*/*"},
            data:{
                action:"save",
                itemCode: document.getElementById("itemCode").value,
                itemDescription:document.getElementById("itemDescription").value,
                itemPrice:document.getElementById("itemPrice").value,
                itemQty:document.getElementById("itemQty").value
            },
            method:"POST"

        }).done(function (responce){
            //$("#txtLoad").val(responce);
            var message=JSON.parse(responce);
//            alert(message);
        }); 
        location.reload();
       });

    $("#btnDelete").click(function () {
        $.ajax("ItemServlet",{
            accepts: {mycustomtype:"*/*"},
            data:{
                action:"delete",
                itemCode: document.getElementById("itemCode").value               
            },
            method:"POST"

        }).done(function (responce){
            var message=JSON.parse(responce);
            alert(message);
        });  
        location.reload();
    });

    $("#btnUpdate").click(function () {
        $.ajax("ItemServlet",{
            accepts: {mycustomtype:"*/*"},
            data:{
                action:"update",
                itemCode: document.getElementById("itemCode").value,
                itemDescription:document.getElementById("itemDescription").value,
                itemPrice:document.getElementById("itemPrice").value,
                itemQty:document.getElementById("itemQty").value
            },
            method:"POST"

        }).done(function (responce){
            var message=JSON.parse(responce);
            alert(message);
        });
        location.reload();
    });
    $("#btnS").click(function () {
        $.ajax("ItemServlet",{
            accepts: {mycustomtype:"*/*"},
            data:{
                action:"search",
                itemCode: document.getElementById("itemCode").value                
            },
            method:"POST"

        }).done(function (responce){
            var message=JSON.parse(responce);
            $("#itemDescription").val(message.description);
            $("#itemQty").val(message.qtyOnHand);
            $("#itemPrice").val(message.price);
            
            alert(message.id+"  "+message.description+" "+message.qtyOnHand);
        });
        });
});


