package mods.Hileb.slashbladetweaker.registry;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;

/**
 * @Project SlashBladeTweaker
 * @Author Hileb
 * @Date 2024/4/23 13:00
 **/
@ZenRegister
@ZenClass("mods.Hileb.slashbladetweaker.BladeType")
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
}
