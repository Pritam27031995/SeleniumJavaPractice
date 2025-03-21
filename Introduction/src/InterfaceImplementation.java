
public class InterfaceImplementation implements TestInterface{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestInterface intf = new InterfaceImplementation();
		InterfaceImplementation clss = new InterfaceImplementation();
		intf.Red();
		clss.Black();
	}

	@Override
	public void Red() {
		// TODO Auto-generated method stub
		System.out.println("Red Light");
	}

	@Override
	public void Yellow() {
		// TODO Auto-generated method stub
		System.out.println("Yellow Light");
	}

	@Override
	public void Green() {
		// TODO Auto-generated method stub
		System.out.println("Green Light");
	}
	
	public void Black() {
		System.out.println("Black Light");
	}

}
