public class SquareShapeB {

        public static void main(String[] args) {

            // Create object of Class A
            SquareShapeA square = new SquareShapeA();

            // Set the length and breadth
            square.setShapeLength(6);
            square.setShapeBreadth(6);

            // Get the length and breadth
            double length = square.getShapeLength();
            double breadth = square.getShapeBreadth();

            // Calculate the area
            double area = length * breadth;

            // Print final result
            System.out.println(
                    "The area of a square of length: "
                            + length
                            + " and breadth "
                            + breadth
                            + " is "
                            + area
            );
        }
    }

