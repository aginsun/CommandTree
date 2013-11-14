package mods.aginsun.commandtree.technology.techs;

import mods.aginsun.commandtree.technology.api.CommandTreeApi;
import mods.aginsun.commandtree.technology.api.Technology;
import mods.aginsun.commandtree.technology.api.Vector2;

public class TechTroll implements Technology
{
	@Override
	public String getName() 
	{
		return "StartPart2";
	}

	@Override
	public String[] getDescription() 
	{
		return new String[] {"one should know,", "where we are gonna be", "watching blizzcon"};
	}

	@Override
	public Technology[] getTechs() 
	{
		return new Technology[] {CommandTreeApi.getTechList().get("tech1")};
	}

	@Override
	public Vector2 getLocation() 
	{
		return new Vector2(140, 150);
	}
	
	public String[] commands()
	{
		return new String[] {};
	}
}
