package FunctionLayer;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The purpose of the Calculator class is
 * to contain all methods that have to do
 * calculating the amounts, lengths etc
 * of all the materials needed for a
 * specific carport.
 * @author Benjamin/benskov95
 */
public class Calculator {

    private int primaryRoofPlateLength;
    private int secondaryRoofPlateLength;

    /**
     * the type1Calc() method calculates all necessary values for each
     * material through a variety of helper methods. Once certain
     * values have been defined, the materials are added to an
     * arraylist whose as of yet incomplete materials are updated
     * and fully defined through a number of Mapper methods. The end
     * result is an arraylist with all of the materials for a type
     * 1 carport (flat roof, no shed), which is added to a
     * BillOfMaterials object.
     *
     * NOTE: Material IDs are hardcoded because we need
     * a way to identify the materials in the database
     * to assign the correct values to them. We found
     * that one way or another, we'd have to hardcode
     * at least one ID (not necessarily material ID)
     * per material somewhere to get the rest of the
     * values belonging to each material, so we did.
     * @param length
     * The chosen length of a customer's carport in cm.
     * @param width
     * The chosen width of a customer's carport in cm.
     * @return
     * A BillOfMaterials object is returned, containing the
     * aforementioned arraylist. This object is used to add
     * the bill of materials to the database (this happens
     * in the FlatOrder class in the PresentationLayer
     * directory).
     * @throws SQLException
     *  Thrown if the provided SQL string in each method
     *  has incorrect syntax, unknown keywords etc. or
     *  if the connection to the database cannot be
     *  established.
     * @throws ClassNotFoundException
     * Thrown from Connector if the "Class.forName" method
     * doesn't find the specified class
     * (JDBC driver in this case).
     * @author Benjamin/benskov95
     */

    public BillOfMaterials type1Calc(int length, int width) throws SQLException, ClassNotFoundException {

        ArrayList<Material> materialHolder = new ArrayList<>();

        int numberOfBeams;
        if (width > 600) {
            numberOfBeams = 3;
        } else {
            numberOfBeams = 2;
        }

        int numberOfPerforatedBands = 2;
        int numberOfFasciaScrewPacks = 1;

        int posts = calcNumberOfPosts(length, width, 0, 0);
        int rafters = calcNumberOfRafters(length);

        int underFasciaFrontBackLength = calcFasciaLength(32, width);
        int underFasciaFrontBackQuantity = calcNumberOfFascia(width, underFasciaFrontBackLength, true); // Understernbrædder til for og bagende.

        int underFasciaSidesLength = calcFasciaLength(32, length);
        int underFasciaSidesQuantity = calcNumberOfFascia(length, underFasciaSidesLength, true); // Understernbrædder til siderne.

        int overFasciaFrontLength = calcFasciaLength(20, width);
        int overFasciaFrontQuantity = calcNumberOfFascia(width, overFasciaFrontLength, false); // Oversternbrædder til forenden.

        int overFasciaSidesLength = calcFasciaLength(20, length);
        int overFasciaSidesQuantity = calcNumberOfFascia(length, overFasciaSidesLength, true); // Oversternbrædder til siderne.

        int waterFasciaFrontLength = calcFasciaLength(1, width);
        int waterFasciaFrontQuantity = calcNumberOfFascia(width, waterFasciaFrontLength, false); // Vandbræt til front.

        int waterFasciaSidesLength = calcFasciaLength(1, length);
        int waterFasciaSidesQuantity = calcNumberOfFascia(length, waterFasciaSidesLength, true); // Vandbræt til siderne.

        int plates = calcNumberOfTrapezPlates(width);
        int plateSizesAndQuantity = calcLengthAndAmountOfTrapezPlates(length, width, MaterialFacade.getMaterialLengths(69));
        int bottomScrews = calcNumberOfBottomScrewPacks(plates);
        int brackets = calcNumberOfUniversalBrackets(rafters);
        int bracketScrews = calcNumberOfBracketScrewPacks(brackets);
        int carriageBolts = calcNumberOfCarriageBolts(posts, numberOfBeams);
        int squareWashers = calcNumberOfSquareWashers(posts);

        materialHolder.add(new Material(38, width, numberOfBeams));
        materialHolder.add(new Material(92, 0, numberOfPerforatedBands));
        materialHolder.add(new Material(95, 0, numberOfFasciaScrewPacks));
        materialHolder.add(new Material(46, 300, posts));
        materialHolder.add(new Material(38, width, rafters));

        materialHolder.add(new Material(32, underFasciaFrontBackLength, underFasciaFrontBackQuantity));
        materialHolder.add(new Material(32, underFasciaSidesLength, underFasciaSidesQuantity));

        materialHolder.add(new Material(20, overFasciaFrontLength,  overFasciaFrontQuantity));
        materialHolder.add(new Material(20, overFasciaSidesLength, overFasciaSidesQuantity));

        materialHolder.add(new Material(1, waterFasciaSidesLength, waterFasciaSidesQuantity));
        materialHolder.add(new Material(1, waterFasciaFrontLength, waterFasciaFrontQuantity));

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

        return new BillOfMaterials(materialHolder);
    }

