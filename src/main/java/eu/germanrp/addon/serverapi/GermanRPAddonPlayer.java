package eu.germanrp.addon.serverapi;

import eu.germanrp.addon.serverapi.model.ATM;
import eu.germanrp.addon.serverapi.packet.AddATMPacket;
import eu.germanrp.addon.serverapi.packet.RegisteredATMsPacket;
import eu.germanrp.addon.serverapi.packet.RemoveATMPacket;
import eu.germanrp.addon.serverapi.packet.UpdateATMPacket;
import net.labymod.serverapi.core.AddonProtocol;
import net.labymod.serverapi.core.integration.LabyModIntegrationPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.UUID;

public class GermanRPAddonPlayer implements LabyModIntegrationPlayer {

    private final AddonProtocol addonProtocol;
    private final UUID uniqueId;

    protected GermanRPAddonPlayer(AddonProtocol addonProtocol, UUID uniqueId) {
        this.addonProtocol = addonProtocol;
        this.uniqueId = uniqueId;
    }

    /**
     * Sends a packet containing all registered ATMs to the connected client.
     * <p>
     * This method uses the {@link RegisteredATMsPacket} to transmit the initial set of ATMs
     * to the client associated with the current player. It ensures the client starts with
     * up-to-date information about all the registered ATMs available in the system.
     * <p>
     * This method should only be called once when the player joins the server.
     * Subsequent updates (add, remove, or update) should be handled via the respective
     * specialized methods: {@link #sendATMAdd(ATM)}, {@link #sendATMRemove(String)},
     * and {@link #sendATMUpdate(String, ATM)}.
     *
     * @param atms A set of {@link ATM} instances representing the registered ATMs to be sent.
     *             Must not be null, and all elements within the set must also be non-null.
     */
    @SuppressWarnings("unused")
    public void sendRegisteredATMs(
            @NotNull Set<@NotNull ATM> atms
    ) {
        this.addonProtocol.sendPacket(uniqueId, new RegisteredATMsPacket(atms));
    }

    /**
     * Sends an update for a specific ATM to the connected client.
     * <p>
     * This method dispatches an {@link UpdateATMPacket} containing the specified
     * ATM's information. It ensures that the client is informed about any changes
     * to the state or details of the ATM identified by the given ID.
     *
     * @param id  The unique identifier of the ATM to update. Must not be null.
     * @param atm The updated ATM instance containing the new details.
     *            Must not be null.
     */
    @SuppressWarnings("unused")
    public void sendATMUpdate(
            final @NotNull String id,
            final @NotNull ATM atm
    ) {
        this.addonProtocol.sendPacket(uniqueId, new UpdateATMPacket(id, atm));
    }

    /**
     * Sends a packet to add a new ATM to the connected client.
     *
     * @param atm The ATM instance to be added. Must not be null.
     */
    @SuppressWarnings("unused")
    public void sendATMAdd(
            final @NotNull ATM atm
    ) {
        this.addonProtocol.sendPacket(uniqueId, new AddATMPacket(atm));
    }

    /**
     * Sends a packet to remove an ATM from the connected client.
     *
     * @param id The unique identifier of the ATM to remove. Must not be null.
     */
    @SuppressWarnings("unused")
    public void sendATMRemove(
            final @NotNull String id
    ) {
        this.addonProtocol.sendPacket(uniqueId, new RemoveATMPacket(id));
    }

}
