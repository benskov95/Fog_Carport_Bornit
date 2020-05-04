package FunctionLayer;
import java.sql.SQLException;
import java.util.ArrayList;

public class Calculator {

    private int primaryRoofPlateLength;
    private int secondaryRoofPlateLength;

    public BillOfMaterials type1Calc(int length, int width, int orderId) throws SQLException, ClassNotFoundException {

        ArrayList<Material> materialHolder = new ArrayList<>();
        int finalPrice = 0;

        int numberOfBeams = 2;
        int numberOfPerforatedBands = 2;
        int numberOfFasciaScrewPacks = 1;

        int posts = calcNumberOfPosts(length, width);
        int rafters = calcNumberOfRafters(length);

        int underFasciaFrontBackQuantity = calcNumberOfFascia(width, true, false); // Understernbrædder til for og bagende.
        int underFasciaFrontBackLength = fasciaLength(false);

        int underFasciaSidesQuantity = calcNumberOfFascia(length, true, true); // Understernbrædder til siderne.
        int underFasciaSidesLength = fasciaLength(true);

        int overFasciaFrontQuantity = calcNumberOfFascia(width, false, false); // Oversternbrædder til forenden.
        int overFasciaFrontLength = fasciaLength(false);

        int overFasciaSidesQuantity = calcNumberOfFascia(length, true, true); // Oversternbrædder til siderne.
        int overFasciaSidesLength = fasciaLength(true);

        int waterFasciaFrontQuantity = calcNumberOfFascia(width, false, false); // Vandbræt til front.
        int waterFasciaFrontLength = fasciaLength(false);

        int waterFasciaSidesQuantity = calcNumberOfFascia(length, true, true); // Vandbræt til siderne.
        int waterFasciaSidesLength = fasciaLength(true);

        int plates = calcNumberOfTrapezPlates(width);
        int plateSizesAndQuantity = calcLengthOfTrapezPlates(length, width, MaterialFacade.getMaterialLengths(69));
        int bottomScrews = calcNumberOfBottomScrewPacks(plates);
        int brackets = calcNumberOfUniversalBrackets(rafters);
        int bracketScrews = calcNumberOfBracketScrewPacks(brackets);
        int carriageBolts = calcNumberOfCarriageBolts(posts, numberOfBeams);
        int squareWashers = calcNumberOfSquareWashers(posts);

        materialHolder.add(new Material(38, width, numberOfBeams));
        materialHolder.add(new Material(92, 0, numberOfPerforatedBands));
        materialHolder.add(new Material(95, 0, numberOfFasciaScrewPacks));
        materialHolder.add(new Material(46, 300, posts)); // Kun en højde?
        materialHolder.add(new Material(38, width, rafters));

        materialHolder.add(new Material(32, underFasciaFrontBackLength, underFasciaFrontBackQuantity));
        materialHolder.add(new Material(32, underFasciaSidesLength, underFasciaSidesQuantity));

        materialHolder.add(new Material(20, overFasciaFrontLength,  overFasciaFrontQuantity));
        materialHolder.add(new Material(20, overFasciaSidesLength, overFasciaSidesQuantity));

        materialHolder.add(new Material(1, waterFasciaFrontLength, waterFasciaFrontQuantity));
        materialHolder.add(new Material(1, waterFasciaSidesLength, waterFasciaSidesQuantity));

        materialHolder.add(new Material(69, primaryRoofPlateLength, plateSizesAndQuantity));
        if (secondaryRoofPlateLength != 0) {
            materialHolder.add(new Material(69, secondaryRoofPlateLength, plateSizesAndQuantity));
        }

        materialHolder.add(new Material(91, 0, bottomScrews));
        materialHolder.add(new Material(93, 0, brackets));
        materialHolder.add(new Material(94, 0, brackets));
        materialHolder.add(new Material(96, 0, bracketScrews));
        materialHolder.add(new Material(97, 0, carriageBolts));
        materialHolder.add(new Material(98, 0, squareWashers));

        CarportPartsFacade.getCarportPartIds(materialHolder, 1);
        CarportPartsFacade.getCarportPartDescriptions(materialHolder);
        MaterialFacade.setMaterialValues(materialHolder);
        MaterialFacade.setMaterialSizeIds(materialHolder);
        MaterialFacade.setLinkMaterialSizeIds(materialHolder);

        for (Material material : materialHolder) {
            finalPrice += material.getSum();
        }

        return new BillOfMaterials(orderId, materialHolder, finalPrice);
    }

