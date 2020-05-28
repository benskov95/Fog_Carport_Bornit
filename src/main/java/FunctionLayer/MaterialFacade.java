package FunctionLayer;

import DBAccess.MaterialMapper;

import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialFacade {

    public static ArrayList<Integer> getMaterialLengths(int materialId) throws SQLException, ClassNotFoundException {
        return MaterialMapper.getMaterialLengths(materialId);
    }

    public static void setMaterialValues(ArrayList<Material> materials) throws SQLException, ClassNotFoundException {
        MaterialMapper.setMaterialValues(materials);
    }

    public static void setMaterialSizeIds(ArrayList<Material> materials) throws SQLException, ClassNotFoundException {
        MaterialMapper.setMaterialSizeIds(materials);
    }

    public static void setLinkMaterialSizeIds(ArrayList<Material> materials) throws SQLException, ClassNotFoundException {
        MaterialMapper.setLinkMaterialSizeIds(materials);
    }

    public static void setUnitTypes(ArrayList<Material> materials) throws SQLException, ClassNotFoundException {
        MaterialMapper.setUnitTypes(materials);
    }

    public static ArrayList<Material> getAllMaterials() throws SQLException, ClassNotFoundException {
        return MaterialMapper.getAllMaterials();
    }

    public static void updateMaterial(int materialId, String name, int price) {
        MaterialMapper.updateMaterial(materialId, name, price);
    }

    public static int insertMaterial(Material material, ArrayList<Size> sizes) throws SQLException, OrderException, ClassNotFoundException {
        return  MaterialMapper.insertMaterial(material, sizes);
    }
}
