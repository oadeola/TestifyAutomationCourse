package javaConstructors;

import org.w3c.dom.ls.LSOutput;

public class Woman {
    static void main(String[] args) {
       // Human fairyBeing = new Human("zeus", 5, true);

        //System.out.println(fairyBeing);

        Human lola = new Human();
        String lolaThroat = lola.getThroat();
        //System.out.println(lolaThroat);
        int lolaLegs = lola.getLegs();
        System.out.println(lolaLegs);
    }
}
