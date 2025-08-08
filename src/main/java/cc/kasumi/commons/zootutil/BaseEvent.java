package cc.kasumi.commons.zootutil;

import cc.kasumi.commons.Commons;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BaseEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public void call() {
		Commons.getInstance().getServer().getPluginManager().callEvent(this);
	}

}
