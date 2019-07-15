package application;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class control {
	@FXML
	public  Label L00;

	@FXML
	public  Label L01;

	@FXML
	public  Label L02;

	@FXML
	public  Label L03;

	@FXML
	public   Label L10;

	@FXML
	public  Label L11;

	@FXML
	public   Label L12;

	@FXML
	public  Label L13;

	@FXML
	public  Label L20;

	@FXML
	public   Label L21;

	@FXML
	public   Label L22;

	@FXML
	public   Label L23;

	@FXML
	public   Label L30;

	@FXML
	public  Label L31;

	@FXML
	public  Label L32;

	@FXML
	public  Label L33;
	@FXML
	public  Label msg;
	
	public static int[] MoveUp(int b[][]) {
		int que[] = new int[4];
		int rec[] = new int[2];
		int i, j, k, y = 0;
		int col = -1, mod = -1;
		for (j = 0; j < 4; j++) {
			col = -1;
			for (i = 0; i < 4; i++) {
				if (b[i][j] == 0)
					continue;
				if (col == -1) {
					col = i;
					continue;
				}
				if (b[col][j] != b[i][j]) {
					col = i;
					continue;
				}
				if (b[col][j] == b[i][j]) {
					b[col][j] = b[col][j] + b[i][j];
					b[i][j] = 0;
					col = -1;
				}
			}
			for (k = 0; k < 16; k++) {
				y = k % 4;
				if (y == 3)
					continue;
				if (b[y][j] == 0 && b[y + 1][j] != 0) {
					b[y][j] = b[y + 1][j];
					b[y + 1][j] = 0;
					mod = 0;
				}
			}
		}

		rec = check(b);
		que[0] = rec[0];
		que[1] = win(b);
		que[2] = mod;
		que[3] = rec[1];
		return que;
	}
	public static int[] MoveLeft(int b[][]) {
		int que[] = new int[4];
		int i, j, k, y = 0, g = -1;
		int col = -1, mod = -1;
		int rec[] = new int[2];
		for (i = 0; i < 4; i++) {
			col = -1;
			for (j = 0; j < 4; j++) {
				if (b[i][j] == 0)
					continue;
				if (col == -1) {
					col = j;
					continue;
				}
				if (b[i][col] != b[i][j]) {
					col = j;
					continue;
				}

				if (b[i][col] == b[i][j]) {
					b[i][col] = b[i][j] + b[i][col];
					b[i][j] = 0;
					col = -1;
				}
			}
			for (k = 0; k < 16; k++) {
				y = k % 4;
				if (y == 3)
					continue;
				if (b[i][y] == 0 && b[i][y + 1] != 0) {
					b[i][y] = b[i][y + 1];
					b[i][y + 1] = 0;
					mod = 0;
				}
			}
		}
		rec = check(b);
		que[0] = rec[0];
		que[1] = win(b);
		que[2] = mod;
		que[3] = rec[1];
		return que;
	}

	public static int[] MoveRight(int b[][]) {
		int que[] = new int[4];
		int i, j, k, y = 0, g = -1;
		int col = -1, mod = -1;
		int rec[] = new int[2];
		for (i = 0; i < 4; i++) {
			col = -1;
			for (j = 3; j >= 0; j--) {
				if (b[i][j] == 0)
					continue;
				if (col == -1) {
					col = j;
					continue;
				}
				if (b[i][col] != b[i][j]) {
					col = j;
					continue;
				}
				if (b[i][j] == b[i][col]) {
					b[i][col] = b[i][col] + b[i][j];
					b[i][j] = 0;
					col = -1;
				}
			}
			for (k = 15; k >= 0; k--) {
				y = k % 4;
				if (y == 0)
					continue;
				if (b[i][y] == 0 && b[i][y - 1] != 0) {
					b[i][y] = b[i][y - 1];
					b[i][y - 1] = 0;
					mod = 0;
				}
			}
		}
		rec = check(b);
		que[0] = rec[0];
		que[1] = win(b);
		que[2] = mod;
		que[3] = rec[1];
		return que;
	}

	public static int[] MoveDown(int b[][]) {
		int que[] = new int[4];
		int i, j, k, y = 0, g = -1;
		int col = -1, mod = -1;
		int rec[] = new int[2];
		for (j = 0; j < 4; j++) {
			col = -1;
			for (i = 3; i >= 0; i--) {
				if (b[i][j] == 0)
					continue;
				if (col == -1) {
					col = i;
					continue;
				}
				if (b[col][j] != b[i][j]) {
					col = i;
					continue;
				}
				if (b[col][j] == b[i][j]) {
					b[col][j] = b[col][j] + b[i][j];
					b[i][j] = 0;
					col = -1;
				}
			}
			for (k = 4 * 4 - 1; k >= 0; k--) {
				y = k % 4;

				if (y == 0)
					continue;
				if (b[y][j] == 0 && b[y - 1][j] != 0) {

					b[y][j] = b[y - 1][j];
					b[y - 1][j] = 0;
					mod = 0;
				}
			}
		}

		rec = check(b);
		que[0] = rec[0];
		que[1] = win(b);
		que[2] = mod;
		que[3] = rec[1];
		return que;
	}

	
	public static int[] check(int b[][]) {
		int gt = 0, quit = 0;
		int copy[][] = new int[4][4];
		int i, j, x = 0, y = 0, r = 0, count = 0, ri = 0, rj = 0, w = 0, val = 0;
		int e[][] = new int[2][16];
		int rep[] = new int[2];
		for (i = 0; i < 4; i++) {
			for (j = 0; j < 4; j++) {
				if (b[i][j] == 0) {
					e[0][x] = i;
					x++;
					e[1][y] = j;
					y++;
					count++;
				}
			}
		}
		if (count == 0) {
			if (gt == 0) {
				copy = b;
				gt++;
			}
			w = 1;
			if (copy == b)
				quit++;
			copy = b;
		}
		Random rand = new Random();
		if (count != 0) {
			val = rand.nextInt(count);
			int v = rand.nextInt(100);
			ri = e[0][val];
			rj = e[1][val];
			if (v >= 0 && v <= 70)
				b[ri][rj] = 2;
			else
				b[ri][rj] = 4;
		}
		
		//display(b);
		rep[0] = w;
		rep[1] = quit;
		return rep;
	}
	public static int win(int b[][]) {
		int i, j, v = -1;
		for (i = 0; i < 4; i++) {
			for (j = 0; j < 4; j++) {
				if (b[i][j] == 2048)
					v = 1;
			}
		}
		return v;
	}
	

	/*public static void display(int b[][]) {
		
		
	
		int i, j, no = 0, t = 0;
		int c = allign(b);
	for (i = 0; i < 4; i++) {
			for (j = 0; j < 4; j++) {
				no = String.valueOf(b[i][j]).length();
				t = (c - no) + c;
				System.out.print(b[i][j]);
				{
					for (int k = 0; k < t; k++)
						System.out.print(" ");
				}
			}
			System.out.println();
	}
System.out.println("__________________________________________");
System.out.println();

	}*/
	
	



	public static int allign(int b[][]) {
		int i, j, max = 0, digits = 0;
		for (i = 0; i < 4; i++) {
			for (j = 0; j < 4; j++) {
				int val = b[i][j];
				digits = String.valueOf(val).length();
				if (digits > max)
					max = digits;
			}
		}

		return max;
	}
	
	

	
	
	int anw[] = new int[4];
	int b[][] = new int[4][4];
	 
	 public void up(ActionEvent Event) throws Exception {
			anw = MoveUp(b);
		GridPane grid=new GridPane();
			GridPane.setConstraints(L00, 0, 0);
			GridPane.setConstraints(L01, 0, 1);
			GridPane.setConstraints(L02, 0, 2);
			GridPane.setConstraints(L03, 0, 3);
			GridPane.setConstraints(L10, 1, 0);
			GridPane.setConstraints(L11, 1, 1);
			GridPane.setConstraints(L12, 1, 2);
			GridPane.setConstraints(L13, 1, 3);
			GridPane.setConstraints(L20, 2, 0);
			GridPane.setConstraints(L21, 2, 1);
			GridPane.setConstraints(L22, 2, 2);
			GridPane.setConstraints(L23, 2, 3);
			GridPane.setConstraints(L30, 3, 0);
			GridPane.setConstraints(L31, 3, 1);
			GridPane.setConstraints(L32, 3, 2);
			GridPane.setConstraints(L33, 3, 3);
			L00.setText(""+(b[0][0]));
			L01.setText(""+b[0][1]);
			L02.setText(""+b[0][2]);
			L03.setText(""+b[0][3]);
			L10.setText(""+b[1][0]);
			L11.setText(""+b[1][1]);
			L12.setText(""+b[1][2]);
			L13.setText(""+b[1][3]);
			L20.setText(""+b[2][0]);
			L21.setText(""+b[2][1]);
			L22.setText(""+b[2][2]);
			L23.setText(""+b[2][3]);
			L30.setText(""+b[3][0]);
			L31.setText(""+b[3][1]);
			L32.setText(""+b[3][2]);
			L33.setText(""+b[3][3]); 
			
			if (anw[1] == 1)
				
			if (anw[3] == 4) {
				
				Stage primaryStage=new Stage();
				Parent root=FXMLLoader.load(getClass().getResource("/application/errormsg1.fxml"));
				Scene scene = new Scene(root,400,400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				
				
			//	msg.setText("Oops! you lost your game. Try once more.");
				
			} else if (anw[0] == 1 && anw[2] == -1) {
				// copy = b;
				msg.setText("No modificiations is possible in this move : ");
				msg.setText("Choose another move: ");
			}

	 }
	 public void down(ActionEvent Event) throws Exception {
		 anw = MoveDown(b);
		 GridPane grid=new GridPane();
			GridPane.setConstraints(L00, 0, 0);
			GridPane.setConstraints(L01, 0, 1);
			GridPane.setConstraints(L02, 0, 2);
			GridPane.setConstraints(L03, 0, 3);
			GridPane.setConstraints(L10, 1, 0);
			GridPane.setConstraints(L11, 1, 1);
			GridPane.setConstraints(L12, 1, 2);
			GridPane.setConstraints(L13, 1, 3);
			GridPane.setConstraints(L20, 2, 0);
			GridPane.setConstraints(L21, 2, 1);
			GridPane.setConstraints(L22, 2, 2);
			GridPane.setConstraints(L23, 2, 3);
			GridPane.setConstraints(L30, 3, 0);
			GridPane.setConstraints(L31, 3, 1);
			GridPane.setConstraints(L32, 3, 2);
			GridPane.setConstraints(L33, 3, 3);
			L00.setText(""+(b[0][0]));
			L01.setText(""+b[0][1]);
			L02.setText(""+b[0][2]);
			L03.setText(""+b[0][3]);
			L10.setText(""+b[1][0]);
			L11.setText(""+b[1][1]);
			L12.setText(""+b[1][2]);
			L13.setText(""+b[1][3]);
			L20.setText(""+b[2][0]);
			L21.setText(""+b[2][1]);
			L22.setText(""+b[2][2]);
			L23.setText(""+b[2][3]);
			L30.setText(""+b[3][0]);
			L31.setText(""+b[3][1]);
			L32.setText(""+b[3][2]);
			L33.setText(""+b[3][3]); 
			
			if (anw[1] == 1)
				
			if (anw[3] == 4) {
				
				
				Stage primaryStage=new Stage();
				Parent root=FXMLLoader.load(getClass().getResource("/application/errormsg1.fxml"));
				Scene scene = new Scene(root,400,400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				
				
				//msg.setText("Oops! you lost your game. Try once more.");
				
			} else if (anw[0] == 1 && anw[2] == -1) {
				// copy = b;
				msg.setText("No modificiations is possible in this move : ");
				msg.setText("Choose another move: ");
			}

	 }
	 public void Right(ActionEvent Event) throws Exception{
			anw = MoveRight(b);
		 GridPane grid=new GridPane();
			GridPane.setConstraints(L00, 0, 0);
			GridPane.setConstraints(L01, 0, 1);
			GridPane.setConstraints(L02, 0, 2);
			GridPane.setConstraints(L03, 0, 3);
			GridPane.setConstraints(L10, 1, 0);
			GridPane.setConstraints(L11, 1, 1);
			GridPane.setConstraints(L12, 1, 2);
			GridPane.setConstraints(L13, 1, 3);
			GridPane.setConstraints(L20, 2, 0);
			GridPane.setConstraints(L21, 2, 1);
			GridPane.setConstraints(L22, 2, 2);
			GridPane.setConstraints(L23, 2, 3);
			GridPane.setConstraints(L30, 3, 0);
			GridPane.setConstraints(L31, 3, 1);
			GridPane.setConstraints(L32, 3, 2);
			GridPane.setConstraints(L33, 3, 3);
			L00.setText(""+(b[0][0]));
			L01.setText(""+b[0][1]);
			L02.setText(""+b[0][2]);
			L03.setText(""+b[0][3]);
			L10.setText(""+b[1][0]);
			L11.setText(""+b[1][1]);
			L12.setText(""+b[1][2]);
			L13.setText(""+b[1][3]);
			L20.setText(""+b[2][0]);
			L21.setText(""+b[2][1]);
			L22.setText(""+b[2][2]);
			L23.setText(""+b[2][3]);
			L30.setText(""+b[3][0]);
			L31.setText(""+b[3][1]);
			L32.setText(""+b[3][2]);
			L33.setText(""+b[3][3]); 
		
			if (anw[1] == 1)
				
			if (anw[3] == 4) {
				Stage primaryStage=new Stage();
				Parent root=FXMLLoader.load(getClass().getResource("/application/errormsg1.fxml"));
				Scene scene = new Scene(root,400,400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				
				//msg.setText("Oops! you lost your game. Try once more.");
				
			} else if (anw[0] == 1 && anw[2] == -1) {
				// copy = b;
				msg.setText("No modificiations is possible in this move : ");
				msg.setText("Choose another move: ");
			}

	 }
	 public void Left(ActionEvent Event) throws Exception {
		 anw = MoveLeft(b);
		 GridPane grid=new GridPane();
			GridPane.setConstraints(L00, 0, 0);
			GridPane.setConstraints(L01, 0, 1);
			GridPane.setConstraints(L02, 0, 2);
			GridPane.setConstraints(L03, 0, 3);
			GridPane.setConstraints(L10, 1, 0);
			GridPane.setConstraints(L11, 1, 1);
			GridPane.setConstraints(L12, 1, 2);
			GridPane.setConstraints(L13, 1, 3);
			GridPane.setConstraints(L20, 2, 0);
			GridPane.setConstraints(L21, 2, 1);
			GridPane.setConstraints(L22, 2, 2);
			GridPane.setConstraints(L23, 2, 3);
			GridPane.setConstraints(L30, 3, 0);
			GridPane.setConstraints(L31, 3, 1);
			GridPane.setConstraints(L32, 3, 2);
			GridPane.setConstraints(L33, 3, 3);
			L00.setText(""+(b[0][0]));
			L01.setText(""+b[0][1]);
			L02.setText(""+b[0][2]);
			L03.setText(""+b[0][3]);
			L10.setText(""+b[1][0]);
			L11.setText(""+b[1][1]);
			L12.setText(""+b[1][2]);
			L13.setText(""+b[1][3]);
			L20.setText(""+b[2][0]);
			L21.setText(""+b[2][1]);
			L22.setText(""+b[2][2]);
			L23.setText(""+b[2][3]);
			L30.setText(""+b[3][0]);
			L31.setText(""+b[3][1]);
			L32.setText(""+b[3][2]);
			L33.setText(""+b[3][3]); 
			
			if (anw[1] == 1)
				
			if (anw[3] == 4) {
				
				
				Stage primaryStage=new Stage();
				Parent root=FXMLLoader.load(getClass().getResource("/application/errormsg1.fxml"));
				Scene scene = new Scene(root,400,400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				//msg.setText("Oops! you lost your game. Try once more.");
				
			} else if (anw[0] == 1 && anw[2] == -1) {
				// copy = b;
				msg.setText("No modificiations is possible in this move : ");
				msg.setText("Choose another move: ");
			}

	 }
	
	public void start(ActionEvent event) {
		
		//int b[][] = new int[4][4];
		
		GridPane grid=new GridPane();
		GridPane.setConstraints(L00, 0, 0);
		GridPane.setConstraints(L01, 0, 1);
		GridPane.setConstraints(L02, 0, 2);
		GridPane.setConstraints(L03, 0, 3);
		GridPane.setConstraints(L10, 1, 0);
		GridPane.setConstraints(L11, 1, 1);
		GridPane.setConstraints(L12, 1, 2);
		GridPane.setConstraints(L13, 1, 3);
		GridPane.setConstraints(L20, 2, 0);
		GridPane.setConstraints(L21, 2, 1);
		GridPane.setConstraints(L22, 2, 2);
		GridPane.setConstraints(L23, 2, 3);
		GridPane.setConstraints(L30, 3, 0);
		GridPane.setConstraints(L31, 3, 1);
		GridPane.setConstraints(L32, 3, 2);
		GridPane.setConstraints(L33, 3, 3);		
		L00.setText(""+b[0][0]);
	 	L01.setText(""+b[0][1]);
		L02.setText(""+b[0][2]);
		L03.setText(""+b[0][3]);
		L10.setText(""+b[1][0]);
		L11.setText(""+b[1][1]);
		L12.setText(""+b[1][2]);
		L13.setText(""+b[1][3]);
		L20.setText(""+b[2][0]);
		L21.setText(""+b[2][1]);
		L22.setText(""+b[2][2]);
		L23.setText(""+b[2][3]);
		L30.setText(""+b[3][0]);
		L31.setText(""+b[3][1]);
		L32.setText(""+b[3][2]);
		L33.setText(""+b[3][3]); 
	 
		//display(b);
		
		  
	}}
	
