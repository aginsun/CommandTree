package mods.aginsun.commandtree.blocks;

import java.util.Random;

import mods.aginsun.commandtree.CommandTree;
import mods.aginsun.commandtree.tileentity.TileEntityCommand;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCommand extends BlockContainer
{
	public BlockCommand(int par1)
	{
		super(par1, Material.iron);
	}

	@Override
	public TileEntity createNewTileEntity(World world) 
	{
		return new TileEntityCommand();
	}
	
    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!par1World.isRemote)
        {
            boolean flag = par1World.isBlockIndirectlyGettingPowered(par2, par3, par4);
            int i1 = par1World.getBlockMetadata(par2, par3, par4);
            boolean flag1 = (i1 & 1) != 0;

            if (flag && !flag1)
            {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, i1 | 1, 4);
                par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate(par1World));
            }
            else if (!flag && flag1)
            {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, i1 & -2, 4);
            }
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        TileEntity tileentity = par1World.getBlockTileEntity(par2, par3, par4);

        if (tileentity != null && tileentity instanceof TileEntityCommand)
        {
        	TileEntityCommand tileentitycommandblock = (TileEntityCommand)tileentity;
            tileentitycommandblock.executeCommandOnPowered(par1World);
            par1World.func_96440_m(par2, par3, par4, this.blockID);
        }
    }

    public int tickRate(World par1World)
    {
        return 1;
    }
    
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
    	TileEntityCommand tileentitycommandblock = (TileEntityCommand)par1World.getBlockTileEntity(par2, par3, par4);

        if (tileentitycommandblock != null)
        {
            par5EntityPlayer.openGui(CommandTree.instance, 1, par1World, par2, par3, par4);
        }

        return true;
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        TileEntityCommand tileentitycommandblock = (TileEntityCommand)par1World.getBlockTileEntity(par2, par3, par4);

        if (par6ItemStack.hasDisplayName())
        {
            tileentitycommandblock.setCommandSenderName(par6ItemStack.getDisplayName());
        }
    }
}
