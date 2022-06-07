package com.sirdanieliii.EggHunt.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.sirdanieliii.EggHunt.configuration.ConfigManager.eggLocationLast;
import static com.sirdanieliii.EggHunt.LT_EggHunt.getInstance;
import static com.sirdanieliii.EggHunt.tasks.EverySecond.timeUntilPoints;

public class Scoreboard implements Listener {
    public static ScoreboardManager manager = Bukkit.getScoreboardManager();
    public static org.bukkit.scoreboard.Scoreboard scoreboard = Objects.requireNonNull(manager).getNewScoreboard();

    @EventHandler
    public void joinServer(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Objective obj = scoreboard.registerNewObjective("title", "dummy", "  §3§LLT SMP S3 - §6§LEGG HUNT  ");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        List<Team> scoreboardTeams = new ArrayList<>();
        String[] entries = {"§F", "§E", "§D", "§C", "§B", "§A", "§9", "§8", "§7", "§6", "§5", "§4", "§3", "§2", "§1"};
        String blank = "     ";

        for (int i = 15; i > 0; i--) {
            scoreboardTeams.add(scoreboard.registerNewTeam("l" + i));  // Add all 15 lines
            obj.getScore(entries[15 - i]).setScore(16 - i);  // Set lines for each entry (15 to 1)
        }

        for (int i = 0; i < 15; i++)
            scoreboardTeams.get(14 - i).addEntry(entries[i]); // Loop though indexes 0 - 14 of entries

        for (Team t : scoreboardTeams) t.setPrefix("     "); // Give lines an empty value

        new BukkitRunnable() {
            @Override
            public void run() {
                Objects.requireNonNull(scoreboard.getTeam("l15")).setPrefix(blank);
                Objects.requireNonNull(scoreboard.getTeam("l14")).setPrefix("§6Possession of Egg:");
                Objects.requireNonNull(scoreboard.getTeam("l13")).setPrefix("→ " + "");
                Objects.requireNonNull(scoreboard.getTeam("l12")).setPrefix(blank);
                Objects.requireNonNull(scoreboard.getTeam("l11")).setPrefix("§DPurple Team: " + " Points");
                Objects.requireNonNull(scoreboard.getTeam("l10")).setPrefix(blank);
                Objects.requireNonNull(scoreboard.getTeam("l9")).setPrefix("§EYellow Team: " + " Points");
                Objects.requireNonNull(scoreboard.getTeam("l8")).setPrefix(blank);
                Objects.requireNonNull(scoreboard.getTeam("l7")).setPrefix("Last Location of Egg:");
                Objects.requireNonNull(scoreboard.getTeam("l6")).setPrefix("→ " + eggLocationLast);
                Objects.requireNonNull(scoreboard.getTeam("l5")).setPrefix(blank);
                Objects.requireNonNull(scoreboard.getTeam("l4")).setPrefix("Next Team Points: ");
                Objects.requireNonNull(scoreboard.getTeam("l3")).setPrefix("→ " + displaySeconds(timeUntilPoints));
                Objects.requireNonNull(scoreboard.getTeam("l2")).setPrefix(blank);
                Objects.requireNonNull(scoreboard.getTeam("l1")).setPrefix("§3smp.losttribeesports.org");
            }
        }.runTaskTimer(getInstance(), 0, 20);
        player.setScoreboard(scoreboard);
    }

    private static String displaySeconds(double s) {
        int sec = (int) (s % 60);
        int min = (int) ((s / 60) % 60);
        String strSec = (sec < 10) ? "0" + sec : Integer.toString(sec);
        String strMin = (min < 10) ? "0" + min : Integer.toString(min);
        return strMin + ":" + strSec;
    }
}
