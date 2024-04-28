package mods.Hileb.slashbladetweaker.registry;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * @Project SlashBladeTweaker
 * @Author Hileb
 * @Date 2024/4/23 13:11
 **/
@ZenRegister
@ZenClass("mods.Hileb.slashbladetweaker.registry.BladeTypeBuilder")
public class BladeTypeBuilder {
    private final BladeType impl;
    public BladeTypeBuilder(String name){
        impl = new BladeType(name);
    }
    @ZenMethod
    public static BladeTypeBuilder create(String name){
        return new BladeTypeBuilder(name);
    }
    @ZenMethod
    public BladeTypeBuilder killCount(int value){
        impl.killCount=value;
        return this;
    }
    @ZenMethod
    public BladeTypeBuilder proudSoul(int value){
        impl.proudSoul=value;
        return this;
    }
    @ZenMethod
    public BladeTypeBuilder refine(int value){
        impl.refine=value;
        return this;
    }
    @ZenMethod
    public BladeTypeBuilder model(String value){
        impl.model=value;
        return this;
    }
    @ZenMethod
    public BladeTypeBuilder texture(String value){
        impl.texture=value;
        return this;
    }
    @ZenMethod
    public BladeTypeBuilder specialAttack(int specialAttackType){
        impl.sa=specialAttackType;
        return this;
    }
    @ZenMethod
    public BladeTypeBuilder isBroken(boolean value){
        impl.isBroken=value;
        return this;
    }
    @ZenMethod
    public BladeTypeBuilder attackAmplifier(float value){
        impl.attackAmplifier=value;
        return this;
    }
    @ZenMethod
    public BladeTypeBuilder standbyRenderType(int value){
        impl.standbyRenderType=value;
        return this;
    }
    @ZenMethod
    public BladeTypeBuilder isDefaultBewitched(boolean value){
        impl.isDefaultBewitched=value;
        return this;
    }
    @ZenMethod
    public BladeTypeBuilder baseAttackModifier(int value){
        impl.baseAttackModifier=value;
        return this;
    }
    @ZenMethod
    public BladeTypeBuilder maxDamage(int value){
        impl.maxDamage=value;
        return this;
    }

    @ZenMethod
    public BladeTypeBuilder process(BladeType.BladeProcessor processor){
        impl.processor = processor;
        return this;
    }

    @ZenMethod
    public BladeTypeBuilder wrap(IItemStack stack){
        impl.wrapper = stack;
        return this;
    }

    @ZenMethod
    public BuilderCallBack register(){
        BladeRegistry.register(impl);
        return new BuilderCallBack();
    }


    @ZenRegister
    @ZenClass("mods.Hileb.slashbladetweaker.registry.BladeTypeBuilder$BuilderCallBack")
    public static class BuilderCallBack{
        @ZenMethod
        public BladeTypeBuilder named(String name){
            return BladeRegistry.named(name);
        }
    }
}
