package mods.aginsun.commandtree.technology.api;

public interface Technology 
{	
	public String getName();
	
	public String[] getDescription();
	
	public Technology[] getTechs();
	
	public Vector2 getLocation();
	
	public String[] commands();
}
