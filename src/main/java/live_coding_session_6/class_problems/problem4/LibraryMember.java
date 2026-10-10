
package live_coding_session_6.class_problems.problem4;

public class LibraryMember {
    protected String memberId;
    protected int booksBorrowed;

    public LibraryMember(String memberId, int booksBorrowed) {
        this.memberId = memberId;
        this.booksBorrowed = booksBorrowed;
    }

    public void displayInfo() {
        System.out.print("General | Books: " + booksBorrowed + " ");
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }
}
