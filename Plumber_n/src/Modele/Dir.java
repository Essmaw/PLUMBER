package Modele;

public enum Dir {
	N, E, S, W;
	
	static final int[] di = {-1, 0, 1, 0};
	static final int[] dj = {0, 1, 0, -1};
	
	Dir rotation (int nbr) {
		return Dir.values()[(((this.ordinal() + nbr) % 4) + 4) % 4];
	}
	
	Dir antiRotation(int nbr)  {
		return this.rotation(-nbr);
	}
	
	Dir oppose() {
		return this.rotation(2);
	}
	
	Dir rotation (Dir dir) {
		return Dir.values()[(((this.ordinal() + dir.ordinal()) % 4) + 4) % 4];
	}
	
	Dir antiRotation(Dir dir)  {
		return this.rotation(-dir.ordinal());
	}
}

