package mods.Hileb.slashbladetweaker.registry;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.player.IPlayer;
import stanhebben.zenscript.annotations.ZenClass;

/**
 * @Project SlashBladeTweaker
 * @Author Hileb
 * @Date 2024/4/23 13:27
 **/
@ZenRegister
@ZenClass("mods.Hileb.slashbladetweaker.registry.ISpecialEffectFunction")
@FunctionalInterface
public interface ISpecialEffectFunction {
    void doSpacialAttack(IItemStack s, IPlayer p);
}
