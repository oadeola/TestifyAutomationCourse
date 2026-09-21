package javaConstructors;

public class Human {
//    String body;
//    int wings;
//    boolean powers;
//
//    public Human(String Pbody){
//        body = Pbody;
//    }
//    public Human(String Pbody, int Pwings){
//        wings = Pwings;
//    }
//
//    public Human(String Pbody, int Pwings, boolean Ppowers){
//        powers = Ppowers;

        private String throat = "short throat";
        private int legs = 2;
        private String stomach;
        String mouth;

    public String getThroat() {
        return throat;
    }

    public int getLegs() {
        return legs;
    }

    public void setThroat(String throat) {
        this.throat = throat;
    }

    public String getStomach() {
        return stomach;
    }

    public void setStomach(String stomach) {
        this.stomach = stomach;
    }

    public String getMouth() {
        return mouth;
    }

    public void setMouth(String mouth) {
        this.mouth = mouth;
    }
}

//    public void printHuman(){
//    System.out.println(body);
//}

