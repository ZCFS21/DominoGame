import java.util.ArrayList;
import java.util.Iterator;

public class GameBoard {
    private final int PLAYER = 0;
    private final int COMP = 1;
    private final int TOP = 1;
    private final int BOT = 2;
    ArrayList<Domino> TopRow = new ArrayList<>(28);
    ArrayList<Domino> BotRow = new ArrayList<>(28);
    DominoSet Set;
    int middle;
    private int left;
    private int right;
    private int shift = TOP;
    public GameBoard() {
    }

    public GameBoard(DominoSet set) {
        Set = set;
    }

    public void addInitial(int index) {
        BotRow.add(0, Set.getPiece(index, PLAYER));
        middle = 0;
        left = TOP;
        right = TOP;
    }

    public void setLeft(int index, int player){
        if(left == TOP){
            addTopLeft(index,player);
            left = BOT;
            shift = BOT;
        }else if(left == BOT){
            addBotLeft(index,player);
            left = TOP;
            shift = TOP;
        }else{
            addInitial(index);
        }
    }

    public void setRight(int index, int player){
        if(right == TOP){
            addTopRight(index,player);
            right = BOT;
        }else if(right == BOT){
            addBotRight(index,player);
            right = TOP;
        }else{
            addInitial(index);
        }
    }
    private void addTopLeft(int index, int player){
        Domino matchingPiece = BotRow.get(0);
        Domino newPiece = Set.getPiece(index, player);

        //match the domino sides

        if (matchingPiece.getLSide() != newPiece.getRSide()) {
            newPiece.flip();
        }
        try {
            if (matchingPiece.getLSide() != newPiece.getRSide()) {
                throw new Exception("Invalid Move");
            }
            TopRow.add(0, newPiece);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Set.addFailedPiece(index, newPiece, player);
        }
    }

    private void addTopRight(int index, int player) {
        Domino matchingPiece = BotRow.get(BotRow.size() - 1);
        Domino newPiece = Set.getPiece(index, player);

        //match the domino sides
        if (matchingPiece.getRSide() != newPiece.getLSide()) {
            newPiece.flip();
        }
        try {
            if (matchingPiece.getRSide() != newPiece.getLSide()) {
                throw new Exception("Invalid Move");
            }
            TopRow.add(newPiece);

        } catch (Exception e) { // incorrect move
            System.out.println("error " + e.getMessage());
            Set.addFailedPiece(index, newPiece, player);
        }
    }

    private void addBotRight(int index, int player) {
        Domino matchingPiece = TopRow.get(TopRow.size() - 1);
        Domino newPiece = Set.getPiece(index, player);

        //match the domino sides
        if (matchingPiece.getRSide() != newPiece.getLSide()) {
            newPiece.flip();
        }

        try {
            if (matchingPiece.getRSide() != newPiece.getLSide()) {
                throw new Exception("Invalid Move");
            }
            BotRow.add(newPiece);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Set.addFailedPiece(index, newPiece, player);
        }
    }

    private void addBotLeft(int index, int player) {
        Domino matchingPiece = TopRow.get(0);
        Domino newPiece = Set.getPiece(index, player);

        //match the Domino Sides
        if (matchingPiece.getLSide() != newPiece.getRSide()) {
            newPiece.flip();
        }
        try {
            if (matchingPiece.getLSide() != newPiece.getRSide()) {
                throw new Exception("Invalid Move");
            }
            BotRow.add(0, newPiece);
            middle++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Set.addFailedPiece(index, newPiece, player);
        }
    }

    public void printBoard() {

        Iterator<Domino> TopIt = TopRow.iterator();
        Iterator<Domino> BotIt = BotRow.iterator();
        if(shift == TOP){
            System.out.print("   ");
        }
        while (TopIt.hasNext()) {
            System.out.print(TopIt.next().getDomino());
        }
        System.out.print("\n");
        if(shift == BOT){
            System.out.print("   ");
        }
        while (BotIt.hasNext()) {
            System.out.print(BotIt.next().getDomino());
        }
        System.out.println("\n");
    }
}

