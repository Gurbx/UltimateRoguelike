package gurbx.ultimateroguelike.elements.tier1;

import gurbx.ultimateroguelike.elements.Element;

public class IceElement implements Element {

	@Override
	public Type getType() {
		return Type.ICE;
	}

	@Override
	public Type mergedFire() {
		return Type.WATER;
	}

	@Override
	public Type mergedIce() {
		return Type.ICE;
	}

	@Override
	public Type mergedRock() {
		return Type.MUD;
	}

	@Override
	public Type mergedNature() {
		return Type.ROCK;
	}

	@Override
	public Type mergedDark() {
		return Type.PLAGUE;
	}

	@Override
	public Type mergedLight() {
		return Type.ARCANE;
	}

//	@Override
//	public Type mergedWater() {
//		return Type.ICE;
//	}
//
//	@Override
//	public Type mergedSteam() {
//		return Type.WATER;
//	}
//
//	@Override
//	public Type mergedLava() {
//		return Type.STEAM;
//	}
//
//	@Override
//	public Type mergedAsh() {
//		return Type.ROCK;
//	}
//
//	@Override
//	public Type mergedChaos() {
//		return Type.PLAGUE;
//	}
//
//	@Override
//	public Type mergedArcane() {
//		return Type.ARCANE;
//	}
//
//	@Override
//	public Type mergedMud() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Type mergedPlague() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Type mergedHoly() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
