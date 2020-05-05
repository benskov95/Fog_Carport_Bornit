package FunctionLayer;

import java.util.ArrayList;

public class Material {

    private int materialId;
    private String materialName;
    private int unitId;
    private String unit;
    private int price;
    private int materialSizeId;
    private int quantity;
    private int size;
    private int sizeId;
    private int carportPartId;
    private String carportPartDescription;
    private int sum;

    public Material(int materialId, String materialName, int unitId, int price, int materialSizeId, int quantity) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.unitId = unitId;
        this.price = price;
        this.materialSizeId = materialSizeId;
        this.quantity = quantity;
    }

    public Material(int materialId, int size, int quantity) {
        this.materialId = materialId;
        this.size = size;
        this.quantity = quantity;
    }

    public Material(int materialId, String materialName, int size, int quantity, int unitId, String carportPartDescription) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.size = size;
        this.quantity = quantity;
        this.unitId = unitId;
        this.carportPartDescription = carportPartDescription;
    }

    public Material() {
    }

    public void roofCarportPartIdHelper(int carportPartId, ArrayList<Material> materials) {
        for (Material material : materials) {
            if (material.getMaterialId() == 69) {
                material.setCarportPartId(carportPartId);
            }
        }
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMaterialSizeId() {
        return materialSizeId;
    }

    public void setMaterialSizeId(int materialSizeId) {
        this.materialSizeId = materialSizeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSum() {
        this.sum = this.quantity * this.price;
        return this.sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getCarportPartId() {
        return carportPartId;
    }

    public void setCarportPartId(int carportPartId) {
        this.carportPartId = carportPartId;
    }

    public String getCarportPartDescription() {
        return carportPartDescription;
    }

    public void setCarportPartDescription(String carportPartDescription) {
        this.carportPartDescription = carportPartDescription;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
