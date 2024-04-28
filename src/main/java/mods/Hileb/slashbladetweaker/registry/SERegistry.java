package mods.Hileb.slashbladetweaker.registry;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * @Project SlashBladeTweaker
 * @Author Hileb
 * @Date 2024/4/23 23:12
 **/
@ZenRegister
@ZenClass("mods.Hileb.slashbladetweaker.registry.SERegistry")
public class SERegistry {

    @ZenMethod
    public static void register(ISpecialEffect seType){
        SpecialEffects.register(seType);
    }

    @ZenMethod
    public static ISpecialEffect get(String name){
        return ISpecialEffect.get(SpecialEffects.getEffect(name));
    }

    @ZenMethod
    public static void addSEToItem(IItemStack iItemStack, String name, int level){
        ItemStack itemStack = (ItemStack) iItemStack.getInternal();
        SpecialEffects.addEffect(itemStack, name, level);
    }

    @ZenMethod
    public static void addSEToItem(IItemStack iItemStack, String name){
        ItemStack itemStack = (ItemStack) iItemStack.getInternal();
        mods.flammpfeil.slashblade.specialeffect.ISpecialEffect effect = SpecialEffects.getEffect(name);
        SpecialEffects.addEffect(itemStack, effect);
    }
}
