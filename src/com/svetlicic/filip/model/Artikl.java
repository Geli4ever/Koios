package com.svetlicic.filip.model;

import java.util.*;

public class Artikl {

    private final String naziv;
    private double cijena;
    private int zaliha;
    private Map<String, Integer> listaProdanihArtikala;

    public Artikl(String naziv, double cijena) {
        this.naziv = naziv;
        this.cijena = cijena;
        this.zaliha = 0;
        this.listaProdanihArtikala = new HashMap<>();
    }

    public Artikl(String naziv, double cijena, int zaliha) {
        this.naziv = naziv;
        this.cijena = cijena;
        this.zaliha = zaliha;
        this.listaProdanihArtikala = new HashMap<>();
    }

    public String getNaziv() {
        return naziv;
    }

    public double getCijena() {
        return cijena;
    }

    public int getZaliha() {
        return zaliha;
    }

    public void setZaliha(int zaliha) {
        this.zaliha += zaliha;
    }

    public void dodajUListuProdanihArtikala(String datum, int kolicina){
        int kolicinaPrijeProdaje = this.listaProdanihArtikala.getOrDefault(datum, 0);
        int novaKolicina = kolicinaPrijeProdaje + kolicina;

        this.listaProdanihArtikala.put(datum, novaKolicina);
    }

    public double prosjekProdaje(){

        double suma = 0.0;
        for(String imeArtikla : this.listaProdanihArtikala.keySet()){
            double kolicina = (double)this.listaProdanihArtikala.get(imeArtikla);
            suma += kolicina;
        }

        return suma/listaProdanihArtikala.size();
    }

    @Override
    public String toString() {
        return "Artikl{" +
                "ime='" + naziv + '\'' +
                ", cijena=" + cijena +
                ", zaliha=" + zaliha +
                '}';
    }
}
