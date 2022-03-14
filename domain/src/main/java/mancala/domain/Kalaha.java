package mancala.domain;

public class Kalaha {

    private Player player;
    private Kalaha neighbor;
    protected int content;

    protected Kalaha(String player1, String player2) {
        this.content = 0;
        this.player = new Player(player1, player2);
        this.neighbor = new Bowl((Bowl) this, 1, this.player);
    }

    protected Kalaha(Bowl first, int index, Player player) {
        this.content = 0;
        if (index < 7) {
            this.player = player;
        } else {
            this.player = player.getOpponent();
        }
        this.setNeighbor(first, index, player);
    }

    private void setNeighbor(Bowl first, int index, Player player) {
        if (index == 13) {
            this.neighbor = first;
        } else {
            this.makeNeighbor(first, index, player);
        }
    }

    private void makeNeighbor(Bowl first, int index, Player player) {
        if (index == 12 || index == 5) {
            this.neighbor = new Kalaha(first, index + 1, player);
        } else {
            this.neighbor = new Bowl(first, index + 1, player);
        }
    }

    protected int getContent() {
        return this.content;
    }

    protected Player getPlayer() {
        return this.player;
    }

    public Kalaha getNeighbor() {
        return this.neighbor;
    }

    public Kalaha getNeighbor(int i) {
        return i == 0 ? this : this.neighbor.getNeighbor(i - 1);
    }

    protected void receiveStones(int beads, Player player) { //end game
        if (this.getPlayer() == player) {
            this.content++;
            beads--;
        }
        if (beads != 0) {
            this.getNeighbor().receiveStones(beads, player);
        }
    }

    protected Kalaha getOpposite() {
        return this;
    }

    protected void takeStolen(int stolen) {
        this.content += stolen;
    }

    protected int getScore() {
        return this.content;
    }

    protected boolean isSideEmpty() {
        return true;
    }
}
