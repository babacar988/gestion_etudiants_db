package entity;

import java.time.Year;
import java.time.*;


public class Etudiant {
    private int id;
    private String matricule;
    protected String nom;
    private String prenom;
    private double moyenne;
    private Classe classe;


    public Etudiant() {
    }
    public Etudiant(String nom, String prenom, double moyenne, Classe classe) {
        this.nom = nom;
        this.prenom = prenom;
        this.moyenne = moyenne;
        this.classe = classe;
        this.matricule = generateMatricule();
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getMatricule() {
        return matricule;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public Classe getClasse() {
        return classe;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    private String generateMatricule() {
        int year = Year.now().getValue();
        int mois = LocalDate.now().getMonth().getValue();
        int day = LocalDate.now().getDayOfMonth();
        int heure = LocalDateTime.now().getHour();
        int minute = LocalDateTime.now().getMinute();
        int second = LocalDateTime.now().getSecond();

        return "ET@" +
              year + mois + day + heure + minute + second + classe.getNom() + "#" ;
    }
    @Override
    public String toString() {
        return
                "matricule=" + matricule
                ;
    }
}
