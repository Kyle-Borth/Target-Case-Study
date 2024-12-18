# Target-Case-Study

Case study project implemented as part of the interview process for Target.

## Setup Instructions

1. **Clone the repository:** `git clone https://github.com/Kyle-Borth/Target-Case-Study.git`
2. **Open the project in Android Studio:** Launch Android Studio and select "Open an existing Android Studio project". Navigate to the cloned repository and select the project directory.
3. **Sync Gradle:**  Android Studio should automatically prompt you to sync the project with Gradle. If not, you can trigger this manually by clicking on the "Sync Project with Gradle Files" button in the toolbar.
4. **Run the application:** Once Gradle sync is complete, click on the "Run" button in the toolbar to build and run the application on your chosen device or emulator.

## Features

* Displays a list of products that are currently on sale at Target

## Known Issues

* The Manifest file in the Debug directory was added to support UI tests. This is causing debug builds of the app to not work correctly. Quick fix is to remove the debug manifest to build the app, need to implement more robust solution for UI tests.

## Contact

Kyle Borth - kyle.borth@outlook.com

Project Link: https://github.com/Kyle-Borth/Target-Case-Study