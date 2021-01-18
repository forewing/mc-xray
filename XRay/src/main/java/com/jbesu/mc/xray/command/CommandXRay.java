package com.jbesu.mc.xray.command;

import com.jbesu.mc.xray.App;
import com.jbesu.mc.xray.player.PlayerInfo;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandXRay implements CommandExecutor {

    private App app;

    public CommandXRay(App app) {
        this.app = app;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("only player can execute the command");
            return true;
        }

        Player player = (Player) sender;
        PlayerInfo playerInfo = app.getPlayerInfo(player);

        if (playerInfo.isXRay()) {
            player.sendMessage("disable x-ray");
            app.stopXRay(player);
            return true;
        }
        player.sendMessage("enable x-ray");
        app.startXRay(player);
        return false;
    }
}
