package FunctionLayer;

public class Material {

    private String materialName;
    private int unitId;
    private int price;
    private int materialSizeId;
    private int quantity;
    private int sum = quantity * price;

    public Material(String materialName, int unitId, int price, int materialSizeId, int quantity) {
        this.materialName = materialName;
        this.unitId = unitId;
        this.price = price;
        this.materialSizeId = materialSizeId;
        this.quantity = quantity;
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
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

}
