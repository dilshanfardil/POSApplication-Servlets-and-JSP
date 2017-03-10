/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.service.custom.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.ijse.thogakade.business.custom.OrderBO;
import lk.ijse.thogakade.business.custom.impl.OrderBOImpl;
import lk.ijse.thogakade.dto.OrderDTO;
import lk.ijse.thogakade.dto.OrderDetailDTO;
import lk.ijse.thogakade.observers.Observer;
import lk.ijse.thogakade.service.custom.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author student
 */
public class OrderServiceImpl implements OrderService{
    private OrderBO bo;
    private static ArrayList<Observer> alObservers = new ArrayList<>();
    
    @Autowired
    public OrderServiceImpl(OrderBOImpl bo){
        this.bo=bo;
    }

    /**
     *
     * @param dto
     * @param arList
     * @return
     */
    @Override
    public boolean add(OrderDTO dto,ArrayList<OrderDetailDTO> arList) {
        try {            
            boolean add = bo.add(dto, arList);
            notifyAllObservers();
            return add;
        } catch (Exception ex) {
            Logger.getLogger(OrderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public OrderDTO get(OrderDTO dto) {
        try {
            return bo.get(dto);
        } catch (Exception ex) {
            Logger.getLogger(OrderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        }

    @Override
    public ArrayList<OrderDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(OrderDTO dto){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public boolean update(OrderDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean delete(OrderDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void registerObserver(Observer observer) throws RemoteException {
        alObservers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) throws RemoteException {
        alObservers.remove(observer);
    }

    @Override
    public void notifyAllObservers() throws RemoteException {
        for (Observer alObserver : alObservers) {
            alObserver.update();
        }
    }
  

    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public net.sf.jasperreports.engine.JasperPrint print() throws RemoteException {
        try {
            return bo.print();
        } catch (Exception ex) {
            Logger.getLogger(OrderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

   
}
