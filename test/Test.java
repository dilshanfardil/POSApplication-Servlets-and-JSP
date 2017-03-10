
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import lk.ijse.thogakade.dto.CustomerDTO;
import lk.ijse.thogakade.dto.ItemDTO;
import lk.ijse.thogakade.service.custom.CustomerService;
import lk.ijse.thogakade.service.custom.impl.ItemServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lahiru-PC
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        
//        ApplicationContext ctx;
//        ctx = (ApplicationContext) new ClassPathXmlApplicationContext("lk/ijse/thogakade/spring/AutoWiredSpringXMLConfig.xml");
//        ItemServiceImpl bean = (ItemServiceImpl) ctx.getBean("itemService");
//        ArrayList<ItemDTO> all = bean.getAll();
//        System.out.println(all.size());
        System.out.println(new Date());
    }
    
}
//$("#btnDelete").click(function () {
//                                    $.ajax({
//                                        accepts: {
//                                            mycustomtype: 'text/html'
//                                        },
//                                        url: "WebServlet?action=delete?id="+$("#custId").val(),
//                                        context: document.body
//                                    }).done(function (data) {
//                                        if (data === true){
//                                            alert("Succes..."+$("#custId").val());
//                                        }else{
//                                            alert("Something went wrong...")
//                                        }
//                                    });
//                                });
//                                
//                                $("#btnUpdate").click(function () {
//                                    $.ajax({
//                                        accepts: {
//                                            mycustomtype: 'text/html'
//                                        },
//                                        url: "WebServlet?action=update",
//                                        context: document.body
//                                    }).done(function (data) {
//                                        if (data === true){
//                                            alert("Succes...");
//                                        }else{
//                                            alert("Something went wrong...")
//                                        }
//                                    });
//                                });