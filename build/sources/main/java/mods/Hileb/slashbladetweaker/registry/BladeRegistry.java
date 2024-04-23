package mods.Hileb.slashbladetweaker.registry;

import crafttweaker.annotations.ZenRegister;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.HashMap;

/**
 * @Project SlashBladeTweaker
 * @Author Hileb
 * @Date 2024/4/23 13:17
 **/
@ZenRegister
@ZenClass("mods.Hileb.slashbladetweaker.BladeRegistry")
public class BladeRegistry {
    public static final HashMap<String, BladeType> BLADES = new HashMap<>();

    @ZenMethod
    public static void register(String name, BladeType type){
        BLADES.put(name, type);
    }

    @ZenMethod
    public static void register(BladeType type){
        register(type.name, type);
    }

    @ZenMethod
    public static ItemStack getBladeItemStack(String name){
        return SlashBlade.getCustomBlade(name).copy();
    }

    public static void registerAll(){
        for(BladeType type:BLADES.values()){
            registerStack(type);
        }
    }

    public static void registerStack(BladeType type){
        ItemSlashBladeNamed.NamedBlades.add(type.name);//添加刀，进入物品栏。
        SlashBlade.registerCustomItemStack(type.name, createBlade(type));//注册刀
    }

    public static ItemStack createBlade(BladeType type){
        ItemStack stack = new ItemStack(SlashBlade.bladeNamed);//新建刀Stack
        try {
            NBTTagCompound tagCompound=new NBTTagCompound();//新建NBT
            stack.setTagCompound(tagCompound);//绑定NBT
            //
            ItemSlashBladeNamed.CurrentItemName.set(tagCompound, type.name);//设置客户端刀名
            ItemSlashBladeNamed.CustomMaxDamage.set(tagCompound, type.maxDamage);//设置最大耐久
            ItemSlashBladeNamed.SpecialAttackType.set(tagCompound, type.sa);//SA类型
            ItemSlashBladeNamed.StandbyRenderType.set(tagCompound, type.standbyRenderType);//绘制类型
            ItemSlashBladeNamed.IsBroken.set(tagCompound, type.isBroken);//是否破损
            ItemSlashBladeNamed.IsDefaultBewitched.set(tagCompound, type.isDefaultBewitched);//是否为妖刀
            ItemSlashBladeNamed.AttackAmplifier.set(tagCompound, type.attackAmplifier);
            ItemSlashBladeNamed.BaseAttackModifier.set(tagCompound, type.baseAttackModifier);//初始攻击力

            ItemSlashBladeNamed.ProudSoul.set(tagCompound, type.proudSoul);
            ItemSlashBladeNamed.KillCount.set(tagCompound, type.killCount);
            ItemSlashBladeNamed.RepairCount.set(tagCompound, type.refine);


            ItemSlashBlade.TextureName.set(tagCompound, type.texture);//贴图路径 .png
            ItemSlashBlade.ModelName.get(tagCompound, type.model);//模型路径 .obj
        }catch (Exception exception){

        }
        return stack;
    }
}
