package com.sirdanieliii.Operation_EggHunt.other;

import static com.sirdanieliii.Operation_EggHunt.other.Utilities.toTitleCase;

public class Messages {
    public static String messageGrace(Boolean grace) {
        if (grace)
            return "Status: §AGRACE";
        else
            return "Status: §4HUNT";
    }
}