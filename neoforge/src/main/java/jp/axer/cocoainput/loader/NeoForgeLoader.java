package jp.axer.cocoainput.loader;

import jp.axer.cocoainput.CocoaInput;
import jp.axer.cocoainput.util.ModLogger;
import jp.axer.cocoainput.util.FCConfig;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.client.ConfigScreenHandler;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod("cocoainput")
public class NeoForgeLoader {
	private CocoaInput instance;

	public NeoForgeLoader(){
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		NeoForge.EVENT_BUS.register(this);

	}

	private void setup(final FMLCommonSetupEvent event) {
			this.instance=new CocoaInput("Minecraft NeoForge",ModList.get().getModFileById("cocoainput").getFile().getFilePath().toString());
			ModLogger.log("NeoForge config setup");
			ModLogger.log("Config path:"+FMLPaths.CONFIGDIR.get().resolve("cocoainput.json").toString());
			FCConfig.init("cocoainput",FMLPaths.CONFIGDIR.get().resolve("cocoainput.json"), FCConfig.class);
			ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, ()->new ConfigScreenHandler.ConfigScreenFactory((mc, modListScreen)->new FCConfig().getScreen(modListScreen)));
			CocoaInput.config=new FCConfig();
			ModLogger.log("ConfigPack:"+CocoaInput.config.isAdvancedPreeditDraw()+" "+CocoaInput.config.isNativeCharTyped());
	}
	@SubscribeEvent
    public void didChangeGui(ScreenEvent.Opening event) {
		this.instance.distributeScreen(event.getScreen());
	}
	@SubscribeEvent
	public void didChangeGui(ScreenEvent.Closing event) {
		this.instance.distributeScreen(event.getScreen());
	}
}
