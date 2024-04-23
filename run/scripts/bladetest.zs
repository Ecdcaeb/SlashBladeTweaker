#debug
#loader slashbladetweaker

mods.Hileb.slashbladetweaker.BladeRegistry
.named("test_one").refine(1000).register()
.named("test_two").refine(1000).register();

mods.Hileb.slashbladetweaker.SARegistry.registerSA(20, "fancy_sa", function( p as crafttweaker.player.IPlayer, s as crafttweaker.item.IItemStack){
    p.sendChat("sa used");
});