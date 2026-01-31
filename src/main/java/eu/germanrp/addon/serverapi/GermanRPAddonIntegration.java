package eu.germanrp.addon.serverapi;

import com.google.auto.service.AutoService;
import eu.germanrp.addon.serverapi.packet.atm.AddATMPacket;
import eu.germanrp.addon.serverapi.packet.atm.RegisteredATMsPacket;
import eu.germanrp.addon.serverapi.packet.atm.RemoveATMPacket;
import eu.germanrp.addon.serverapi.packet.atm.UpdateATMPacket;
import net.labymod.serverapi.api.packet.Direction;
import net.labymod.serverapi.core.AbstractLabyModPlayer;
import net.labymod.serverapi.core.AbstractLabyModProtocolService;
import net.labymod.serverapi.core.AddonProtocol;
import net.labymod.serverapi.core.integration.LabyModProtocolIntegration;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
@AutoService(LabyModProtocolIntegration.class)
public class GermanRPAddonIntegration implements LabyModProtocolIntegration {

    private AddonProtocol addonProtocol;

    @Override
    public void initialize(final AbstractLabyModProtocolService protocolService) {
        if (this.addonProtocol != null) {
            throw new IllegalStateException("GermanRPAddonIntegration is already initialized");
        }

        this.addonProtocol = new AddonProtocol(protocolService, "germanrpaddon");
        this.addonProtocol.registerPacket(0, RegisteredATMsPacket.class, Direction.CLIENTBOUND);
        this.addonProtocol.registerPacket(1, UpdateATMPacket.class, Direction.CLIENTBOUND);
        this.addonProtocol.registerPacket(2, AddATMPacket.class, Direction.CLIENTBOUND);
        this.addonProtocol.registerPacket(3, RemoveATMPacket.class, Direction.CLIENTBOUND);

        protocolService.registry().registerProtocol(this.addonProtocol);
    }

    @Override
    public GermanRPAddonIntegrationPlayer createIntegrationPlayer(final AbstractLabyModPlayer<?> labyModPlayer) {
        if (this.addonProtocol == null) {
            throw new IllegalStateException("GermanRPAddonIntegration is not initialized");
        }

        return new GermanRPAddonIntegrationPlayer(this.addonProtocol, labyModPlayer.getUniqueId());
    }

    public @NotNull AddonProtocol addonProtocol() {
        if (this.addonProtocol == null) {
            throw new IllegalStateException("GermanRPAddonIntegration is not initialized");
        }

        return this.addonProtocol;
    }

}
