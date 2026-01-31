package eu.germanrp.addon.serverapi.packet.atm;

import eu.germanrp.addon.serverapi.model.ATM;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.labymod.serverapi.api.packet.Packet;
import net.labymod.serverapi.api.payload.io.PayloadReader;
import net.labymod.serverapi.api.payload.io.PayloadWriter;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.StringJoiner;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class ATMListPacket implements Packet {

    @Accessors(fluent = true)
    protected Set<ATM> atms;

    @Override
    public void read(@NotNull PayloadReader reader) {
        this.atms = reader.readSet(() -> new ATM(
                reader.readString(),
                reader.readString(),
                reader.readDouble(),
                reader.readDouble(),
                reader.readDouble(),
                reader.readLong()
        ));
    }

    @Override
    public void write(@NotNull PayloadWriter writer) {
        writer.writeCollection(this.atms, atm -> {
            writer.writeString(atm.displayName());
            writer.writeString(atm.id());
            writer.writeDouble(atm.x());
            writer.writeDouble(atm.y());
            writer.writeDouble(atm.z());
            writer.writeLong(atm.cooldownTimestamp());
        });
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ATMListPacket.class.getSimpleName() + "[", "]")
                .add("atms=" + atms)
                .toString();
    }

}
