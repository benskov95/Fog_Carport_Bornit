package FunctionLayer;

import DBAccess.OrderMapper;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderFacade {

    public static int insertOrder(Customer customer, Order order) {
       return OrderMapper.insertOrder(customer, order);
    }

    public static Order getMyOrder(int orderId, int phone) throws LoginSampleException, SQLException, ClassNotFoundException {
        return OrderMapper.getMyOrder(orderId, phone);
    }

    public static void deleteOrder(int orderId) throws LoginSampleException, SQLException, ClassNotFoundException {
        OrderMapper.deleteOrder(orderId);
    }

    public static String getOrderStatus(int statusId) throws SQLException, ClassNotFoundException {
        return OrderMapper.getOrderStatus(statusId);
    }

    public static String getCarportType(int typeId) throws SQLException, ClassNotFoundException {
        return OrderMapper.getCarportType(typeId);
    }
    public static ArrayList<Order> getAllOrdersByStatusId (int status_id) throws SQLException, ClassNotFoundException {
        return OrderMapper.getAllOrderByStatus(status_id);
    }
}
