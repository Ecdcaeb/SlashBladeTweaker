package mods.Hileb.slashbladetweaker.registry;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.TagPropertyAccessor;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenProperty;

/**
 * @Project SlashBladeTweaker
 * @Author Hileb
 * @Date 2024/4/23 13:00
 **/
@ZenRegister
@ZenClass("mods.Hileb.slashbladetweaker.registry.BladeType")
public class BladeType {

    public BladeType(String name){
        this.name = name;
    }
    @ZenProperty
    public final String name;
    @ZenProperty
    public int killCount = 0;
    @ZenProperty
    public int proudSoul = 0;
    @ZenProperty
    public int refine = 0;
    @ZenProperty
    public String model = "";
    @ZenProperty
    public String texture = "";
    @ZenProperty
    public int sa = 0;
    @ZenProperty
    public boolean isBroken = false;
    @ZenProperty
    public float attackAmplifier = 0;
    @ZenProperty
    public int standbyRenderType = 0;
    @ZenProperty
    public boolean isDefaultBewitched = true;
    @ZenProperty
    public float baseAttackModifier = 4;
    @ZenProperty
    public int maxDamage = 14;

    @ZenProperty
    public BladeProcessor processor = null;

    @ZenProperty
    public IItemStack wrapper = CraftTweakerMC.getIItemStack(ItemStack.EMPTY);

    @ZenRegister
    @ZenClass("mods.Hileb.slashbladetweaker.BladeProcessor")
    @FunctionalInterface
    public interface BladeProcessor{
        IItemStack process(IItemStack stack);
    }


    @ZenMethod
    public static int getKillCount(IItemStack stack){
        return getProperty(stack, ItemSlashBladeNamed.KillCount);
    }

    @ZenMethod
    public static int getProudSoul(IItemStack stack){
        return getProperty(stack, ItemSlashBladeNamed.ProudSoul);
    }

    @ZenMethod
    public static int getRepairCount(IItemStack stack){
        return getProperty(stack, ItemSlashBladeNamed.RepairCount);
    }
    @ZenMethod
    public static float getBaseAttackModifier(IItemStack stack){
        return getProperty(stack, ItemSlashBladeNamed.BaseAttackModifier);
    }
    @ZenMethod
    public static float getAttackAmplifier(IItemStack stack){
        return getProperty(stack, ItemSlashBladeNamed.AttackAmplifier);
    }
    @ZenMethod
    public static boolean isBroken(IItemStack stack){
        return getProperty(stack, ItemSlashBladeNamed.IsBroken);
    }

    @ZenMethod
    public static boolean isDefaultBewitched(IItemStack stack){
        return getProperty(stack, ItemSlashBladeNamed.IsDefaultBewitched);
    }

    @ZenMethod
    public static int getStandbyRenderType(IItemStack stack){
        return getProperty(stack, ItemSlashBladeNamed.StandbyRenderType);
    }

    @ZenMethod
    public static IItemStack getWrapper(IItemStack stack){
        return CraftTweakerMC.getIItemStack(SlashBlade.wrapBlade.getWrapedItem((ItemStack) stack.getInternal()));
    }

    @ZenMethod
    public static void setKillCount(IItemStack stack, int value){
        setProperty(stack, ItemSlashBladeNamed.KillCount, value);
    }

    @ZenMethod
    public static void setProudSoul(IItemStack stack, int value){
        setProperty(stack, ItemSlashBladeNamed.ProudSoul, value);
    }

    @ZenMethod
    public static void setRepairCount(IItemStack stack, int value){
        setProperty(stack, ItemSlashBladeNamed.RepairCount, value);
    }
    @ZenMethod
    public static void setBaseAttackModifier(IItemStack stack, float value){
        setProperty(stack, ItemSlashBladeNamed.BaseAttackModifier, value);
    }
    @ZenMethod
    public static void setAttackAmplifier(IItemStack stack, float value){
        setProperty(stack, ItemSlashBladeNamed.AttackAmplifier, value);
    }
    @ZenMethod
    public static void setBroken(IItemStack stack, boolean value){
        setProperty(stack, ItemSlashBladeNamed.IsBroken, value);
    }

    @ZenMethod
    public static void setDefaultBewitched(IItemStack stack, boolean value){
        setProperty(stack, ItemSlashBladeNamed.IsDefaultBewitched, value);
    }

    @ZenMethod
    public static void setStandbyRenderType(IItemStack stack, int value){
        setProperty(stack, ItemSlashBladeNamed.StandbyRenderType, value);
    }

    @ZenMethod
    public static void setWrapper(IItemStack stack, IItemStack value){
        SlashBlade.wrapBlade.setWrapItem((ItemStack) stack.getInternal(), (ItemStack) value.getInternal());
    }

    public static <T extends Comparable> void setProperty(IItemStack stack, TagPropertyAccessor<T> accessor, T value){
        accessor.set(((ItemStack)stack.getInternal()).getTagCompound(), value);
    }

    public static <T extends Comparable> T getProperty(IItemStack stack, TagPropertyAccessor<T> accessor){
        return accessor.get(((ItemStack)stack.getInternal()).getTagCompound());
    }

}
