package com.svetlicic.filip;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Prodavaonica prodavaonica1 = new Prodavaonica("Prodavaonica1");
        prodavaonica1.dodajArtikl(new Artikl("A1", 11.22, 30));
        prodavaonica1.dodajArtikl(new Artikl("A2", 12.22, 100));
        prodavaonica1.dodajArtikl(new Artikl("A3", 11.22, 48));

        for (Artikl artikl : prodavaonica1.getArtikli()){
            System.out.println(artikl.toString());
        }

        prodavaonica1.prodajArtikl("1-9-2020", "A1", 1);

        for (Artikl artikl : prodavaonica1.getArtikli()){
            System.out.println(artikl.toString());
        }

        prodavaonica1.prodajArtikl("2-9-2020", "A1", 5);
        prodavaonica1.prodajArtikl("3-9-2020", "A1", 5);
        prodavaonica1.prodajArtikl("4-9-2020", "A1", 3);

        for (Artikl artikl : prodavaonica1.getArtikli()){
            System.out.println(artikl.toString());
        }

        prodavaonica1.prodajArtikl("5-9-2020", "A1", 3);
        prodavaonica1.prodajArtikl("6-9-2020", "A1", 4);
        prodavaonica1.prodajArtikl("7-9-2020", "A1", 6);

        prodavaonica1.prodajArtikl("1-9-2020", "A2", 1);
        prodavaonica1.prodajArtikl("2-9-2020", "A2", 2);
        prodavaonica1.prodajArtikl("3-9-2020", "A2", 3);
        prodavaonica1.prodajArtikl("4-9-2020", "A2", 3);
        prodavaonica1.prodajArtikl("5-9-2020", "A2", 3);
        prodavaonica1.prodajArtikl("6-9-2020", "A2", 3);
        prodavaonica1.prodajArtikl("7-9-2020", "A2", 3);

        prodavaonica1.prodajArtikl("1-9-2020", "A3", 1);
        prodavaonica1.prodajArtikl("2-9-2020", "A3", 2);
        prodavaonica1.prodajArtikl("3-9-2020", "A3", 3);
        prodavaonica1.prodajArtikl("4-9-2020", "A3", 4);
        prodavaonica1.prodajArtikl("5-9-2020", "A3", 5);
        prodavaonica1.prodajArtikl("6-9-2020", "A3", 6);
        prodavaonica1.prodajArtikl("7-9-2020", "A3", 7);

        for (Artikl artikl : prodavaonica1.getArtikli()){
            System.out.println(artikl.toString());
            System.out.println(artikl.prosjekProdaje());
        }

        Set<String> artikliZaNaruciti = new HashSet<>();
        artikliZaNaruciti.add("A1");
        artikliZaNaruciti.add("A2");
        artikliZaNaruciti.add("A3");

        prodavaonica1.makeNarudzba(artikliZaNaruciti, 5);

        
    }
}
