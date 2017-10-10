public class QGen {

	private String question = "";
	private String answer;
	private int ans;

	public void newQ(int qtype) {
		/* Questions:
		 * 0 : a = bx + c 
		 * 1 : unit circle
		 * 2 : inverse unit circle
		 */

		switch(qtype) {
		case 0:
			//generate base numbers
			int a = (int)(Math.random() * 100) + 1;
			//System.out.println("a: " + a);
			int c = a;
			do {
				c = (int)(Math.random() * 100) + 1;
			} while(a == c);
			//System.out.println("c: " + c);

			//generate b
			//b has to be factor of a - c
			int b;
			do {
				b = (int)(Math.random() * (a-c)) + 1;
				if(b == 0)
					b = 1;
			} while((a - c) % b != 0);
			//System.out.println("b: " + b);

			question = a + " = " + b + "x + " + c;

			//generate answers
			ans = (a - c) / b;
			answer = ans + "";
			break;
		case 1:
			
		case 2:
		}
	}

	public String question() {
		return question;
	}

	public String answer() {
		return answer;
	}

	public void print() {
		System.out.println(question());
		System.out.println(answer());
	}

	public static void main(String[] args) {
		QGen qg = new QGen();
		qg.newQ(0);
		qg.print();
	}

}
