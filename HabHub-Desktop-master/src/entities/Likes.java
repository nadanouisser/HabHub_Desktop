/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Ed
 */
public class Likes {
    private Individu individu;
    private Chien chien;

    public Likes(Individu individu, Chien chien) {
        this.individu = individu;
        this.chien = chien;
    }

    public Individu getIndividu() {
        return individu;
    }

    public void setIndividu(Individu individu) {
        this.individu = individu;
    }

    public Chien getChien() {
        return chien;
    }

    public void setChien(Chien chien) {
        this.chien = chien;
    }

    @Override
    public String toString() {
        return "likes{" + "individu=" + individu + ", chien=" + chien + '}';
    }
   
    
}
