package ru.inspired;

import ru.inspired.model.DailyStatus;

import java.util.List;

public class MotivationScoreCalc {
    private final int initialScore;

    public MotivationScoreCalc(int initialScore) {
        this.initialScore = initialScore;
    }

    public int calculateScore(List<DailyStatus> log) {
        int score = initialScore;
        for (DailyStatus logEntry : log) {
            switch (logEntry.getStatus()) {
                case DONE -> score += logEntry.getCalculationScore();
                case FAILED -> score -= logEntry.getCalculationScore();
            }
        }
        return score;
    }
}