    /**
     * Works similarly to the type1Calc() method but with
     * additional helper methods for calculating the
     * material values for the shed. This method is
     * used when a type 2 carport is ordered
     * (flat roof with shed).
     * @param length
     * The chosen length of a customer's carport in cm.
     * @param width
     * The chosen width of a customer's carport in cm.
     * @param shedLength
     * The chosen length of a customer's shed in cm.
     * Cannot be greater than the length of the
     * carport (handled in the FlatOrder class).
     * @param shedWidth
     * The chosen width of a customer's shed in cm.
     * Cannot be greater than the width of the
     * carport (handled in the FlatOrder class).
     * @return
     * Same as type1Calc(), a BillOfMaterials
     * object containing the arraylist that
     * has been filled in this method.
     * @throws SQLException
     *  Thrown if the provided SQL string in each method
     *  has incorrect syntax, unknown keywords etc. or
     *  if the connection to the database cannot be
     *  established.
     * @throws ClassNotFoundException
     * Thrown from Connector if the "Class.forName" method
     * doesn't find the specified class
     * (JDBC driver in this case).
     * @author Benjamin/benskov95
     */

    public BillOfMaterials type2Calc(int length, int width, int shedLength, int shedWidth) throws SQLException, ClassNotFoundException {

        ArrayList<Material> materialHolder = new ArrayList<>();

        int numberOfBeams;
        if (width > 600) {
            numberOfBeams = 3;
        } else {
            numberOfBeams = 2;
        }

        int numberOfPerforatedBands = 2;
        int numberOfFasciaScrewPacks = 1;

        int posts = calcNumberOfPosts(length, width, shedLength, shedWidth);
        int rafters = calcNumberOfRafters(length);

        int underFasciaFrontBackLength = calcFasciaLength(32, width);
        int underFasciaFrontBackQuantity = calcNumberOfFascia(width, underFasciaFrontBackLength, true); // Understernbrædder til for og bagende.

        int underFasciaSidesLength = calcFasciaLength(32, length);
        int underFasciaSidesQuantity = calcNumberOfFascia(length, underFasciaSidesLength, true); // Understernbrædder til siderne.

        int overFasciaFrontLength = calcFasciaLength(20, width);
        int overFasciaFrontQuantity = calcNumberOfFascia(width, overFasciaFrontLength, false); // Oversternbrædder til forenden.

        int overFasciaSidesLength = calcFasciaLength(20, length);
        int overFasciaSidesQuantity = calcNumberOfFascia(length, overFasciaSidesLength, true); // Oversternbrædder til siderne.

        int waterFasciaFrontLength = calcFasciaLength(1, width);
        int waterFasciaFrontQuantity = calcNumberOfFascia(width, waterFasciaFrontLength, false); // Vandbræt til front.

        int waterFasciaSidesLength = calcFasciaLength(1, length);
        int waterFasciaSidesQuantity = calcNumberOfFascia(length, waterFasciaSidesLength, true); // Vandbræt til siderne.

        int plates = calcNumberOfTrapezPlates(width);
        int plateSizesAndQuantity = calcLengthAndAmountOfTrapezPlates(length, width, MaterialFacade.getMaterialLengths(69));
        int bottomScrews = calcNumberOfBottomScrewPacks(plates);
        int brackets = calcNumberOfUniversalBrackets(rafters);
        int bracketScrews = calcNumberOfBracketScrewPacks(brackets);
        int carriageBolts = calcNumberOfCarriageBolts(posts, numberOfBeams);
        int squareWashers = calcNumberOfSquareWashers(posts);

        int lath = 1; // Lægte, altid 420 lang - til z på bagside af dør.
        int numberOfOuterCladdingScrewPacks = 2;
        int numberOfInnerCladdingScrewPacks = 2;
        int numberOfBarnDoorLatch = 1;
        int numberOfTHinges = 1;

        int lengthOfShedBeams = calcLengthOfBeamsForShed(shedLength, 38);
        int numberOfShedBeams = calcNumberOfBeamsForShed(shedLength, lengthOfShedBeams);
        int lengthOfStudsForSides = calcOptimalLengthOfMaterial(shedLength, 54);
        int lengthOfStudsForGables = calcOptimalLengthOfMaterial(shedWidth, 54);

        int numberOfStudsForSides = determineNumberOfStuds(shedLength, 54);
        int numberOfStudsForGables = determineNumberOfStuds(shedWidth, 54);
        int numberOfAngleBrackets = (numberOfStudsForSides + numberOfStudsForGables) * 2;
        int numberOfCladdingPlanks = calcNumberOfCladdingPlanks(shedWidth, shedLength);


        materialHolder.add(new Material(38, width, numberOfBeams));
        materialHolder.add(new Material(38, width, rafters));
        materialHolder.add(new Material(38, lengthOfShedBeams, numberOfShedBeams));
        materialHolder.add(new Material(92, 0, numberOfPerforatedBands));
        materialHolder.add(new Material(95, 0, numberOfFasciaScrewPacks));
        materialHolder.add(new Material(46, 300, posts));

        materialHolder.add(new Material(32, underFasciaFrontBackLength, underFasciaFrontBackQuantity));
        materialHolder.add(new Material(32, underFasciaSidesLength, underFasciaSidesQuantity));

        materialHolder.add(new Material(20, overFasciaFrontLength,  overFasciaFrontQuantity));
        materialHolder.add(new Material(20, overFasciaSidesLength, overFasciaSidesQuantity));

        materialHolder.add(new Material(1, 210, numberOfCladdingPlanks));
        materialHolder.add(new Material(1, waterFasciaSidesLength, waterFasciaSidesQuantity));
        materialHolder.add(new Material(1, waterFasciaFrontLength, waterFasciaFrontQuantity));

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

        materialHolder.add(new Material(65, 420, lath));
        materialHolder.add(new Material(99, 0, numberOfOuterCladdingScrewPacks));
        materialHolder.add(new Material(100, 0, numberOfInnerCladdingScrewPacks));
        materialHolder.add(new Material(101, 0, numberOfBarnDoorLatch));
        materialHolder.add(new Material(102, 0, numberOfTHinges));
        materialHolder.add(new Material(103, 0, numberOfAngleBrackets));

        materialHolder.add(new Material(54, lengthOfStudsForGables, numberOfStudsForGables));
        materialHolder.add(new Material(54, lengthOfStudsForSides, numberOfStudsForSides));


        CarportPartsFacade.getCarportPartIds(materialHolder, 2);
        CarportPartsFacade.getCarportPartDescriptions(materialHolder);
        MaterialFacade.setMaterialValues(materialHolder);
        MaterialFacade.setMaterialSizeIds(materialHolder);
        MaterialFacade.setLinkMaterialSizeIds(materialHolder);

        return new BillOfMaterials(materialHolder);
    }

