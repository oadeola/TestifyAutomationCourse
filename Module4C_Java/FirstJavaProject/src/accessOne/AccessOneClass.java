package accessOne;

import accessTwo.AccessTwoClass;

public class AccessOneClass {
    static void main(String[] args) {
        AccessTwoClass access2 = new AccessTwoClass();
        access2.accessTwoMethod();

    }
}