    public int calcNumberOfTrapezPlates(int width) {

        boolean test = false;
        int count = 0;

        while (!test) {
            width -= 100;
            count++;
            if (width == 0) {
                test = true;
            }
            if (width < 100 && width != 0) {
                count++;
                test = true;
            }
        }
        return count;
    }


        public int calcLengthOfTrapezPlates(int length, int width, ArrayList<Integer> measurements){

        // todo - Skriver bare en todo for at gøre det her tydeligt: Da metoden potentielt
        // todo - kan regne flere værdier ud (hvis der bruges mere end én pladestørrelse)
        // todo - tænker jeg, at de skal settes - evt. på en variabel, der er erklæret i
        // todo - denne klase, som så kan bruges i type1Calc metoden når al styklisteinfo
        // todo - skal smides op til databasen.

            final int overhang = 5;
            final int overlap = 20;
            length += overhang;
            int initialLength = length;
            ArrayList<TrapezPlate> trapezPlates = new ArrayList<>();
            int numberOfPlates;
            int leftOverNumberOfPlates = 0;
            int count = 0;
            int remainder = 0;
            int excess = 0;
            int secondPlateLength = 0;
            int remainderPlateLength = 0;
            int smallestRemainder;
            TrapezPlate plateForComparison;
            TrapezPlate finalPlate = new TrapezPlate();

            for (Integer plateLength : measurements) {
                while (length > 0) {
                    if (length - plateLength == 0) {
                        remainder = 0;
                        count++;
                    }
                    if (length - plateLength < 0) {
                        remainder = length;

                        if (remainder == initialLength) {
                            excess = plateLength - length;
                            remainder = 0;
                        }
                    }

                    length -= plateLength;
                    count++;

                    if (length <= 0) {
                        count -= 1; // Trækker 1 fra for at tage højde for, at den går i minus før den når hertil.
                        length = initialLength;
                        remainder += overlap;
                        trapezPlates.add(new TrapezPlate(plateLength, count, remainder, excess));
                        count = 0;
                        break;
                    }
                }
            }
            plateForComparison = trapezPlates.get(0);
            TrapezPlate maxLengthPlate = plateForComparison;
            int firstCompareForLength = initialLength - maxLengthPlate.getLength();

            for (TrapezPlate trapezPlate : trapezPlates) {
                smallestRemainder = plateForComparison.getRemainderRelativeToLength();
                int remainderForComparison = trapezPlate.getRemainderRelativeToLength();

                if (initialLength - trapezPlate.getLength() == 5) {
                    finalPlate = trapezPlate;
                    break;
                }

                if (remainderForComparison < smallestRemainder && remainderForComparison != 0 ||  remainderForComparison == smallestRemainder ) {
                    smallestRemainder = remainderForComparison;
                }

                if (plateForComparison.getLength() < trapezPlate.getLength() && smallestRemainder == remainderForComparison) {
                    finalPlate = trapezPlate;
                }

                if (trapezPlate.getRemainderRelativeToLength() == 0 && trapezPlate.getExcessRelativeToLength() == 0) {
                    if (initialLength - trapezPlate.getLength() != 0) {
                        finalPlate = trapezPlate;
                    }  else {
                        finalPlate = trapezPlate;
                        break;
                    }
                }

                int secondCompareForLength = initialLength - trapezPlate.getLength();

                if (firstCompareForLength == secondCompareForLength || firstCompareForLength > secondCompareForLength && secondCompareForLength > 0) {
                    maxLengthPlate = trapezPlate;
                    finalPlate = maxLengthPlate;
                }
            }
            int remainingSpace = initialLength - (finalPlate.getLength() * finalPlate.getAmount());
            numberOfPlates = finalPlate.getAmount() * calcNumberOfTrapezPlates(width);

            if (numberOfPlates == 0) {
                numberOfPlates += 1;
            }

            if (remainingSpace > overhang) {
                int totalSpaceRemaining = remainingSpace * numberOfPlates;
                int initialTotalSpace = totalSpaceRemaining;
                boolean keepGoing = true;
                TrapezPlate previousPlate = new TrapezPlate();
                int counter = 0;
                int combinedPlateLengths;

                for (TrapezPlate trapezPlate : trapezPlates) {

                    for (TrapezPlate plate : trapezPlates) {
                        if (plate.getLength() + previousPlate.getLength() == initialTotalSpace) {
                            trapezPlate = plate;
                        }
                    }

                    combinedPlateLengths = previousPlate.getLength() + trapezPlate.getLength();


                    while (keepGoing) {

                        totalSpaceRemaining -= trapezPlate.getLength();
                        counter++;

                        if (combinedPlateLengths == initialTotalSpace) {
                            secondPlateLength = trapezPlate.getLength();
                            leftOverNumberOfPlates = 1;
                            counter = 1;

                            if (secondPlateLength == finalPlate.getLength()) {
                                numberOfPlates += 1;
                                leftOverNumberOfPlates -= 1;
                            }

                            keepGoing = false;
                        }

                        if (totalSpaceRemaining < 0 && totalSpaceRemaining > -10) { // Sætter en grænse for hvor meget overskydende længde der må være.
                            secondPlateLength = trapezPlate.getLength();
                            leftOverNumberOfPlates = counter;
                            keepGoing = false;
                        }

                        if (totalSpaceRemaining < 0) {

                            secondPlateLength = trapezPlate.getLength();
                            leftOverNumberOfPlates = counter;
                            totalSpaceRemaining = initialTotalSpace;
                            counter = 0;
                            previousPlate =  trapezPlate;
                            break;
                        }

                        if (totalSpaceRemaining == 0) {
                            secondPlateLength = trapezPlate.getLength();
                            if (counter > 1) {
                                leftOverNumberOfPlates = counter;
                            }

                            keepGoing = false;
                            break;

                        }
                    }
                    int multi = initialTotalSpace * 2;
                    if (multi >= trapezPlate.getLength()) {
                        remainderPlateLength = trapezPlate.getLength();
                    }
                }
            } else {
                secondPlateLength = finalPlate.getLength();
            }

            if (secondPlateLength == finalPlate.getLength()) {
                if (leftOverNumberOfPlates == 0) {
                    numberOfPlates += 1;
                } else {
                    numberOfPlates += leftOverNumberOfPlates;
                }
                leftOverNumberOfPlates = 0;
                secondPlateLength = 0;
            }

            int finalPlateFullSize = finalPlate.getLength() * numberOfPlates;
            int secondPlateFullSize = secondPlateLength * leftOverNumberOfPlates;
            int combinedFullSizes = finalPlateFullSize + secondPlateFullSize;
            int carportFullSize = length * calcNumberOfTrapezPlates(width);
            int leftOverSpace = combinedFullSizes - carportFullSize;

            if (combinedFullSizes > carportFullSize) {
                for (TrapezPlate trapezPlate : trapezPlates) {
                    int determine = combinedFullSizes - trapezPlate.getLength();

                    if (determine <= carportFullSize) {
                        if (trapezPlate.getLength() != finalPlate.getLength()) {
                            secondPlateLength = trapezPlate.getLength();
                            if (leftOverSpace > trapezPlate.getLength()) {
                                numberOfPlates -= 1;
                                leftOverNumberOfPlates += 1;
                            } else {
                                break;
                            }
                        }
                        break;
                    }
                }
            }

            if (remainderPlateLength != 0 && remainderPlateLength < secondPlateLength) {
                if (finalPlate.getLength() == remainderPlateLength) {
                    numberOfPlates += 1;
                    secondPlateLength = 0;
                } else {
                    secondPlateLength = remainderPlateLength;
                }
            }

            this.setPrimaryRoofPlateLength(finalPlate.getLength());
            this.setSecondaryRoofPlateLength(secondPlateLength);
            return numberOfPlates;
        }


    public int fasciaLength(boolean isSides) {

        int fasciaSize;

        if (isSides) {
            fasciaSize = 540;
        } else {
            fasciaSize = 360;
        }

        return fasciaSize;
    }


    public int calcNumberOfFascia(int measurement, boolean doMultiply, boolean isSides) { // doMultiply bruges primært til at gange siderne på carporten. Fascia = sternbræt.

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

    public int getPrimaryRoofPlateLength() {
        return primaryRoofPlateLength;
    }

    public void setPrimaryRoofPlateLength(int primaryRoofPlateLength) {
        this.primaryRoofPlateLength = primaryRoofPlateLength;
    }

    public int getSecondaryRoofPlateLength() {
        return secondaryRoofPlateLength;
    }

    public void setSecondaryRoofPlateLength(int secondaryRoofPlateLength) {
        this.secondaryRoofPlateLength = secondaryRoofPlateLength;
    }
}
