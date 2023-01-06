package de.wroracer.justenoughtnt;

import de.wroracer.justenoughtnt.setup.Registration;
import de.wroracer.justenoughtnt.util.ModResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    public static ModResourceLocation getId(String path) {
        if (path.contains(":")) {
            throw new IllegalArgumentException("path contains namespace");
        }
        return new ModResourceLocation(path);
    }
}
