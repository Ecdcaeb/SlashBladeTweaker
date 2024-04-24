package mods.Hileb.slashbladetweaker.registry;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * @Project SlashBladeTweaker
 * @Author Hileb
 * @Date 2024/4/23 23:12
 **/
@ZenRegister
@ZenClass("mods.Hileb.slashbladetweaker.SERegistry")
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
        ItemStack itemStack = CraftTweakerMC.getItemStack(iItemStack);
        if (!itemStack.hasTagCompound())itemStack.setTagCompound(new NBTTagCompound());
        NBTTagCompound itemTag = itemStack.getTagCompound();
        if (!itemTag.hasKey("SB.SEffect", 10)) itemTag.setTag("SB.SEffect", new NBTTagCompound());
        NBTTagCompound effects = itemTag.getCompoundTag("SB.SEffect");
        effects.setInteger(name, level);
    }

    @ZenMethod
    public static void addSEToItem(IItemStack iItemStack, String name){
        ItemStack itemStack = CraftTweakerMC.getItemStack(iItemStack);
        if (!itemStack.hasTagCompound())itemStack.setTagCompound(new NBTTagCompound());
        NBTTagCompound itemTag = itemStack.getTagCompound();
        if (!itemTag.hasKey("SB.SEffect", 10)) itemTag.setTag("SB.SEffect", new NBTTagCompound());
        NBTTagCompound effects = itemTag.getCompoundTag("SB.SEffect");
        mods.flammpfeil.slashblade.specialeffect.ISpecialEffect effect = SpecialEffects.getEffect(name);
        effects.setInteger(effect.getEffectKey(), effect.getDefaultRequiredLevel());
    }
}
