package net.noobsters.core.paper;

import org.bukkit.plugin.java.JavaPlugin;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.noobsters.core.paper.Commands.permadedCMD;
import net.noobsters.core.paper.Commands.worldCMD;
import net.noobsters.core.paper.Listeners.ListenerManager;

public class PERMADED extends JavaPlugin {

  private @Getter PaperCommandManager commandManager;
  private @Getter ListenerManager listenerManager;
  private @Getter Game game = new Game();

  private static @Getter PERMADED instance;

  @Override
  public void onEnable() {

    // worldcreator
    /*
    WorldCreator arenaWorld = new WorldCreator("PERMADED");
    arenaWorld.environment(Environment.THE_END);
    arenaWorld.createWorld();
    */
    instance = this;

    // managers
    commandManager = new PaperCommandManager(this);
    listenerManager = new ListenerManager(this);
        
    //commands
    commandManager.registerCommand(new worldCMD(this));
    commandManager.registerCommand(new permadedCMD(this));

    }

    @Override
    public void onDisable() {

  }
    
}