package gurbx.ultimateroguelike.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

import gurbx.ultimateroguelike.utils.particles.ParticleEffects;

public class LifeComponent implements Component {
	public static final ComponentMapper<LifeComponent> MAPPER = ComponentMapper.getFor(LifeComponent.class);
	
//	public static enum ArmorType { FLESH, STEEL, WOOD, ETHERAL; } //Armor type for sound when taking damage
	public int health = 1;
//	public ArmorType armorType = ArmorType.WOOD;
	public ParticleEffects hitEffect = null;
	public ParticleEffects deathEffect = null;
}
