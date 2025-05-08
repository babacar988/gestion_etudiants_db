package dao;

import entity.Classe;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClasseImpl implements IClasse {
    private DB db = new DB();
    private ResultSet rs;
    private int ok;

    @Override
    public int add(Classe classe) {
        String sql = "INSERT INTO classe(id, nom) VALUES(NULL,?)";
        try {
           db.initPrepar(sql);
           db.getPstm().setString(1, classe.getNom());
           ok = db.executeMaj();
           db.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public int update(Classe classe) {
        String sql = "UPDATE classe SET nom = ?, effectif = ? WHERE id  = ?";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, classe.getNom());
            db.getPstm().setInt(2, classe.getEffectif());
            db.getPstm().setInt(3, classe.getId());
            ok = db.executeMaj();
            db.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM classe WHERE id = ?";
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1,id);
            ok = db.executeMaj();
            db.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public List<Classe> list() {
        List<Classe> classes = new ArrayList<>();
        String sql = "SELECT * FROM classe ORDER BY nom ASC";
        try {
            db.initPrepar(sql);
            rs = db.executeSelect();
            while (rs.next()) {
                Classe cl = new Classe();
                cl.setId(rs.getInt("id"));
                cl.setNom(rs.getString("nom"));
                cl.setEffectif(rs.getInt("effectif"));
                classes.add(cl);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

    @Override
    public Classe get(int id) {
        Classe cl = null;
        String sql = "SELECT * FROM classe WHERE id = ?";
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1,id);
            rs = db.executeSelect();
            if (rs.next()) {
                cl = new Classe();
                cl.setId(rs.getInt("id"));
                cl.setNom(rs.getString("nom"));
                cl.setEffectif(rs.getInt("effectif"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return cl;
    }
}
