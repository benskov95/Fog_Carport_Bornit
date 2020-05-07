package FunctionLayer;

public class Employee {


    private int employee_id;
    private String password;
    private int roleId;
    private String role;

    public Employee(int employee_id, String password, int roleId) {
        this.employee_id = employee_id;
        this.password = password;
        this.roleId = roleId;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
