package smartin.tetraticcombat;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import smartin.tetraticcombat.ItemResolver.ReloadListener;


// The value here should match an entry in the META-INF/mods.toml file
@Mod("tetratic_combat_expanded")
public class TetraticCombat {

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public static String MODID = "tetratic_combat_expanded";

    public TetraticCombat() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ForgeConfigHolder.COMMON_SPEC);
        if(FMLEnvironment.dist==Dist.CLIENT){
            clientSetup();
        }
        MinecraftForge.EVENT_BUS.register(this);
    }

    @OnlyIn(Dist.CLIENT)
    private void clientSetup(){
        MinecraftForge.EVENT_BUS.register(ClientEventHandler.class);
    }

    @SubscribeEvent
    public void addReloadListener(AddReloadListenerEvent event) {
        event.addListener(new ReloadListener());
    }
}
