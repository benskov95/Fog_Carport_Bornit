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

        while (length >= 55) {
            numberOfRafters++;
            length -= 55;
        }

        return numberOfRafters;
    }

}
