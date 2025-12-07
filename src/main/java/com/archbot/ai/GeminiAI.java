package com.archbot.ai;

public class GeminiAI {

    public static void initialize() {
        // Nothing to do here for now
    }

    public static String getNextAction(String mobState) {
        String[] actions = {"wander", "mine stone", "build dirt"};
        int randomIndex = (int) (Math.random() * actions.length);
        return actions[randomIndex];
    }
}
