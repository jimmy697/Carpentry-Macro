package com.carpmacro;

import com.carpmacro.macro.Carpentry;
import com.carpmacro.utils.ModMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "carpmacro", name = "Carpentry Macro", version = "1.0.0")
public class CarpMacro {
    public static final Minecraft mc = Minecraft.getMinecraft();

    public static KeyBinding enableKey = new KeyBinding("Enable Macro", 33, "Carp Macro");

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        registerEvents(
                new Carpentry(), new ModMessage()
        );
        ClientRegistry.registerKeyBinding(enableKey);

    }

    private void registerEvents(Object... events) {
        for (Object event : events)
            MinecraftForge.EVENT_BUS.register(event);
    }
}