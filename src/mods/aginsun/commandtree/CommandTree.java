package mods.aginsun.commandtree;

import mods.aginsun.commandtree.blocks.BlockCommand;
import mods.aginsun.commandtree.client.core.KeyHandlerCL;
import mods.aginsun.commandtree.core.CommonProxy;
import mods.aginsun.commandtree.handlers.CommandHandlerCT;
import mods.aginsun.commandtree.handlers.PlayerLoginHandler;
import mods.aginsun.commandtree.network.PacketHandler;
import mods.aginsun.commandtree.tileentity.TileEntityCommand;
import mods.aginsun.commandtree.util.Util;
import net.minecraft.block.Block;
import net.minecraft.command.CommandHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = Util.ModID, name = Util.Name, version = Util.Version)
@NetworkMod(channels = { Util.ModID },clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class CommandTree 
{
	@Instance(Util.ModID)
	public static CommandTree instance = new CommandTree();
	
	@SidedProxy (clientSide = "mods.aginsun.commandtree.client.core.ClientProxy", serverSide = "mods.aginsun.commandtree.core.CommonProxy")
	public static CommonProxy proxy;

	public static Block CommandBlock;
	public static int CommandBlockID;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		config.load();
		
		CommandBlockID = config.getBlock("Command Block", Util.COMMAND_BLOCK_ID).getInt();
		
		config.save();
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		CommandBlock = new BlockCommand(CommandBlockID).setUnlocalizedName("BlockCommand").setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(CommandBlock, "CommandosBlock");
		LanguageRegistry.addName(CommandBlock, "El Command Block");
		KeyBindingRegistry.registerKeyBinding(new KeyHandlerCL());
		proxy.registerTechnologies();
		
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);
		GameRegistry.registerTileEntity(TileEntityCommand.class, "TileCommand");
	}
	
	@EventHandler
	public void serverStartingEvent(FMLServerStartingEvent event)
	{
		CommandHandler commandManager = (CommandHandler)event.getServer().getCommandManager();
		commandManager.registerCommand(new CommandHandlerCT());
	}
	
	@EventHandler
	public void serverStartedEvent(FMLServerStartedEvent event)
	{
		GameRegistry.registerPlayerTracker(new PlayerLoginHandler());
	}
}
