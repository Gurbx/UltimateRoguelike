package gurbx.ultimateroguelike.utils.particles;

public enum ParticleEffects {
	CRATE_HIT(0, "particles/crateHit.p", 1, 5, 20),
	HIT(1, "particles/hit.p", 0.75f, 5, 20);
	
	public final int ID;
	public final String path;
	public final float scale;
	public final int startCapacity;
	public final int maxCapacity;
	
	private ParticleEffects(int id, String path, float scale, int startCapacity, int maxCapacity) {
		this.ID = id;
		this.path = path;
		this.scale = scale;
		this.startCapacity = startCapacity;
		this.maxCapacity = maxCapacity;
	}
}
