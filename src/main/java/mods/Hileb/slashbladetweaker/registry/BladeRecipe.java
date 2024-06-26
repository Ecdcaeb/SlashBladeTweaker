package mods.Hileb.slashbladetweaker.registry;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.recipes.IRecipeAction;
import crafttweaker.api.recipes.IRecipeFunction;
import crafttweaker.mc1120.recipes.MCRecipeManager;
import crafttweaker.mc1120.recipes.MCRecipeShaped;
import mods.Hileb.slashbladetweaker.utils.BladeUtils;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.TagPropertyAccessor;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import stanhebben.zenscript.annotations.NotNull;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

/**
 * @project SlashBladeTweaker
 * @author  Hileb
 * @date 2024/4/26 12:04
 **/
@ZenRegister
@ZenClass("mods.Hileb.slashbladetweaker.registry.BladeRecipe")
public class BladeRecipe {

    /**
     * @param name the name of recipe, could be a resourceLocation, if not , domain is crafttweaker
     * @param output the output, usually blade
     * @param required the blade required, you can add some additional condition if you like
     * @param ingredients 2D recipe format
     * @param function optional, function, like any other crt recipe
     * @param action optional, action, like any other crt recipe
     */
    @ZenMethod
    public static void addShaped(String name, IItemStack output, IItemStack required, IIngredient[][] ingredients, @Optional IRecipeFunction function, @Optional IRecipeAction action){
        MCRecipeManager.recipesToAdd.add(new RecipeAwakeBlade.Action(name, output, ingredients, required, function, action, false, false));
    }
    public static class RecipeAwakeBlade extends MCRecipeShaped{
        public IItemStack requiredStateBlade;
        public String name;
        public boolean isMirror;
        public RawRecipeAwakeBlade recipeAwakeBlade;

        public RecipeAwakeBlade(String name, IIngredient[][] ingredients, IItemStack output, IItemStack requiredBlade, IRecipeFunction recipeFunction, IRecipeAction recipeAction, boolean isMirrored, boolean isHidden) {
            super(ingredients, output, recipeFunction, recipeAction, isMirrored, isHidden);
            this.requiredStateBlade = requiredBlade;
            this.name = mapName(name);
            this.isMirror = isMirrored;

            ResourceLocation rname = new ResourceLocation(getCorrectName(name));
            CraftingHelper.ShapedPrimer shapedPrimer = new CraftingHelper.ShapedPrimer();
            shapedPrimer.height = this.getRecipeHeight();
            shapedPrimer.width = this.getRecipeWidth();
            shapedPrimer.mirrored = this.isMirror;
            shapedPrimer.input = this.ingredientList;
            this.recipeAwakeBlade  = new RawRecipeAwakeBlade(rname, this.getRecipeOutput(), this.requiredStateBlade, shapedPrimer);
        }
        public static String getCorrectName(String rawName){
            return BladeUtils.getResourceLocationWithDefaultDomain(rawName, "crafttweaker");
        }
        public static String mapName(String rawName){
            return rawName.replace(':','_');
        }

        @Override
        public boolean matches(InventoryCrafting inv, World worldIn) {
            return recipeAwakeBlade.matches(inv, worldIn);
        }

        @Override
        public ItemStack getCraftingResult(InventoryCrafting inv) {
            return recipeAwakeBlade.getCraftingResult(inv);
        }

        public static class Action extends MCRecipeManager.ActionBaseAddRecipe {
            public final String name;

            public Action(String name, IItemStack output, IIngredient[][] ingredients, IItemStack requiredBlade, IRecipeFunction function, IRecipeAction action, boolean mirrored, boolean hidden) {
                this.recipe = new RecipeAwakeBlade(name, ingredients, output, requiredBlade, function, action, mirrored, hidden);
                this.name = name;
                this.output = output;
                this.isShaped = true;
                if (recipe.hasTransformers())
                    MCRecipeManager.transformerRecipes.add(recipe);
                if (recipe.hasRecipeAction())
                    MCRecipeManager.actionRecipes.add(recipe);
                setName(mapName(name));
            }

            @Override
            public void apply() {
                ForgeRegistries.RECIPES.register(this.getRecipe().setRegistryName(new ResourceLocation(getCorrectName(this.name))));
            }
        }
        @SuppressWarnings("all")
        public static class RawRecipeAwakeBlade extends ShapedOreRecipe{

            ItemStack requiredStateBlade;

            public RawRecipeAwakeBlade(ResourceLocation loc, ItemStack result, IItemStack requiredStateBlade, CraftingHelper.ShapedPrimer shapedPrimer) {
                super(loc, result, shapedPrimer);
                this.requiredStateBlade = (ItemStack) requiredStateBlade.getInternal();
            }

            @SuppressWarnings({"rawtypes","unchecked"})
            int tagValueCompare(TagPropertyAccessor access, NBTTagCompound reqTag, NBTTagCompound srcTag){
                return access.get(reqTag).compareTo(access.get(srcTag));
            }

