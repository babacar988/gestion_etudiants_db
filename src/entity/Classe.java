package entity;

public class Classe {
    private int id;
    private String nom;
    private int effectif;

    public Classe() {
    }
    public Classe(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEffectif() {
        return effectif;
    }

    public void setEffectif(int effectif) {
        this.effectif = effectif;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Classe{" + "id=" + id + ", nom=" + nom + ", effectif=" + effectif + '}';
    }
}
