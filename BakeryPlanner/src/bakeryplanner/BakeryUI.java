package bakeryplanner;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import numberlist.IndexException;
import numberlist.objectlist.Money;
import numberlist.objectlist.Temperature;
import java.awt.Toolkit;
import java.util.Optional;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * This is the presentation layer (GUI) of our program.
 *
 * @author Octavia Stappart
 * @author Robert Crocker
 * @author Feny Dai
 * @version 03/18/21
 */
public class BakeryUI extends Application {

    private BakedGoods list = new BakedGoods();
    //variables
    private int selected;
    Toolkit errorSound;
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
    private Button btnStatsBatch;
    private Button btnStatsTemp;
    private Button btnStatsCost;
    private Button btnStatsDuration;
    private Button btnGenStats;
    //Combo Box
    private ComboBox sortChoice;
    //Toggle Group
    private ToggleGroup tempGroup;
    //radio buttons
    private RadioButton rbF;
    private RadioButton rbC;
    //Nodes
    private VBox listBox;
    private VBox chartBox;
    private GridPane genStats;
    private BarChart<Number, String> statsBatch;
    private BarChart<Number, String> statsTemp;
    private BarChart<Number, String> statsDuration;
    private BarChart<Number, String> statsCost;

    /**
     * start method required for any JavaFX application. Sets up the Stage,
     * Scene and all of the component and functionality methods.
     *
     * @param stage the primary stage of the application
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        errorSound = Toolkit.getDefaultToolkit();
        //top pane
        FlowPane topPane = getTopPane();
        //center pane
        VBox centerPane = getCenterPane();
        // right pane
        // NOTE: Unless we rewrite the code, for now, we MUST create the rightPane BEFORE the leftPane,
        //       because the leftPane USES the rightPane 
        VBox rightPane = getRightPane();
        //left pane
        VBox leftPane = getLeftPane();
        //Building Functionality
        addFunctionality();
        //parent 
        BorderPane root = new BorderPane();
        root.setTop(topPane);
        root.setLeft(leftPane);
        root.setRight(rightPane);
        root.setCenter(centerPane);
        //scene
        Scene scene = new Scene(root, 1400, 600);
        //stage
        stage.setTitle("Bakery Planner");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * This methods handles setup of the actual functionality of the buttons and
     * user controls.
     *
     */
    public void addFunctionality() {
        //User Input Controls
        btnAdd.setOnAction((ActionEvent e) -> addGood());
        btnClear.setOnAction((ActionEvent e) -> clearFields());
        btnUpdate.setOnAction((ActionEvent e) -> updateGood());
        btnDelete.setOnAction((ActionEvent e) -> deleteGood());
        //List Controls
        sortChoice.setOnAction((e) -> sortGood());
        //Stats Controls
        btnStatsBatch.setOnAction((ActionEvent e) -> chartBox.getChildren().set(0, statsBatch));
        btnStatsTemp.setOnAction((ActionEvent e) -> chartBox.getChildren().set(0, statsTemp));
        btnStatsDuration.setOnAction((ActionEvent e) -> chartBox.getChildren().set(0, statsDuration));
        btnStatsCost.setOnAction((ActionEvent e) -> chartBox.getChildren().set(0, statsCost));
        btnGenStats.setOnAction((ActionEvent e) -> chartBox.getChildren().set(0, genStats));
    }

