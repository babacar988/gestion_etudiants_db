package main;

import dao.*;
import entity.Classe;
import entity.Etudiant;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        IEtudiant dao = new EtudiantImpl();
        for (Etudiant e : dao.getEtudiantsByClasse("GE")){
            System.out.println(e);
        }
    }
}