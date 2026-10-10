package live_coding_session_7.assignment_problems.problem2;

public abstract class ArtPiece {
    private static int counter = 1000;
    private final String pieceId;

    public ArtPiece() {
        pieceId = "ART-" + (++counter);
    }

    public abstract String describe();

    public String getPieceId() {
        return pieceId;
    }
}