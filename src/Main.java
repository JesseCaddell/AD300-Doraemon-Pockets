import java.util.ArrayList;

interface Storage {
    public void addItem(String itemName);
    public void removeItem(String itemName);
    public void printItems();
    public int getNumItems();
}
class Doraemon implements Storage {

    public ArrayList<String> pocket = new ArrayList<>();

    @Override
    public void addItem(String itemName) { //can add some checks to ensure no special characters or numbers added (p1)
        pocket.add(itemName);

    }

    @Override
    public void removeItem(String itemName) { //can add same check as (p1)
        if (pocket.contains(itemName)) {
            pocket.remove(itemName);
        }
        else {
            System.out.println("I'm sorry, no item of this name exists...");
        }


    }

    @Override
    public void printItems() {
        if (pocket.size() > 0) {
            for (int index = 0; index < pocket.size(); ++index) {
                System.out.println("You have a " + pocket.get(index) + " in the pocket!");
            }
        }
        else {
            System.out.println("Doraemon's pockets are empty!");
        }

    }

    @Override
    public int getNumItems() {
        return pocket.size();
    }


}
public class Main {
    public static void main(String[] args) {
        Doraemon doraemon = new Doraemon();

        // Prepare the table header
        System.out.printf("%-15s%-15s%-20s%-20s\n", "File Size", "Operation", "Time (ms)", "Memory Usage (MB)");
        System.out.println("----------------------------------------------------------");

        // Test with 10 items
        performTest(doraemon, 10);

        // Test with 50 items
        performTest(doraemon, 50);

        // Test with 1000 items
        performTest(doraemon, 1000);

        // Test with 50000 items
        performTest(doraemon, 50000);
    }

    private static void performTest(Doraemon doraemon, int numItems) {
        // Add items
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numItems; i++) {
            doraemon.addItem("Item" + i);
        }
        long endTime = System.currentTimeMillis();
        long memoryUsageAddition = getUsedMemory();

        // Print results for addition
        System.out.printf("%-15d%-15s%-20d%-20d\n", numItems, "Addition", (endTime - startTime), memoryUsageAddition);

        // Remove items
        startTime = System.currentTimeMillis();
        for (int i = 0; i < numItems; i++) {
            doraemon.removeItem("Item" + i);
        }
        endTime = System.currentTimeMillis();
        long memoryUsageRemoval = getUsedMemory();

        // Print results for removal
        System.out.printf("%-15d%-15s%-20d%-20d\n", numItems, "Removal", (endTime - startTime), memoryUsageRemoval);

        System.out.println("---------------------------");
    }

    private static long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();

        // Run garbage collector multiple times
        for (int i = 0; i < 10; i++) {
            runtime.gc();
        }

        // Measure memory usage
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();

        return (usedMemoryAfter - usedMemoryBefore) / (1024 * 1024); // Convert to MB
    }
}
