package com.svetlicic.filip.model;

import java.text.SimpleDateFormat;
import java.util.*;

public class Prodavaonica {

    private final String naziv;
    private Set<Artikl> artikli;

    public Prodavaonica(String naziv) {
        this.naziv = naziv;
        this.artikli = new HashSet<>();
    }

    public Set<Artikl> getArtikli() {
        return artikli;
    }

    public boolean dodajArtikl(Artikl noviArtikl){
        if(noviArtikl == null){
            return false;
        }
        if(artikli.size() == 0){
            artikli.add(noviArtikl);
            return true;
        }
        for(Artikl artikl : artikli ){
            if(artikl.getNaziv().equalsIgnoreCase(noviArtikl.getNaziv())){
                artikl.setZaliha(noviArtikl.getZaliha());
            } else {
                artikli.add(noviArtikl);
            }
            return true;
        }
        return false;
    }

    public boolean prodajArtikl(String datum, String imeArtikla, int kolicina){

        if(datum.equals("") || kolicina <= 0){
            return false;
        }

        for(Artikl artikl : artikli){
            if(artikl.getNaziv().equalsIgnoreCase(imeArtikla)){
                if(artikl.getZaliha() >= kolicina){
                    artikl.setZaliha(-kolicina);
                    artikl.dodajUListuProdanihArtikala(datum, kolicina);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean makeNarudzba(int brojDana){

        if(brojDana <= 0){
            return false;
        }

        Narudzba narudzba = new Narudzba(brojDana);
        System.out.println(narudzba.toString());
        return true;
    }

    private final class Narudzba {

        private final Zaglavlje zaglavlje;
        private final Stavka stavka;

        public Narudzba(int brojDana) {
            this.zaglavlje = new Zaglavlje();
            this.stavka = new Stavka(brojDana);
        }

        @Override
        public String toString() {
            return "Narudzba{\n" +
                      zaglavlje.toString() +
                    "\n" + stavka.toString() +
                    '}';
        }

        private final class Zaglavlje {
            private final String imeProdavaonice;
            private final String datum;

            public Zaglavlje() {
                this.imeProdavaonice = naziv;
                this.datum = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            }

            public String getImeProdavaonice() {
                return imeProdavaonice;
            }

            public String getDatum() {
                return datum;
            }

            @Override
            public String toString() {
                return "Zaglavlje{" +
                        "imeProdavaonice='" + imeProdavaonice + '\'' +
                        ", datum='" + datum + '\'' +
                        '}';
            }
        }

        private final class Stavka {
            private final Map<String, Integer> artikliZaNarudzbu;

            public Stavka(int brojDana) {
                artikliZaNarudzbu = new HashMap<>();
                for(Artikl artikl : artikli){
                        int kolicina = ((int)Math.round(artikl.prosjekProdaje()) * brojDana) - artikl.getZaliha();
                        if(kolicina > 0){
                            artikliZaNarudzbu.put(artikl.getNaziv(), kolicina);
                        } else {
                            System.out.println("Artikl " + artikl.getNaziv() + " ne treba naruciti");
                        }
                }
            }

            @Override
            public String toString() {
                StringBuilder ispis = new StringBuilder("Stavka{\n");
                for (String key : artikliZaNarudzbu.keySet()){
                    ispis.append(key).append(" : ").append(artikliZaNarudzbu.get(key)).append("\n");
                }
                ispis.append("}");
                return ispis.toString();
            }
        }

    }


}
