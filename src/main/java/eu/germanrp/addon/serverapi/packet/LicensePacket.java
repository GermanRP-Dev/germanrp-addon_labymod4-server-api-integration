package eu.germanrp.addon.serverapi.packet;

import eu.germanrp.addon.serverapi.model.License;
import eu.germanrp.addon.serverapi.model.LicenseStatus;
import eu.germanrp.addon.serverapi.model.LicenseType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import net.labymod.serverapi.api.packet.Packet;
import net.labymod.serverapi.api.payload.io.PayloadReader;
import net.labymod.serverapi.api.payload.io.PayloadWriter;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * This packet contains information about the player's licenses.
 */
@Getter
@RequiredArgsConstructor
@SuppressWarnings("unused")
public final class LicensePacket implements Packet {

    /**
     * The licenses and their status, if a license is missing, it's considered unowned.
     *
     * @see LicenseType
     */
    @Accessors(fluent = true)
    private Set<License> licenses;

    @Override
    public void read(@NotNull PayloadReader reader) {
        reader.readSet(() -> new License(
                LicenseType.values()[reader.readVarInt()],
                LicenseStatus.values()[reader.readVarInt()]
        ));
    }

    @Override
    public void write(@NotNull PayloadWriter writer) {
        writer.writeCollection(this.licenses, license -> {
            writer.writeVarInt(license.type().ordinal());
            writer.writeVarInt(license.status().ordinal());
        });
    }

}
