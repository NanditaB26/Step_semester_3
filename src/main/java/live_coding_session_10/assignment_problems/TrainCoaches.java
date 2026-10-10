
public class TrainCoaches {

    static class Node {
        String name;
        Node prev;
        Node next;

        Node(String name) {
            this.name = name;
            this.prev = null;
            this.next = null;
        }
    }

    static class DoublyLinkedList {
        Node head;
        Node tail;

        // Add a coach at the end
        void addLast(String name) {
            Node newNode = new Node(name);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
        }

        // Remove a coach by name
        void remove(String name) {
            Node current = head;

            // Find the coach
            while (current != null && !current.name.equals(name)) {
                current = current.next;
            }

            // Coach not found
            if (current == null) {
                return;
            }

            // Update the previous coach or head
            if (current.prev == null) {
                head = current.next;
            } else {
                current.prev.next = current.next;
            }

            // Update the next coach or tail
            if (current.next == null) {
                tail = current.prev;
            } else {
                current.next.prev = current.prev;
            }
        }

        // Print from engine to guard
        void printForward() {
            Node current = head;

            System.out.print("Forward: ");

            while (current != null) {
                System.out.print(current.name);
                if (current.next != null) {
                    System.out.print(" <-> ");
                }
                current = current.next;
            }

            System.out.println();
        }

        // Print from guard to engine
        void printBackward() {
            Node current = tail;

            System.out.print("Backward: ");

            while (current != null) {
                System.out.print(current.name);
                if (current.prev != null) {
                    System.out.print(" <-> ");
                }
                current = current.prev;
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList train = new DoublyLinkedList();

        train.addLast("Engine");
        train.addLast("A1");
        train.addLast("B1");
        train.addLast("B2");
        train.addLast("Guard");

        train.remove("B1");

        train.printForward();
        train.printBackward();
    }
}
