package dao;

import entity.Classe;
import entity.Etudiant;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EtudiantImpl implements IEtudiant {

    private DB db = new DB();
    private ResultSet rs;
    private int ok;
    private IClasse daoClasse = new ClasseImpl();

    @Override
    public List<Etudiant> getEtudiantsByClasse(String classe) {
        List<Etudiant> etudiants = new ArrayList<>();
        String sql = "SELECT * FROM etudiant e, classe c WHERE e.classe = c.id AND c.nom LIKE ? ORDER BY e.nom ASC";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, "%" + classe + "%");
            rs = db.executeSelect();
            while (rs.next()) {
                Etudiant et = new Etudiant();
                et.setId(rs.getInt(1));
                et.setNom(rs.getString("nom"));
                et.setPrenom(rs.getString("prenom"));
                et.setMatricule(rs.getString(2));
                et.setMoyenne(rs.getDouble("moyenne"));
                et.setClasse(daoClasse.get(rs.getInt("classe")));
                etudiants.add(et);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return etudiants;
    }

    @Override
    public int add(Etudiant etudiant) {
        String sql = "INSERT INTO etudiant(id,matricule,nom,prenom,moyenne,classe) VALUES(NULL,?,?,?,?,?)";
        try{
            int idClasse = etudiant.getClasse().getId();
            db.initPrepar(sql);
            db.getPstm().setString(1, etudiant.getMatricule());
            db.getPstm().setString(2, etudiant.getNom());
            db.getPstm().setString(3, etudiant.getPrenom());
            db.getPstm().setDouble(4, etudiant.getMoyenne());
            db.getPstm().setInt(5, idClasse);
            ok = db.executeMaj();
            if(ok == 1){
                String req = "UPDATE classe SET effectif = effectif + 1 WHERE id = ?";
                db.initPrepar(req);
                db.getPstm().setInt(1, idClasse);
                ok = db.executeMaj();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public int update(Etudiant etudiant) {
        return 0;
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM etudiant WHERE id = ?";
        try {
            Etudiant et = get(id);
            int idClasse = et.getClasse().getId();
            db.initPrepar(sql);
            db.getPstm().setInt(1, id);
            ok = db.executeMaj();
            if(ok == 1){
                String req = "UPDATE classe SET effectif = effectif - 1 WHERE id = ?";
                db.initPrepar(req);
                db.getPstm().setInt(1, idClasse);
                ok = db.executeMaj();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public List<Etudiant> list() {
        List<Etudiant> etudiants = new ArrayList<>();
        String sql = "SELECT * FROM etudiant ORDER BY nom ASC";
        try {
            db.initPrepar(sql);
            rs = db.executeSelect();
            while (rs.next()) {
                Etudiant et = new Etudiant();
                et.setId(rs.getInt(1));
                et.setNom(rs.getString("nom"));
                et.setPrenom(rs.getString("prenom"));
                et.setMatricule(rs.getString(2));
                et.setMoyenne(rs.getDouble("moyenne"));
                et.setClasse(daoClasse.get(rs.getInt("classe")));
                etudiants.add(et);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return etudiants;
    }

    @Override
    public Etudiant get(int id) {
        Etudiant et = null;
        String sql = "SELECT * FROM etudiant WHERE id = ?";
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1, id);
            rs = db.executeSelect();
            if (rs.next()) {
                et = new Etudiant();
                et.setId(rs.getInt(1));
                et.setNom(rs.getString("nom"));
                et.setPrenom(rs.getString("prenom"));
                et.setMatricule(rs.getString(2));
                et.setMoyenne(rs.getDouble("moyenne"));
                et.setClasse(daoClasse.get(rs.getInt("classe")));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return et;
    }
}
