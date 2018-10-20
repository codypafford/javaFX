import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class n00894619 extends Application {

    private static final String myVid = "http://www.unf.edu/~n00894619/myVideo.mp4";
    Button btnUp = new Button("UP");
    Button btnDown = new Button("DOWN");

    Text name = new Text(50,50,"Cody Pafford");

    HBox top = new HBox();
    GridPane mid = new GridPane();
    VBox bot = new VBox();

    Rectangle rectangle = new Rectangle(50, 50);
    Slider rectangleSpeed = new Slider();

    ToggleGroup radios = new ToggleGroup();
    RadioButton rb1 = new RadioButton("Orange");
    RadioButton rb2 = new RadioButton("Purple");
    RadioButton rb3 = new RadioButton("Yellow");


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Project 7 - n00894619");

        BorderPane root = new BorderPane();

        top.getChildren().add(new TopPane());
        mid.getChildren().add(new MidPane());
        bot.getChildren().add(new BotPane());

        root.setTop(top);
        root.setCenter(mid);
        root.setBottom(bot);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

    }

    public class BotPane extends Pane {

        public BotPane() {
            bot.setStyle("-fx-background-color: black;");
            bot.setPadding(new Insets(7, 7, 7, 7));

            VBox buttonsBox = new VBox(10, btnUp, btnDown);
            buttonsBox.setAlignment(Pos.CENTER);

            btnUp.setOnAction(e -> {

                mid.getChildren().remove(name);
                mid.add(name, 0, 0);
            });


            btnDown.setOnAction(e -> {

                mid.getChildren().remove(name);
                mid.add(name, 0, 9);
            });

            bot.getChildren().add(buttonsBox);

        }
    }

    public class MidPane extends Pane {

        public MidPane() {
            CheckBox chkItalic = new CheckBox("Italic");
            CheckBox chkBold = new CheckBox("Bold");

            Font fontBoldItalic = Font.font("Veranda",FontWeight.BOLD,FontPosture.ITALIC,20);
            Font fontBold = Font.font("Veranda",FontWeight.BOLD,FontPosture.REGULAR,20);
            Font fontItalic = Font.font("Veranda",FontWeight.NORMAL,FontPosture.ITALIC,20);
            Font fontNormal = Font.font("Veranda",FontWeight.NORMAL,FontPosture.REGULAR,20);

            mid.setAlignment(Pos.TOP_CENTER);
            name.setFont(fontNormal);

            EventHandler<ActionEvent> handler = e -> {
                if (chkItalic.isSelected() && chkBold.isSelected()) {
                    name.setFont(fontBoldItalic);
                } else if (chkItalic.isSelected()) {
                    name.setFont(fontItalic);
                } else if (chkBold.isSelected()) {
                    name.setFont(fontBold);
                } else {
                    name.setFont(fontNormal);
                }
            };

            VBox vboxFont = new VBox(15, chkItalic, chkBold);
            vboxFont.setAlignment(Pos.CENTER_LEFT);


            chkItalic.setOnAction(handler);
            chkBold.setOnAction(handler);

            // chkItalic.requestFocus();
            //chkBold.requestFocus();


            //radio buttons for font color
            VBox vboxColors = new VBox(10, rb1, rb2, rb3);
            rb1.setToggleGroup(radios);
            rb2.setToggleGroup(radios);
            rb3.setToggleGroup(radios);
            vboxColors.setAlignment(Pos.TOP_LEFT);
            rb1.setOnAction(e -> {
                if (rb1.isSelected()) {
                    name.setFill(Color.ORANGE);
                }
            });
            rb2.setOnAction(e -> {
                if (rb2.isSelected()) {
                    name.setFill(Color.PURPLE);
                }
            });
            rb3.setOnAction(e -> {
                if (rb3.isSelected()) {
                    name.setFill(Color.YELLOW);
                }
            });

            Media media = new Media(myVid);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);
            mediaView.setFitHeight(200);
            mediaView.setFitWidth(200);

            Button play = new Button(">");
            HBox hboxPlay = new HBox(play);
            hboxPlay.setAlignment(Pos.BASELINE_CENTER);


            play.setOnAction(e -> {
                if(play.getText().equals(">")) {
                    mediaPlayer.play();
                    play.setText("||");
                } else {
                    mediaPlayer.pause();
                    play.setText(">");
                }
            });

            mid.setVgap(5);
            mid.add(name,    0, 5);
            mid.add(vboxFont,   0, 10);
            mid.add(mediaView,  2, 5);
            mid.add(hboxPlay,   2, 10);
            mid.add(vboxColors, 1, 10);

        }
    }

    public class TopPane extends Pane {

        public TopPane() {
            top.setAlignment(Pos.CENTER);
            top.setPrefHeight(210);
            top.setPadding(new Insets(5, 5, 5, 5));
            top.setStyle("-fx-background-color: black;");

            Line line = new Line();
            line.setStartX(0);
            line.setStartY(100);
            line.setEndX(0);
            line.setEndY(-50);

            rectangleSpeed.setMax(3);
            rectangleSpeed.setMin(.05);

            PathTransition pt = new PathTransition();
            pt.rateProperty().bind(rectangleSpeed.valueProperty());
            pt.setPath(line);
            pt.setNode(rectangle);
            pt.setAutoReverse(true);
            pt.setCycleCount(Animation.INDEFINITE);
            pt.play();

            rectangle.setFill(Color.RED);

            top.getChildren().addAll(rectangle, rectangleSpeed);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
