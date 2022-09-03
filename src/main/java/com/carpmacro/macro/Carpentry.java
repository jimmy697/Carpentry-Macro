package com.carpmacro.macro;

import com.carpmacro.CarpMacro;
import com.carpmacro.utils.ModMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import java.util.List;

public class Carpentry {

    private Thread thread;

    public static boolean carpentry;

    @SubscribeEvent
    public void key(InputEvent.KeyInputEvent event) {
        if(CarpMacro.enableKey.isPressed()) {
            carpentry = !carpentry;
            String str = carpentry ? "Carpentry macro activated" : "Carpentry macro deactivated";
            System.out.println("HI HIHIHI: " + carpentry);
            ModMessage.sendModMessage(str);
        }
     }

    public static void openInventory(){
        CarpMacro.mc.displayGuiScreen(new GuiInventory(CarpMacro.mc.thePlayer));
    }
    public static List<String> getLore(ItemStack item) {
        return (item != null) ? item.getTooltip((EntityPlayer)(Minecraft.getMinecraft()).thePlayer, false) : null;
    }

    public static String removeFormatting(String input) {
        return input.replaceAll("§[0-9a-fk-or]", "");
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if(!carpentry)
            return;
        if(thread == null || !thread.isAlive()) {
            thread = new Thread(() -> {
                try {
                    CarpMacro.mc.thePlayer.sendChatMessage("/bz");
                    Thread.sleep(1000);
                    //Claiming items
                    if(CarpMacro.mc.thePlayer.openContainer instanceof ContainerChest) {
                        clickWindow(CarpMacro.mc.thePlayer.openContainer.windowId, 50, 0, 0);
                        Thread.sleep(1000);
                        List<Slot> chestInventory = ((GuiChest) CarpMacro.mc.currentScreen).inventorySlots.inventorySlots;
                        for(Slot slot : chestInventory) {
                            if(!slot.getHasStack()) continue;
                            if(slot.getStack().getDisplayName().contains("Enchanted Diamond") && removeFormatting(String.valueOf(getLore(slot.getStack()))).contains("Click to claim")) {
                                clickWindow(CarpMacro.mc.thePlayer.openContainer.windowId, slot.slotNumber, 0, 0);
                                Thread.sleep(900);
                                CarpMacro.mc.thePlayer.closeScreen();
                                Thread.sleep(900);
                                openInventory();
                                Thread.sleep(900);
                                clickWindow(CarpMacro.mc.thePlayer.openContainer.windowId, 44, 0, 0);
                                Thread.sleep(900);
                                clickWindow(CarpMacro.mc.thePlayer.openContainer.windowId, 31, 0, 0);
                                Thread.sleep(900);
                                clickWindow(CarpMacro.mc.thePlayer.openContainer.windowId, 16, 0, 0);
                                Thread.sleep(300);
                                for(int b = 1; b <= 13; b++) {
                                    clickWindow(CarpMacro.mc.thePlayer.openContainer.windowId, 16, 0, 0);
                                    Thread.sleep(300);
                                }
                                CarpMacro.mc.thePlayer.closeScreen();
                                Thread.sleep(1000);
                            }

                        }
                    }
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
    }

    public static void clickWindow(int windowID, int slotID, int mouseButtonClicked, int mode) {
        if (CarpMacro.mc.thePlayer.openContainer instanceof ContainerChest || CarpMacro.mc.currentScreen instanceof GuiInventory) {
            CarpMacro.mc.playerController.windowClick(windowID, slotID, mouseButtonClicked, mode, CarpMacro.mc.thePlayer);
        } else {
            return;
        }
    }

}
