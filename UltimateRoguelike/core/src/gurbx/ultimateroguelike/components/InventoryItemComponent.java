package gurbx.ultimateroguelike.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

import gurbx.ultimateroguelike.inventory.Item;

public class InventoryItemComponent implements Component {
	public static final ComponentMapper<InventoryItemComponent> MAPPER = ComponentMapper.getFor(InventoryItemComponent.class);
	
	public boolean isInInventory = false;
	public Item item;
}
