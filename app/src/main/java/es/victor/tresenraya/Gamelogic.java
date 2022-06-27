package es.victor.tresenraya;

import android.net.wifi.hotspot2.pps.HomeSp;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Gamelogic {
    private int[][] gameBoard;
    private int player = 1;
    private Button playAgainBTN;
    private Button homeBTN;
    private TextView playerTurn;
    private String [] playerNames = {"Jugador1", "Jugador2"};

    Gamelogic(){
        gameBoard = new int [3][3];
        for (int r=0; r<3; r++){
            for (int c=0; c<3; c++){
                gameBoard [r] [c] = 0;
            }
        }
    }

    public boolean updateGameBoard(int row, int col){
        if (gameBoard[row-1][col-1] == 0){
            gameBoard[row-1][col-1] = player;

            if(player == 1){
                playerTurn.setText(("Turno de " + playerNames[1]));
            }else{
                playerTurn.setText(("Turno de " + playerNames[0]));
            }

            return true;
        }else { return false; }
    }

    public boolean winnerCheck(){
        boolean isWinner = false;
        for(int r = 0; r<3; r++){
            if(gameBoard[r][0] == gameBoard[r][1] && gameBoard[r][0] == gameBoard[r][2] && gameBoard[r][0] != 0){
                isWinner = true;
            }
        }
        for(int c = 0; c<3; c++){
            if(gameBoard[0][c] == gameBoard[1][c] && gameBoard[0][c] == gameBoard[2][c] && gameBoard[0][c] != 0){
                isWinner = true;
            }
        }

        if(gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2] && gameBoard[0][0] != 0) {
            isWinner = true;
        }
        if(gameBoard[2][0] == gameBoard[1][1] && gameBoard[2][0] == gameBoard[0][2] && gameBoard[2][0] != 0) {
            isWinner = true;
        }

        int tablerolleno = 0;

        for(int r = 0; r<3; r++){
            for(int c = 0; c<3; c++){
                if (gameBoard[r][c] != 0){
                    tablerolleno += 1;
                }
            }
        }

        if (isWinner){
            playAgainBTN.setVisibility(View.VISIBLE);
            homeBTN.setVisibility(View.VISIBLE);
            playerTurn.setText(("Ganador: " + playerNames[player-1]));
            return true;
        }
        else if (tablerolleno == 9){
            playAgainBTN.setVisibility(View.VISIBLE);
            homeBTN.setVisibility(View.VISIBLE);
            playerTurn.setText("Empate");
            return true;
        }
        else {return false;}
    }

    public void resetGame(){
        for (int r=0; r<3; r++) {
            for (int c = 0; c < 3; c++) {
                gameBoard[r][c] = 0;
            }
        }

        player = 1;

        playAgainBTN.setVisibility(View.GONE);
        homeBTN.setVisibility(View.GONE);
        playerTurn.setText(("Turno de " + playerNames[0]));
    }

    public void setPlayAgainBTN(Button playAgainBTN){
        this.playAgainBTN = playAgainBTN;
    }

    public void setHomeBTN(Button homeBTN){
        this.homeBTN = homeBTN;
    }

    public void setPlayerTurn(TextView playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setPlayerNames(String[] playerNames){
        this.playerNames = playerNames;
    }

    public int [][] getGameBoard() {
        return gameBoard;
    }

    public void setPlayer(int player){
        this.player = player;
    }
    public int getPlayer(){
        return player;
    }
}
