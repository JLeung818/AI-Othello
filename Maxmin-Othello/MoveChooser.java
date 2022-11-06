import java.util.ArrayList;  

public class MoveChooser {
  
    public static Move chooseMove(BoardState boardState){

	int depth= Othello.searchDepth;
    double alp = Double.NEGATIVE_INFINITY;
    double bet = Double.POSITIVE_INFINITY;


        ArrayList<Move> moves= boardState.getLegalMoves();
        if(moves.isEmpty()){
            return null;
	}
    double maxVal = Double.NEGATIVE_INFINITY;
    Move bestMove = moves.get(0);
    Move currentMove = moves.get(0);

    for (int i = 0; i < moves.size(); i++){
        BoardState node1 = boardState.deepCopy();
        currentMove = moves.get(i);
        node1.makeLegalMove(moves.get(i).x, moves.get(i).x);

        double best = minimax(node1, node1.colour, depth, alp, bet);
        if(best > maxVal){
            maxVal = best;
            bestMove = currentMove;
        }
    }return bestMove;
    }

    public static double playerScore(BoardState boardState){
        int whitePiece = 0;
        int blackPiece = 0;

        int[][] score = {
            {120, -20, 20,  5,  5, 20, -20, 120},
            {-20, -40, -5, -5, -5, -5, -40, -20},
            {20,   -5, 15,  3,  3, 15,  -5,  80},
            { 5,   -5,  3,  3,  3,  3,  -5,   5},
            { 5,   -5,  3,  3,  3,  3,  -5,   5},
            {20,   -5, 15,  3,  3, 15,  -5,  80},
            {-20, -40, -5, -5, -5, -5, -40, -20},
            {120, -20, 20,  5,  5, 20, -20, 120},
            };

            for (int i = 0; i<8; i++){
                for (int j = 0; j<8; j++){
                    if(boardState.getContents(i, j) == 1){
                        whitePiece = whitePiece + score[i][j];
                    }
                    else if (boardState.getContents(i, j) == -1){
                        blackPiece = blackPiece + score[i][j];
                    }
                }
            }
            return (whitePiece - blackPiece);
    }

    public static double minimax(BoardState boardState, int player, int depth, double alp, double bet){
        alp = Double.NEGATIVE_INFINITY;
        bet = Double.POSITIVE_INFINITY;
        ArrayList<Move> moves = boardState.getLegalMoves();

        if(depth == 0){
            return playerScore(boardState);
        }
        if(moves.size() == 0){
            return minimax(boardState, player * -1, depth - 1, alp, bet);
        }

        else if (player == 1){
            for(int i = 0; i<moves.size(); i++){
                BoardState node1 = boardState.deepCopy();
                node1.makeLegalMove(moves.get(i).x , moves.get(i).y);
                double best = minimax(node1, player, depth -1, alp, bet);

                if(best > alp){
                    alp = best;
                } 
            }return alp;
        }
        else if(player == -1){
            for (int i = 0; i<moves.size(); i ++){
                BoardState node1 = boardState.deepCopy();
                node1.makeLegalMove(moves.get(i).x, moves.get(i).y);
                double best = minimax(node1, player, depth -1, alp, bet);
                if(best<bet){
                    bet = best;
                }
            }return bet;
        }return -1;
    }
}
















 