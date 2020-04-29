package FunctionLayer;

public class Calculator {

    public void type1Calc(int length, int width) {
        int numberOfBeams = 2;
        int numberOfPerforatedBands = 2;
        int numberOfFasciaScrewPacks = 1;

        calcNumberOfPosts(length, width);
        calcNumberOfRafters(length);
        calcNumberOfFascia(width, true, false); // Understernbrædder til for og bagende.
        calcNumberOfFascia(length, true, true); // Understernbrædder til siderne.
        calcNumberOfFascia(width, false, false); // Oversternbrædder til forenden.
        calcNumberOfFascia(length, true, true); // Oversternbrædder til siderne.
        calcNumberOfFascia(width, false, false); // Vandbræt til front.
        calcNumberOfFascia(length, true, true); // Vandbræt til siderne.
        calcNumberOfBottomScrewPacks(0); // <-- Placeholder værdi
        calcNumberOfUniversalBrackets(0); // <-- Placeholder værdi
        calcNumberOfBracketScrewPacks(0); // <-- Placeholder værdi
        calcNumberOfCarriageBolts(0, 0); // <-- Placeholder værdi
        calcNumberOfSquareWashers(0); // <-- Placeholder værdi

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

    public int calcNumberOfBottomScrewPacks(int numberOfRoofPlates) { // Bundskruepakker.
        return numberOfRoofPlates / 4; // For hver 4 tagplader skal der 1 pakke bundskruer til.

    }

    public int calcNumberOfUniversalBrackets(int numberOfRafters) { // Universal (beslag).
        // Setter metoder til at sætte antal af højre og venstre beslag.
        return  numberOfRafters;
    }

    public int calcNumberOfBracketScrewPacks(int numberOfUniversalBrackets) { // Skruer til stern og vandbræt.
        return (numberOfUniversalBrackets / 10) + 1; // For hver 10 beslag skal der 1 pakke skruer til.
                                                     // En ekstra pakke følger med til hulbånd + hvis der er nedrundet.
    }

    public int calcNumberOfCarriageBolts(int numberOfPosts, int numberOfBeams) { // Bræddebolte.
        return numberOfBeams + numberOfPosts;
    }

    public int calcNumberOfSquareWashers(int numberOfPosts) {
        return numberOfPosts * 2; // Der skal 2 firkantskiver til hver stolpe (IKKE inklusive stolper til skur).
    }

}
