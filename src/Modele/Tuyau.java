package Modele;

public enum Tuyau {
	E, L, T, F, C;
	
	static final Dir[][] extremites = {{Dir.N}, {Dir.N, Dir.S}, {Dir.N, Dir.E}, {Dir.N, Dir.E, Dir.S}, {Dir.N, Dir.E, Dir.S, Dir.W}};

}
