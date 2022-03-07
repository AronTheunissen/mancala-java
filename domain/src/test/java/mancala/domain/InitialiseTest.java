package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InitialiseTest {
    @Test
    public void A_bowl_starts_with_4_beads() {
        var bowl = new Bowl("player1","player2");
        assertEquals(4, bowl.getContent());
    }

    @Test
    public void A_bowl_has_a_player() {
        var bowl = new Bowl("player1","player2");
        assertEquals("player1", bowl.getPlayer().getName());
    }

    @Test
    public void A_bowls_neighbor_starts_with_4_beads(){
        var bowl = new Bowl("player1","player2");
        assertEquals(4, bowl.getNeighbor().getContent());
    }

    @Test
    public void The_sixth_neighbor_is_not_a_bowl(){
        var bowl = new Bowl("player1","player2");
        assertFalse(bowl.getNeighbor(6) instanceof Bowl);
    }

    @Test
    public void A_kalaha_starts_with_0_beads(){
        var bowl = new Bowl("player1","player2");
        assertEquals(0, bowl.getNeighbor(6).getContent());
    }

    @Test
    public void Opposite_bowl_starts_with_a_different_player(){
        var bowl = new Bowl("player1","player2");
        assertEquals("player2", bowl.getNeighbor(10).getPlayer().getName());
    }

    @Test
    public void All_bowls_on_one_side_belong_to_the_same_player(){
        var bowl = new Bowl("player1","player2");
        for(int i=0; i<7; i++){
            assertEquals("player1", bowl.getNeighbor(i).getPlayer().getName());
        }
        for(int i=7;i<14; i++){
            assertEquals("player2", bowl.getNeighbor(i).getPlayer().getName());
        }
    }

    @Test
    public void The_opposite_of_a_bowl_is_indeed_its_opposite(){
        var bowl = new Bowl("player1","player2");
        for(int i=0; i<6; i++){
            assertEquals(bowl.getNeighbor(12-i), ((Bowl) bowl.getNeighbor(i)).getOpposite());
        }
        for(int i=7; i<13; i++){
            assertEquals(bowl.getNeighbor(5-i%7), ((Bowl) bowl.getNeighbor(i)).getOpposite());
        }
    }

}
