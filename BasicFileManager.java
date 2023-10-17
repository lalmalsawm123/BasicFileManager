import java.io.File;
import java.util.Scanner;

public class BasicFileManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File currentDirectory = new File(System.getProperty("user.dir"));

        while (true) {
            System.out.println("Current Directory: " + currentDirectory.getAbsolutePath());
            System.out.println("Select an operation:");
            System.out.println("1. List files and directories");
            System.out.println("2. Create file");
            System.out.println("3. Create directory");
            System.out.println("4. Rename file/directory");
            System.out.println("5. Copy file/directory");
            System.out.println("6. Move file/directory");
            System.out.println("7. Delete file/directory");
            System.out.println("8. Change directory");
            System.out.println("9. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    listFilesAndDirectories(currentDirectory);
                    break;
                case 2:
                    createFile(currentDirectory, scanner);
                    break;
                case 3:
                    createDirectory(currentDirectory, scanner);
                    break;
                case 4:
                    renameFileOrDirectory(currentDirectory, scanner);
                    break;
                case 5:
                    copyFileOrDirectory(currentDirectory, scanner);
                    break;
                case 6:
                    moveFileOrDirectory(currentDirectory, scanner);
                    break;
                case 7:
                    deleteFileOrDirectory(currentDirectory, scanner);
                    break;
                case 8:
                    changeDirectory(currentDirectory, scanner);
                    break;
                case 9:
                    System.out.println("Exiting the file manager. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void listFilesAndDirectories(File directory) {
        File[] files = directory.listFiles();
        for (File file : files) {
            System.out.println(file.getName());
        }
    }

    private static void createFile(File directory, Scanner scanner) {
        System.out.print("Enter the name of the file to create: ");
        String fileName = scanner.nextLine();
        File newFile = new File(directory, fileName);

        try {
            if (newFile.createNewFile()) {
                System.out.println("File created successfully.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    private static void createDirectory(File directory, Scanner scanner) {
        System.out.print("Enter the name of the directory to create: ");
        String dirName = scanner.nextLine();
        File newDirectory = new File(directory, dirName);

        if (newDirectory.mkdir()) {
            System.out.println("Directory created successfully.");
        } else {
            System.out.println("Directory already exists.");
        }
    }

    private static void renameFileOrDirectory(File directory, Scanner scanner) {
        System.out.print("Enter the name of the file/directory to rename: ");
        String oldName = scanner.nextLine();
        File oldFile = new File(directory, oldName);

        System.out.print("Enter the new name: ");
        String newName = scanner.nextLine();
        File newFile = new File(directory, newName);

        if (oldFile.renameTo(newFile)) {
            System.out.println("Renamed successfully.");
        } else {
            System.out.println("Error renaming file/directory.");
        }
    }

    private static void copyFileOrDirectory(File directory, Scanner scanner) {
        System.out.print("Enter the name of the file/directory to copy: ");
        String sourceName = scanner.nextLine();
        File sourceFile = new File(directory, sourceName);

        System.out.print("Enter the destination directory: ");
        String destinationDir = scanner.nextLine();
        File destinationDirectory = new File(directory, destinationDir);

        if (sourceFile.isDirectory()) {
            copyDirectory(sourceFile, new File(destinationDirectory, sourceFile.getName()));
        } else {
            copyFile(sourceFile, new File(destinationDirectory, sourceFile.getName()));
        }
    }

    private static void copyFile(File source, File destination) {
        // Copy file logic
    }

    private static void copyDirectory(File source, File destination) {
        // Copy directory logic
    }

    private static void moveFileOrDirectory(File directory, Scanner scanner) {
        System.out.print("Enter the name of the file/directory to move: ");
        String sourceName = scanner.nextLine();
        File sourceFile = new File(directory, sourceName);

        System.out.print("Enter the destination directory: ");
        String destinationDir = scanner.nextLine();
        File destinationDirectory = new File(directory, destinationDir);

        if (sourceFile.renameTo(new File(destinationDirectory, sourceFile.getName()))) {
            System.out.println("Moved successfully.");
        } else {
            System.out.println("Error moving file/directory.");
        }
    }

    private static void deleteFileOrDirectory(File directory, Scanner scanner) {
        System.out.print("Enter the name of the file/directory to delete: ");
        String name = scanner.nextLine();
        File fileToDelete = new File(directory, name);

        if (fileToDelete.isDirectory()) {
            deleteDirectory(fileToDelete);
        } else {
            if (fileToDelete.delete()) {
                System.out.println("Deleted successfully.");
            } else {
                System.out.println("Error deleting file/directory.");
            }
        }
    }

    private static void deleteDirectory(File directory) {
        // Delete directory logic
    }

    private static void changeDirectory(File currentDirectory, Scanner scanner) {
        System.out.print("Enter the path to the new directory: ");
        String newPath = scanner.nextLine();
        File newDirectory = new File(currentDirectory, newPath);

        if (newDirectory.isDirectory()) {
            currentDirectory = newDirectory;
        } else {
            System.out.println("Invalid directory path.");
        }
    }
}