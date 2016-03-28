import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javax.swing.JOptionPane;

public class JavaFxDemo extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	// initialize enum for account type
	private enum AccountType {Administrator, Faculty, Student, Staff, Guest}
	AccountType[] choicesAccount = { AccountType.Administrator, AccountType.Faculty, AccountType.Student, AccountType.Staff, AccountType.Guest };
	
	String usernameCreate;
	String passwordCreate;
	AccountType accounttypeCreate;
	static int count = 0;
	
	Object[] contactOption = {"Contact", "Cancel"};
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Login");
		GridPane gPane = new GridPane();
		gPane.setAlignment(Pos.CENTER);
		gPane.setHgap(10);
		gPane.setVgap(10);
		gPane.setPadding(new Insets(10,10,10,10));
		
		// Set Font
		Text sceneTitle = new Text("Welcome to CSC 200");
		sceneTitle.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
		gPane.add(sceneTitle, 0, 0, 2, 1);
		
		// Create label and textfield
		Label username = new Label(" Username:");
		gPane.add(username,0,1);
		TextField tfusername = new TextField();
		gPane.add(tfusername, 1, 1);
		Label password = new Label(" Password:");
		gPane.add(password, 0, 2);
		PasswordField pfpassword = new PasswordField();
		gPane.add(pfpassword, 1, 2);
		
		// Create buttons and put them in Pane
		Button btSignIn = new Button ("Sign in");
		Button btGetAccount = new Button ("Sign up");
		HBox hBox = new HBox(10);
		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(btGetAccount,btSignIn);
		gPane.add(hBox, 1, 4);
		
		
		
		// Initialize action for GetAccount button
		btGetAccount.setOnAction(e -> {
			usernameCreate = JOptionPane.showInputDialog(null,"Enter your username",
					"Message",JOptionPane.PLAIN_MESSAGE);
			passwordCreate = JOptionPane.showInputDialog(null,"Enter your password",
					"Message",JOptionPane.PLAIN_MESSAGE);
			accounttypeCreate = (AccountType) JOptionPane.showInputDialog(null,"Select your account type.", "Account Type", 
	 				JOptionPane.PLAIN_MESSAGE,null, choicesAccount, choicesAccount[0]);
		});
		
		
		// Initialize action for SignIn button
		// set count = 0
		
		
		btSignIn.setOnAction(e -> {
			if (count < 3 ) {
				AccountType accounttypeInput;
				String usernameInput = tfusername.getText();
				String passwordInput = pfpassword.getText();
				
				if (usernameInput.equals(usernameCreate) && passwordInput.equals(passwordCreate)) {
					do {
						accounttypeInput = (AccountType) JOptionPane.showInputDialog(null,"Select your account type.", "Account Type", 
				 				JOptionPane.PLAIN_MESSAGE,null, choicesAccount, choicesAccount[0]);
					} while (!(accounttypeInput.equals(accounttypeCreate)));
					
					 JOptionPane.showMessageDialog(null,"Welcome " + usernameCreate + 
							 							"\nAccount Type: " + accounttypeCreate );
					 System.exit(1);
				}
				
				// when the user failed to log in
				else {
					count++;
					JOptionPane.showMessageDialog(null, "Attemp " + count +
						 "\nWrong username or password","Warning",JOptionPane.WARNING_MESSAGE);	
				}		 
			}
			else {
				
				int selectedOption = JOptionPane.showOptionDialog(null,
				" Please contact your adminstrator to unlock your account!", "Message",
				JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null,
				contactOption, null);
				
				if (selectedOption == 0) {
					JOptionPane.showMessageDialog(null, "Your account is unlocked!",
							"Message",JOptionPane.INFORMATION_MESSAGE);
					count = 0;
				}
			}
			
				
		});
		
		// Initialize scene and put it in Stage
		Scene scene = new Scene(gPane,300,300);
		primaryStage.setTitle("Log In");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	 

	
		  
		
	 
	  
}

