package mods.aginsun.commandtree.network.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import mods.aginsun.commandtree.network.PacketType;
import mods.aginsun.commandtree.tileentity.TileEntityCommand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.Player;

public class PacketCommand extends PacketCT
{
	String command;
	int x, y, z;
	
	public PacketCommand()
	{
		super(PacketType.COMMAND, false);
	}
	
	public PacketCommand(String command, int x, int y, int z) 
	{
		super(PacketType.COMMAND, false);
		this.command = command;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void readData(DataInputStream data) throws IOException 
	{
		command = data.readUTF();
		x = data.readInt();
		y = data.readInt();
		z = data.readInt();
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException 
	{
		dos.writeUTF(command);
		dos.writeInt(x);
		dos.writeInt(y);
		dos.writeInt(z);
	}

	@Override
	public void execute(INetworkManager network, Player thePlayer) 
	{
		EntityPlayer player = (EntityPlayer)thePlayer;
		World world = player.worldObj;
		TileEntityCommand tileEntity = (TileEntityCommand)world.getBlockTileEntity(x, y, z);
		tileEntity.setCommandSenderName(player.username);
		tileEntity.setCommand(command);
	}
}
