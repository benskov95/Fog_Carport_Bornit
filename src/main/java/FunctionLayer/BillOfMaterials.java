package FunctionLayer;

import java.util.ArrayList;

public class BillOfMaterials {

    private int orderId;
    private ArrayList<Material> materials;
    private int totalPrice;

    public BillOfMaterials(ArrayList<Material> materials) {
        this.materials = materials;
        calcTotalPrice();
    }

    public BillOfMaterials() {
    }

    public void calcTotalPrice() {
        for (Material material : this.materials) {
            this.totalPrice += material.getSum();
        }
        this.setTotalPrice(this.totalPrice);
    }

    public void addMaterial(Material material) {
        if (materials == null) {
            materials = new ArrayList<>();
        }
        this.materials.add(material);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public ArrayList<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(ArrayList<Material> materials) {
        this.materials = materials;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
