package info.yurtsevich.nobel.service;

import info.yurtsevich.nobel.model.Choice;
import info.yurtsevich.nobel.model.Game;
import info.yurtsevich.nobel.model.MoveResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by yurtsevich on 10/15/21
 */
@Service
public class GameEngine {
    public void makeMove(final Game game) {
        game.setEngineChoice(generateChoice());
        game.setMoveResult(getMoveResult(game));
        if (MoveResult.DRAW != game.getMoveResult()) {
            if (MoveResult.PLAYER_WINS == game.getMoveResult()) {
                game.addPlayerScore();
            } else {
                game.addEngineScore();
            }
        }
    }

    private Choice generateChoice() {
        final Choice[] choises = Choice.values();
        return choises[ThreadLocalRandom.current().nextInt(choises.length)];
    }

    private MoveResult getMoveResult(final Game game) {
        if (game.getPlayerChoice() == game.getEngineChoice()) {
            return MoveResult.DRAW;
        }
        if (Choice.ROCK == game.getPlayerChoice()) {
            return Choice.SCISSORS == game.getEngineChoice() ? MoveResult.PLAYER_WINS : MoveResult.ENGINE_WINS;
        } else if (Choice.PAPER == game.getPlayerChoice()) {
            return Choice.ROCK == game.getEngineChoice() ? MoveResult.PLAYER_WINS : MoveResult.ENGINE_WINS;
        } else if (Choice.SCISSORS == game.getPlayerChoice()) {
            return Choice.PAPER == game.getEngineChoice() ? MoveResult.PLAYER_WINS : MoveResult.ENGINE_WINS;
        } else {
            throw new IllegalStateException("Unexpected move value");
        }
    }
}
