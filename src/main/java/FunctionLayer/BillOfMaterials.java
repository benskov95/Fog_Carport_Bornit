package FunctionLayer;

import java.util.ArrayList;

public class BillOfMaterials {

    private int orderId;
    private ArrayList<Material> materials;
    private int finalPrice;

    public BillOfMaterials(int orderId, ArrayList<Material> materials, int finalPrice) {
        this.orderId = orderId;
        this.materials = materials;
        this.finalPrice = finalPrice;
    }

    public int calcFinalPrice() {
        for (Material material : this.materials) {
            this.finalPrice += material.getSum();
        }
        return finalPrice;
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

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }
}
