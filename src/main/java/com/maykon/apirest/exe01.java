package com.maykon.apirest;

import java.util.Arrays;
import java.util.Scanner;

public class exe01 {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int vetor[];
		System.out.println("Digite 3(três) Numeros");
		vetor = new int[3];
		int i ;
		int n;
		int Maior = 0;
		int Meio = 0;
		int Menor = 0;
		for (i = 0 ; i < vetor.length;i++) {
			System.out.printf("Digite o %d numero : ",(i+1));
			n = sc.nextInt();
			if(n > 100000000) {
				n= -1;
			}
			if (i==0) {
				Maior = n;
				Meio = n;
				Menor = n;
			}
			vetor[i] = n;
			if (n < Menor) {
				Meio = Menor;
				Menor = n;}
			if (n > Maior) {
				Meio = Maior;
				Maior = n;}
		}
		
		System.out.println("Numeros irmaos");
		for (i=0; i < vetor.length ; i++) {
			if (i==0) {
				System.out.println(vetor[i]+ "" +  vetor[1] + "" + vetor[2]);
				System.out.println(vetor[i] + "" + vetor[2] + "" + vetor[1]);
			}
			if (i==1) {
				System.out.println(vetor[i]+ "" +  vetor[0] + "" + vetor[2]);
				System.out.println(vetor[i]+ "" +  vetor[2] + "" + vetor[0]);
			}	
			if (i==2) {
				System.out.println(vetor[i]+ "" +  vetor[0] + "" + vetor[1]);
				System.out.println(vetor[i]+ "" +  vetor[1] + "" + vetor[0]);
			}					
			
		}
		System.out.println("Maior Irmão");
		System.out.println((Maior * 100) + (Meio *10) + Menor);
		System.out.println("Numeros ordenados");
		Arrays.sort(vetor); 
		System.out.println(Arrays.toString(vetor));
	}

}
