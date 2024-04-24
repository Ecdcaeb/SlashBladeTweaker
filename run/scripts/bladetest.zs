#debug
#loader slashbladetweaker

import crafttweaker.item.IItemStack;
import crafttweaker.player.IPlayer;
import mods.Hileb.slashbladetweaker.SEType;

import mods.Hileb.slashbladetweaker.SARegistry;
import mods.Hileb.slashbladetweaker.SERegistry;
import mods.Hileb.slashbladetweaker.BladeRegistry;

SARegistry.registerSA(20, "fancy_sa", function(s as IItemStack,  p as IPlayer){
    p.sendChat("sa used");
});

SERegistry.register(
    SEType.create( 10, "test_se", function(s as SEType){
             SEType.addToBus(s);
        }
    )
);

BladeRegistry

.named("test_one")
.killCount(10000).refine(1000).proudSoul(2600000)
.texture("named/sange/white").model("named/sange/sange")
.specialAttack(20)
.process(function(s as crafttweaker.item.IItemStack){
    mods.Hileb.slashbladetweaker.SERegistry.addSEToItem(s, "test_se");
    return s;
})
.register()

.named("test_two").register();


