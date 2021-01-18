package com.jbesu.mc.xray.tasks;

import java.util.ArrayList;

import com.jbesu.mc.xray.App;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BlockTask extends Task {

    private ArrayList<Block> runningBlocks;

    public BlockTask(App app, Player player) {
        super(app, player);
        calculateRunningBlocks();
    }

    private void calculateRunningBlocks() {
        runningBlocks = new ArrayList<>();
        Location location = player.getLocation();
        int chunkSize = 16;
        int rangeBlocks = range * chunkSize;

        for (int x = location.getBlockX() - rangeBlocks; x <= location.getBlockX() + rangeBlocks; x++) {
            for (int y = 1; y < player.getWorld().getMaxHeight(); y++) {
                for (int z = location.getBlockZ() - rangeBlocks; z <= location.getBlockZ() + rangeBlocks; z++) {
                    runningBlocks.add(location.getWorld().getBlockAt(x, y, z));
                }
            }
        }
    }

    public void start() {
        for (Block runningBlock : runningBlocks) {
            if (!app.getTargetBlocks().contains(runningBlock.getType().toString())) {
                player.sendBlockChange(runningBlock.getLocation(), Material.BARRIER.createBlockData());
            }
        }
    }

    public void stop() {
        World world = player.getWorld();
        for (Block runningBlock : runningBlocks) {
            Location location = runningBlock.getLocation();
            player.sendBlockChange(location, world.getBlockAt(location).getBlockData());
        }
    }
}
