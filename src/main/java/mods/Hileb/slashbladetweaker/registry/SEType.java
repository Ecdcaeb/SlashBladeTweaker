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
    @ZenProperty
    public RegisterHandler registerHandler;
    public SEType(int level, String name, RegisterHandler registerHandler){
        this.name = name;
        this.level = level;
        this.registerHandler = registerHandler;
    }

    @ZenMethod
    public static SEType create(int level, String name, RegisterHandler registerHandler){
        return new SEType(level, name, registerHandler);
    }

    @Override
    public void register() {
        this.registerHandler.register(this);
    }

    @Override
    public int getDefaultRequiredLevel() {
        return level;
    }

    @Override
    public String getEffectKey() {
        return name;
    }

    @ZenMethod
    static void addToBus(SEType seType) {
        MinecraftForge.EVENT_BUS.register(seType);
    }

    @ZenRegister
    @ZenClass("mods.Hileb.slashbladetweaker.SEType.RegisterHandler")
    @FunctionalInterface
    public interface RegisterHandler {
        void register(SEType seType);
    }
}
