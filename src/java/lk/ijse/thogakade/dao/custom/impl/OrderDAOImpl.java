/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.thogakade.dao.custom.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import lk.ijse.thogakade.dao.custom.OrderDAO;
import lk.ijse.thogakade.dto.OrderDTO;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author student
 */
public class OrderDAOImpl implements OrderDAO {

    private final String TABLE_NAME = "Orders";

    private Connection connection = null;
    private String orderId="D003";
    public void setOrderID(String id){
        this.orderId=id;
    }
    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;

    }

//    @Override
//    public boolean add(OrderDTO t) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    @Override
//    public boolean update(OrderDTO dto) throws SQLException {
//        PreparedStatement pst = connection.prepareStatement("update orders set date=?,customerId=?  where id=? ");
//        pst.setObject(3, dto.getId());
//        pst.setObject(1, dto.getDate());
//        pst.setObject(2, dto.getCustomerId());
//        int result = pst.executeUpdate();
//        return (result > 0);
//    }
//
//    @Override
//    public boolean delete(OrderDTO t) throws SQLException {
//        String id = t.getCustomerId();
//        String sql = "DELETE FROM Orders WHERE id = ? ";
//        PreparedStatement pst = connection.prepareStatement(sql);
//        pst.setObject(1, id);
//        int result = pst.executeUpdate();
//        return (result > 0);
//    }
    @Override
    public ArrayList<OrderDTO> getAll() throws SQLException {
        ArrayList<OrderDTO> alOrders = new ArrayList<>();
        String sql = "select * from Orders";
        Statement stm = connection.createStatement();
        ResultSet rset = stm.executeQuery(sql);

        while (rset.next()) {
            String id = rset.getString(1);
            Date date = rset.getDate(2);
            String custId = rset.getString(3);
            OrderDTO order = new OrderDTO(id, date, custId);
            alOrders.add(order);
        }

        return alOrders;
    }

    @Override
    public OrderDTO get(OrderDTO t) throws SQLException {
        String id = t.getCustomerId();

        OrderDTO order = null;
        String sql = "SELECT * FROM orders WHERE id = '" + id + "' ";
        Statement stm = connection.createStatement();
        ResultSet rset = stm.executeQuery(sql);
        if (rset.next()) {
            order = new OrderDTO(rset.getString(1), rset.getDate(2), rset.getString(3));

        }
        return order;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public JasperPrint print() throws Exception {
        JasperReport jasperreport;
        HashMap<String, Object> hashMap = new HashMap<>();
        System.out.println("order id in dao"+orderId);
        hashMap.put("orderId", orderId);
        jasperreport = (JasperReport) JRLoader.loadObject(CustomerDAOImpl.class.getResourceAsStream("/lk/ijse/thogakade/Jasper/reports/Invoice.jasper"));
        JasperPrint filledreport = JasperFillManager.fillReport(jasperreport,hashMap , connection);
        return filledreport;
    }
}
