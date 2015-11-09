package com.tibo.processors.util;

public class UselessIfs {


	void UselessPositiveIf() {

		if(true){


		}

	}		

	void UselessNegativeIf() {

		if(false){


		}

	}	

	void UselessPositiveIfWithString() {
		if(new String("toto").equals("toto")){

		}


	}

	void UselessNegativeIfWithString() {
		if(new String("j'aime la galette").equals("savez vous comment")){

		}


	}	

	void UselessPositiveAndIf(){
		if(5 == new Integer("5") && !(new String[5].length == 1)){

		}
	}

	void UselessNegativeAndIf(){
		if(8 == new Integer("5") && new String[5].length == new Float("1.0")){

		}
	}

	void UselessPositiveOrIf(){
		if("ToBe" == "OR" || !(new String("Not").equals("ToBe"))){

		}
	}

	void UselessNegativeOrIf(){
		boolean toto = false;
		
		if(true == toto || toto == true){

		}
	}

}
