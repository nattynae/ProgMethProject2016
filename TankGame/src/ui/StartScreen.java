package ui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import Main.Main;
import Model.IRenderableHolder;
import Model.Player;
import Model.Pond;
import Model.StrongObstacle;
import Model.WeakObstacle;
import Utility.ColorUtility;
import Utility.GameUtility;
import Utility.NameFormatException;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class StartScreen extends StackPane{
	private Button startButton, body1, body2 ,body3 ,body4 , gun1, gun2, gun3, gun4;
	private Canvas canvas;
	private Image bg;
	private int currentX;
	private int imageWidth;
	private static String style = "-fx-text-fill: white; -fx-font: bold 20pt \"Time News Roman\";";
	private TextField player1TextField, player2TextField;
	private GameScreen gameScreen;
	
	public StartScreen(GameScreen gameScreen){
		super();
		this.setVisible(true);
		initializeGUI();
		addListener();
		this.gameScreen = gameScreen;
	}
	
	public void addListener(){
		startButton.setOnAction((ActionEvent e) -> {
			try {
				action();
			} catch (NameFormatException e1) {
				// TODO Auto-generated catch block
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("NameFormatError");
				alert.setHeaderText(null);
				alert.setContentText("-Name must not be white space.\n"
						+"-Replicated name is not allowed.");
				alert.show();
			}
		});
		
		body1.setOnAction((ActionEvent e) -> {
			ColorUtility.setColorBodyPlayer1(Color.DARKBLUE);
		});
		
		body2.setOnAction((ActionEvent e) -> {
			ColorUtility.setColorBodyPlayer1(Color.GREEN);
		});
		
		body3.setOnAction((ActionEvent e) -> {
			ColorUtility.setColorBodyPlayer2(Color.ORANGE);
		});
		
		body4.setOnAction((ActionEvent e) -> {
			ColorUtility.setColorBodyPlayer2(Color.PINK);
		});
		
		gun1.setOnAction((ActionEvent e) -> {
			ColorUtility.setColorGunPlayer1(Color.WHITE);
		});
		
		gun2.setOnAction((ActionEvent e) -> {
			ColorUtility.setColorGunPlayer1(Color.BROWN);
		});
		
		gun3.setOnAction((ActionEvent e) -> {
			ColorUtility.setColorGunPlayer2(Color.PURPLE);
		});
		
		gun4.setOnAction((ActionEvent e) -> {
			ColorUtility.setColorGunPlayer2(Color.AQUAMARINE);
		});
	}
	
	// Action when click startButton
	public void action() throws NameFormatException{ 
		int x1=210,y1=55,x2=100,y2=20;
		if(player1TextField.getText().matches("^\\s*$") || player2TextField.getText().matches("^\\s*$")){
			throw new NameFormatException(1);
		}
		else if(player1TextField.getText().equals(player2TextField.getText())){
			throw new NameFormatException(0);
		}
		Main.instance.startAnimation.stop();
		// create the map here
		for(int y = -20; y <= GameScreen.maxHeight + 20; y += 40){ // create boundary
			if(y == -20 || y == GameScreen.maxHeight +20){
				for(int x = 20; x <= GameScreen.maxWidth - 20; x += 40){
					IRenderableHolder.getInstance().addEntity(new StrongObstacle(x,y));
				}
			}else{
				IRenderableHolder.getInstance().addEntity(new StrongObstacle(-20,y));
				IRenderableHolder.getInstance().addEntity(new StrongObstacle(GameScreen.maxWidth +20,y));
			}
		}
		BufferedReader br;
		try{
			System.out.println(ClassLoader.getSystemResource("gamemap.txt").toString());
			//br = new BufferedReader(new FileReader("gamemap.txt"));
			br = new BufferedReader(new FileReader(ClassLoader.getSystemResource("gamemap.txt").getFile()));
		}catch(FileNotFoundException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
			return;
		}
		try{
			String line;
			int p = 0;
		    while((line = br.readLine()) != null) {
		    	String[] tmp = line.split(" ");
		    	int x = Integer.parseInt(tmp[1]);
		    	int y = Integer.parseInt(tmp[2]);
		    	if(tmp[0].equals("StrongObstacle")) {
		    		IRenderableHolder.getInstance().addEntity(new StrongObstacle(x, y));
		    	}
		    	else if(tmp[0].equals("WeakObstacle")) {
		    		IRenderableHolder.getInstance().addEntity(new WeakObstacle(x, y));
		    	}
		    	else if(tmp[0].equals("Pond")) {
		    		IRenderableHolder.getInstance().addEntity(new Pond(x, y));
		    	}
		    	else if(tmp[0].equals("Player")) {
		    		if (p==0) {
		    			x1=x;
		    			y1=y;
		    		}
		    		else {
		    			x2=x;
		    			y2=y;
		    		}
		    		p++;
		    	}
		    }
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		if(ColorUtility.getColorBodyPlayer2() != null && ColorUtility.getColorBodyPlayer1() != null 
				&& ColorUtility.getColorGunPlayer2() != null && ColorUtility.getColorGunPlayer1() != null){
			IRenderableHolder.getInstance().addEntity(new Player(player2TextField.getText(), x1, 
					y1, GameUtility.UP, ColorUtility.getColorBodyPlayer2(), ColorUtility.getColorGunPlayer2()));
			IRenderableHolder.getInstance().addEntity(new Player(player1TextField.getText(), x2, 
					y2, GameUtility.UP, ColorUtility.getColorBodyPlayer1(), ColorUtility.getColorGunPlayer1()));
		}else{
			IRenderableHolder.getInstance().addEntity(new Player(player2TextField.getText(), x1, y1, GameUtility.UP));
			IRenderableHolder.getInstance().addEntity(new Player(player1TextField.getText(), x2, y2, GameUtility.UP));
		}
		// set the new players to frame
		Main.instance.ChangeScene();
		gameScreen.findPlayer();
		
	}
	
	public void initializeGUI(){
		this.setPrefSize(GameUtility.GAMESCREEN_WIDTH, GameUtility.GAMESCREEN_HEIGHT);
		this.setAlignment(Pos.CENTER);
		this.bg = IRenderableHolder.bg;
		imageWidth = (int) bg.getWidth();
		
		startButton = new Button("Start");
		startButton.setAlignment(Pos.CENTER);
		startButton.setPrefSize(200, 20);
		startButton.setStyle(style+"-fx-background-color: gray");
		
		player1TextField = new TextField();
		player2TextField = new TextField();
		
		canvas = new Canvas(GameUtility.GAMESCREEN_WIDTH, GameUtility.GAMESCREEN_HEIGHT);
		
		body1 = new Button();
		body1.setPrefSize(75, 75);
		body1.setStyle("-fx-background-color: darkblue");
		body2 = new Button();
		body2.setPrefSize(75,75);
		body2.setStyle("-fx-background-color: green");
		body3 = new Button();
		body3.setPrefSize(75,75);
		body3.setStyle("-fx-background-color: orange");
		body4 = new Button();
		body4.setPrefSize(75,75);
		body4.setStyle("-fx-background-color: pink");
		gun1 = new Button();
		gun1.setPrefSize(75,75);
		gun1.setStyle("-fx-background-color: white");
		gun2 = new Button();
		gun2.setPrefSize(75,75);
		gun2.setStyle("-fx-background-color: brown");
		gun3 = new Button();
		gun3.setPrefSize(75,75);
		gun3.setStyle("-fx-background-color: purple");
		gun4 = new Button();
		gun4.setPrefSize(75,75);
		gun4.setStyle("-fx-background-color: aquamarine");

		Label bodyLabel1 = new Label("Body");
		bodyLabel1.setStyle(style);
		Label bodyLabel2 = new Label("Body");
		bodyLabel2.setStyle(style);
		Label gunLabel1 = new Label("Gun");
		gunLabel1.setStyle(style);
		Label gunLabel2 = new Label("Gun");
		gunLabel2.setStyle(style);
		Label player1Label = new Label("Player1 -->");
		player1Label.setStyle(style);
		Label player2Label = new Label("<-- Player2");
		player2Label.setStyle(style);
		Label vsLabel = new Label("VS");
		vsLabel.setStyle(style);
		Label titleLabel = new Label("Tank Game 1 vs 1");
		titleLabel.setStyle(style);
		
		
		GridPane leftPane = new GridPane();
		leftPane.setPrefSize(300, 300);
		leftPane.setVgap(10);
		leftPane.setHgap(50);
		leftPane.setAlignment(Pos.CENTER);
		leftPane.add(bodyLabel1, 0, 0);
		leftPane.add(body1, 0, 1);
		leftPane.add(body2, 1, 1);
		leftPane.add(gunLabel1, 0, 2);
		leftPane.add(gun1, 0, 3);
		leftPane.add(gun2, 1, 3);
		
		GridPane rightPane = new GridPane();
		rightPane.setPrefSize(300, 300);
		rightPane.setVgap(10);
		rightPane.setHgap(50);
		rightPane.setAlignment(Pos.CENTER);
		rightPane.add(bodyLabel2, 0, 0);
		rightPane.add(body3, 0, 1);
		rightPane.add(body4, 1, 1);
		rightPane.add(gunLabel2, 0, 2);
		rightPane.add(gun3, 0, 3);
		rightPane.add(gun4, 1, 3);
		
		GridPane centerPane = new GridPane();
		centerPane.setPrefSize(500, 500);
		centerPane.setVgap(30);
		centerPane.setHgap(30);
		centerPane.setAlignment(Pos.CENTER);
		centerPane.add(player2Label, 0, 0);
		centerPane.add(player2TextField, 1, 0);
		centerPane.add(player1Label, 1, 2);
		centerPane.add(player1TextField, 0, 2);
		
		
		BorderPane boderPane = new BorderPane();
		boderPane.setPrefSize(GameUtility.GAMESCREEN_WIDTH, GameUtility.GAMESCREEN_HEIGHT);
		boderPane.setPadding(new Insets(20,20,20,20));
		boderPane.setBottom(startButton);
		boderPane.setLeft(leftPane);
		boderPane.setRight(rightPane);
		boderPane.setCenter(centerPane);
		boderPane.setTop(titleLabel);
		boderPane.setAlignment(startButton, Pos.CENTER);
		boderPane.setAlignment(titleLabel, Pos.CENTER);
		
		this.getChildren().add(canvas);
		this.getChildren().add(boderPane);
		this.getChildren().add(vsLabel);
	}
	
	public void paintComponents(){
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setGlobalAlpha(0.8);
		gc.clearRect(0, 0, GameUtility.GAMESCREEN_WIDTH, GameUtility.GAMESCREEN_HEIGHT);
		WritableImage croppedImage = new WritableImage(bg.getPixelReader(), currentX, 0,
		GameUtility.GAMESCREEN_WIDTH, GameUtility.GAMESCREEN_HEIGHT);
		gc.drawImage(croppedImage, 0, 0);
		gc.setGlobalAlpha(1);
		
	}
	
	public void movementUpdate(){
		int newX = currentX + 1;
		if(newX + GameUtility.GAMESCREEN_WIDTH > imageWidth) newX = 0;
		currentX = newX;
	}

}