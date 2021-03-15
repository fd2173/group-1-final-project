package bakeryplanner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import numberlist.IndexException;
import numberlist.objectlist.Money;
import numberlist.objectlist.Temperature;

/**
 *
 * @author crocker
 */
public class BakeryUI extends Application {

    private BakedGoods list = new BakedGoods();
    //variables
    private int selected;
    //labels
    private Label title;
    private Label centerTitle;
    private Label leftTitle;
    private Label rightTitle;
    //textfields
    private TextField nameField;
    private TextField batchField;
    private TextField tempField;
    private TextField duraField;
    private TextField costField;
    //buttons
    private Button[] goodList;
    private Button btnAdd;
    private Button btnUpdate;
    private Button btnDelete;
    private Button btnClear;
    //Combo Box
    private ComboBox sortChoice;
    //Toggle Group
    private ToggleGroup tempGroup;
    //radio buttons
    private RadioButton rbF;
    private RadioButton rbC;
    //Nodes
    private VBox listBox;

    @Override
    public void start(Stage stage) throws Exception {
        //top pane
        FlowPane topPane = getTopPane();
        //center pane
        VBox centerPane = getCenterPane();
        //left pane
        VBox leftPane = getLeftPane();
        //right pane
        VBox rightPane = getRightPane();
        //Building Functionality
        addFunctionality();

        //parent 
        BorderPane root = new BorderPane();
        root.setTop(topPane);
        root.setLeft(leftPane);
        root.setRight(rightPane);
        root.setCenter(centerPane);
        //scene
        Scene scene = new Scene(root);
        //stage
        stage.setScene(scene);
        stage.show();

    }

    public void addFunctionality() {
        btnAdd.setOnAction((ActionEvent e) -> addGood());
        btnClear.setOnAction((ActionEvent e) -> clearFields());
        btnUpdate.setOnAction((ActionEvent e) -> updateGood());
        btnDelete.setOnAction((ActionEvent e) -> deleteGood());
        sortChoice.setOnAction((e)-> sortGood());

    }

