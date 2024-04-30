package mods.Hileb.slashbladetweaker.registry;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenProperty;

/**
 * @Project SlashBladeTweaker
 * @Author Hileb
 * @Date 2024/4/23 23:04
 **/
@ZenRegister
@ZenClass("mods.Hileb.slashbladetweaker.registry.SEType")
public class SEType implements mods.Hileb.slashbladetweaker.registry.ISpecialEffect {
    @ZenProperty
    public int level;
    @ZenProperty
    public String name;
    public SEType(int level, String name){
        this.name = name;
        this.level = level;
    }

    /**
     * @param level the level that se required for default
     * @param name the register name,
     *             the translation key is "slashblade.seffect.name.%NAME%"
     *
     * @return the created SEType
     */
    @ZenMethod
    public static SEType create(int level, String name){
        return new SEType(level, name);
    }

    /**
     * register the se
     */
    @Override
    @ZenMethod
    public void register() {
        SERegistry.register(this);
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
