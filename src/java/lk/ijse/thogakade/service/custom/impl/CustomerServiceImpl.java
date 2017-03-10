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

import lk.ijse.thogakade.business.custom.CustomerBO;
import lk.ijse.thogakade.dto.CustomerDTO;
import lk.ijse.thogakade.observers.Observer;
import lk.ijse.thogakade.service.custom.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author student
 */
public class CustomerServiceImpl implements CustomerService{
    private CustomerBO customerBO;
    
    private static ArrayList<Observer> alObservers = new ArrayList<>();
    @Autowired
    public CustomerServiceImpl(CustomerBO customerBO){
        this.customerBO = customerBO;
        System.out.println(customerBO);
    }
    
    
    @Override
    public boolean add(CustomerDTO customer) {
        try {
            boolean result = customerBO.add(customer);
            //notifyAllObservers();
            return result;
        } catch (Exception ex) {
            Logger.getLogger(CustomerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public CustomerDTO get(CustomerDTO dto) throws RemoteException {
        try {
            
            return customerBO.get(dto);
        } catch (Exception ex) {
            Logger.getLogger(CustomerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws RemoteException {
        
//        ArrayList<CustomerDTO> all = null;
//        boolean add;
//        add = all.add(new CustomerDTO("C001","Lahiru","Hikkaduwa",12452));
//          
        System.out.println("Hi service");
        try {           
            
            ArrayList<CustomerDTO> all=customerBO.getAll();
            
            return all;
            
        } catch (Exception ex) {
            Logger.getLogger(CustomerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Come to service");
//        return all;
            return null;
    }

    @Override
    public boolean update(CustomerDTO dto) {
        try {
            boolean update = customerBO.update(dto);
            notifyAllObservers();
            return update;
        } catch (Exception ex) {
            Logger.getLogger(CustomerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean delete(CustomerDTO dto) {
        try {
            boolean delete = customerBO.delete(dto);
            notifyAllObservers();
            
            return delete;
        } catch (Exception ex) {
            Logger.getLogger(CustomerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return false;
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

    

    @Override
    public net.sf.jasperreports.engine.JasperPrint print() throws RemoteException {
        try {
            return customerBO.print();
        } catch (Exception ex) {
            Logger.getLogger(CustomerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    

    
    
}
