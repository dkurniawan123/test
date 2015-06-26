import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class HighLowGame extends Application{

	private final static int xWindowSize = 400;
	private final static int yWindowSize = 250;
	
	private final static BackgroundImage backgroundImage = new BackgroundImage(new Image("image/background.jpg"), 
			BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, 
			new BackgroundSize(xWindowSize, yWindowSize, true, true, true, true));
	
	private int score;
	private Card currentCard;
	private Deck deck;
	private Button highButton = new Button("High");
	private Button lowButton = new Button("Low");
	
	public static void main(String[] args){
		launch();
	}
	
	public void start(Stage gameStage) throws Exception {
	
		
	
	initGame();	
	renderGameScene(gameStage);
	
	gameStage.setResizable(false);	
	gameStage.show();	
	
	highButton.setOnAction(e -> {
		Card newCard = deck.draw();		
		
		if (newCard.compareTo(currentCard) > 0) {
			score++;
		}
		
		currentCard = newCard;
		
		renderGameScene(gameStage);		
		
		if (deck.size() == 0){
			endGame(gameStage);
		}
	});
	
	lowButton.setOnAction(e -> {
		Card newCard = deck.draw();		
		
		if (newCard.compareTo(currentCard) < 0) {
			score++;
		}
		
		currentCard = newCard;
		renderGameScene(gameStage);
		
		
		if (deck.size() == 0){
			endGame(gameStage);
		}
	});
	
	}
	
	private void initGame(){
		deck = new Deck();
		deck.shuffle();			
		currentCard = deck.draw();		
		score = 0;
	}
	
	private Parent initHighLowGameLayout(){
		
		BorderPane mainLayout = new BorderPane();
		mainLayout.setPadding(new Insets(10));
		
		mainLayout.prefWidth(xWindowSize);
		mainLayout.prefHeight(yWindowSize);
		
				
		HBox gameLayout = new HBox(10);
		gameLayout.setPadding(new Insets(0,0,10,0));
		
		
		HBox pointLayout = new HBox(5);
		
		mainLayout.setCenter(gameLayout);
		mainLayout.setBottom(pointLayout);
		
		
		
		ImageView deckImage = new ImageView(deck.getBackImage());
		ImageView cardImage = new ImageView(currentCard.getCardImage());
		
		int imageWidth = (xWindowSize - 4 * 10 ) / 3 ;
		
		deckImage.setFitWidth(imageWidth);
		deckImage.setPreserveRatio(true);
		
		
		cardImage.setFitWidth(imageWidth);
		cardImage.setPreserveRatio(true);
		
		
		if (deck.size() == 0) {
			deckImage.setOpacity(0);
		}
		
		VBox buttonLayout = new VBox(10);
		
		highButton.setFont(Font.font(14));
		lowButton.setFont(Font.font(14));	
		
		highButton.setPrefWidth(imageWidth);
		
		lowButton.prefWidthProperty().bind(highButton.widthProperty());
		
		buttonLayout.getChildren().addAll(highButton, lowButton);
	
		
		
		gameLayout.getChildren().addAll(deckImage, cardImage, buttonLayout);

		
		Label pointLabel = new Label("POINTS:");
		pointLabel.setFont(Font.font(18));
		pointLabel.setTextFill(Color.WHITE);;
		
		
		Label scoreLabel = new Label(Integer.toString(score));
		scoreLabel.setFont(Font.font(18));
		scoreLabel.setTextFill(Color.WHITE);
		
			
		pointLayout.setAlignment(Pos.BASELINE_CENTER);
		pointLayout.getChildren().addAll(pointLabel, scoreLabel);
		
	
		mainLayout.setBackground(new Background(backgroundImage));
		return mainLayout;
		
	}
	
	private void renderGameScene(Stage stage){
		stage.setScene(new Scene(initHighLowGameLayout(), xWindowSize, yWindowSize));	
	}
	
	private void endGame(Stage gameStage){
		
		Stage endStage = new Stage();
		
		Button restartButton = new Button("Restart");
		
		Parent endLayout = initializeEndLayout(restartButton);
		
		Scene endScene = new Scene(endLayout, 200, 100);
		
		endStage.setScene(endScene);		
		endStage.initModality(Modality.APPLICATION_MODAL);
		endStage.setResizable(false);
		endStage.show();
		
		restartButton.setOnAction(e -> {
			endStage.close();
			
			initGame();
			
			renderGameScene(gameStage);
			
		});
		
	}
	
	private Parent initializeEndLayout(Button restartButton){
		
		VBox endLayout = new VBox(10);
		endLayout.setPadding(new Insets(10));
		endLayout.setAlignment(Pos.CENTER);
		
		String scoreString = ("Your final score: " + score);
		
		Label scoreLabel = new Label(scoreString);				
	
		endLayout.getChildren().addAll(scoreLabel, restartButton);
		
		return endLayout;
	}

}