    /**
     * This method sets up the Title of the Application and builds a flowPane
     * Object to be added to the root BorderPane.
     *
     * @return a FlowPane containing the Title of the Application.
     */
    public FlowPane getTopPane() {
        FlowPane topPane = new FlowPane();
        topPane.setPadding(new Insets(10, 10, 10, 10));
        //labels
        title = new Label("Bakery Planner");
        title.setFont(Font.font("serif", FontWeight.BOLD, 60));
        topPane.getChildren().add(title);
        //Building the layout
        topPane.setAlignment(Pos.CENTER);
        topPane.setBackground(new Background(new BackgroundFill(
                Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        //return the pane
        return topPane;
    }

    /**
     * This method sets up the User Controls for adding, modifying, and deleting
     * a Job and sets them into a VBox to be added to the root BorderPane.
     *
     * @return a VBox containing the User Controls.
     */
    public VBox getCenterPane() {
        VBox centerPane = new VBox();
        centerPane.setFillWidth(true);
        //labels
        centerTitle = new Label("Add or Modify Order");
        centerTitle.setFont(Font.font("serif", FontWeight.BOLD, 30));
        centerPane.getChildren().add(centerTitle);
        String[] labels = {"Item: ", "Batch Count: ", "Temperature (F,C): ",
            "Duration (min): ", "Cost ($): "};
        //text fields
        nameField = new TextField();
        batchField = new TextField();
        tempField = new TextField();
        duraField = new TextField();
        costField = new TextField();
        //building the layout
        centerPane.setAlignment(Pos.TOP_CENTER);
        centerPane.setBackground(new Background(new BackgroundFill(
                Color.POWDERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        GridPane userInput = makeUserInput();
        userInput.setMaxWidth(Control.USE_PREF_SIZE);
        userInput.setPrefWidth(450.0);
        userInput.setMinWidth(Control.USE_PREF_SIZE);
        HBox userControl = makeUserControl();
        centerPane.getChildren().addAll(userInput, userControl);
        //Return the pane
        return centerPane;
    }

    /**
     * Organizational Method called by getCenterPane to build the Labels and
     * TextFields for receiving user Input. Uses a gridPane object to align text
     * and input boxes.
     *
     * @return GridPane containing Labels and TextFields for user Input
     */
    public GridPane makeUserInput() {
        GridPane userInput = new GridPane();
        userInput.setHgap(5);
        userInput.setVgap(10);
        //labels
        Label lblName = new Label("Item: ");
        lblName.setFont(Font.font("sans serif", FontWeight.BOLD, 15));
        Label lblBatch = new Label("Batch Count: ");
        lblBatch.setFont(Font.font("sans serif", FontWeight.BOLD, 15));
        Label lblTemp = new Label("Temperature (F,C): ");
        lblTemp.setFont(Font.font("sans serif", FontWeight.BOLD, 15));
        Label lblDura = new Label("Duration (min): ");
        lblDura.setFont(Font.font("sans serif", FontWeight.BOLD, 15));
        Label lblCost = new Label("Cost ($): ");
        lblCost.setFont(Font.font("sans serif", FontWeight.BOLD, 15));
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
        userInput.setPadding(new Insets(20, 20, 20, 20));
        userInput.setAlignment(Pos.CENTER);
        userInput.setBackground(new Background(new BackgroundFill(
                Color.CADETBLUE, CornerRadii.EMPTY, new Insets(10, 10, 10, 10))));
        //labels group
        //fields group
        HBox tempBox = new HBox(4);
        tempBox.setAlignment(Pos.CENTER);
        tempBox.getChildren().addAll(tempField, rbF, rbC);
        //putting it together
        userInput.add(lblName, 0, 0);
        userInput.add(nameField, 1, 0);
        userInput.add(lblBatch, 0, 1);
        userInput.add(batchField, 1, 1);
        userInput.add(lblTemp, 0, 2);
        userInput.add(tempBox, 1, 2);
        userInput.add(lblDura, 0, 3);
        userInput.add(duraField, 1, 3);
        userInput.add(lblCost, 0, 4);
        userInput.add(costField, 1, 4);
        userInput.setHalignment(lblName, HPos.RIGHT);
        userInput.setHalignment(lblBatch, HPos.RIGHT);
        userInput.setHalignment(lblTemp, HPos.RIGHT);
        userInput.setHalignment(lblDura, HPos.RIGHT);
        userInput.setHalignment(lblCost, HPos.RIGHT);
        //return the pane
        return userInput;
    }

    /**
     * Organizational Method called by getCenterPane to build the Buttons for
     * adding, modifying, deleting new Baking Jobs, as well as clearing the
     * TextFields of any unwanted data.
     *
     * @return
     */
    public HBox makeUserControl() {
        HBox userControl = new HBox(10);
        userControl.setAlignment(Pos.CENTER);
        btnAdd = new Button("Add");
        btnUpdate = new Button("Update");
        btnDelete = new Button("Delete");
        btnClear = new Button("Clear");
        userControl.getChildren().addAll(btnAdd, btnUpdate, btnDelete, btnClear);
        return userControl;
    }

    /**
     * This method sets up the ScrollPane and VBox that holds the list of Baking
     * Jobs. Also builds a drop down menu for sorting said list.
     *
     * @return VBox containing the VBox for the list, scrollPane, and drop down
     * menu.
     */
    public VBox getLeftPane() {
        VBox leftPane = new VBox(10);
        leftPane.setMinWidth(350);
        leftPane.setPadding(new Insets(10, 10, 10, 10));
        leftPane.setFillWidth(true);
        //title
        leftTitle = new Label("   Baking Jobs   ");
        leftTitle.setFont(Font.font("serif", FontWeight.BOLD, 30));
        leftPane.getChildren().add(leftTitle);
        //building the layout
        leftPane.setAlignment(Pos.TOP_CENTER);
        leftPane.setBackground(new Background(new BackgroundFill(
                Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));
        //Drop Down menu
        Label lblSort = new Label("Sort by: ");
        lblSort.setFont(Font.font("sans serif", FontWeight.BOLD, 15));
        sortChoice = new ComboBox();
        sortChoice.getItems().addAll("Names A-Z", "Batches", "Temperature", "Duration", "Cost");
        HBox sortMenu = new HBox(5);
        sortMenu.setAlignment(Pos.CENTER);
        sortMenu.getChildren().addAll(lblSort, sortChoice);
        leftPane.getChildren().add(sortMenu);
        //building the list
        VBox listBox = makeListBox();
        listBox.setFillWidth(true);
        ScrollPane listPane = new ScrollPane(listBox);
        listPane.setFitToWidth(true);
        listPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        listPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        leftPane.getChildren().add(listPane);
        return leftPane;
    }

    /**
     * Organizational method called by getLeftPane that sets up and fills the
     * list of Baking jobs on start up.
     *
     * @return a VBox containing the list of Baking Jobs.
     */
    public VBox makeListBox() {
        //building the layout
        goodList = new Button[list.getCount()];
        listBox = new VBox();
        populateList();
        //return the list
        return listBox;
    }

    /**
     * This Method sets up the a VBox that holds the calculations and charts
     * made for the Application, which is then added to the root BorderPane.
     *
     * @return VBox containing calculations and data charts based on the jobs in
     * the BakedGoods object.
     */
    public VBox getRightPane() {
        VBox rightPane = new VBox(10);
        //title
        rightTitle = new Label("      Stats      ");
        rightTitle.setFont(Font.font("serif", FontWeight.BOLD, 30));
        rightPane.getChildren().add(rightTitle);
        //building the layout
        rightPane.setAlignment(Pos.TOP_CENTER);
        rightPane.setBackground(new Background(new BackgroundFill(
                Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        //Chart Area
        VBox chartArea = getChartArea();
        rightPane.getChildren().add(chartArea);
        //return the pane
        return rightPane;
    }

    /**
     * Organizational method called by getRightPane to build the chart display
     * as well as the controls for switching between views. The following views
     * are supported: Total Batch Count, Average Temperature, Average Duration,
     * Average Cost, General Statistics.
     *
     * @return VBox contain chart window and User Controls to switch views.
     */
    public VBox getChartArea() {
        btnStatsBatch = new Button("Batches");
        btnStatsTemp = new Button("Temperatures");
        btnStatsDuration = new Button("Durations");
        btnStatsCost = new Button("Costs");
        btnGenStats = new Button("General Stats");
        VBox chartArea = new VBox(5);
        chartArea.setAlignment(Pos.CENTER);
        chartBox = new VBox();
        chartBox.setAlignment(Pos.CENTER);
        chartBox.setBackground(new Background(new BackgroundFill(
                Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        chartBox.setPadding(new Insets(10, 10, 10, 10));
        genStats = getGenStats();
        genStats.setAlignment(Pos.CENTER);
        chartBox.getChildren().add(genStats);
        FlowPane statsControl = new FlowPane(10, 10);
        statsControl.setPadding(new Insets(10, 10, 10, 10));
        statsControl.getChildren().addAll(btnStatsBatch, btnStatsTemp,
                btnStatsDuration, btnStatsCost, btnGenStats);
        statsControl.setMaxWidth(Control.USE_PREF_SIZE);
        statsControl.setAlignment(Pos.BASELINE_CENTER);
        chartArea.getChildren().addAll(chartBox, statsControl);
        return chartArea;

    }

    /**
     * Organizational method called by getChartArea for building the General
     * Statistics View. Displays the calculations for Total Jobs, Cost, and
     * Duration as well as Average Temp and Cost across all items in the list.
     *
     * @return GridPane containing the above calculations.
     */
    public GridPane getGenStats() {
        GridPane genStats = new GridPane();
        genStats.setHgap(5);
        genStats.setVgap(10);
        Label lblGoods = new Label("Total Jobs: ");
        lblGoods.setFont(Font.font("sans serif", FontWeight.BOLD, 15));
        Label lblGoodsCalc = new Label(String.valueOf(list.getCount()));
        Label lblDura = new Label("Total Duration (min): ");
        lblDura.setFont(Font.font("sans serif", FontWeight.BOLD, 15));
        Label lblDuraCalc = new Label(String.valueOf(list.totalDuration()));
        Label lblCost = new Label("Total Cost: ");
        lblCost.setFont(Font.font("sans serif", FontWeight.BOLD, 15));
        Label lblCostCalc = new Label(String.valueOf(list.totalCost()));
        Label lineA = new Label("-------------------------");
        Label lineB = new Label("---------");
        Label lblAvgCost = new Label("Avg Cost: ");
        lblAvgCost.setFont(Font.font("sans serif", FontWeight.BOLD, 15));
        Label lblAvgCostCalc = new Label(String.valueOf(list.averageCost()));
        Label lblAvgTemp = new Label("Avg Temp: ");
        lblAvgTemp.setFont(Font.font("sans serif", FontWeight.BOLD, 15));
        Label lblAvgTempCalc = new Label(String.valueOf(list.averageTemp()));
        genStats.add(lblGoods, 0, 0);
        genStats.add(lblGoodsCalc, 1, 0);
        genStats.add(lblDura, 0, 1);
        genStats.add(lblDuraCalc, 1, 1);
        genStats.add(lblCost, 0, 2);
        genStats.add(lblCostCalc, 1, 2);
        genStats.add(lineA, 0, 3);
        genStats.add(lineB, 1, 3);
        genStats.add(lblAvgCost, 0, 4);
        genStats.add(lblAvgCostCalc, 1, 4);
        genStats.add(lblAvgTemp, 0, 5);
        genStats.add(lblAvgTempCalc, 1, 5);
        genStats.setHalignment(lblGoods, HPos.RIGHT);
        genStats.setHalignment(lblDura, HPos.RIGHT);
        genStats.setHalignment(lblCost, HPos.RIGHT);
        genStats.setHalignment(lblAvgCost, HPos.RIGHT);
        genStats.setHalignment(lblAvgTemp, HPos.RIGHT);
        genStats.setHalignment(lineA, HPos.RIGHT);
        return genStats;
    }

    /**
     * Functionality Method for taking the information present in the TextFields
     * and adding it to the BakedGoods list. When done, updates the list display
     * in the Application.
     *
     */
    public void addGood() {
        if (inputValidation() == true) {
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
            this.clearFields();
        }

    }

    /**
     * Functionality Method for updating the information of an item in the
     * BakedGoods list, using the selected button in goodList as the index to
     * pass to the BakedGoods.updateGood() Method.
     *
     * If list is empty, plays an error sound.
     *
     */
    public void updateGood() {
        if (inputValidation() == true) {
            try {
                if (list.getCount() == 0) {
                    errorSound.beep();
                    clearFields();
                    return;
                }
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
            }
        }
    }

    /**
     * Functionality Method for removing an item from the BakedGoods List. Uses
     * the index of the selected button to pass to the BakedGoods.deleteGood
     * method.
     *
     * If list is empty, plays an error sound.
     *
     */
    public void deleteGood() {
        try {
            if (list.getCount() == 0) {
                errorSound.beep();
                clearFields();
                return;
            }
            list.deleteGood(selected);
            populateList();
        } catch (IndexException ex) {
            Logger.getLogger(BakeryUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Functionality method for sorting the BakedGoods list. Takes the chosen
     * item from the drop down menu and calls the relevant sorting method in the
     * BakedGoods class, and then updates the list displayed by the Application.
     *
     */
    public void sortGood() {
        String choice = sortChoice.getValue().toString();
        switch (choice) {
            case ("Names A-Z"):
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

    /**
     * Functionality Method for actually displaying the list to the user. For
     * each item in the BakedGoods List, creates a button with text in the
     * format: " "NAME - ##x ###?? , ##min. $##.##"
     *
     * When called, also updates the data charts displayed to the user.
     *
     */
    public void populateList() {
        listBox.getChildren().clear();
        goodList = new Button[list.getCount()];
        try {
            //making the buttons and adding it to the list
            for (int i = 0; i < list.getCount(); i++) {
                String[] arr = list.getGoodArray(i);
                int index = i;
                Button btnGood = new Button(list.getGoodString(i));
                btnGood.setMaxWidth(Double.MAX_VALUE);
                btnGood.setAlignment(Pos.CENTER_LEFT);
                btnGood.setOnAction((ActionEvent e) -> selectItem(arr, index));
                goodList[i] = btnGood;
                listBox.getChildren().add(btnGood);
            }
            statsBatch = getBatchChart();
            chartBox.getChildren().set(0, statsBatch);
            statsTemp = getTempChart();
            chartBox.getChildren().set(0, statsTemp);
            statsDuration = getDuraChart();
            chartBox.getChildren().set(0, statsDuration);
            statsCost = getCostChart();
            chartBox.getChildren().set(0, statsCost);
            genStats = getGenStats();
            chartBox.getChildren().set(0, genStats);

        } catch (IndexException ex) {
            Logger.getLogger(BakeryUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method for populating the TextFields when an item in the Job list is
     * selected. Assigns the index of the selected button in goodList to a
     * global variable for use in other methods.
     *
     * @param arr a String array containing Job Information
     * @param index the index of the button selected.
     */
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

    /**
     * Clears all of the textFields of any data.
     *
     */
    public void clearFields() {
        nameField.clear();
        batchField.clear();
        tempField.clear();
        duraField.clear();
        costField.clear();
    }

    /**
     * Method for building the chart to display the information on total Batches
     * by name of the Item in the list.
     *
     * @return the BarChart object.
     */
    public BarChart getBatchChart() {
        final NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Total Batch(es) or Item(s) Count");
        xAxis.setAutoRanging(true);
        final CategoryAxis yAxis = new CategoryAxis();
        yAxis.setAutoRanging(true);
        final BarChart<Number, String> statsBatch = new BarChart<>(xAxis, yAxis);
        statsBatch.setTitle("Total Batch(es) or Item(s) Count");
        statsBatch.setLegendVisible(false);
        yAxis.setLabel("Job");
        String[] names = list.getNames();
        long[] batches = list.getBatches();
        Series series = new Series();
        for (int i = 0; i < list.getCount(); i++) {
            long total = list.getTotalBatchesByName(names[i]);
            series.getData().add(new Data(total, names[i]));
        }
        statsBatch.getData().add(series);
        return statsBatch;

    }

    /**
     * Method for building the chart to display the information on Average
     * Temperature by name of the Item in the list.
     *
     * @return the BarChart object.
     */
    public BarChart getTempChart() {
        final NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Average Temperature (F)");
        xAxis.setAutoRanging(true);
        final CategoryAxis yAxis = new CategoryAxis();
        yAxis.setAutoRanging(true);
        final BarChart<Number, String> statsTemp = new BarChart<>(xAxis, yAxis);
        statsTemp.setTitle("Average Temperature (F)");
        statsTemp.setLegendVisible(false);
        yAxis.setLabel("Job");
        String[] names = list.getNames();
        Temperature[] temps = list.getTemps();
        Series series = new Series();
        for (int i = 0; i < list.getCount(); i++) {
            Temperature average = list.averageTempByName(names[i]);
            series.getData().add(new Data(average.getValue(), names[i]));
        }
        statsTemp.getData().add(series);
        return statsTemp;

    }

    /**
     * Method for building the chart to display the information on average
     * Duration by name of the Item in the list.
     *
     * @return the BarChart object.
     */
    public BarChart getDuraChart() {
        final NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Average Duration(min)");
        xAxis.setAutoRanging(true);
        final CategoryAxis yAxis = new CategoryAxis();
        yAxis.setAutoRanging(true);
        final BarChart<Number, String> statsDura = new BarChart<>(xAxis, yAxis);
        statsDura.setTitle("Average Baking Time (min)");
        statsDura.setLegendVisible(false);
        yAxis.setLabel("Job");
        String[] names = list.getNames();
        long[] durations = list.getDurations();
        Series series = new Series();
        for (int i = 0; i < list.getCount(); i++) {
            double average = list.averageDurationByName(names[i]);
            series.getData().add(new Data(average, names[i]));
        }
        statsDura.getData().add(series);
        return statsDura;

    }

    /**
     * Method for building the chart to display the information on average Cost
     * by name of the Item in the list.
     *
     * @return the BarChart object.
     */
    public BarChart getCostChart() {
        final NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Average Costs per Batch ($)");
        xAxis.setAutoRanging(true);
        final CategoryAxis yAxis = new CategoryAxis();
        yAxis.setAutoRanging(true);
        final BarChart<Number, String> statsCost = new BarChart<>(xAxis, yAxis);
        statsCost.setTitle("Average Costs Per Batch Per Item Type");
        statsCost.setLegendVisible(false);
        yAxis.setLabel("Job");
        String[] names = list.getNames();
        Money[] costs = list.getCosts();
        Series series = new Series();
        for (int i = 0; i < list.getCount(); i++) {
            Money average = list.averageCostByName(names[i]);
            series.getData().add(new Data(average.getDollars() + (average.getCents() / 100), names[i]));
        }
        statsCost.getData().add(series);
        return statsCost;

    }

    /**
     * Bulletproofing
     *
     * @return a Boolean that tells the program if input is valid
     */
    public boolean inputValidation() {
        boolean isValid = true;
        try {
            // Batch count bulletproofing
            if (batchField.getText().isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR, "Please fill all fields completely to add / update.");
                errorSound.beep();
                alert.showAndWait();
                isValid = false;
            } else if (Integer.parseInt(batchField.getText()) <= 0.0) {
                Alert alert = new Alert(AlertType.ERROR, "Batches count must be a positive whole number.");
                errorSound.beep();
                alert.showAndWait();
                isValid = false;
                // Temperature bulletproofing
            } else if (tempField.getText().isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR, "Please fill all fields completely to add / update.");
                errorSound.beep();
                alert.showAndWait();
                isValid = false;
            } else if (Double.parseDouble(tempField.getText()) <= 0.0) {
                Alert alert = new Alert(AlertType.ERROR, "Temperature of the oven must be positive.");
                errorSound.beep();
                alert.showAndWait();
                isValid = false;
                // Duration bulletproofing
            } else if (duraField.getText().isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR, "Please fill all fields completely to add / update.");
                errorSound.beep();
                alert.showAndWait();
                isValid = false;
            } else if (Integer.parseInt(duraField.getText()) <= 0.0) {
                Alert alert = new Alert(AlertType.ERROR, "Duration must be a postive whole number.");
                errorSound.beep();
                alert.showAndWait();
                isValid = false;
                // Cost bulletproofing
            } else if (costField.getText().isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR, "Please fill all fields completely to add / update.");
                errorSound.beep();
                alert.showAndWait();
                isValid = false;
            } else if (Double.parseDouble(costField.getText()) <= 0.0) {
                Alert alert = new Alert(AlertType.ERROR, "Cost must be a postive.");
                errorSound.beep();
                alert.showAndWait();
                isValid = false;
            } else if (!rbF.isSelected() && !rbC.isSelected()) {
                Alert alert = new Alert(AlertType.ERROR, "Please select a unit for the temperature.");
                errorSound.beep();
                alert.showAndWait();
                isValid = false;
            }
        } catch (NumberFormatException nfe) {
            Alert alert = new Alert(AlertType.ERROR, "All fields, except \"Item\" must be numeric. \"Batch Count\" & \"Duration\" must be a positive whole number.");
            errorSound.beep();
            alert.showAndWait();
            isValid = false;
        }
        return isValid;
    }
}
