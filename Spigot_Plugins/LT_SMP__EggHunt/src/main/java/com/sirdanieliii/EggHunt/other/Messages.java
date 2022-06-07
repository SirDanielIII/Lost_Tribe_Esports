package com.sirdanieliii.EggHunt.other;

import static com.sirdanieliii.EggHunt.configuration.ConfigManager.cmdHeader;

public class Messages {
    public static String gameState(Boolean grace) {
        if (grace)
            return "Status: §AGRACE";
        else
            return "Status: §4HUNT";
    }

    public static String errorMessage(String s) {
        return switch (s) {
            case ("OP_ONLY") -> header() + errorHeader() + "§CSorry, but only operators can use this command!";
            case ("PERMISSION") -> errorHeader() + "§CYou do not have the permission to do that!";
            case ("TEAMS") -> errorHeader() + "§CTeams must be Purple / Yellow / None!";
            case ("PLAYER") -> errorHeader() + "This specified player isn't online or does not exist!";
        };
    }

    public static String header() {
        return "§D[§F" + cmdHeader + "§D] ";
    }

    public static String errorHeader() {
        return "§4[§C§L!§R§4] ";
    }
}