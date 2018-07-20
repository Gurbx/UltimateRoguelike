package gurbx.ultimateroguelike.elements.tier1;

import gurbx.ultimateroguelike.elements.Element;

public class FireElement implements Element {
	
	@Override
	public Type getType() {
		return Type.FIRE;
	}

	@Override
	public Type mergedFire() {
		return Type.FIRE;
	}

	@Override
	public Type mergedIce() {
		return Type.WATER;
	}

	@Override
	public Type mergedRock() {
		return Type.LAVA;
	}

	@Override
	public Type mergedNature() {
		return Type.ASH;
	}

	@Override
	public Type mergedDark() {
		return Type.CHAOS;
	}

	@Override
	public Type mergedLight() {
		return Type.ARCANE;
	}

//	@Override
//	public Type mergedWater() {
//		return Type.STEAM;
//	}
//
//	@Override
//	public Type mergedSteam() {
//		return Type.FIRE;
//	}
//
//	@Override
//	public Type mergedLava() {
//		return Type.LAVA;
//	}
//
//	@Override
//	public Type mergedAsh() {
//		return Type.ASH;
//	}
//
//	@Override
//	public Type mergedChaos() {
//		return Type.CHAOS;
//	}
//
//	@Override
//	public Type mergedArcane() {
//		return Type.CHAOS;
//	}
//
//	@Override
//	public Type mergedMud() {
//		return Type.ROCK;
//	}
//
//	@Override
//	public Type mergedPlague() {
//		return Type.CHAOS;
//	}
//
//	@Override
//	public Type mergedHoly() {
//		return Type.HOLY;
//	}
}
