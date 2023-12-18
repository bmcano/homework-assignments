import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Brandon Cano
 */
public class Controller {

    private final ObservableList<String> districts = FXCollections.observableArrayList(
            "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware",
            "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana",
            "Maine 1st", "Maine 2nd", "Maine Popular", "Maryland", "Massachusetts", "Michigan", "Minnesota",
            "Mississippi", "Missouri", "Montana", "Nebraska 1st", "Nebraska 2nd", "Nebraska 3rd", "Nebraska Popular",
            "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio",
            "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas",
            "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming", "Washington, D.C."
    );

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private RadioButton radioButton1;
    @FXML
    private RadioButton radioButton2;
    @FXML
    private RadioButton radioButton3;
    @FXML
    private PieChart pieChart;
    @FXML
    private ToggleGroup group;
    @FXML
    private Text democratText;
    @FXML
    private Text republicanText;
    @FXML
    private Text otherText;
    @FXML
    private Text winnerText;
    @FXML
    private ImageView imageView;

    private final States states = new States();
    private final Map<String, String> selections = new HashMap<>(); // state, party
    private final Map<String, Integer> stateColor = new HashMap<>(); // state code, color

    private int democratVotes = 0;
    private int republicanVotes = 0;
    private int otherVotes = 0;

    PieChart.Data democratData;
    PieChart.Data republicanData;
    PieChart.Data otherData;
    ObservableList<PieChart.Data> pieChartData;

    /**
     * This will initialize a lot of values and items for the GUI
     *
     * @throws IOException if something goes wrong
     */
    public void initialize() throws IOException {
        comboBox.setItems(districts);
        winnerText.setVisible(false);

        // radio buttons
        group = new ToggleGroup();
        radioButton1.setToggleGroup(group);
        radioButton2.setToggleGroup(group);
        radioButton3.setToggleGroup(group);

        // pie chart
        democratData = new PieChart.Data("Democrat", democratVotes);
        republicanData = new PieChart.Data("Republican", republicanVotes);
        otherData = new PieChart.Data("Other", otherVotes);

        pieChartData = FXCollections.observableArrayList(democratData, republicanData, otherData);
        pieChart.setData(pieChartData);

        democratData.getNode().setStyle("-fx-pie-color: #0D69EF;");
        republicanData.getNode().setStyle("-fx-pie-color: #EF0D0D;");
        otherData.getNode().setStyle("-fx-pie-color: #888888;");

        // google API documentation for map
        // https://developers.google.com/chart/image/docs/gallery/map_charts
        URL imageURL = new URL("https://chart.googleapis.com/chart?cht=t&chs=440x220&chtm=usa&chco=FFFFFF,0D69EF,EF0D0D&chf=bg,s,C1DEED");
        Image map = new Image(imageURL.openStream());
        imageView.setImage(map);
        imageView.setVisible(true);
    }

    /**
     * Upon action of the combo box, this will show the current selections if there are any.
     */
    public void loadDistrictData() {
        String district = comboBox.getValue();
        if (district == null) return;

        if (selections.containsKey(district)) {
            String selected = selections.get(district);
            switch (selected) {
                case "Other" -> radioButton1.setSelected(true);
                case "Democrat" -> radioButton2.setSelected(true);
                case "Republican" -> radioButton3.setSelected(true);
            }
        } else {
            radioButton1.setSelected(false);
            radioButton2.setSelected(false);
            radioButton3.setSelected(false);
        }
    }

    /**
     * Depending on the selection this will update the vote counts every time a radio button is chosen
     *
     * @throws IOException is an issue appears
     */
    public void radioButtons() throws IOException {
        String district = comboBox.getValue();
        if (radioButton1.isSelected()) {
            updateVoteCounts(district, radioButton1.getText());
        } else if (radioButton2.isSelected()) {
            updateVoteCounts(district, radioButton2.getText());
        } else if (radioButton3.isSelected()) {
            updateVoteCounts(district, radioButton3.getText());
        }
    }

    /**
     * This will update the number of votes each party has on screen
     *
     * @param district congressional district
     * @param party    political party
     * @throws IOException for when an error appears
     */
    private void updateVoteCounts(String district, String party) throws IOException {
        String previous = "";
        if (selections.containsKey(district)) {
            previous = selections.get(district);
        }
        selections.put(district, party);

        int votes = states.getElectoralVotes(district);
        switch (party) {
            case "Other" -> otherVotes += votes;
            case "Democrat" -> democratVotes += votes;
            case "Republican" -> republicanVotes += votes;
        }

        switch (previous) {
            case "Other" -> otherVotes -= votes;
            case "Democrat" -> democratVotes -= votes;
            case "Republican" -> republicanVotes -= votes;
        }

        otherText.setText(String.valueOf(otherVotes));
        democratText.setText(String.valueOf(democratVotes));
        republicanText.setText(String.valueOf(republicanVotes));

        updatePieChart();
        updateMap(party);
        determineWinner();
    }

    /**
     * This will update the pie chart with the current values
     */
    private void updatePieChart() {
        democratData.setPieValue(democratVotes);
        republicanData.setPieValue(republicanVotes);
        otherData.setPieValue(otherVotes);
        pieChartData = FXCollections.observableArrayList(democratData, republicanData, otherData);
    }

    /**
     * This will take the current state selection and will update the map so that the state is colored by party
     *
     * @param party political party
     * @throws IOException when an issue appears
     */
    private void updateMap(String party) throws IOException {
        String state = comboBox.getValue();
        if (state == null) return;

        // exclude D.C. because map doesn't have it shown
        if (state.equals("Washington, D.C.")) return;

        String code = states.getStateCode(state);
        int color = switch (party) {
            case "Other" -> 100; // gray
            case "Democrat" -> 0; // blue
            case "Republican" -> 50; // red
            default -> -1;
        };
        if (color == -1) return;

        if (state.contains("Maine")) {
            stateColor.put("ME", 25);
        } else if (state.contains("Nebraska")) {
            stateColor.put("NE", 25);
        } else {
            stateColor.put(code, color);
        }

        String url = createURL();
        URL googleChart = new URL(url);
        Image map = new Image(googleChart.openStream());
        imageView.setImage(map);
        imageView.setVisible(true);
    }

    /**
     * this will update the URL for the image from the Google API, with any new states that have been added
     *
     * @return the image URL
     */
    private String createURL() {
        Set<String> set = stateColor.keySet();
        Object[] stateCodes = set.toArray();

        StringBuilder states = new StringBuilder();
        StringBuilder colors = new StringBuilder();

        for (Object stateCode : stateCodes) {
            states.append(stateCode);
            colors.append(stateColor.get(stateCode.toString())).append(",");
        }
        colors = new StringBuilder(colors.substring(0, colors.length() - 1));

        return "https://chart.googleapis.com/chart?cht=t&chs=440x220&chtm=usa&chld="
                + states + "&chd=t:" + colors + "&chco=FFFFFF,0D69EF,EF0D0D,888888&chf=bg,s,C1DEED";
    }

    /**
     * This will show when a winner is detected need 270 or more to be a winner
     */
    private void determineWinner() {
        if (democratVotes >= 270) {
            winnerText.setText("Winner: Democrats!");
            winnerText.setVisible(true);
        } else if (republicanVotes >= 270) {
            winnerText.setText("Winner: Republicans!");
            winnerText.setVisible(true);
        } else if (otherVotes >= 270) {
            winnerText.setText("Winner: Other!");
            winnerText.setVisible(true);
        } else {
            winnerText.setVisible(false);
        }
    }
}
