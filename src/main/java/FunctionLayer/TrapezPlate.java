package FunctionLayer;

public class TrapezPlate {


    private int length;
    private int amount;
    private int remainderRelativeToLength;
    private int excessRelativeToLength;

    public TrapezPlate(int length, int amount, int remainderRelativeToLength, int excessRelativeToLength) {
        this.length = length;
        this.amount = amount;
        this.remainderRelativeToLength = remainderRelativeToLength;
        this.excessRelativeToLength = excessRelativeToLength;
    }

    public TrapezPlate() {
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getRemainderRelativeToLength() {
        return remainderRelativeToLength;
    }

    public void setRemainderRelativeToLength(int remainderRelativeToLength) {
        this.remainderRelativeToLength = remainderRelativeToLength;
    }

    public int getExcessRelativeToLength() {
        return excessRelativeToLength;
    }

    public void setExcessRelativeToLength(int excessRelativeToLength) {
        this.excessRelativeToLength = excessRelativeToLength;
    }
}
