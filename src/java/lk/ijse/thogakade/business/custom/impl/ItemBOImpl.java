/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.business.custom.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.thogakade.business.custom.ItemBO;
import lk.ijse.thogakade.dao.ConnectionFactory;
import lk.ijse.thogakade.dao.SuperDAO;
import lk.ijse.thogakade.dao.custom.ItemDAO;
import lk.ijse.thogakade.dao.custom.impl.ItemDAOImpl;
import lk.ijse.thogakade.dto.ItemDTO;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author lahiru
 */
public class ItemBOImpl implements ItemBO {

    private SuperDAO dao;
    private Connection connection;
    @Autowired
    public ItemBOImpl(ItemDAOImpl idao) {
        this.dao=idao;
    }

    @Override
    public ItemDTO get(ItemDTO dto) throws SQLException, Exception {

        connection = ConnectionFactory.getInstance().getConnection();
        dao.setConnection(connection);
        ItemDTO get = (ItemDTO) dao.get(dto);
        connection.close();
        return get;

    }

    @Override
    public ArrayList<ItemDTO> getAll() throws Exception {
        connection = ConnectionFactory.getInstance().getConnection();
        System.out.println("came to getALL");
            
        dao.setConnection(connection);        
        ArrayList all = dao.getAll();
        connection.close();
        return all;

    }

    @Override
    public boolean add(ItemDTO dto) throws Exception {
        connection = ConnectionFactory.getInstance().getConnection();
        dao.setConnection(connection);
        boolean add = dao.add(dto);
        connection.close();
        return add;
    }

    @Override
    public boolean update(ItemDTO dto) throws Exception {
        connection = ConnectionFactory.getInstance().getConnection();
        dao.setConnection(connection);
        boolean update = dao.update(dto);
        connection.close();
        return update;
    }

    @Override
    public boolean delete(ItemDTO dto) throws Exception {
        connection = ConnectionFactory.getInstance().getConnection();
        dao.setConnection(connection);
        boolean delete = dao.delete(dto);
        connection.close();
        return delete;
    }

    @Override
    public JasperPrint print() throws Exception {
        connection = ConnectionFactory.getInstance().getConnection();
        dao.setConnection(connection);
       
        JasperPrint print = dao.print();
        connection.close();
        return print;
    }

}
