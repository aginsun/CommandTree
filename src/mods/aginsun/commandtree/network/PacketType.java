package mods.aginsun.commandtree.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import mods.aginsun.commandtree.network.packets.PacketCT;
import mods.aginsun.commandtree.network.packets.PacketCommand;
import mods.aginsun.commandtree.util.Util;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

public enum PacketType 
{
	COMMAND(PacketCommand.class);
	
	private Class<? extends PacketCT> clazz;
	
	PacketType(Class<? extends PacketCT> clazz)
	{
		this.clazz = clazz;
	}
	
    public static PacketCT buildPacket(byte[] data) {

        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        int selector = bis.read();
        DataInputStream dis = new DataInputStream(bis);

        PacketCT packet = null;

        try {
            packet = values()[selector].clazz.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }

        packet.readPopulate(dis);

        return packet;
    }

    public static PacketCT buildPacket(PacketType type) {

    	PacketCT packet = null;

        try {
            packet = values()[type.ordinal()].clazz.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return packet;
    }

    public static Packet populatePacket(PacketCT packet) {

        byte[] data = packet.populate();

        Packet250CustomPayload packet250 = new Packet250CustomPayload();
        packet250.channel = Util.ModID;
        packet250.data = data;
        packet250.length = data.length;
        packet250.isChunkDataPacket = packet.isChunkDataPacket;

        return packet250;
    }
}
