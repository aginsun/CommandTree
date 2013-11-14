package mods.aginsun.commandtree.technology;

import java.util.HashMap;

import mods.aginsun.commandtree.technology.api.CommandTreeApi;
import mods.aginsun.commandtree.technology.api.Technology;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class TechHandler 
{
	public static final String COMPOUND_NAME = "TechStuff";
	private static TechHandler instance = new TechHandler();
	
	private HashMap<String, NBTTagCompound> playerTechList = new HashMap<String, NBTTagCompound>();
	
	public static TechHandler getInstance()
	{
		return instance;
	}
	
	public EnumState getTechnologyState(EntityPlayer player, Technology tech)
	{
		NBTTagCompound nbt = getTagCompound(player);
		return EnumState.values()[nbt.getInteger(tech.getName())];
	}
	
	public EnumState getTechnologyState(String username, Technology tech)
	{
		NBTTagCompound nbt = playerTechList.get(username);
		return EnumState.values()[nbt.getInteger(tech.getName())];
	}
	
	public void setTechnologyState(EntityPlayer player, Technology tech, EnumState state)
	{
		NBTTagCompound nbt = getTagCompound(player);
		nbt.setInteger(tech.getName(), state.ordinal());
		setTagCompound(player, nbt);	
	}
	
	public void setTagCompound(EntityPlayer player, NBTTagCompound nbt)
	{
		playerTechList.put(player.username, nbt);
	}
	
	public NBTTagCompound getTagCompound(EntityPlayer player)
	{
		return playerTechList.get(player.username);
	}

	public boolean canUseCommand(String command, String username) 
	{
		if(username == "@" || username == "Rcon")
			return false;
		
		command = command.trim();

        if (command.startsWith("/"))
        {
        	command = command.substring(1);
        }
		
		String[] list = command.split(" ");
		
		for(Technology tech : CommandTreeApi.getTechList().values())
		{
			if(getTechnologyState(username, tech) == EnumState.COMPLETED)
			{
				for(String string : tech.commands())
				{
					if(list[0].equals(string))
					{
						return true;
					}
				}
			}
		}
		
		return false;
	}
}