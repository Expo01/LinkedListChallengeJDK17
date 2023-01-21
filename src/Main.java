
import java.util.LinkedList;
import java.util.Scanner;

record Place(String name, int distance) {  //automatically has constructor, accessor methods and overridden toString method
// the default overrided toString method will print record in the format "[Place[name=Sydney, distance=0]"

    @Override
    public String toString() { //overriding the toString method in the record to get more readable format "Sydney (0)"
        return String.format("%s (%d)", name, distance);
    }
}

public class Main {

    public static void main(String[] args) {

        LinkedList<Place> placesToVisit = new LinkedList<>();

        Place adelaide = new Place("Adelaide", 1374);
        addPlace(placesToVisit, adelaide);
        addPlace(placesToVisit, new Place("adelaide", 1373));
        addPlace(placesToVisit, new Place("Brisbane", 917));
        addPlace(placesToVisit, new Place("Perth", 3923));
        placesToVisit.addFirst(new Place("Sydney", 0));
        System.out.println(placesToVisit);


    var iterator = placesToVisit.listIterator();
    Scanner scanner = new Scanner(System.in);
    boolean quitLoop = false;
    boolean forward = true;

    printMenu();

        while (!quitLoop) {
        if (!iterator.hasPrevious()) {
            System.out.println("Originating : " + iterator.next());
            forward = true;
        }
        if (!iterator.hasNext()) {
            System.out.println("Final : " + iterator.previous());
            forward = false;
        }
        System.out.print("Enter Value: ");
        String menuItem = scanner.nextLine().toUpperCase().substring(0, 1); //accepts user input, converts to upper case
            // then specifies that it will only accept the character at index 0 of the input (index 1 not inclusive)
            // to remove extraneous input

        switch (menuItem) {
            case "F":
                System.out.println("User wants to go forward");
                if (!forward) {           // Reversing Direction
                    forward = true;
                    if (iterator.hasNext()) {
                        iterator.next();  // Adjust position forward then proceeds to second if statement
                    }
                }

                if (iterator.hasNext()) {
                    System.out.println(iterator.next());
                }

                break;

            case "B":
                System.out.println("User wants to go backwards");
                if (forward) {           // Reversing Direction
                    forward = false;
                    if (iterator.hasPrevious()) {
                        iterator.previous();  // Adjust position backwards then proceeds to next if statement
                    }
                }

                if (iterator.hasPrevious()) {
                    System.out.println(iterator.previous());
                }
                break;

            case "M":
                printMenu();
                break;

            case "L":
                System.out.println(placesToVisit);
                break;

            default:
                quitLoop = true;
                break;
        }
    }

}

    private static void addPlace(LinkedList<Place> list, Place place) {

//        if (list.contains(place)) {  //checks for all record contents including exact name and distance
//            System.out.println("Found duplicate: " + place);
//            return;
//        }

        for (Place p : list) { //checks for all cases of name only. Code runs the same without list.contains because
            // we don't care if there are any duplicate distances but we do care about duplicate names which
            // .name().equalsIgnoreCase appears much more comprehensive with
            if (p.name().equalsIgnoreCase(place.name())) {
                System.out.println("Found duplicate: " + place);
                return; //(return true)
            }
        }

        int matchedIndex = 0; // keeps track of index we are processin and starts at 0
        for (var listPlace: list) {
            if (place.distance() < listPlace.distance()) { //this loops through elements in the LinkedList and compares
                // the distance field of the established element in the LinkedList to the distance field of the Place object that we are attempting
                // to correctly order in the list. If it is of less distance than the established element, it is added
                list.add(matchedIndex, place);
                return;
            }

            matchedIndex++;
        }

        list.add(place);
        // if we get to the end of the list and there is no established element of a lesser distance, the new element is
        // added at the last index
    }

    private static void printMenu() {

        System.out.println("""
                Available actions (select word or letter):
                (F)orward
                (B)ackwards
                (L)ist Places
                (M)enu
                (Q)uit""");
    }
}