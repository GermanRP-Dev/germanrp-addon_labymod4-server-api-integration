package eu.germanrp.addon.serverapi.packet;

import lombok.*;
import net.labymod.serverapi.api.packet.Packet;
import net.labymod.serverapi.api.payload.io.PayloadReader;
import net.labymod.serverapi.api.payload.io.PayloadWriter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    @Getter
    @RequiredArgsConstructor
    public enum EffectType {
        FAST_BREAK("Flinke Hände"),
        BONUS_HOUSE_ACTION("Meisterklingler");

        private final String displayName;

        public static @Nullable EffectType fromDisplayName(final String displayName) {
            for (val value : values()) {
                if (value.displayName.equals(displayName)) {
                    return value;
                }
            }
            return null;
        }

    }

}
