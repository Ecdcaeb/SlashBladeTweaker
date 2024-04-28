#debug
#loader slashbladetweaker

import crafttweaker.item.IItemStack;
import crafttweaker.player.IPlayer;

import mods.Hileb.slashbladetweaker.SEType;
import mods.Hileb.slashbladetweaker.BladeType;

import mods.Hileb.slashbladetweaker.SARegistry;
import mods.Hileb.slashbladetweaker.SERegistry;
import mods.Hileb.slashbladetweaker.BladeRegistry;
import mods.Hileb.slashbladetweaker.BladeRecipe;

SARegistry.registerSA(20, "fancy_sa", function(s as IItemStack,  p as IPlayer){
    p.sendChat("sa used");
});

SERegistry.register(SEType.create( 10, "test_se"));

BladeRegistry
.named("example:test_one")
.killCount(10000).refine(1000).proudSoul(2600000)
.texture("named/sange/white").model("named/sange/sange")
.specialAttack(20)
.wrap(BladeRegistry.findItem("minecraft:wooden_sword"))
.process(function(s as crafttweaker.item.IItemStack){
    mods.Hileb.slashbladetweaker.SERegistry.addSEToItem(s, "test_se");
    return s;
})
.register()

.named("test_two").register();


val required as IItemStack =  BladeRegistry.getBladeItemStack("example:test_one");

BladeType.setKillCount(required, 1000);

BladeRecipe.addShaped("example:shaped",
        BladeRegistry.getBladeItemStack("flammpfeil.slashblade.named.fox.white") , //Output
        required, //required
        [
            [<ore:ingotIron>, <ore:ingotIron>, <ore:ingotIron>],
            [<ore:ingotIron>, required, <ore:ingotIron>],
            [<ore:ingotIron>, null, <ore:ingotIron>]
        ]
        //@Optional IRecipeFunction function
        //@Optional IRecipeAction action
);


