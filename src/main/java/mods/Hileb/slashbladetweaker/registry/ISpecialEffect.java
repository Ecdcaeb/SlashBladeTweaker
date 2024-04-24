package mods.Hileb.slashbladetweaker.registry;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.player.IPlayer;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * @Project SlashBladeTweaker
 * @Author Hileb
 * @Date 2024/4/23 23:17
 **/
@ZenRegister
@ZenClass("mods.Hileb.slashbladetweaker.ISE")
public interface ISpecialEffect extends mods.flammpfeil.slashblade.specialeffect.ISpecialEffect {
    @ZenMethod
    default boolean match(IPlayer player){
        return CraftTweakerMC.getPlayer(player).experienceLevel >= this.getDefaultRequiredLevel();
    }

    static ISpecialEffect get(mods.flammpfeil.slashblade.specialeffect.ISpecialEffect effect){
        if (effect instanceof  ISpecialEffect) return (ISpecialEffect) effect;
        else return new ISpecialEffect() {@Override public void register() {effect.register();}@Override public int getDefaultRequiredLevel() {return effect.getDefaultRequiredLevel();}@Override public String getEffectKey() {return effect.getEffectKey();}}; // warp
    }
}
