import React from "react";
import type { GameState } from "../gameState";
import "./Play.css";
import { StartGameOnServer } from "./StartGame";

type PlayProps = {
    gameState: GameState;
    setGameState(newGameState: GameState): void;
}

export function Play({ gameState, setGameState }: PlayProps) {
    async function tryToPlay(index:number) {
            //check if player has turn then
            try {
                const response = await fetch('mancala/api/play', {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ index:index })
                });

                if (response.ok) {
                    const updatedGameState = await response.json();
                    setGameState({gameState, ...updatedGameState});
                } else {
                    console.error(response.statusText);
                }
            } catch (error) {
                console.error(error.toString());
            }
        }




        return (
            <div>
                <p>{gameState.players[0].name} vs {gameState.players[1].name}</p>
                <div>
                    {gameState.players[0].pits.map((pit) => <button className="pit"   onClick={()=>tryToPlay(pit.index)}>{pit.nrOfStones}  </button> )}
                </div>
                <div>
                    {gameState.players[1].pits.map((pit) => <button className="pit"   onClick={()=>tryToPlay(pit.index)}>{pit.nrOfStones}  </button> )}
                </div>
                <p> The winner is: {gameState.gameStatus.winner}!
                </p>
            </div>
        )
    }