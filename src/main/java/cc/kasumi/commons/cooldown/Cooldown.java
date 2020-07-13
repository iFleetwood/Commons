package cc.kasumi.commons.cooldown;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cooldown {

    private long start, length;

    public Cooldown(long length) {
        this.length = length;
    }

    public Cooldown(long length, long start) {
        this.length = length;
        this.start = start;
    }

    public boolean isActive() {
        return start + length >= System.currentTimeMillis();
    }

    public long getTimeRemaining() {
        return start + length - System.currentTimeMillis();
    }
}
