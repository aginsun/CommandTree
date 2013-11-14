package mods.aginsun.commandtree.client.guis;

import mods.aginsun.commandtree.network.PacketType;
import mods.aginsun.commandtree.network.packets.PacketCommand;
import mods.aginsun.commandtree.tileentity.TileEntityCommand;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCommand extends GuiScreen
{
    private GuiTextField commandTextField;

    private final TileEntityCommand commandBlock;
    private GuiButton doneBtn;
    private GuiButton cancelBtn;
    private GuiButton validateBtn;

    public GuiCommand(TileEntityCommand par1TileEntityCommandBlock)
    {
        this.commandBlock = par1TileEntityCommandBlock;
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        this.commandTextField.updateCursorCounter();
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(this.doneBtn = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 72 + 12, I18n.getString("gui.done")));
        this.buttonList.add(this.validateBtn = new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96 + 12, "Validate Command"));
        this.buttonList.add(this.cancelBtn = new GuiButton(2, this.width / 2 - 100, this.height / 4 + 120 + 12, I18n.getString("gui.cancel")));
        this.commandTextField = new GuiTextField(this.fontRenderer, this.width / 2 - 150, 60, 300, 20);
        this.commandTextField.setMaxStringLength(32767);
        this.commandTextField.setFocused(true);
        this.commandTextField.setText(this.commandBlock.getCommand());
        this.doneBtn.enabled = this.commandTextField.getText().trim().length() > 0;
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.enabled)
        {
            if (par1GuiButton.id == 2)
            {
                this.mc.displayGuiScreen((GuiScreen)null);
            }
            else if (par1GuiButton.id == 0)
            {
            	PacketDispatcher.sendPacketToServer(PacketType.populatePacket(new PacketCommand(commandTextField.getText(), commandBlock.xCoord, commandBlock.yCoord, commandBlock.zCoord)));
            	commandBlock.setCommand(commandTextField.getText());
                this.mc.displayGuiScreen((GuiScreen)null);
            }
            else if (par1GuiButton.id == 1)
            {
            	//PacketDispatcher.sendPacketToServer(PacketType.populatePacket(new PacketCommand(commandTextField.getText(), commandBlock.xCoord, commandBlock.yCoord, commandBlock.zCoord)));
            }
        }
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2)
    {
        this.commandTextField.textboxKeyTyped(par1, par2);
        this.doneBtn.enabled = this.commandTextField.getText().trim().length() > 0;

        if (par2 != 28 && par2 != 156)
        {
            if (par2 == 1)
            {
                this.actionPerformed(this.cancelBtn);
            }
        }
        else
        {
            this.actionPerformed(this.doneBtn);
        }
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int par1, int par2, int par3)
    {
        super.mouseClicked(par1, par2, par3);
        this.commandTextField.mouseClicked(par1, par2, par3);
    }
    
    public boolean doesGuiPauseGame()
    {
    	return false;
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, I18n.getString("advMode.setCommand"), this.width / 2, 20, 16777215);
        this.drawString(this.fontRenderer, I18n.getString("advMode.command"), this.width / 2 - 150, 47, 10526880);
        this.drawString(this.fontRenderer, I18n.getString("advMode.nearestPlayer"), this.width / 2 - 150, 97, 10526880);
        this.drawString(this.fontRenderer, I18n.getString("advMode.randomPlayer"), this.width / 2 - 150, 108, 10526880);
        this.drawString(this.fontRenderer, I18n.getString("advMode.allPlayers"), this.width / 2 - 150, 119, 10526880);
        this.commandTextField.drawTextBox();
        super.drawScreen(par1, par2, par3);
    }
}