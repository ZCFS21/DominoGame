public class Domino {
    private int LSide;
    private int RSide;

    public Domino(int s1, int s2) {
        this.LSide = s1;
        this.RSide = s2;
    }

    public int getLSide(){
        return LSide;
    }

    public int getRSide(){ return RSide; }

    public void flip(){
        int temp = this.LSide;
        this.LSide = this.RSide;
        this.RSide = temp;
    }
    public boolean match(Domino OtherPiece){
        int s1 = OtherPiece.getRSide();
        int s2 = OtherPiece.getLSide();
        if(LSide == s1 || RSide == s2 || LSide == s2 || RSide == s1){
            return true;
        }
        return false;
    }
    public String getDomino(){
        return "(" + LSide + "," + RSide + ")";
    }
}
