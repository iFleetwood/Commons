package cc.kasumi.commons.inventory.scroller;

import cc.kasumi.commons.inventory.Button;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Page {

    @Getter
    private Map<Integer, Button> buttons = new HashMap<>();
}
