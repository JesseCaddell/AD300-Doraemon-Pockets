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
    public void addItem(String itemName) {
        pocket.add(itemName);

    }

    @Override
    public void removeItem(String itemName) {
        if (pocket.contains(itemName)) {
            pocket.remove(itemName);
        }
        else {
            System.out.println("I'm sorry, no item of this name exists...");
        }


    }

    @Override
    public void printItems() {
        for (int index = 0; index < pocket.size(); ++index) {
            System.out.println("You have a " + pocket.get(index) + " in the pocket!");
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
        doraemon.addItem("Raygun");
        doraemon.printItems();
    }
}
