package pl.beaverlib;

import org.bukkit.plugin.java.JavaPlugin;
import pl.beaverlib.events.GUIEvents;
import pl.beaverlib.gui.GUIHandler;

public final class BeaverLib extends JavaPlugin {

    private static BeaverLib main;
    private GUIHandler guiHandler;
    private GUIEvents guiEvents;

    @Override
    public void onEnable() {
        main = this;
        this.guiHandler = new GUIHandler();
        this.guiEvents = new GUIEvents();
        getServer().getPluginManager().registerEvents(guiEvents, this);
        getLogger().info("Enabled.");
    }

    @Override
    public void onDisable() {
        guiHandler.closeAllInventories();
        getLogger().info("Disabled.");
    }

    public static BeaverLib getInstance() {
        return main;
    }

    public GUIHandler getGUIHandler() {
        return this.guiHandler;
    }

    public GUIEvents getGUIEvents() {
        return this.guiEvents;
    }

}
