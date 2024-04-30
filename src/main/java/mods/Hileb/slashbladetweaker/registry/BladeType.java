package mods.Hileb.slashbladetweaker.registry;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
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

    /**
     * The function after basic information is built.
     * You can add Enchantments, SE, or other process before it real build and register.
     */
    @ZenRegister
    @ZenClass("mods.Hileb.slashbladetweaker.BladeProcessor")
    @FunctionalInterface
    public interface BladeProcessor{
        IItemStack process(IItemStack stack);
    }




}
