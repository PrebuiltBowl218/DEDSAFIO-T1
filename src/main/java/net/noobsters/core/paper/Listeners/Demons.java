package net.noobsters.core.paper.Listeners;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Vex;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;
import net.noobsters.core.paper.PERMADED;

public class Demons implements Listener{

    PERMADED instance;
    Random random = new Random();

    Demons(PERMADED instance){
        this.instance = instance;
    }
    
    @EventHandler
    public void demons(CreatureSpawnEvent e) {
        var entity =  e.getEntity();
        var difficulty = instance.getGame().getDifficultyChanges();
        if (entity instanceof Vex && difficulty.get("mages")) {
            var spirit = (Vex) entity;
            spirit.setCustomName(ChatColor.DARK_PURPLE + "Spirit");

        }else if(difficulty.get("demons") && entity instanceof Ghast){
            var ghast = (Ghast) entity;
            ghast.setCustomName(ChatColor.DARK_PURPLE + "Carminite Ghast Guard");
            var loc = ghast.getLocation();
            var players = loc.getNearbyPlayers(64, player-> player.getGameMode() == GameMode.SURVIVAL).stream().findAny();
            if(players.isPresent()) ghast.setTarget(players.get());

        }else if(difficulty.get("demons") && entity instanceof Phantom){
            e.setCancelled(true);
            var loc = entity.getLocation();
            loc.getWorld().spawnEntity(loc, EntityType.GHAST);

        }else if(difficulty.get("demons") && entity instanceof Blaze){
            var blaze = (Blaze) entity;
            blaze.setCustomName(ChatColor.DARK_PURPLE + "Pure Demon");
            blaze.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 600, 3));
            var loc = blaze.getLocation();
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                    "playsound minecraft:lich master @a " + loc.getX() + " " + loc.getY() + " " + loc.getZ() + " 1 1");

        }else if(difficulty.get("demons") && entity instanceof Enderman){
            var enderman = (Enderman) entity;
            enderman.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 600, 1));
            switch (random.nextInt(7)) {
                case 1:{
                    enderman.setCustomName(ChatColor.DARK_PURPLE + "Corrupted Demon");
                }
                    break;
                case 2:{
                    enderman.setCustomName(ChatColor.DARK_PURPLE + "Horned Demon");
                }
                    break;
                case 3:{
                    enderman.setCustomName(ChatColor.DARK_PURPLE + "Demon");
                }
                    break;
                case 4:{
                    enderman.setCustomName(ChatColor.DARK_PURPLE + "Old Demon");
                }
                    break;
                case 5:{
                    enderman.setCustomName(ChatColor.DARK_PURPLE + "Wights");
                }
                    break;
                case 6:{
                    enderman.setCustomName(ChatColor.DARK_PURPLE + "Ghost");
                }
                    break;
            
                default:{
                    enderman.setCustomName(ChatColor.DARK_PURPLE + "Wendigo");
                }
                    break;
            }
            if(random.nextInt(30) == 1){
                var loc = enderman.getLocation();
                var players = loc.getNearbyPlayers(64, player-> player.getGameMode() == GameMode.SURVIVAL).stream().findAny();
                if(players.isPresent()) enderman.setTarget(players.get());
            }
        }


    }

}