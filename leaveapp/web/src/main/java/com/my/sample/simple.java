package com.my.sample;

public class simple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "pradeep";
		Pojo p = new Pojo();
		p.setName("pradeep");
		if(p.getName().trim() == s){
			System.out.println("in side loop ");
		}
		if(p.getName().equals(s)){
			System.out.println("in side loop1 ");
		}
	}

}
