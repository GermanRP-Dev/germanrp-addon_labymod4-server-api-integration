package eu.germanrp.addon.serverapi.packet;

import eu.germanrp.addon.serverapi.model.ATM;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.labymod.serverapi.api.packet.Packet;
import net.labymod.serverapi.api.payload.io.PayloadReader;
import net.labymod.serverapi.api.payload.io.PayloadWriter;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
@AllArgsConstructor
public abstract class ATMPacket implements Packet {

    @Accessors(fluent = true)
    public ATM atm;

    @Override
    public void read(@NotNull PayloadReader reader) {
        this.atm = new ATM(
                reader.readString(),
                reader.readString(),
                reader.readDouble(),
                reader.readDouble(),
                reader.readDouble(),
                reader.readLong()
        );
    }

    @Override
    public void write(@NotNull PayloadWriter writer) {
        writer.writeString(this.atm.displayName());
        writer.writeString(this.atm.id());
        writer.writeDouble(this.atm.x());
        writer.writeDouble(this.atm.y());
        writer.writeDouble(this.atm.z());
        writer.writeLong(this.atm.cooldownTimestamp());
    }

}
