package main;

import dao.ClasseImpl;
import dao.DB;
import dao.IClasse;
import entity.Classe;
import entity.Etudiant;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        IClasse dao = new ClasseImpl();

        //1er Methode
       /*for (Classe c: dao.list()){
           System.out.println(c);
       }
        //2e Methode
        List<Classe> classes = dao.list();
        for (int i = 0; i < classes.size(); i++) {
            System.out.println(classes.get(i));
        }
        int result = dao.delete(3);
        if (result == 1) {
            System.out.println("success");
        }
        else {
            System.out.println("fail");
        }*/
        Classe classe = dao.get(4);
        classe.setNom("L3RT");
        classe.setEffectif(2);
        dao.update(classe);
    }
}