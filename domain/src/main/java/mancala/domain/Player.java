package mancala.domain;

public class Player {

    private String name;
    private Player opponent;
    private boolean onTurn;
    private Bowl bowl;

    protected Player(String name, String opponentName) {
        this.opponent = new Player(this, opponentName);
        this.onTurn = true;
        this.name = name;
    }

    private Player(Player opponent, String name) {
        this.opponent = opponent;
        this.onTurn = false;
        this.name = name;
    }

    public Player getOpponent() {
        return this.opponent;
    }

    public String getName() {
        return this.name;
    }

    public boolean hasTurn() {
        return this.onTurn;
    }

    protected void setBowl(Bowl bowl) {
        this.bowl = bowl;
    }

    protected Bowl getBowl() {
        return this.bowl;
    }

    protected void endTurn() {
        this.onTurn = false;
        if (this.canStillPlay() && this.opponent.canStillPlay()) {
            opponent.takeTurn();
        }
    }

    private void takeTurn() {
        this.onTurn = true;
    }

    protected boolean canStillPlay() {
        return !this.bowl.isSideEmpty();
    }

    public int getScore() {
        return this.bowl.getScore();
    }

    public String whoWon() {
        if (!(this.canStillPlay() && this.getOpponent().canStillPlay())) {
            return this.getScore() > this.opponent.getScore() ? this.name : this.opponent.getName();
        }
        return "no-one";
    }
}

