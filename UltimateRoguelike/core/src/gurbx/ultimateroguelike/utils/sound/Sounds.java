package gurbx.ultimateroguelike.utils.sound;

public enum Sounds {
	OUT_OF_AMMO("sound/out_of_ammo.wav", 1f),
	SLIME_HIT ("sound/slime_hit.wav", 1f);
	
	public String path;
	public float volume;

	private Sounds(String path, float volume) {
		this.path = path;
		this.volume = volume;
	}
}
