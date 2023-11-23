package Modele;

public enum Tuyau {
	E, L, T, F, C;
	
	//N, E, S, W
	static final boolean[][] extremites = {{true, false, false, false}, 
			{true, false, true, false}, 
			{true, true, false, false}, 
			{true, true, true, false}, 
			{true, true, true, true}};
	
	boolean estOuvert(Dir extremite, Dir rotation) {
		int ancienne_dir = extremite.antiRotation(rotation).ordinal();
		return extremites[this.ordinal()][ancienne_dir];
	}
	
	static Tuyau stringToTuyau(char tuyau){
		Tuyau t;
		switch(tuyau) {
			case 'E' : t = Tuyau.E; break;
			case 'L' : t = Tuyau.L; break;
			case 'T' : t = Tuyau.T;	break;
			case 'F' : t = Tuyau.F; break;
			case 'C' : t = Tuyau.C; break;
			default : t = null;
		}
		return t;
	}
	
	static String tuyauToString(Tuyau t) {
		int t_ord = t.ordinal();
		String tuyau_str;
		switch(t_ord) {
			case 0 : tuyau_str = "E"; break;
			case 1 : tuyau_str = "L"; break;
			case 2 : tuyau_str = "T"; break;
			case 3 : tuyau_str = "F"; break;
			case 4 : tuyau_str = "C"; break;
			default : tuyau_str = ".";
		}
		return tuyau_str;
	}
	
	//TEST
	/*public static void main(String[] arg) {
		System.out.println(T.estOuvert(Dir.S, Dir.E));
	}*/

}
