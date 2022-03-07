package mancala.domain;

import java.lang.ClassCastException;

public class MancalaImpl implements Mancala {

    Bowl bowl;

    public MancalaImpl() {

        this.bowl = new Bowl("player1", "player2");
    }


    @Override
    public boolean isPlayersTurn(int playerNumber) {
        Player player = bowl.getPlayer();
        if(playerNumber == 1) {
            return player.hasTurn();
        }
        else if(playerNumber == 2) {
            return player.hasTurn();
        }
        else {
            return false;
        }
    }

    @Override
	public void playPit(int index) throws MancalaException {
        try {
            ((Bowl) bowl.getNeighbor(index)).play();
        } catch(ClassCastException e){
          throw new MancalaException("Kalaha kan niet gespeeld worden.");
        }
    }
	
	@Override
	public int getStonesForPit(int index) {
        return bowl.getNeighbor(index).getContent();
    }

	@Override
	public boolean isEndOfGame() {
        if(!(bowl.getPlayer().hasTurn() || bowl.getPlayer().getOpponent().hasTurn())){
            return true;
        }
            return false;
    }

	@Override
	public int getWinner() {
        if (isEndOfGame()) {
            if (bowl.getPlayer().whoWon() == "player1") {
                return Mancala.PLAYER_ONE;
            } else if (bowl.getPlayer().whoWon() == "player2") {
                return Mancala.PLAYER_TWO;
            } else {
                return Mancala.BOTH_PLAYERS;
            }
        }
        return Mancala.NO_PLAYERS;
    }
}