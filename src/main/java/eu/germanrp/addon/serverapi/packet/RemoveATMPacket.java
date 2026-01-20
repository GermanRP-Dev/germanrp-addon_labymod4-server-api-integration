package eu.germanrp.addon.serverapi.packet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.labymod.serverapi.api.packet.Packet;
import net.labymod.serverapi.api.payload.io.PayloadReader;
import net.labymod.serverapi.api.payload.io.PayloadWriter;
import org.jetbrains.annotations.NotNull;

/**
 * This packet is sent to the player when a single ATM is removed.
 */
@NoArgsConstructor
@AllArgsConstructor
public class RemoveATMPacket implements Packet {

    @Getter
    @Accessors(fluent = true)
    private String id;

    @Override
    public void read(@NotNull PayloadReader reader) {
        this.id = reader.readString();
    }

    @Override
    public void write(@NotNull PayloadWriter writer) {
        writer.writeString(this.id);
    }

}
