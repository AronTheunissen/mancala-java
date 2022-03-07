package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void A_player_is_initialized_with_an_opponent() {
        var player = new Player("player1","player2");
        assertEquals("player2", player.getOpponent().getName());
    }

    @Test
    public void A_player_is_on_turn_when_initialized() {
        var player = new Player("player1","player2");
        assertEquals(true, player.hasTurn());
    }

    @Test
    public void A_player_opponent_is_not_on_turn_when_initialized(){
        var player = new Player("player1","player2");
        assertEquals(false, player.getOpponent().hasTurn());
    }

    @Test
    public void A_player_is_no_longer_on_turn_after_switching_turns_with_its_opponent(){
        var bowl = new Bowl("player1","player2");
        var player = bowl.getPlayer();
        player.endTurn();
        assertEquals(false, player.hasTurn());
    }

    @Test
    public void A_player_opponent_is_on_turn_after_it_switches_turns(){
        var bowl = new Bowl("player1","player2");
        var player = bowl.getPlayer();
        player.endTurn();
        assertEquals(true, player.getOpponent().hasTurn());
    }

    @Test
    public void A_player_is_again_on_turn_and_its_opponent_is_not_when_turns_are_switched_twice(){
        var bowl = new Bowl("player1","player2");
        var player = bowl.getPlayer();
        player.endTurn();
        player.getOpponent().endTurn();
        assertEquals(true, player.hasTurn());
        assertEquals(false, player.getOpponent().hasTurn());
    }

    @Test
    public void The_first_bowl_belongs_to_the_first_player(){
        var bowl = new Bowl("player1","player2");
        assertEquals(bowl, bowl.getPlayer().getBowl());
    }

    @Test
    public void The_seventh_bowl_belongs_to_the_second_player(){
        var bowl = new Bowl("player1","player2");
        assertEquals(bowl.getNeighbor(7), bowl.getPlayer().getOpponent().getBowl());
    }
}
