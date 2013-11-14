package mods.aginsun.commandtree.tileentity;

import mods.aginsun.commandtree.technology.TechHandler;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class TileEntityCommand extends TileEntity implements ICommandSender
{
    private String command = "";

	private String commandSenderName = "@";
	
	public TileEntityCommand() { }
	
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setString("command", command);
		nbt.setString("sender", commandSenderName);
	}
	
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		command = nbt.getString("command");
		commandSenderName = nbt.getString("sender");
	}
	
    public int executeCommandOnPowered(World par1World)
    {
        if (par1World.isRemote)
        {
            return 0;
        }
        else
        {
            MinecraftServer minecraftserver = MinecraftServer.getServer();

            if (minecraftserver != null && minecraftserver.isCommandBlockEnabled())
            {
                ICommandManager icommandmanager = minecraftserver.getCommandManager();
                return icommandmanager.executeCommand(this, this.command);
            }
            else
            {
                return 0;
            }
        }
    }
    
	public String getCommand()
	{
		return command;
	}
	
	@Override
	public String getCommandSenderName() 
	{
		return commandSenderName;
	}

	public void setCommandSenderName(String displayName)
	{
		commandSenderName = displayName;
	}

	@Override
	public boolean canCommandSenderUseCommand(int i, String s) 
	{
		return TechHandler.getInstance().canUseCommand(s, commandSenderName);
	}

	@Override
	public ChunkCoordinates getPlayerCoordinates()
	{
		return new ChunkCoordinates(this.xCoord, this.yCoord, this.zCoord);
	}

	@Override
	public World getEntityWorld() 
	{
		return this.getWorldObj();
	}
	
	@Override
	public void sendChatToPlayer(ChatMessageComponent chatmessagecomponent) { }


	public void setCommand(String s)
	{
		command = s;
	}
}