package menu;

import lombok.Getter;
import menu.type.ScrollerMenu;

import java.util.*;

@Getter
public class MenuHandler {

    @Getter
    private static final Map<UUID, Menu> menus = new HashMap<>();
    @Getter
    private static final Map<UUID, ScrollerMenu> scrollerMenus = new HashMap<>();

    public static void addMenu(Menu menu) {
        menus.put(menu.getUuid(), menu);
    }

    public static void removeMenuByUUID(UUID uuid) {
        menus.remove(uuid);
    }

    public static void removeMenuByMenu(Menu menu) {
        menus.entrySet().removeIf(entry -> entry.getValue().equals(menu));
    }
}
