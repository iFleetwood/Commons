package cc.kasumi.commons.menu.type;

import cc.kasumi.commons.menu.Menu;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ScrollerPage extends Menu {

    private final int actualSize;

    public ScrollerPage(UUID uuid, String title, int size) {
        super(uuid, title, size, true);
        this.actualSize = size - 9;
    }

    public boolean isFull() {
        return actualSize <= getInventorySlots().size();
    }
}
