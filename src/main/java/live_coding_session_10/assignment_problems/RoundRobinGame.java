
public class RoundRobinGame {

    static class Node {
        String name;
        Node next;

        Node(String name) {
            this.name = name;
            this.next = null;
        }
    }

    static class CircularList {
        Node tail;

        // Add a player at the end
        void addLast(String name) {
            Node newNode = new Node(name);

            if (tail == null) {
                tail = newNode;
                tail.next = tail;
            } else {
                newNode.next = tail.next;
                tail.next = newNode;
                tail = newNode;
            }
        }

        // Print players for the given number of turns
        void printTurns(int turns) {
            if (tail == null) {
                return;
            }

            Node current = tail.next; // Head

            for (int i = 0; i < turns; i++) {
                System.out.print(current.name);

                if (i < turns - 1) {
                    System.out.print(" ");
                }

                current = current.next;
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        CircularList game = new CircularList();

        game.addLast("Asha");
        game.addLast("Ravi");
        game.addLast("Neha");

        int turns = 7;

        game.printTurns(turns);
    }
}
