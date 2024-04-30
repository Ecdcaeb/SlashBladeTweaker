package mods.Hileb.slashbladetweaker.registry;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import mods.Hileb.slashbladetweaker.utils.BladeUtils;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.util.ResourceLocationRaw;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nullable;
import java.util.HashMap;

/**
 * @Project SlashBladeTweaker
 * @Author Hileb
 * @Date 2024/4/23 13:17
 **/
@ZenRegister
@ZenClass("mods.Hileb.slashbladetweaker.registry.BladeRegistry")
public class BladeRegistry {
    public static final HashMap<String, BladeType> BLADES = new HashMap<>();

    /**
     * @param name register name, could be a resourceLocation or default domain "flammpfeil.slashblade"
     * @param type BladeType
     */
    @ZenMethod
    public static void register(String name, BladeType type){
        BLADES.put(name, type);
        registerStack(type);
    }

    /**
     * @param type BladeType
     */
    @ZenMethod
    public static void register(BladeType type){
        register(type.name, type);
        registerStack(type);
    }

    /**
     * @param name the register name
     *
     * @return the Builder
     */
    @ZenMethod
    public static BladeTypeBuilder named(String name){
        return BladeTypeBuilder.create(name);
    }

    /**
     * @param name the register name
     *
     * @return the blade IItemStack
     * null if blade not registered
     */
    @Nullable
    @ZenMethod
    public static IItemStack getBladeItemStack(String name){
        return CraftTweakerMC.getIItemStack(SlashBlade.getCustomBlade(name).copy());
    }

    /**
     * @param name the register name for a common item
     *
     * @return the IItemStack
     * null if not found
     */
    @ZenMethod
    public static IItemStack findItem(String name){
        return CraftTweakerMC.getIItemStack(new ItemStack(Item.getByNameOrId(name)));
    }

    public static void registerStack(BladeType type){
        ItemStack blade = createBlade(type);
        ItemSlashBladeNamed.NamedBlades.add(type.name);//添加刀，进入物品栏。
        SlashBlade.BladeRegistry.put(new ResourceLocationRaw(BladeUtils.getResourceLocationWithDefaultDomain(type.name, "flammpfeil.slashblade")), blade);
    }

    public static ItemStack createBlade(BladeType type){
        if ((!(type.wrapper==null)) && (!type.wrapper.isEmpty())){
            ItemStack stack = new ItemStack(SlashBlade.bladeNamed);//新建刀Stack
            try {
                NBTTagCompound tagCompound = new NBTTagCompound();//新建NBT
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
                ItemSlashBlade.ModelName.set(tagCompound, type.model);//模型路径 .obj

                if (type.processor != null) return CraftTweakerMC.getItemStack(type.processor.process(CraftTweakerMC.getIItemStack(stack)));
            }catch (Exception exception){

            }
            return stack;
        }else {
            ItemStack customblade = SlashBlade.findItemStack("flammpfeil.slashblade", "slashbladeWrapper", 1);
            SlashBlade.wrapBlade.removeWrapItem(customblade);
            ItemStack innerBlade = CraftTweakerMC.getItemStack(type.wrapper);
            SlashBlade.wrapBlade.setWrapItem(customblade, innerBlade);
            NBTTagCompound tagCompound = customblade.getTagCompound();
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
            ItemSlashBlade.ModelName.set(tagCompound, type.model);//模型路径 .obj

            if (type.processor != null) return CraftTweakerMC.getItemStack(type.processor.process(CraftTweakerMC.getIItemStack(customblade)));
            return customblade;
        }
    }

}
