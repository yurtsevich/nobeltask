package info.yurtsevich.nobel.model;

import lombok.Data;

/**
 * Created by yurtsevich on 10/15/21
 */
@Data
public class Game {
    private int playerScore;
    private int engineScore;

    private Choice playerChoice;
    private Choice engineChoice;

    private MoveResult moveResult;

    public void addPlayerScore() {
        playerScore++;
    }

    public void addEngineScore() {
        engineScore++;
    }
}
