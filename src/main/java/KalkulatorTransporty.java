
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.lang3.math.NumberUtils;


public class KalkulatorTransporty extends Application {

    private final int stawkaPoczatkowa = 70;
    private final int kosztPracyGodzinowejZespGlownego = 78;
    private final double kosztJednegoKilometra = 2.7;

    public static String roundFormat(double value) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
        return df.format(value);
    }

    private double kosztZDeska(double czasPracyDodZespolu) {
        int kosztPracyDodZespolu = 28;
        return 2 * kosztPracyDodZespolu * czasPracyDodZespolu;
    }

    private double obliczKosztTransportu(double iloscKilometrow, double czasKierowcyISanitariusza) {
        return iloscKilometrow * kosztJednegoKilometra + stawkaPoczatkowa + kosztPracyGodzinowejZespGlownego * czasKierowcyISanitariusza;
    }

    private boolean checkTextIsNumeric(String text) {
        return NumberUtils.isNumber(text);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Font font = Font.font("Times New Roman", FontWeight.BOLD, 14);
        Font fontDuzy = Font.font("Times New Roman", FontWeight.BOLD, 25);


        Label iloscKilometrow = new Label("Ilość kilometrów w dwie strony");
        iloscKilometrow.setLayoutX(180);
        iloscKilometrow.setLayoutY(10);
        iloscKilometrow.setFont(font);
        iloscKilometrow.setPadding(new Insets(30));

//        Label czasPracyOpis = new Label("Czas pracy Zespołu Wyjazdowego w godzinach ( każda rozpoczęta godzina liczona jest jako pełna)");
//        czasPracyOpis.setLayoutX(10);
//        czasPracyOpis.setLayoutY(50);
//        czasPracyOpis.setFont(font);
//        czasPracyOpis.setWrapText(true);

        Label czasPracyKierSan = new Label("Czas pracy Zespołu karetki: Kierowca + Sanitariusz \n ( każda rozpoczęta godzina liczona jest jako pełna)");
        czasPracyKierSan.setLayoutX(180);
        czasPracyKierSan.setLayoutY(90);
        czasPracyKierSan.setFont(font);
        czasPracyKierSan.setPadding(new Insets(30));

        CheckBox checkCzyZespDod = new CheckBox("Czy wymagany jest dodatkowy zespół 2 osób do przeniesienia pacjenta na desce?");
        checkCzyZespDod.setLayoutX(40);
        checkCzyZespDod.setLayoutY(130);
        checkCzyZespDod.setPadding(new Insets(30));
        checkCzyZespDod.setFont(font);

        Label czasPracyDodZesp = new Label("Czas pracy dodatkowego zespołu w przypadku transportu pacjenta z deską w 4 osoby\n ( każda rozpoczęta godzina liczona jest jako pełna)");
        czasPracyDodZesp.setLayoutX(180);
        czasPracyDodZesp.setLayoutY(170);
        czasPracyDodZesp.setFont(font);
        czasPracyDodZesp.setWrapText(true);
        czasPracyDodZesp.setVisible(false);
        czasPracyDodZesp.setPadding(new Insets(30));


        Label kosztCalkowity = new Label("Koszt całkowity wyliczanego transportu:");
        kosztCalkowity.setLayoutX(10);
        kosztCalkowity.setLayoutY(280);
        kosztCalkowity.setFont(font);
        kosztCalkowity.setDisable(true);
        kosztCalkowity.setPadding(new Insets(20));

        Label wynikObliczen = new Label();
        wynikObliczen.setVisible(false);
        wynikObliczen.setFont(fontDuzy);
        wynikObliczen.setTextFill(Color.GREEN);
        wynikObliczen.setPadding(new Insets(20));


        Image logoCitomed = new Image("image001.jpg");
        ImageView imageViewlogoCitomed = new ImageView(logoCitomed);
//        imageViewlogoCitomed.setX(550);
//        imageViewlogoCitomed.setY(450);
        imageViewlogoCitomed.setPreserveRatio(true);

        Button buttonWynik = new Button("Oblicz transport");
        buttonWynik.setLayoutX(10);
        buttonWynik.setLayoutY(240);


        TextField iloscKm = new TextField();
        iloscKm.setLayoutX(10);
        iloscKm.setLayoutY(10);
        iloscKm.setPromptText("Ilość km");
        checkTextIsNumeric(iloscKm.getText());


//        iloscKm.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                if (!checkTextIsNumeric(iloscKm.getText())){
//                    iloscKm.setStyle("-fx-text-fill: green");
//                }
//            }
//        });


        TextField czasKierSan = new TextField();
        czasKierSan.setLayoutX(10);
        czasKierSan.setLayoutY(90);
        czasKierSan.setPromptText("Ilość h");

        TextField czasDodZesp = new TextField();
        czasDodZesp.setLayoutX(10);
        czasDodZesp.setLayoutY(170);
        czasDodZesp.setPromptText("Ilość h");
        czasDodZesp.setVisible(false);

        checkCzyZespDod.setOnAction(event -> {
            if (!checkCzyZespDod.isSelected()) {
                czasPracyDodZesp.setVisible(false);
                czasDodZesp.setVisible(false);

            } else {
                czasPracyDodZesp.setVisible(true);
                czasDodZesp.setVisible(true);
            }

        });


//        FlowPane flowPane = new FlowPane();
//        flowPane.getChildren().addAll(
//                iloscKilometrow,
//                iloscKm,
//                czasPracyKierSan,
//                czasKierSan,
//                checkCzyZespDod,
//                czasPracyDodZesp,
//                czasDodZesp,
//                buttonWynik,
//                kosztCalkowity,
//                wynikObliczen
//
//        );
//        flowPane.setOrientation(Orientation.VERTICAL);
//        flowPane.setPadding(new Insets(20));
//        flowPane.setVgap(30);
//        flowPane.setAlignment(Pos.TOP_CENTER);

        HBox hBox = new HBox();
        hBox.getChildren().add(imageViewlogoCitomed);
        hBox.setPadding(new Insets(10));

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30));
        gridPane.add(iloscKm, 0, 0);
        gridPane.add(iloscKilometrow, 1, 0);
        gridPane.add(czasKierSan, 0, 1);
        gridPane.add(czasPracyKierSan, 1, 1);
        gridPane.add(checkCzyZespDod, 1, 2);
        gridPane.add(czasDodZesp, 0, 3);
        gridPane.add(czasPracyDodZesp, 1, 3);
        gridPane.add(buttonWynik, 0, 4);
        gridPane.add(kosztCalkowity, 1, 5);
        gridPane.add(wynikObliczen, 1, 6);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridPane);
        borderPane.setRight(hBox);

