package mods.aginsun.commandtree.core;

import mods.aginsun.commandtree.client.guis.GuiCommand;
import mods.aginsun.commandtree.technology.api.CommandTreeApi;
import mods.aginsun.commandtree.technology.techs.TechStart;
import mods.aginsun.commandtree.technology.techs.TechTroll;
import mods.aginsun.commandtree.tileentity.TileEntityCommand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler
{
	public void registerTechnologies()
	{
		CommandTreeApi.registerTechnology("tech1", new TechStart());
		CommandTreeApi.registerTechnology("tech2", new TechTroll());
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) 
	{
    	switch(ID)
    	{
    		case 1:
    			return null;
            default:
            	return null;
    	}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
    	switch(ID)
    	{
    		case 1:
    			return new GuiCommand((TileEntityCommand) world.getBlockTileEntity(x, y, z));
    		default:
    			return null;
    	}
	}
}