            @Override
            public boolean matches(@Nonnull InventoryCrafting inv,@Nullable World world) {

                @SuppressWarnings("all")
                boolean result = super.matches(inv, world);

                if(result && !requiredStateBlade.isEmpty()){
                    requiredStateBlade.setItemDamage(OreDictionary.WILDCARD_VALUE);
                    for(int idx = 0; idx < inv.getSizeInventory(); idx++){
                        ItemStack curIs = inv.getStackInSlot(idx);
                        if(!curIs.isEmpty()
                                && curIs.getItem() instanceof ItemSlashBlade
                                && curIs.hasTagCompound()){



                            Map<Enchantment,Integer> oldItemEnchants = EnchantmentHelper.getEnchantments(requiredStateBlade);
                            for(Map.Entry<Enchantment,Integer> enchant: oldItemEnchants.entrySet())
                            {
                                int level = EnchantmentHelper.getEnchantmentLevel(enchant.getKey(),curIs);
                                if(level < enchant.getValue()){
                                    return false;
                                }
                            }

                            NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(requiredStateBlade);
                            NBTTagCompound srcTag = ItemSlashBlade.getItemTagCompound(curIs);

                            if(!curIs.getUnlocalizedName().equals(requiredStateBlade.getUnlocalizedName()))
                                return false;

                            if(0 < tagValueCompare(ItemSlashBlade.ProudSoul, reqTag, srcTag))
                                return false;
                            if(0 < tagValueCompare(ItemSlashBlade.KillCount, reqTag, srcTag))
                                return false;
                            if(0 < tagValueCompare(ItemSlashBlade.RepairCount, reqTag, srcTag))
                                return false;



                            break;
                        }
                    }
                }

                return result;
            }

            @Override
            public ItemStack getCraftingResult(@Nonnull InventoryCrafting var1) {
                ItemStack result = super.getCraftingResult(var1);

                for(int idx = 0; idx < var1.getSizeInventory(); idx++){
                    ItemStack curIs = var1.getStackInSlot(idx);
                    if(!curIs.isEmpty()
                            && curIs.getItem() instanceof ItemSlashBlade
                            && curIs.hasTagCompound()){

                        NBTTagCompound oldTag = curIs.getTagCompound();
                        oldTag = oldTag.copy();

                        {
                            NBTTagCompound newTag;
                            newTag = ItemSlashBlade.getItemTagCompound(result);

                            if(ItemSlashBladeNamed.CurrentItemName.exists(newTag)){
                                ItemStack tmp;
                                String key = ItemSlashBladeNamed.CurrentItemName.get(newTag);
                                tmp = SlashBlade.getCustomBlade(key);

                                if(!tmp.isEmpty())
                                    result = tmp;
                            }
                        }

                        NBTTagCompound newTag;
                        newTag = ItemSlashBlade.getItemTagCompound(result);

                        ItemSlashBlade.KillCount.set(newTag, ItemSlashBlade.KillCount.get(oldTag));
                        ItemSlashBlade.ProudSoul.set(newTag, ItemSlashBlade.ProudSoul.get(oldTag));
                        ItemSlashBlade.RepairCount.set(newTag, ItemSlashBlade.RepairCount.get(oldTag));

                        if(oldTag.hasUniqueId("Owner"))
                            newTag.setUniqueId("Owner", oldTag.getUniqueId("Owner"));

                        if(oldTag.hasKey(ItemSlashBlade.adjustXStr))
                            newTag.setFloat(ItemSlashBlade.adjustXStr,oldTag.getFloat(ItemSlashBlade.adjustXStr));

                        if(oldTag.hasKey(ItemSlashBlade.adjustYStr))
                            newTag.setFloat(ItemSlashBlade.adjustYStr,oldTag.getFloat(ItemSlashBlade.adjustYStr));

                        if(oldTag.hasKey(ItemSlashBlade.adjustZStr))
                            newTag.setFloat(ItemSlashBlade.adjustZStr,oldTag.getFloat(ItemSlashBlade.adjustZStr));

                        {
                            Map<Enchantment,Integer> newItemEnchants = EnchantmentHelper.getEnchantments(result);
                            Map<Enchantment,Integer> oldItemEnchants = EnchantmentHelper.getEnchantments(curIs);
                            for(Enchantment enchantIndex : oldItemEnchants.keySet())
                            {

                                int destLevel = newItemEnchants.getOrDefault(enchantIndex, 0);
                                int srcLevel = oldItemEnchants.get(enchantIndex);

                                srcLevel = Math.max(srcLevel, destLevel);
                                srcLevel = Math.min(srcLevel, enchantIndex.getMaxLevel());


                                boolean canApplyFlag = enchantIndex.canApply(result);
                                if(canApplyFlag){
                                    for(Enchantment curEnchantIndex : newItemEnchants.keySet()){
                                        if (curEnchantIndex != enchantIndex && !enchantIndex.isCompatibleWith(curEnchantIndex) /*canApplyTogether*/)
                                        {
                                            canApplyFlag = false;
                                            break;
                                        }
                                    }
                                    if (canApplyFlag)
                                        newItemEnchants.put(enchantIndex, srcLevel);
                                }
                            }
                            EnchantmentHelper.setEnchantments(newItemEnchants, result);
                        }
                    }
                }

                return result;
            }
        }
    }

}
