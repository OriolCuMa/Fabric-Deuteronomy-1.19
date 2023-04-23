package net.oriol.deuteronomymod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Hand;
import org.lwjgl.glfw.GLFW;

public class DeuteronomyModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        var keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "deuteronomymod.keybinds.usepearl",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_Q,
                "category.deuteronomymod"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keybind.wasPressed()) {
                var initialHotbar = MinecraftClient.getInstance().player.getInventory().selectedSlot;
                MinecraftClient.getInstance().player.getInventory().selectedSlot = 6;
                MinecraftClient.getInstance().interactionManager.interactItem(
                        MinecraftClient.getInstance().player, Hand.MAIN_HAND);
                MinecraftClient.getInstance().player.getInventory().selectedSlot = initialHotbar;
            }
        });
    }
}
