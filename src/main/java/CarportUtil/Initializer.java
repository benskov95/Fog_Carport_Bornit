package CarportUtil;

import FunctionLayer.LogicFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

public class Initializer {

    private static ArrayList<Integer> CWList = null;
    private static ArrayList<Integer> CLList = null;
    private static ArrayList<Integer> SWList = null;
    private static ArrayList<Integer> SLList = null;


    public static ArrayList<Integer> initCWLIST() {
        try {
            CWList = LogicFacade.getCWList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CWList;

    }

    public static ArrayList<Integer> initCLList() {
        try {
            CLList = LogicFacade.getCLList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  CLList;
    }

    public static ArrayList<Integer> initSWList() {
        try {
            SWList = LogicFacade.getSWList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SWList;
    }

    public static ArrayList<Integer> initSLList() {
        try {
            SLList = LogicFacade.getSLList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SLList;
    }
}
