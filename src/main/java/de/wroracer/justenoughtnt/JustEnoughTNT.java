package de.wroracer.justenoughtnt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.wroracer.justenoughtnt.setup.Registration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("justenoughtnt")
public class JustEnoughTNT {

    public static final String MOD_ID = "justenoughtnt";

    public static final Logger LOGGER = LogManager.getLogger(JustEnoughTNT.class);

    public JustEnoughTNT() {
        Registration.register();
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
}
