package pl.beaverlib.util;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import pl.beaverlib.BeaverLib;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class PlayerUtil {

    private final static BeaverLib plugin = BeaverLib.getInstance();

    @Nullable
    public static Player getPlayer(String nickname) {
        if(nickname == null) {
            return null;
        }
        for(Player p : plugin.getServer().getOnlinePlayers()) {
            if(p.getName().equals(nickname) || p.getUniqueId().toString().equals(nickname)) {
                return p;
            }
        }
        return null;
    }

    @Nullable
    public static Player getPlayerFor(String nickname, Player player) {
        boolean sourcePlayerVanished = isVanished(player);
        for(Player p : plugin.getServer().getOnlinePlayers()) {
            if(!sourcePlayerVanished) {
                if(isVanished(p)) {
                    continue;
                }
            }
            if(p.getName().equals(nickname)) {
                return p;
            }
        }
        return null;
    }

    public static boolean isVanished(Player player) {
        for(MetadataValue meta : player.getMetadata("vanished")) {
            if(meta.asBoolean()) {
                return true;
            }
        }
        return false;
    }

    public static List<Player> getPlayers(Player forPlayer) {
        boolean sourcePlayerVanished = isVanished(forPlayer);
        List<Player> playerList = new ArrayList<>();
        for(Player p : plugin.getServer().getOnlinePlayers()) {
            if(!sourcePlayerVanished) {
                if(isVanished(p)) {
                    continue;
                }
            }
            playerList.add(p);
        }
        return playerList;
    }

    public static String parseNickname(String player, boolean uuidMode) {
        if(player.length() > 16) {
            return player;
        }
        if(!uuidMode) {
            return player;
        }
        return plugin.getServer().getOfflinePlayer(player).getUniqueId().toString();
    }

    public static String unParseName(String uuid, boolean uuidMode) {
        if(!uuidMode) {
            return uuid;
        }
        return plugin.getServer().getOfflinePlayer(UUID.fromString(uuid)).getName();
    }

    public static void sendActionBarMessage(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }

    public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }

}
