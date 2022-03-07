package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {
    @Test
    public void A_bowl_is_emptied_when_it_is_played() {
        var bowl = new Bowl("player1","player2");
        bowl.play();
        assertEquals(0, bowl.getContent());
    }

    @Test
    public void A_bowl_passes_stones_to_its_neighbors_when_it_is_played(){
        var bowl = new Bowl("player1","player2");
        bowl.play();
        for(int i = 1; i<5; i++){
            assertEquals(5, bowl.getNeighbor(i).getContent());
        }
    }

    @Test
    public void A_bowl_cannot_be_played_when_its_player_does_not_have_the_turn(){
        var bowl = new Bowl("player1","player2");
        ((Bowl) bowl.getNeighbor(7)).play();
        assertEquals(4, bowl.getNeighbor(7).getContent());
    }

    @Test
    public void A_bowl_cannot_be_played_when_it_is_empty(){
        var bowl = new Bowl("player1","player2");
        bowl.play();
        ((Bowl) bowl.getNeighbor(7)).play();
        bowl.play();
        assertTrue(bowl.getPlayer().hasTurn());
    }

    @Test
    public void A_kalaha_accepts_stones_from_the_right_player(){
        var bowl = new Bowl("player1","player2");
        bowl.getNeighbor(6).receiveStones(1, bowl.getPlayer());
        assertEquals(1,bowl.getNeighbor(6).getContent());
    }

    @Test
    public void A_kalaha_rejects_stones_from_the_wrong_player(){
        var bowl = new Bowl("player1","player2");
        bowl.getNeighbor(13).receiveStones(1, bowl.getPlayer());
        assertEquals(0, bowl.getNeighbor(13).getContent());
    }

    @Test
    public void The_player_has_switched_turns_when_a_bowl_has_been_played(){
        var bowl = new Bowl("player1","player2");
        boolean turn = bowl.getPlayer().hasTurn();
        bowl.play();
        assertEquals(!turn, bowl.getPlayer().hasTurn());
        assertEquals(turn, bowl.getPlayer().getOpponent().hasTurn());
    }

    @Test
    public void If_the_last_bead_ends_in_an_empty_bowl_of_the_same_player_its_opposites_contents_are_stolen(){
        var bowl = new Bowl("player1","player2");
        ((Bowl) bowl.getNeighbor(5)).play();
        ((Bowl) bowl.getNeighbor(12)).play();
        bowl.play();
        assertEquals(0,bowl.getNeighbor(7).getContent());
        assertEquals(0,bowl.getNeighbor(5).getContent());
        assertEquals(7,bowl.getNeighbor(6).getContent());
    }

    @Test
    public void If_the_last_bead_ends_in_an_empty_bowl_of_a_different_player_its_opposites_contents_are_not_stolen(){
        var bowl = new Bowl("player1","player2");
        bowl.play();
        ((Bowl) bowl.getNeighbor(10)).play();
        assertEquals(0,bowl.getNeighbor(6).getContent());
        assertEquals(1,bowl.getNeighbor(13).getContent());
    }

    @Test
    public void If_a_side_becomes_empty_no_one_has_a_turn_anymore(){
        var bowl = new Bowl("player1","player2");
        playUntilEnd(bowl);
        assertFalse(bowl.getPlayer().hasTurn());
        assertFalse(bowl.getPlayer().getOpponent().hasTurn());
    }

    @Test
    public void If_a_side_becomes_empty_the_players_know_who_won(){
        var bowl = new Bowl("player1","player2");
        playUntilEnd(bowl);
        assertEquals("player2",bowl.getPlayer().whoWon());
    }

    @Test
    public void If_a_side_becomes_empty_the_players_know_their_score(){
        var bowl = new Bowl("player1","player2");
        playUntilEnd(bowl);
        assertEquals(36, bowl.getPlayer().getOpponent().getScore());
        assertEquals(12, bowl.getPlayer().getScore());
    }

    private void playUntilEnd(Bowl bowl){
        bowl.play();
        ((Bowl) bowl.getNeighbor(7)).play();
        ((Bowl) bowl.getNeighbor(1)).play();
        ((Bowl) bowl.getNeighbor(2)).play();
        ((Bowl) bowl.getNeighbor(7)).play();
        ((Bowl) bowl.getNeighbor(3)).play();
        ((Bowl) bowl.getNeighbor(7)).play();
        ((Bowl) bowl.getNeighbor(4)).play();
        ((Bowl) bowl.getNeighbor(7)).play();
        ((Bowl) bowl.getNeighbor(5)).play();
    }
}
