package CarportUtil;

import FunctionLayer.Log;
import FunctionLayer.LogicFacade;
import FunctionLayer.Size;
import FunctionLayer.Unit;

import java.util.ArrayList;

public class Initializer {

    private static ArrayList<Integer> CWList = null;
    private static ArrayList<Integer> CLList = null;
    private static ArrayList<Integer> SWList = null;
    private static ArrayList<Integer> SLList = null;
    private static ArrayList<Size> sizeList = null;
    private static ArrayList<Unit> unitList = null;


    public static ArrayList<Integer> initCWLIST() {
        try {
            CWList = LogicFacade.getCWList();
        } catch (Exception e) {
            Log.severe("Kan ikke hente Carport bredde listen");
        }

        return CWList;

    }

    public static ArrayList<Integer> initCLList() {
        try {
            CLList = LogicFacade.getCLList();
        } catch (Exception e) {
            Log.severe("Kan ikke hente Carport længde listen");
        }

        return  CLList;
    }

    public static ArrayList<Integer> initSWList() {
        try {
            SWList = LogicFacade.getSWList();
        } catch (Exception e) {
            Log.severe("Kan ikke hente Skur bredde listen");
        }

        return SWList;
    }

    public static ArrayList<Integer> initSLList() {
        try {
            SLList = LogicFacade.getSLList();
        } catch (Exception e) {
            Log.severe("Kan ikke hente Skur længde listen");
        }

        return SLList;
    }

    public static ArrayList<Size> initSizeList(boolean isLengths) {
        try {
            sizeList = LogicFacade.getSList(isLengths);
        } catch (Exception e) {
            Log.severe("Kan ikke hente størrelse listen");
        }

        return sizeList;
    }

    public static ArrayList<Unit> initUnitList() {
        try {
            unitList = LogicFacade.getUList();
        } catch (Exception e) {
            Log.severe("Kan ikke hente størrelse listen");
        }

        return unitList;
    }
}
