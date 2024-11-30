# Forge Universal

Forge Universal is a project that allows starting a Minecraft Forge server from a universal JAR file. This is designed to enhance compatibility with MineOS.

## How to Build

To build the Forge Universal JAR file, follow these steps:

1. **Ensure Java is installed**:
    - Java Development Kit (JDK) version 17 is required to build this project. You can download it from the [official Oracle JDK website](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) or [AdoptOpenJDK](https://adoptopenjdk.net/).

2. **Clone the repository**:
    - Use the following command to clone the repository:
      ```bash
      git clone https://github.com/Drew-Chase/forge-universal.git
      ```

3. **Navigate to the project directory**:
      ```bash
     cd forge-universal
     ```

4. **Build the JAR file**:
    - Use a build tool like Maven or Gradle. If a build tool configuration is included:
      ```bash
      ./gradlew build # for Gradle
      ```
      or
      ```bash
      mvn package # for Maven
      ```
    - If not using a build tool, use the Java compiler and JAR commands manually:
      ```bash
      javac -d out src/chase/minecraft/forge/*.java
      jar cfe forge-universal.jar chase.minecraft.forge.Main -C out .
      ```

## How to Run the JAR

To run the Forge Universal JAR file, ensure you meet the following Java requirements and run it with the appropriate arguments:

### Java Requirements

- **Java Runtime Environment (JRE) version 17** is required to run this project. You can verify your Java installation by using the command:
  ```bash
  java -version
  ```

### Running the JAR

Execute the JAR using the following command:

```bash
java -jar forge-universal.jar <userArgs> <osArgs> <minecraft-args>
```

- **userArgs**: A file containing user-specific arguments.
- **osArgs**: A file containing operating system-specific arguments.
- **minecraft-args**: Any additional arguments needed for Minecraft.

For example:

```bash
java -jar forge-universal.jar user-args.txt os-args.txt nogui # ... other Minecraft arguments
```

### Known Issues
- MineOS sometimes has issues sending commands to the server console. If you encounter this issue, try running the server in a terminal window using the created screen.

### Troubleshooting

- Ensure that the paths provided in `<userArgs>` and `<osArgs>` exist and are correct.
- Check `forge-universal.txt` for logs and errors if you encounter any issues during startup.

Feel free to contribute to the project by forking the repository and creating pull requests. For any questions, please open an issue on GitHub.
