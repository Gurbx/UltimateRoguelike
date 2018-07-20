package gurbx.ultimateroguelike.elements;

import gurbx.ultimateroguelike.elements.tier1.FireElement;
import gurbx.ultimateroguelike.elements.tier1.IceElement;

public interface Element {
	public enum Type {
		
		//Done when all the nulls are removed :)
		
		//Tier 1
		FIRE (new FireElement()), ICE(new IceElement()), 
		ROCK(null), NATURE(null), DARK(null), LIGHT(null), 			//TODO
		
		//Tier 2
		WATER(null), STEAM(null), LAVA(null), ASH(null), CHAOS(null), ARCANE(null), MUD(null), PLAGUE(null), HOLY(null);	 //TODO
		
		public Element element;
		private Type(Element element) {
			this.element = element;
		}
	}
	public Type getType();
	
	//Tier 1 (Elements that drop and have to be merged)
	public Type mergedFire();
	public Type mergedIce();
	public Type mergedRock();
	public Type mergedNature();
	public Type mergedDark();
	public Type mergedLight();

	//Tier 2 //Maybe not needed? 
//	public Type mergedWater();
//	public Type mergedSteam();
//	public Type mergedLava();
//	public Type mergedAsh();
//	public Type mergedChaos();
//	public Type mergedArcane();
//	public Type mergedMud();
//	public Type mergedPlague();
//	public Type mergedHoly();
}
