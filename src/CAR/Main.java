package CAR;

public class Main {
    public static void main(String[] args) {
        Car[] ca = {
                new Car("Zaz968", 1975, 400000, "white"),
                new Car("Vaz2101", 1969, 600000, "red"),
                new Car("Gaz24", 1989, 500001, "black"),
                new Car("Kamaz5320", 1985, 120000, "green")
        };

        DynamicArray dynamicArray = new DynamicArray(ca);

        dynamicArray.readFromFile("C:\\2ND SEM. CODES\\CW2.info\\src\\input.txt");
        dynamicArray.sort(); // Sort the elements by year of release
        dynamicArray.display(); // Display all elements on the screen

        dynamicArray.removeHighMileageCars(500000); // Remove elements with mileage > 500,000

        dynamicArray.writeToFile("output.txt"); // Write the resulting set to another text file
    }
}