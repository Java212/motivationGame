package ru.inspired.person;

import ru.inspired.person.model.DailyLog;

import java.util.List;

public class MotivationScoreCalc {
    private final int initialScore;

    public MotivationScoreCalc(int initialScore) {
        this.initialScore = initialScore;
    }

    public int calculateScore(List<DailyLog> log){
        int score = initialScore;
        for(DailyLog logEntry: log){ // проходим по всем записям
            switch (logEntry.getStatus()){
                case DONE -> score += logEntry.getEvent().getBonus();
                case FAILED -> score -= logEntry.getEvent().getFee();
            }
        }
        return score;
    }
}
