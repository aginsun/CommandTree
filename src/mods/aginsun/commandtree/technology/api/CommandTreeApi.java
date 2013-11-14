package mods.aginsun.commandtree.technology.api;

import java.util.HashMap;

import cpw.mods.fml.common.FMLLog;


public class CommandTreeApi
{
	private static HashMap<String, Technology> techList = new HashMap<String, Technology>();
	
	/** Register your technologies in the Post-Init event!
	 * 
	 * @param UniqueID (Unique ID for the Technology)
	 * @param tech (The technology)
	 */
	public static void registerTechnology(String UniqueID, Technology tech)
	{
		if(!techList.containsKey(UniqueID))
			techList.put(UniqueID, tech);
		else
		{
			FMLLog.warning("[CommandTree] A different mod tried to register a technology with the name %s whilst there is already a tech with that name!", UniqueID);
			FMLLog.warning("[CommandTree] Technology not registered due to this conflict.");
		}
	}
	
	public static HashMap<String, Technology> getTechList()
	{
		return techList;
	}
}
