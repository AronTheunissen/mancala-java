package mancala.domain;

public class Bowl extends Kalaha {

    public Bowl(String player1, String player2) {
        super(player1, player2);
        this.content = 4;
        this.getPlayer().setBowl(this);
    }

    protected Bowl(Bowl first, int index, Player player) {
        super(first, index, player);
        this.content = 4;
        if(index == 7){
            this.getPlayer().setBowl(this);
        }
    }

    @Override
    protected void receiveStones(int beads, Player player){
        this.content++;
        beads--;
        if(beads != 0){
            this.getNeighbor().receiveStones(beads, player);
        } else {
            this.tryToSteal(player);
            player.endTurn();
        }
    }

    @Override
    protected Bowl getOpposite(){
        return (Bowl) this.getNeighbor().getOpposite().getNeighbor();
    }

    @Override
    protected void takeStolen(int stolen){
        this.getNeighbor().takeStolen(stolen);
    }

    @Override
    protected int getScore(){
        return this.content + this.getNeighbor().getScore();
    }

    @Override
    protected boolean isSideEmpty(){
        return this.getContent() == 0 && this.getNeighbor().isSideEmpty();
    }

    public void play(){
        if(this.getPlayer().hasTurn() && this.content != 0){
            int beads = this.content;
            this.content = 0;
            this.getNeighbor().receiveStones(beads, this.getPlayer());
        }
    }

    private void tryToSteal(Player whoPlayed){
        if(this.content == 1 && this.getPlayer() == whoPlayed && this.getOpposite().getContent() != 0){
            this.getNeighbor().takeStolen(this.getOpposite().beStolen()+this.content);
            this.content = 0;
        }
    }

    protected int beStolen(){
        int stolen=this.content;
        if(stolen != 0){ this.content = 0; }
        return stolen;
    }
}

