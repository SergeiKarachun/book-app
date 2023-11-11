package by.sergo.book.app.bookclient.service.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionMessageUtil {

    public static String getAlreadyExistsMessage(String className, String variableName, String variableValue) {

        return className + " with this " + variableName + " " + variableValue + " already exists.";
    }

    public static String getNotFoundMessage(String className, String variableName, Long variableValue) {
        return className + " with " + variableName + " " + variableValue + " doesn't exist.";
    }
}
