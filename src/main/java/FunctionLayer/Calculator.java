package FunctionLayer;

public class Calculator {

    public void type1Calc(int length, int width) {
        int numberOfBeams = 2;
        int numberOfPerforatedBands = 2;

        calcNumberOfPosts(length, width);
        calcNumberOfRafters(length);

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
