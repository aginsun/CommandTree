package mods.aginsun.commandtree.network;

import mods.aginsun.commandtree.network.packets.PacketCT;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet250, Player player) 
	{
		PacketCT packet = PacketType.buildPacket(packet250.data);
		packet.execute(manager, player);
	}
}
