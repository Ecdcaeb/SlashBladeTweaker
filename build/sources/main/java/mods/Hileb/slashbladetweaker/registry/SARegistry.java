package mods.Hileb.slashbladetweaker.registry;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.minecraft.CraftTweakerMC;
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
@ZenClass("mods.Hileb.slashbladetweaker.registry.SARegistry")
public class SARegistry {
    /**
     * @param id the number id for identical, if two sa have same id, later one will replace the earlier one.
     * @param name the name, used for display,
     *             the translation key is "flammpfeil.slashblade.specialattack.%NAME%"
     * @param ISpecialEffectFunction the function of sa
     */
    @ZenMethod
    public static void registerSA(int id, String name, ISpecialAttackFunction ISpecialEffectFunction){
        ItemSlashBlade.specialAttacks.put(id, new SpecialAttackBase() {
            @Override
            public String toString() {
                return name;
            }

            @SuppressWarnings("unchecked")
            @Override
            public void doSpacialAttack(ItemStack itemStack, EntityPlayer entityPlayer) {
                ISpecialEffectFunction.doSpacialAttack(CraftTweakerMC.getIItemStack(itemStack), CraftTweakerMC.getIPlayer(entityPlayer));
            }
        });
    }
}
