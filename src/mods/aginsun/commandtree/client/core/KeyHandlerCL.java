package mods.aginsun.commandtree.client.core;

import java.util.EnumSet;

import mods.aginsun.commandtree.client.guis.GuiCommand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class KeyHandlerCL extends KeyHandler
{
	public static KeyBinding StatsScreen = new KeyBinding("Tale of Kingdoms 2 StatScreen", Keyboard.KEY_O);

	public static KeyBinding[] arrayOfKeys = new KeyBinding[] {StatsScreen};
	public static boolean[] areRepeating = new boolean[] {false};

	public KeyHandlerCL() 
	{
	  super(arrayOfKeys, areRepeating);
	}


	@Override
	public String getLabel()
	{
		return "LALALALLA";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) 
	{
		if(kb.keyCode == StatsScreen.keyCode && Minecraft.getMinecraft().currentScreen == null)
		{
			//Minecraft.getMinecraft().displayGuiScreen(new GuiCommand());
		}
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) 
	{
		
	}

	@Override
	public EnumSet<TickType> ticks() 
	{
		return EnumSet.of(TickType.CLIENT);
	}
}
