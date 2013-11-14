package mods.aginsun.commandtree.technology.techs;

import mods.aginsun.commandtree.technology.api.Technology;
import mods.aginsun.commandtree.technology.api.Vector2;

public class TechStart implements Technology
{
	@Override
	public String getName() 
	{
		return "The Start";
	}

	@Override
	public String[] getDescription() 
	{
		return new String[] {"When one starts which such technology,", "one should keep in mind", "that there are limits."};
	}

	@Override
	public Technology[] getTechs() 
	{
		return null;
	}

	@Override
	public Vector2 getLocation() 
	{
		return new Vector2(100, 100);
	}
	
	@Override
	public String[] commands()
	{
		return new String[] { "say" };
	}
}
