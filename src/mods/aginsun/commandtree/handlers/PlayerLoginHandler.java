package mods.aginsun.commandtree.handlers;

import mods.aginsun.commandtree.technology.TechHandler;
import mods.aginsun.commandtree.util.Util;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.IPlayerTracker;

public class PlayerLoginHandler implements IPlayerTracker
{
	@Override
	public void onPlayerLogin(EntityPlayer player) 
	{
		getData(player);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) 
	{
		saveData(player);
	}

	private void getData(EntityPlayer player)
	{
		NBTTagCompound nbt = getDataCompound(player);
		if(nbt != null)
		{
			TechHandler.getInstance().setTagCompound(player, nbt.getCompoundTag(TechHandler.COMPOUND_NAME));
		}
	}
	
	private void saveData(EntityPlayer player)
	{
		NBTTagCompound nbt = getDataCompound(player);
		if(nbt != null)
		{
			nbt.setCompoundTag(TechHandler.COMPOUND_NAME, TechHandler.getInstance().getTagCompound(player));
			saveDataCompound(player, nbt);
		}
	}
	
	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {}
	@Override
	public void onPlayerRespawn(EntityPlayer player) {}
	
	private NBTTagCompound getDataCompound(EntityPlayer player)
	{
		if(player != null)
		{
			NBTTagCompound nbt = player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
			if(nbt != null)
			{
				NBTTagCompound data = nbt.getCompoundTag(Util.ModID);
				if(data != null)
				{
					return data;
				}
			}
		}
		return null;
	}	
	
	private void saveDataCompound(EntityPlayer player, NBTTagCompound nbt) 
	{
		if(player != null)
		{
			NBTTagCompound data = player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
			if(data != null)
			{
				data.setCompoundTag(Util.ModID, nbt);
				player.getEntityData().setCompoundTag(EntityPlayer.PERSISTED_NBT_TAG, data);
			}
		}
	}
}
