package mods.Hileb.slashbladetweaker;

import crafttweaker.CraftTweakerAPI;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * @Project SlashBladeTweaker
 * @Author Hileb
 * @Date 2024/4/23 12:46
 **/

@Mod(modid = SlashBladeTweaker.MODID, name = SlashBladeTweaker.NAME, version = SlashBladeTweaker.VERSION,
        dependencies = SlashBladeTweaker.DEPENDS
)
public class SlashBladeTweaker{
    public static final String MODID = "slashbladetweaker";
    public static final String NAME = "Slash Blade Tweaker";
    public static final String VERSION = "1.0";
    public static final String DEPENDS = "required-after:crafttweaker;required-after:flammpfeil.slashblade";

    @Mod.EventHandler
    public void preInit(FMLInitializationEvent event){
        CraftTweakerAPI.tweaker.loadScript(false, MODID);
    }
}
