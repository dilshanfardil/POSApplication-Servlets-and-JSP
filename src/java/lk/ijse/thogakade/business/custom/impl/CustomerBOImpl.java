/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.business.custom.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.thogakade.business.custom.CustomerBO;
import lk.ijse.thogakade.dao.ConnectionFactory;

import lk.ijse.thogakade.dao.custom.CustomerDAO;
import lk.ijse.thogakade.dto.CustomerDTO;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author student
 */
public class CustomerBOImpl implements CustomerBO {

    private CustomerDAO customerDAO;
    private Connection connection;

    @Autowired
    public CustomerBOImpl(CustomerDAO cdao) {        
        this.customerDAO=cdao;      

    }

    /**
     *
     * @param customer
     * @return
     * @throws Exception
     */
    @Override
    public boolean add(CustomerDTO customer) throws Exception {
        connection = ConnectionFactory.getInstance().getConnection();

        customerDAO.setConnection(connection);
        boolean add = customerDAO.add(customer);
        connection.close();
        return add;
    }

    @Override
    public CustomerDTO get(CustomerDTO dto) throws SQLException, Exception {
        connection = ConnectionFactory.getInstance().getConnection();

        customerDAO.setConnection(connection);

        CustomerDTO get = customerDAO.get(dto);
        connection.close();
        return get;

    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws Exception {
        System.out.println("Came to BO");
        Connection connection = ConnectionFactory.getInstance().getConnection();
        
        customerDAO.setConnection(connection);

        ArrayList<CustomerDTO> all = customerDAO.getAll();
        connection.close();
        return all;

    }

    @Override
    public boolean update(CustomerDTO dto) throws Exception {
        connection = ConnectionFactory.getInstance().getConnection();
        customerDAO.setConnection(connection);
        boolean update = customerDAO.update(dto);
        connection.close();
        return update;
    }

    @Override
    public boolean delete(CustomerDTO dto) throws Exception {
        connection = ConnectionFactory.getInstance().getConnection();
        customerDAO.setConnection(connection);
        boolean delete = customerDAO.delete(dto);
        connection.close();
        
        return delete;
    }

    @Override
    public JasperPrint print() throws Exception {
        connection = ConnectionFactory.getInstance().getConnection();
        customerDAO.setConnection(connection);

        JasperPrint print = customerDAO.print();
        connection.close();
        return print;
    }

}
