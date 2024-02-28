package com.example.elearning.models;

import java.util.ArrayList;

public class A {
    private ArrayList<Statistique>stat;
    private int total;

    public A(ArrayList<Statistique>s){

        this.setStat(s);

        int rep=0;

        for (int i=0;i<stat.size();i++){

            rep=rep+stat.get(i).getTotalnombre();

        }

        this.setTotal(rep);
    }

    public ArrayList<Statistique> getStat() {
        return stat;
    }

    public void setStat(ArrayList<Statistique> stat) {
        this.stat = stat;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
