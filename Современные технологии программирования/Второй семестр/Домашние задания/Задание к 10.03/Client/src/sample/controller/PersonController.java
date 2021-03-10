package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.models.Person;
import sample.services.PersonsLoader;

public class PersonController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private Label postalCodeLabel;

    private Main mainApp;

    public PersonController() {
    }

    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());

        showPersonDetails(null);

        personTable.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldValue, newValue) -> showPersonDetails(newValue))

        );
    }

    @FXML
    private void handleDeleteAction() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            PersonsLoader personsLoader = new PersonsLoader();
            try {
                personsLoader.deletePerson(personTable.getItems().get(selectedIndex).getId());
            } catch (Exception e) {
                e.printStackTrace();
            }

            personTable.getItems().remove(selectedIndex);

        } else {
            getAlert().showAndWait();
        }
    }

    @FXML
    private void handleEditAction() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Person selectedPerson = personTable.getItems().get(selectedIndex);

            boolean isOkClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (isOkClicked) {
                mainApp.getPersonData().set(selectedIndex, selectedPerson);
                showPersonDetails(selectedPerson);
            }

        } else {
            getAlert().showAndWait();
        }
    }

    private Alert getAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Error");
        alert.setHeaderText("User not selected.");
        alert.setContentText("Please, select user from list on left.");

        return alert;
    }

    @FXML
    private void handleNewAction() {
        Person person = new Person();
        boolean isOkClicked = mainApp.showPersonEditDialog(person);
        if (isOkClicked) {
            mainApp.getPersonData().add(person);
            showPersonDetails(person);
        }
    }

    private void showPersonDetails(Person person) {
        if (person != null) {
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(person.getBirthday());
            postalCodeLabel.setText(person.getPostalCode());
        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
            postalCodeLabel.setText("");

        }
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        personTable.setItems(mainApp.getPersonData());
    }
}
