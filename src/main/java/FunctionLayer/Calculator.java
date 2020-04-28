package FunctionLayer;

public class Calculator {

    public void type1Calc(int length, int width) {
        int numberOfBeams = 2;
        int numberOfPerforatedBands = 2;

        calcNumberOfPosts(length, width);
        calcNumberOfRafters(length);
        calcNumberOfFascia(width, true, false); // Understernbrædder til for og bagende.
        calcNumberOfFascia(length, true, true); // Understernbrædder til siderne.
        calcNumberOfFascia(width, false, false); // Oversternbrædder til forenden.
        calcNumberOfFascia(length, true, true); // Oversternbrædder til siderne.
        calcNumberOfFascia(width, false, false); // Vandbræt til front.
        calcNumberOfFascia(length, true, true); // Vandbræt til siderne.

    }

    public int calcNumberOfFascia(int measurement, boolean doMultiply, boolean isSides) { // doMultiply bruges primært til at gange siderne på carporten.

        double fasciaSize;

        if (isSides) {
            fasciaSize = 540;
        } else {
            fasciaSize = 360;
        }

        int fasciaNumber = (int) Math.ceil(measurement/ fasciaSize);

        if (doMultiply) {
            return fasciaNumber * 2;
        } else{
            return fasciaNumber;
        }

    }

    public int calcNumberOfPosts(int length, int width) {
        int numberOfPosts = 4;

        if (length > 600) {
            numberOfPosts += 2;
        }
        if (width > 600) {
            numberOfPosts += 1;
        }

        return numberOfPosts;
    }

    public int calcNumberOfRafters(int length) {
        int numberOfRafters = 1;
        int space = calcSpaceBetweenRafters(length);

        while (length >= space) {
            numberOfRafters++;
            length -= space;
        }
        return numberOfRafters;
    }

    public int calcSpaceBetweenRafters(int length) {
        int space = 45;
        int initialLength = length;

        while (length >= 0) {
            length -= space;
            if (length < 0) {
                length = initialLength;
                space++;
            }
            if (length == 0) {
                break;
            }
        }
        return space;

    }

}
