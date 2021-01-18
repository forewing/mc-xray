package com.jbesu.mc.xray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.jbesu.mc.xray.command.CommandXRay;
import com.jbesu.mc.xray.player.PlayerInfo;
import com.jbesu.mc.xray.tasks.BlockTask;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {

    private Map<UUID, PlayerInfo> playerInfoMap;

    private Set<String> targetBlocks;

    public App() {
        playerInfoMap = new HashMap<>();
    }

    public Set<String> getTargetBlocks() {
        if (targetBlocks == null) {
            targetBlocks = new HashSet<>();
            for (String block : getConfig().getStringList("target")) {
                targetBlocks.add(block);
            }
        }
        return targetBlocks;
    }

    @Override
    public void onEnable() {
        this.getCommand("xray").setExecutor(new CommandXRay(this));
        initConfig();
    }

    private void initConfig() {
        FileConfiguration config = getConfig();

        config.addDefault("mode", "block");

        config.addDefault("range", 2);

        List<String> defaultTarget = new ArrayList<>();
        defaultTarget.add("AIR");
        defaultTarget.add("COAL_ORE");
        defaultTarget.add("REDSTONE_ORE");
        defaultTarget.add("IRON_ORE");
        defaultTarget.add("GOLD_ORE");
        defaultTarget.add("DIAMOND_ORE");
        defaultTarget.add("LAPIS_ORE");
        defaultTarget.add("EMERALD_ORE");
        defaultTarget.add("NETHER_GOLD_ORE");
        defaultTarget.add("NETHER_QUARTZ_ORE");
        defaultTarget.add("ANCIENT_DEBRIS");
        defaultTarget.add("WATER");
        defaultTarget.add("LAVA");
        defaultTarget.add("BEDROCK");
        config.addDefault("target", defaultTarget);

        config.options().copyDefaults(true);
        saveDefaultConfig();
    }

    public PlayerInfo getPlayerInfo(Player player) {
        return playerInfoMap.computeIfAbsent(player.getUniqueId(), f -> new PlayerInfo(player.getUniqueId()));
    }

    public void removePlayerInfo(Player player) {
        playerInfoMap.remove(player.getUniqueId());
    }

    public void startXRay(Player player) {
        PlayerInfo playerInfo = getPlayerInfo(player);
        playerInfo.setTask(new BlockTask(this, player));
        playerInfo.getTask().start();
    }

    public void stopXRay(Player player) {
        PlayerInfo playerInfo = getPlayerInfo(player);
        playerInfo.getTask().stop();
        getPlayerInfo(player).setTask(null);
    }

}