    /**
     * The calcOptimalLengthOfMaterial() method
     * is mainly used to calculate the optimal
     * length of studs for a type 2 carport.
     *
     * The length is optimized by getting
     * all stud lengths in the database through
     * the getMaterialLengths() method from
     * the MaterialMapper into an arraylist,
     * looping through it and subtracting the
     * current length from the measurement
     * until the measurement reaches 0 or
     * becomes negative and has an excess that
     * is not greater than 30 cm (at which point
     * the loop breaks and restarts with the next
     * length until all lengths have been tested),
     * and returning the length with which this
     * was achieved.
     *
     * If the measurement does not reach 0
     * through the subtraction of a given
     * length it is reset to its initial
     * value, after which the loop continues
     * until the optimal length is found.
     * @param measurement
     * A value used to determine the optimal length
     * of a material for a specified measurement in
     * cm (typically length or width of the shed).
     * @param materialId
     * In order for the getMaterialLengths method
     * to return the correct lengths for the
     * material we want the optimal length of,
     * we need to know its ID in the database.
     * @return
     * The calculated optimal length of a
     * material for a given measurement.
     * @throws SQLException
     *  Thrown if the provided SQL string in each method
     *  has incorrect syntax, unknown keywords etc. or
     *  if the connection to the database cannot be
     *  established.
     * @throws ClassNotFoundException
     * Thrown from Connector if the "Class.forName" method
     * doesn't find the specified class
     * (JDBC driver in this case).
     * @author Benjamin/benskov95
     */
    public int calcOptimalLengthOfMaterial(int measurement, int materialId) throws SQLException, ClassNotFoundException { // Løsholter

        int initialWidth = measurement;
        ArrayList<Integer> studSizes = MaterialFacade.getMaterialLengths(materialId);
        boolean stop = false;
        int res = 0;
        int count = 0;

        for (Integer integer : studSizes) {
            while (measurement != 0) {
                measurement -= integer;
                count++;
                if (measurement <= 0 && measurement >= -30 && count < 3) {
                    res = integer;
                    stop = true;
                    break;
                } else if (measurement <= 0) {
                    measurement = initialWidth;
                    break;
                }
            }
            if (stop) {
                break;
            }
            count = 0;
        }
        return res;
    }

