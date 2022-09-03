package com.carpmacro.utils;

import com.carpmacro.CarpMacro;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ModMessage {
    public static void sendModMessage(String message) {
        String modMsg = "§5[§bCarpMacro§5]§f " + message;
        CarpMacro.mc.thePlayer.addChatMessage((IChatComponent)new ChatComponentText(modMsg));
    }
}