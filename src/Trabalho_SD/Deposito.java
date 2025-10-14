package Trabalho_SD;

class Produtor extends Thread {
	private int caixasProduzidas = 0;
	private int producaoMax = 100;
	private Deposito dep;
	private int sleepTime;
	
	Produtor(Deposito d, int time) {
		dep = d;
		sleepTime = time;
	}
	
	public synchronized void run() {
		try {
			while (caixasProduzidas < producaoMax) {
				synchronized (this) {
					dep.colocar();
					caixasProduzidas++;
					System.out.println("Produto novo! Produtos atualizados: " + dep.getNumItens());
					sleep(this.sleepTime);
					notifyAll();
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

class Consumidor extends Thread {
	private int consumo = 20;
	private int consumidos = 0;
	private Deposito dep;
	private int sleepTime;
	
	Consumidor(Deposito d, int time) {
		this.dep = d;
		this.sleepTime = time;
	}
	
	public void run() {
		try {
			while(this.consumidos < this.consumo) {
				synchronized (this) {
					if (this.dep.retirar()) {
						this.consumidos++;
						System.out.println("Produto consumido! Produtos restantes: " + this.dep.getNumItens());
						sleep(this.sleepTime);
					} else {
						System.out.println("Não foi possível remover o item");
						wait(200);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

public class Deposito {
	 private int items = 0;
	 private final int capacidade = 100;

	 public synchronized int getNumItens(){
		 return items;
	 }
	 
	 public synchronized boolean retirar() {
		 if (getNumItens() == 0) {
			return false;
		 }
		 
		 items=getNumItens() - 1;
		 return true;
	 }
	 
	 public synchronized boolean colocar() {
		 items=getNumItens() + 1;
		 return true;
	 }
	 
	 public static void main(String[] args) {
		 Deposito dep = new Deposito();
		 Produtor p = new Produtor(dep, 50);
		 Consumidor c1 = new Consumidor(dep, 150);
		 Consumidor c2 = new Consumidor(dep, 100);
		 Consumidor c3 = new Consumidor(dep, 150);
		 Consumidor c4 = new Consumidor(dep, 100);
		 Consumidor c5 = new Consumidor(dep, 150);
		 //Startar o produtor
		 p.start();
		 
		 //Startar os consumidores.
		 c1.start();
		 c2.start();
		 c3.start();
		 c4.start();
		 c5.start();
		 
		 System.out.println("Execucao do main da classe Deposito terminada");
	 }
	}