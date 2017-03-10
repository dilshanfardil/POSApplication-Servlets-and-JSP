/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.business.custom.impl;

import java.sql.Connection;
import java.util.ArrayList;
import lk.ijse.thogakade.business.custom.OrderBO;
import lk.ijse.thogakade.dao.*;
import lk.ijse.thogakade.dao.custom.ItemDAO;
import lk.ijse.thogakade.dao.custom.OrderDetailDAO;
import lk.ijse.thogakade.dao.custom.impl.ItemDAOImpl;
import lk.ijse.thogakade.dao.custom.impl.OrderDAOImpl;
import lk.ijse.thogakade.dao.custom.impl.OrderDetailDAOImpl;
import lk.ijse.thogakade.dto.*;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author student
 */

public class OrderBOImpl implements OrderBO{
    private final SuperDAO daoI;
    private final OrderDAOImpl daoO;
    private final SuperDAO daoOD;
    @Autowired
    public OrderBOImpl(OrderDAOImpl daoO,ItemDAOImpl daoI,OrderDetailDAOImpl detailDAO){
        this.daoI= daoI;
        this.daoO=daoO;
        this.daoOD=detailDAO;
    }
    

    @Override
    public boolean add(OrderDTO dto,ArrayList<OrderDetailDTO> arList) throws Exception {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        daoI.setConnection(connection);
        daoO.setConnection(connection);
        daoOD.setConnection(connection);
        daoO.setOrderID(dto.getId());
        boolean result=daoO.add(dto);
        if(!result){
            
            connection.close();
            return result;
        }
        for(OrderDetailDTO odto:arList){
            
            result=result && daoOD.add( odto);
            if(!result) {
                connection.rollback();
                connection.setAutoCommit(result);
                connection.close();
                return result;
            }
            ItemDTO itemDTO=new ItemDTO(odto.getItemCode());
            itemDTO = (ItemDTO) daoI.get(itemDTO);
            int qty=itemDTO.getQtyOnHand()-odto.getQty();
            itemDTO.setQtyOnHand(qty);
            result=result && daoI.update(itemDTO);
            if(!result){
                connection.rollback();
                connection.setAutoCommit(result);
                connection.close();
                return result;
            }           
            
        }
        connection.commit();
        connection.setAutoCommit(result);
        connection.close();
        return result;
        
        
    }

    @Override
    public OrderDTO get(OrderDTO dto) throws Exception {

        Connection connection = ConnectionFactory.getInstance().getConnection();
        daoO.setConnection(connection);
        
        ArrayList<OrderDTO> al=daoO.getAll();
        OrderDTO a=al.get(al.size()-1);
        String lastId=a.getId();
        int x=Integer.parseInt(lastId.substring(1))+1;
        if(x<10){
            dto.setId("D00"+x);
        }else if(x<100){
            dto.setId("D0"+x);            
        }else{
            dto.setId("D"+x);
        }
        
        connection.close();
        System.out.println(dto.getId());
        return dto;
    }

    

    @Override
    public boolean update(OrderDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(OrderDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JasperPrint print() throws Exception {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        daoO.setConnection(connection);
       
        JasperPrint print = daoO.print();
        connection.close();
        return print;

    }

    

    

    
    
}
