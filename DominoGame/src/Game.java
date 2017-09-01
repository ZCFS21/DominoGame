import java.util.Iterator;
import java.util.Scanner;

public class Game {

    private final static int PLAYER = 0;
    private final static int Comp = 1;

    public static void main(String[] args){
        DominoSet DomSet = new DominoSet();
        GameBoard board = new GameBoard(DomSet);

        Scanner input = new Scanner(System.in);

        Boolean flag = true;
        while(flag){
            //user input variables
            flag = playerTurn(board,input);
    //        flag = computerTurn(board);
        }

    }
 /*   public static Boolean computerTurn(GameBoard board){
        Iterator<Domino> compHand = board.Set.compHand.iterator();

        while(compHand.hasNext()){
            Domino current = compHand.next();
        }
    }*/

    public static boolean playerTurn(GameBoard board, Scanner input){
        int playPiece;
        String position;

        //print the board and hands
        System.out.println("Player hand");
        board.Set.printSet(board.Set.playerHand);
        System.out.println("Board");
        board.printBoard();

        //user picks domino
        System.out.print("pick Domino: ");
        playPiece = input.nextInt();

        //user picks where to place domino
        System.out.println("Selected Piece " + board.Set.playerHand.get(playPiece).getDomino());
        System.out.println("pick position (left/right):");
        input.nextLine(); //consume remaining "\n"
        position = input.nextLine().toLowerCase();
        switch(position) {
            case "left": board.setLeft(playPiece,PLAYER);
                break;
            case "right": board.setRight(playPiece,PLAYER);
                break;
            default:
                System.out.println("Exiting");
                return false;
        }

        return true;
    }
}
