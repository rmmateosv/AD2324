
public class Info {
	private  int kcal;
	private  int grasas;
	private  int hidratos;
	public Info(int kcal, int grasas, int hidratos) {
		super();
		this.kcal = kcal;
		this.grasas = grasas;
		this.hidratos = hidratos;
	}
	public Info() {
		super();
	}
	public int getKcal() {
		return kcal;
	}
	public void setKcal(int kcal) {
		this.kcal = kcal;
	}
	public int getGrasas() {
		return grasas;
	}
	public void setGrasas(int grasas) {
		this.grasas = grasas;
	}
	public int getHidratos() {
		return hidratos;
	}
	public void setHidratos(int hidratos) {
		this.hidratos = hidratos;
	}
	@Override
	public String toString() {
		return "Info [kcal=" + kcal + ", grasas=" + grasas + ", hidratos=" + hidratos + "]";
	}
	
	
}
