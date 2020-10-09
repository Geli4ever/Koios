package com.svetlicic.filip;

import java.text.SimpleDateFormat;
import java.util.*;

public class Prodavaonica {

    private final String ime;
    private Set<Artikl> artikli;

    public Prodavaonica(String ime) {
        this.ime = ime;
        this.artikli = new HashSet<>();
    }

    public Set<Artikl> getArtikli() {
        return artikli;
    }

    public boolean dodajArtikl(Artikl noviArtikl){
        if(artikli.size() == 0){
            artikli.add(noviArtikl);
            return true;
        }
        for(Artikl artikl : artikli ){
            if(artikl.getIme().equalsIgnoreCase(noviArtikl.getIme())){
                System.out.println("Artikl vec postoji!");
            } else {
                artikli.add(noviArtikl);
                return true;
            }
        }
        return false;
    }

    public boolean prodajArtikl(String datum, String imeArtikla, int kolicina){

        for(Artikl artikl : artikli){
            if(artikl.getIme().equalsIgnoreCase(imeArtikla)){
                if(artikl.getZaliha() >= kolicina && kolicina > 0){
                    artikl.setZaliha(-kolicina);
                    artikl.dodajUListuProdanihArtikala(datum, kolicina);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean makeNarudzba(Set<String> imenaArtikala, int brojDana){
        Narudzba narudzba = new Narudzba(imenaArtikala, brojDana);
        System.out.println(narudzba.toString());
        return true;
    }

    private final class Narudzba {

        private final Zaglavlje zaglavlje;
        private final Stavka stavka;

        public Narudzba(Set<String> imenaArtikala, int brojDana) {
            this.zaglavlje = new Zaglavlje();
            this.stavka = new Stavka(imenaArtikala, brojDana);
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
                this.imeProdavaonice = ime;
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

            public Stavka(Set<String> imenaArtikala, int brojDana) {
                artikliZaNarudzbu = new HashMap<>();
                for(Artikl artikl : artikli){
                    if(imenaArtikala.contains(artikl.getIme())){
                        int kolicina = ((int)Math.round(artikl.prosjekProdaje()) * brojDana) - artikl.getZaliha();
                        if(kolicina < 0){
                            kolicina = 0;
                        }
                        artikliZaNarudzbu.put(artikl.getIme(), kolicina);
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
