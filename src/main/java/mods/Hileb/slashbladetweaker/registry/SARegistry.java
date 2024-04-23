package mods.Hileb.slashbladetweaker.registry;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.player.IPlayer;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * @Project SlashBladeTweaker
 * @Author Hileb
 * @Date 2024/4/23 13:26
 **/
@ZenRegister
@ZenClass("mods.Hileb.slashbladetweaker.SARegistry")
public class SARegistry {
    @ZenMethod
    public static void registerSA(int id, String name, SAInterface saInterface){
        ItemSlashBlade.specialAttacks.put(id, new SpecialAttackBase() {
            @Override
            public String toString() {
                return name;
            }

            @SuppressWarnings("unchecked")
            @Override
            public void doSpacialAttack(ItemStack itemStack, EntityPlayer entityPlayer) {
                saInterface.doSpacialAttack(IItemStack.class.cast(itemStack), (IPlayer) entityPlayer); //TODO find out a good way avoid cast
            }
        });
    }
}
