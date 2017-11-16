package net.email.db;

public class EmailRand {
	public String getRand(){
		String x = "";
		for(int i = 0 ; i <= 7 ; i ++){
			int rnd = 1 + (int)(Math.random()*52);
			if(rnd>26){
				rnd = rnd+70;
			}
			else{
				rnd = rnd+64;
			}
			x = x + (char)(rnd);
		}
		return x;
	}
}
