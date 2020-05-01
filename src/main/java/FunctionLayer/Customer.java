package FunctionLayer;

public class Customer {
    int phone;
    String name;
    String address;
    String email;
    String zip_code;

    public Customer(int phone, String name, String address, String email, String zip_code) {
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.email = email;
        this.zip_code = zip_code;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }



}
