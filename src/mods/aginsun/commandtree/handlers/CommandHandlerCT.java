package mods.aginsun.commandtree.handlers;

import mods.aginsun.commandtree.technology.EnumState;
import mods.aginsun.commandtree.technology.TechHandler;
import mods.aginsun.commandtree.technology.api.CommandTreeApi;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;

public class CommandHandlerCT extends CommandBase
{
	@Override
	public String getCommandName() 
	{
		return "tok";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) 
	{		
		TechHandler.getInstance().setTechnologyState((EntityPlayer) sender, CommandTreeApi.getTechList().get("tech1"), EnumState.COMPLETED);
	}
		
	public void sendMessage(ICommandSender sender, String message)
	{
		sender.sendChatToPlayer(new ChatMessageComponent().addText(message));
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return null;
	}
}