    public FlowPane getTopPane() {
        //labels
        title = new Label("Bakery Planner");
        title.setFont(Font.font("serif", FontWeight.BOLD, 40));
        //Building the layout
        FlowPane topPane = new FlowPane();
        topPane.setAlignment(Pos.CENTER);
        topPane.getChildren().add(title);
        topPane.setBackground(new Background(new BackgroundFill(
                Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        //return the pane
        return topPane;
    }

    public VBox getCenterPane() {
        //labels
        centerTitle = new Label(" Add or Modify Order ");
        centerTitle.setFont(Font.font("serif", FontWeight.BOLD, 20));
        String[] labels = {"Item: ", "Batch Count: ", "Temperature (F,C): ",
            "Duration (min): ", "Cost ($): "};
        //text fields
        nameField = new TextField();
        batchField = new TextField();
        tempField = new TextField();
        duraField = new TextField();
        costField = new TextField();
        //building the layout
        VBox centerPane = new VBox();
        centerPane.setAlignment(Pos.CENTER);
        centerPane.getChildren().add(centerTitle);
        centerPane.setBackground(new Background(new BackgroundFill(
                Color.POWDERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox userInput = makeUserInput();
        HBox userControl = makeUserControl();
        centerPane.getChildren().addAll(userInput, userControl);
        //Return the pane
        return centerPane;
    }

    public HBox makeUserInput() {
        //labels
        String[] labels = {"Item: ", "Batch Count: ", "Temperature (F,C): ",
            "Duration (min): ", "Cost ($): "};
        //text fields
        nameField = new TextField();
        batchField = new TextField();
        tempField = new TextField();
        duraField = new TextField();
        costField = new TextField();
        //toggle group
        tempGroup = new ToggleGroup();
        //radio buttions
        rbF = new RadioButton("F");
        rbF.setToggleGroup(tempGroup);
        rbF.setUserData('F');
        rbC = new RadioButton("C");
        rbC.setToggleGroup(tempGroup);
        rbC.setUserData('C');
        //building the layout
        HBox userInput = new HBox();
        userInput.setPadding(new Insets(20, 20, 20, 20));
        userInput.setAlignment(Pos.CENTER);
        userInput.setBackground(new Background(new BackgroundFill(
                Color.CADETBLUE, CornerRadii.EMPTY, new Insets(10, 10, 10, 10))));
        //labels group
        VBox labelsBox = new VBox(20);
        labelsBox.setAlignment(Pos.CENTER_RIGHT);
        for (String label : labels) {
            labelsBox.getChildren().add(new Label(label));
        }
        //fields group
        VBox fieldsBox = new VBox(10);
        HBox tempBox = new HBox(4);
        tempBox.setAlignment(Pos.CENTER);
        tempBox.getChildren().addAll(tempField, rbF, rbC);
        fieldsBox.getChildren().addAll(nameField, batchField, tempBox,
                duraField, costField);
        //putting it together
        userInput.getChildren().addAll(labelsBox, fieldsBox);
        //return the pane
        return userInput;
    }

    public HBox makeUserControl() {
        HBox userControl = new HBox(10);
        userControl.setAlignment(Pos.CENTER);
        userControl.setBackground(new Background(new BackgroundFill(
                Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
        btnAdd = new Button("Add");
        btnUpdate = new Button("Update");
        btnDelete = new Button("Delete");
        btnClear = new Button("Clear");
        userControl.getChildren().addAll(btnAdd, btnUpdate, btnDelete, btnClear);
        return userControl;
    }

    public VBox getLeftPane() {
        //title
        leftTitle = new Label("   Baking Jobs   ");
        leftTitle.setFont(Font.font("serif", FontWeight.BOLD, 20));
        //building the layout
        VBox leftPane = new VBox();
        leftPane.setAlignment(Pos.TOP_CENTER);
        leftPane.getChildren().add(leftTitle);
        leftPane.setBackground(new Background(new BackgroundFill(
                Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));
        //Drop Down menu
        Label lblSort = new Label("Sort by: ");
        sortChoice = new ComboBox();
        sortChoice.getItems().addAll("A-Z","Batches","Temperature","Duration","Cost");
        HBox sortMenu = new HBox(5);
        sortMenu.setAlignment(Pos.CENTER);
        sortMenu.getChildren().addAll(lblSort, sortChoice);
        leftPane.getChildren().add(sortMenu);

        //building the list
        VBox listBox = makeListBox();
        ScrollPane listPane = new ScrollPane(listBox);
        listPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        listPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        leftPane.getChildren().add(listPane);
        return leftPane;
    }

    public VBox makeListBox() {
        //building the layout
        goodList = new Button[list.getCount()];
        listBox = new VBox();
        populateList();
        //return the list
        return listBox;
    }

    public VBox getRightPane() {
        //title
        rightTitle = new Label("      Stats      ");
        rightTitle.setFont(Font.font("serif", FontWeight.BOLD, 20));
        //building the layout
        VBox rightPane = new VBox();
        rightPane.setAlignment(Pos.TOP_CENTER);
        rightPane.getChildren().add(rightTitle);
        rightPane.setBackground(new Background(new BackgroundFill(
                Color.CHARTREUSE, CornerRadii.EMPTY, Insets.EMPTY)));
        //return the pane
        return rightPane;
    }

    public void addGood() {
        double price = Double.valueOf(costField.getText());
        int dollars = (int) Math.floor(price);
        int cents = (int) (price * 100) - (dollars * 100);
        String newName = nameField.getText();
        int newBatch = Integer.valueOf(batchField.getText());
        Temperature newTemp = new Temperature(Double.valueOf(tempField.getText()),
                (char) tempGroup.getSelectedToggle().getUserData());
        int newDuration = Integer.valueOf(duraField.getText());
        Money newCost = new Money(dollars, (byte) cents);
        list.addGood(newName, newBatch, newTemp, newDuration, newCost);
        populateList();
    }

    public void updateGood() {
        try {
            double price = Double.valueOf(costField.getText());
            int dollars = (int) Math.floor(price);
            int cents = (int) (price * 100) - (dollars * 100);
            String newName = nameField.getText();
            int newBatch = Integer.valueOf(batchField.getText());
            Temperature newTemp = new Temperature(Double.valueOf(tempField.getText()),
                    (char) tempGroup.getSelectedToggle().getUserData());
            int newDuration = Integer.valueOf(duraField.getText());
            Money newCost = new Money(dollars, (byte) cents);
            list.updateGood(selected, newName, newBatch, newTemp, newDuration, newCost);
            populateList();
        } catch (IndexException ex) {
            Logger.getLogger(BakeryUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteGood() {
        try {
            list.deleteGood(selected);
            populateList();
        } catch (IndexException ex) {
            Logger.getLogger(BakeryUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sortGood() {
        String choice = sortChoice.getValue().toString();
        switch (choice) {
            case ("A-Z"):
                list.sortByName();
                populateList();
                break;
            case ("Batches"):
                list.sortByBatches();
                populateList();
                break;
            case ("Temperature"):
                list.sortByTemps();
                populateList();
                break;
            case ("Duration"):
                list.sortByDuration();
                populateList();
                break;
            case ("Cost"):
                list.sortByCosts();
                populateList();
                break;
        }
    }

    public void populateList() {
        listBox.getChildren().clear();
        goodList = new Button[list.getCount()];
        try {
            //making the buttons and adding it to the list
            for (int i = 0; i < list.getCount(); i++) {
                String[] arr = list.getGoodArray(i);
                int index = i;
                Button btnGood = new Button(list.getGoodString(i));
                btnGood.setOnAction((ActionEvent e) -> selectItem(arr, index));
                goodList[i] = btnGood;
                listBox.getChildren().add(btnGood);
            }
        } catch (IndexException ex) {
            Logger.getLogger(BakeryUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectItem(String[] arr, int index) {
        selected = index;
        clearFields();
        nameField.setText(arr[0]);
        batchField.setText(arr[1]);
        tempField.setText(arr[2]);
        if (arr[3].equals("F")) {
            tempGroup.selectToggle(rbF);

        } else {
            tempGroup.selectToggle(rbC);
        }
        duraField.setText(arr[4]);
        costField.setText(arr[5]);

    }

    public void clearFields() {
        nameField.clear();
        batchField.clear();
        tempField.clear();
        duraField.clear();
        costField.clear();
    }

}