    public int calcLengthOfBeamsForShed(int shedMeasurement, int materialId) throws SQLException, ClassNotFoundException { // Hvad er den til? Ingen ved det.
        shedMeasurement *= 2;
        return calcOptimalLengthOfMaterial(shedMeasurement, materialId);
    }

    public int calcNumberOfBeamsForShed(int shedLength, int beamLength) {
        int splitBeam = beamLength / 2;
        if (shedLength == splitBeam) {
            return 1;
        } else {
            return 2;
        }
    }

    public int determineNumberOfStuds(int shedMeasurement, int materialId) throws SQLException, ClassNotFoundException {
        int length = calcOptimalLengthOfMaterial(shedMeasurement, materialId);
        int plankCaseOne = shedMeasurement - (length * 2);
        int plankCaseTwo = shedMeasurement - length;
        int res;

        if (plankCaseOne <=0 && plankCaseOne >= -30) {
            res = 12;
        } else if (plankCaseTwo <= 0 && plankCaseTwo > -30) {
            res = 6;
        } else {
            res = 4;
        }

        return res;
    }

    public int calcNumberOfCladdingPlanks(int shedWidth, int shedLength) {
        double boardWidthWithOverlap = 7.5;
        int circumference = (shedWidth * 2) + (shedLength * 2);
        return (int) Math.ceil(circumference / boardWidthWithOverlap);
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

    /**
     * The calcLengthAndAmountOfTrapezPlates() method is used
     * to calculate the optimal length of roofplates
     * for a carport. This is done in a similar manner
     * to the calcOptimalLengthOfMaterial() method, but
     * is noticeably more complex.
     *
     * A provided arraylist of lengths (these being
     * lengths of roofplates from the database) is
     * looped through and for each length, a while
     * loop is executed that subtracts the length
     * from the carport length until it is as close
     * to 0 as possible.
     *
     * Remainder (meaning any amount of carport length
     * that is not covered by a roofplate length) and
     * excess (meaning any amount length that is greater
     * than the carport length) values are then used to
     * further optimize the roofplate lengths later in
     * the method.
     *
     * Once fully optimized, the primaryPlateLength
     * variable, which is a variable in this
     * (Calculator) class, is set to be equal
     * to the optimized plate length. This is
     * also done for the secondaryPlateLength
     * variable if a second roofplate length
     * was needed to ensure as little
     * waste/excess as possible.
     * @param length
     * The chosen length of a customer's carport in cm.
     * @param width
     * The chosen width of a customer's carport in cm.
     * @param measurements
     * An arraylist of lengths for a material.
     * The getMaterialLengths() method is used
     * to get the lengths of the roofplates
     * in the type1Calc and type2Calc methods
     * like it was in the calcOptimalLengthOfMaterial
     * method.
     * @return
     * This method returns the number of roofplates
     * needed to cover the entire roof of the carport.
     * If a secondary plate length is needed, the
     * amount of plates needed is equal to the amount
     * of primary plates. This is not obvious here, but
     * can be seen on the bill of materials page on the
     * website.
     * @author Benjamin/benskov95
     */
    public int calcLengthAndAmountOfTrapezPlates(int length, int width, ArrayList<Integer> measurements){

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


    public int calcFasciaLength(int materialId, int measurement) throws SQLException, ClassNotFoundException {
        return calcOptimalLengthOfMaterial(measurement, materialId);
    }


    public int calcNumberOfFascia(int measurement, int fasciaLength, boolean doMultiply) { // doMultiply bruges primært til at gange siderne på carporten. Fascia = sternbræt.

        int fasciaNumber = (int) Math.ceil(measurement/ fasciaLength);

        if (doMultiply) {
            return fasciaNumber * 2;
        } else{
            return fasciaNumber;
        }

    }

    /**
     * The calcNumberOfPosts() method is used
     * to calculate the number of posts needed
     * for a carport. The base amount is 4,
     * which is what the local variable
     * numberOfPosts is set to.
     *
     * Additional posts are added if certain
     * conditions are met, such as if the carport
     * is wider or longer than 600 cm, or if it
     * has a shed.
     * @param length
     * The chosen length of a customer's carport in cm.
     * @param width
     * The chosen width of a customer's carport in cm.
     * @param shedLength
     * The chosen length of a customer's shed in cm.
     * Cannot be greater than the length of the
     * carport (handled in the FlatOrder class).
     * @param shedWidth in cm
     * The chosen width of a customer's shed in cm.
     * Cannot be greater than the width of the
     * carport (handled in the FlatOrder class).
     * @return
     * The final amount of posts needed for the
     * carport.
     * @author Benjamin/benskov95
     */

    public int calcNumberOfPosts(int length, int width, int shedLength, int shedWidth) {
        int numberOfPosts = 4;
        int eaves = 35;
        boolean check = false;

        if (length > 600) {
            numberOfPosts += 2;
        }
        if (width > 600) {
            numberOfPosts += 2;
        }

        if (shedLength != 0 && shedWidth != 0) {
            if (shedWidth < 300 && shedWidth > 0) {
                numberOfPosts += 3;
                check = true;
            }
            if (shedWidth < (width - 2 * eaves) && width > 300) {
                numberOfPosts += 5;
                check = true;
            }
            if (!check) {
                numberOfPosts += 4;
            }

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

    /**
     * The calcSpaceBetweenRafters() method is used
     * to calculate the optimal amount of space
     * between rafters on a carport, based on the
     * length of the carport.
     *
     * This is done in a similar manner to the
     * calcOptimalLengthOfMaterial() method,
     * with a while loop that subtracts the
     * space variable from the provided length
     * until the length is 0.
     *
     * The space variable starts at 45 to ensure
     * that the space between rafters is minimum
     * 45 cm, and is then increased by 1 for each
     * time the loop results in the length becoming
     * negative (going below 0).
     *
     * Eventually, if the length ends up hitting
     * 0, the loop will break and the space value
     * with which this was achieved will be returned.
     *
     * @param length
     * The chosen length of a customer's carport in cm.
     * @return
     * The optimal amount of space between rafters
     * in cm.
     * @author Benjamin/benskov95
     */
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
        int total = numberOfBeams + numberOfPosts;
        return total * 2;
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
