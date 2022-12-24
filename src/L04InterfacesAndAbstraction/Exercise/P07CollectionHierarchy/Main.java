package L04InterfacesAndAbstraction.Exercise.P07CollectionHierarchy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AddCollection addCollection = new AddCollection();
        AddRemoveCollection addRemoveCollection = new AddRemoveCollection();
        MyListImpl myList = new MyListImpl();

        String[] input = scanner.nextLine().split("\\s+");
        int removeCount = Integer.parseInt(scanner.nextLine());

        printAdd(input, addCollection);
        printAdd(input, addRemoveCollection);
        printAdd(input, myList);

        printRemove(removeCount, addRemoveCollection);
        printRemove(removeCount, myList);

    }

    private static void printRemove(int counter, AddRemovable collection) { // Интересува ни този метод да може да приема само AddRemovable, защото ще използваме само
        // поведенията add(String element) и String remove(), които са типични за обектите, който имплементират AddRemovable
        for (int i = 0; i < counter; i++) {
            System.out.print(collection.remove() + " ");
        }
        System.out.println();
    }

    private static void printAdd(String[] input, Addable someAddableCollection) { // Интересува ни този метод да може да приема само Addable, защото ще използваме само
        for (String element : input) {                                    // поведението add(String element), което е типично за обектите, който имплементират Addable
            System.out.print(someAddableCollection.add(element) + " ");
        }
        System.out.println();
    }

}
