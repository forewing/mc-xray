package com.jbesu.mc.xray.tasks;

import com.jbesu.mc.xray.App;

import org.bukkit.entity.Player;

public abstract class Task {
    protected App app;
    protected Player player;

    protected int range;

    protected Task(App app, Player player) {
        this.app = app;
        this.player = player;

        this.range = app.getConfig().getInt("range");
    }

    public abstract void start();

    public abstract void stop();
}
