package FunctionLayer;

public class Order {

    int carport_id;
    int carport_width;
    int carport_length;
    int shed_width;
    int shed_length;
    int phone;
    int status_id;

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getCarport_id() {
        return carport_id;
    }

    public void setCarport_id(int carport_id) {
        this.carport_id = carport_id;
    }

    public int getCarport_width() {
        return carport_width;
    }

    public void setCarport_width(int carport_width) {
        this.carport_width = carport_width;
    }

    public int getCarport_length() {
        return carport_length;
    }

    public void setCarport_length(int carport_length) {
        this.carport_length = carport_length;
    }

    public int getShed_width() {
        return shed_width;
    }

    public void setShed_width(int shed_width) {
        this.shed_width = shed_width;
    }

    public int getShed_length() {
        return shed_length;
    }

    public void setShed_length(int shed_length) {
        this.shed_length = shed_length;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone_id(int phone) {
        this.phone = phone;
    }



    public Order(int carport_id, int carport_width, int carport_length, int shed_width, int shed_length, int phone, int status_id) {
        this.carport_id = carport_id;
        this.carport_width = carport_width;
        this.carport_length = carport_length;
        this.shed_width = shed_width;
        this.shed_length = shed_length;
        this.phone = phone;
        this.status_id = status_id;
    }
}
