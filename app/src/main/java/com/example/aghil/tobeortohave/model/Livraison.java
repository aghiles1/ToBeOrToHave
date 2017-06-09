package com.example.aghil.tobeortohave.model;

import com.example.aghil.tobeortohave.adapter.LivAdapter;

/**
 * Created by aghil on 17/05/2017.
 */

public class Livraison {
    private int temps;
    private int id;
    private String liv;
    private double currentTime;
    public Livraison(int id,String liv,int temps,double currentTime){
        this.id = id;
        this.liv = liv;
        this.temps =temps;
        this.currentTime = currentTime/60000.0;
    }

    public int getTemps() {
        return temps;
    }

    public int getId() {
        return id;
    }

    public String getLiv() {
        return liv;
    }

    public double getCurrentTime() {
        return currentTime;
    }
}
