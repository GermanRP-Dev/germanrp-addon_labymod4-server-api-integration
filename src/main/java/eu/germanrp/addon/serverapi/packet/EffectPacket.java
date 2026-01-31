package eu.germanrp.addon.serverapi.packet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.labymod.serverapi.api.packet.Packet;
import net.labymod.serverapi.api.payload.io.PayloadReader;
import net.labymod.serverapi.api.payload.io.PayloadWriter;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public final class EffectPacket implements Packet {

    /**
     * The type of the effect.
     */
    private EffectType type;
    /**
     * The display name of the effect.
     */
    private String displayName;
    /**
     * When the effect ends.
     */
    private Instant end;

    @Override
    public void write(@NotNull PayloadWriter writer) {
        writer.writeVarInt(this.type.ordinal());
        writer.writeString(this.displayName);
        writer.writeLong(this.end.toEpochMilli());
    }

    @Override
    public void read(@NotNull PayloadReader reader) {
        this.type = EffectType.values()[reader.readVarInt()];
        this.displayName = reader.readString();
        this.end = Instant.ofEpochMilli(reader.readLong());
    }

    public enum EffectType {
        FAST_BREAK,
        BONUS_HOUSE_ACTION
    }

}
