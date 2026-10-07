public class FinalClass {

        final int ballSize = 5;

        String ballColour = "Blue";

        String ballTexture = "Smooth";


        public void displayBall() {

            System.out.println("Ball Size: " + ballSize);
            System.out.println("Ball Colour: " + ballColour);
            System.out.println("Ball Texture: " + ballTexture);

            // This will NOT work because ballSize is final
            //ballSize = 15;
        }
    }

