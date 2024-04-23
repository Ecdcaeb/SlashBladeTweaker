package mods.Hileb.slashbladetweaker.registry;

import crafttweaker.annotations.ZenRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;

/**
 * @Project SlashBladeTweaker
 * @Author Hileb
 * @Date 2024/4/23 13:27
 **/
@ZenRegister
@ZenClass("mods.Hileb.slashbladetweaker.SAEffect")
@FunctionalInterface
public interface SAInterface {
    void doSpacialAttack(ItemStack var1, EntityPlayer var2);
}
