package mods.Hileb.slashbladetweaker.registry;

import crafttweaker.annotations.ZenRegister;
import net.minecraftforge.common.MinecraftForge;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenProperty;

/**
 * @Project SlashBladeTweaker
 * @Author Hileb
 * @Date 2024/4/23 23:04
 **/
@ZenRegister
@ZenClass("mods.Hileb.slashbladetweaker.SEType")
public class SEType implements mods.Hileb.slashbladetweaker.registry.ISpecialEffect {
    @ZenProperty
    public int level;
    @ZenProperty
    public String name;
    public SEType(int level, String name){
        this.name = name;
        this.level = level;
    }

    @ZenMethod
    public static SEType create(int level, String name){
        return new SEType(level, name);
    }

    @Override
    public void register() {
    }

    @Override
    public int getDefaultRequiredLevel() {
        return level;
    }

    @Override
    public String getEffectKey() {
        return name;
    }

}
