package Lab1.Zadaca3;

import java.util.Scanner;

class RabotnaNedela{

    private int [] casovi;
    private int brNedela;

    public RabotnaNedela(int[] casovi, int brNedela) {
        super();
        this.casovi = casovi;
        this.brNedela = brNedela;
    }
    @Override
    public String toString() {
        String s="";
        int sum=0;
        for(int i=0;i<casovi.length;i++){
            sum+=casovi[i];
        }
        s+=sum+"   ";
        return s;
    }
    public int getVkupnoCasovi(){
        int sum=0;
        for (int i=0;i<casovi.length;i++){
            sum+=casovi[i];
        }
        return sum;
    }

    public void setCasovi(int[] casovi) {
        this.casovi = casovi;
    }

    public void setBrNedela(int brNedela) {
        this.brNedela = brNedela;
    }
}

class Rabotnik{

    private String ime;
    private RabotnaNedela [] nedeli;

    public Rabotnik(String ime, RabotnaNedela[] nedeli) {
        super();
        this.ime = ime;
        this.nedeli = nedeli;
    }
    @Override
    public String toString() {
        String s=ime+"   ";
        for(int i=0;i<nedeli.length;i++){
            s+=nedeli[i].toString();
        }
        return s;
    }

    public RabotnaNedela[] getNedeli() {
        return nedeli;
    }
    public RabotnaNedela getNedela(int indx) {
        return nedeli[indx];
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setNedeli(RabotnaNedela[] nedeli) {
        this.nedeli = nedeli;
    }
}

public class WorkingTime {

    static int sumNedeli(Rabotnik r){
        int sum=0;
        for(int i=0;i<r.getNedeli().length;i++){
            sum+=r.getNedela(i).getVkupnoCasovi();
        }
        return sum;
    }

    public static Rabotnik najvreden_rabotnik(Rabotnik [] niza)
    {
        Rabotnik rab = niza[0];
        for(int i=0;i<niza.length;i++){
            int sum=sumNedeli(niza[i]);
            if(sum > sumNedeli(rab)){
                rab=niza[i];
            }
        }
        return rab;
    }

    public static void table(Rabotnik [] niza){
        System.out.println("Rab   1   2   3   4   Vkupno");
        for(int i=0;i<niza.length;i++){
            System.out.println(niza[i].toString() + sumNedeli(niza[i]));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n=scan.nextInt();
        Rabotnik [] rabotnici = new Rabotnik[n];

        for(int i=0;i<n;i++){
            String name=scan.next();
            RabotnaNedela []  nedela = new RabotnaNedela[4];
            for(int j=0;j<4;j++){
                //scanRabNedela
                int [] cas = new int[5];
                for(int k=0; k<5 ;k++){
                    cas[k]=scan.nextInt();
                }
                nedela[j]=new RabotnaNedela(cas,j+1);
            }
            rabotnici[i]=new Rabotnik(name, nedela);
        }
        table(rabotnici);
        System.out.println("NAJVREDEN RABOTNIK: " +najvreden_rabotnik(rabotnici).getIme());
    }
}

