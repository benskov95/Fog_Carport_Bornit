package FunctionLayer;

import DBAccess.OrderMapper;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderFacade {

    public static int insertOrder(Customer customer, Order order) {
       return OrderMapper.insertOrder(customer, order);
    }

    public static int insertOrderForExistingCustomer(Order order) throws SQLException, ClassNotFoundException {
        return OrderMapper.insertOrderForExistingCustomer(order);
    }

    public static Order getMyOrder(int orderId, int phone) throws LoginSampleException, SQLException, ClassNotFoundException {
        return OrderMapper.getMyOrder(orderId, phone);
    }

    public static Order getOrderForWarehouse(int orderId) throws LoginSampleException, SQLException, ClassNotFoundException {
        return OrderMapper.getOrderForWarehouse(orderId);
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
        return OrderMapper.getAllOrdersByStatus(status_id);
    }

    public static void updateStatus (int order_id, int status_id) throws LoginSampleException {
        OrderMapper.updateStatus(order_id,status_id);
    }
    public static void updateTotalPrice(int order_id, int totalPrice) {
        OrderMapper.updateTotalPrice (order_id, totalPrice);
    }
}
