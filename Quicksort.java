import java.util.*;

public class Quicksort{

	public static void swap(int[] array, int a, int b){
		int temp=array[a];
		array[a]=array[b];
		array[b]=temp;
	}

	public static void quicksort(int[] array, int esq, int dir){
		int i=esq; int j=dir; int pivo=array[(esq+dir)/2];
		while(i<=j){
			while(array[i]<pivo)i++;
			while(array[j]>pivo)j--;
			if(i <= j){
				swap(array, i, j);
				i++;
				j--;
			}
		}
		if(esq<j)quicksort(array,esq,j);
		if(i<dir)quicksort(array,i,dir);
	}

	public static void main(String[] args){
		Scanner ler = new Scanner(System.in);

		System.out.print("[!]Digite o tamanho desejado do array:");
		int n = ler.nextInt();
		int[] array = new int[n];

		for(int i=0;i < n; i++){
			System.out.print("[!]Digite o "+ (i+1) +"° elemento desejado do array:");
			array[i]=ler.nextInt();
		}
		System.out.print("Desordenado: [ ");
		for(int i=0;i < n; i++){
			System.out.print(array[i]);
			System.out.print(" ");
		}
		System.out.println("]");

		quicksort(array,0,n-1);

		System.out.print("Ordenado: [ ");
		for(int i=0;i < n; i++){
			System.out.print(array[i]);
			System.out.print(" ");
		}
		System.out.println("]");
		ler.close();
	}

}
