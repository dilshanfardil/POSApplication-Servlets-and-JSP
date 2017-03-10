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
import lk.ijse.thogakade.business.custom.ItemBO;
import lk.ijse.thogakade.business.custom.impl.ItemBOImpl;
import lk.ijse.thogakade.dto.ItemDTO;
import lk.ijse.thogakade.observers.Observer;
import lk.ijse.thogakade.service.custom.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author lahiru
 */
public class ItemServiceImpl implements ItemService {

    private ItemBO bo;
    private static ArrayList<Observer> alObservers = new ArrayList<>();
    @Autowired
    public ItemServiceImpl(ItemBOImpl itemBO ) {
        this.bo=itemBO;
    }

    @Override
    public boolean add(ItemDTO dto) {
        try {
            boolean add = bo.add(dto);
            notifyAllObservers();
            return add;
        } catch (Exception ex) {
            Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ItemDTO get(ItemDTO dto) throws RemoteException {
        try {
            return (ItemDTO) bo.get(dto);
        } catch (Exception ex) {
            Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<ItemDTO> getAll() {
        try {
            return bo.getAll();
        } catch (Exception ex) {
            System.out.println("Awlak wage");
            Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean update(ItemDTO dto) {
        try {
            boolean update = bo.update(dto);
            notifyAllObservers();
            return update;
        } catch (Exception ex) {
            Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Boolean delete(ItemDTO dto) {
        try {
            boolean delete = bo.delete(dto);
            notifyAllObservers();
            return delete;
        } catch (Exception ex) {
            Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            return bo.print();
        } catch (Exception ex) {
            Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    return null;
    }
    

}