//        Group group = new Group();
//        group.getChildren().add(iloscKilometrow);
//        group.getChildren().add(czasPracyOpis);
//        group.getChildren().add(czasPracyKierSan);
//        group.getChildren().add(czasPracyDodZesp);
//        group.getChildren().add(kosztCalkowity);
//        group.getChildren().add(imageViewlogoCitomed);
//        group.getChildren().add(buttonWynik);
//        group.getChildren().add(iloscKm);
//        group.getChildren().add(czasKierSan);
//        group.getChildren().add(czasDodZesp);
//        group.getChildren().add(checkCzyZespDod);

        buttonWynik.setOnAction(event -> {
            kosztCalkowity.setDisable(false);

            double wynik = obliczKosztTransportu(Double.parseDouble(iloscKm.getText()), Double.parseDouble(czasKierSan.getText()));

            if (checkCzyZespDod.isSelected()) {
                wynik += kosztZDeska(Double.parseDouble(czasDodZesp.getText())*100*0.01);

            }
            System.out.printf("Wynik to: %.2f zł", wynik);
            wynikObliczen.setVisible(true);
            wynikObliczen.setText(roundFormat(wynik) + " zł brutto");
        });

        //TODO weryfikacja czy wprowadza się liczbę

        // scene
        Scene scene = new Scene(borderPane, Color.gray(0.95));


        // stage
        primaryStage.setScene(scene);

        primaryStage.setTitle("Kalkulator do Transportów Prywatnych");
        primaryStage.setHeight(600);
        primaryStage.setWidth(1000);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
        primaryStage.initStyle(StageStyle.DECORATED);


        primaryStage.show();


    }




}
