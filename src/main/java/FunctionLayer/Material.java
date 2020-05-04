package FunctionLayer;

public class Material {

    private int materialId;
    private String materialName;
    private int unitId;
    private int price;
    private int materialSizeId;
    private int quantity;
    private int size;
    private int sum = quantity * price;

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

    public Material() {
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }
}
