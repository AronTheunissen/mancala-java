package mancala.api;

import java.io.IOException;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import mancala.api.models.*;
import mancala.domain.MancalaImpl;
import mancala.domain.MancalaException;

@Path("/play")
public class PlayMancala {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response initialize (
            @Context HttpServletRequest request,
            IndexInputDTO index) {
        HttpSession session = request.getSession();
        MancalaImpl mancala = (MancalaImpl) session.getAttribute("mancala");
        String namePlayer1 = (String) session.getAttribute("player1");
        String namePlayer2 = (String) session.getAttribute("player2");

        try{
            mancala.playPit(index.getIndex());
        }
        catch(MancalaException exception){

        }
        var output = new MancalaDTO(mancala, namePlayer1, namePlayer2);
        return Response.status(200).entity(output).build();
    }
}