package ru.inspired;

import ru.inspired.model.DailyLog;

import java.util.List;

public class MotivationScoreCalc {
    private final int initialScore;

    public MotivationScoreCalc() {
        initialScore = 0;
    }

    public MotivationScoreCalc(int initialScore) {
        this.initialScore = initialScore;
    }

    public int calculateScore(List<DailyLog> log){
        int score = initialScore;
        for(DailyLog logEntry: log){
            switch (logEntry.getStatus()){
                case DONE -> score += logEntry.getEvent().getBonus();
                case FAILED -> score -= logEntry.getEvent().getFee();
            }
        }
        return score;
    }
}
