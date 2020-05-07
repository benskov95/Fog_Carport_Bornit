package FunctionLayer;

public class CarPortPart {

    private int carPortPartId;
    private int materialId;
    private String beskrivelse;
    private int carPortId;

    public CarPortPart(int carPortPartId, int materialId, String beskrivelse, int carPortId) {
        this.carPortPartId = carPortPartId;
        this.materialId = materialId;
        this.beskrivelse = beskrivelse;
        this.carPortId = carPortId;
    }

    public int getCarPortPartId() {
        return carPortPartId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public int getCarPortId() {
        return carPortId;
    }
}
