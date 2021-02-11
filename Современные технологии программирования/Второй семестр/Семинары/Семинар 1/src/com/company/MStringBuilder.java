package com.company;

import java.util.Stack;

public class MStringBuilder {

    // Action's interface
    public interface UndoAction {
        void undo();
    }

    private final StringBuilder stringBuilder; // Instance of StringBuilder
    private final Stack<UndoAction> actionsStack = new Stack<>(); // Stack of actions

    public MStringBuilder(StringBuilder s) {
        this.stringBuilder = s;
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public MStringBuilder delete(int s, int e) {

        // Delegate to StringBuilder instance
        String deleted = stringBuilder.substring(s, e);
        stringBuilder.delete(s, e);

        // Save reverse operation to Stack
        UndoAction action = () -> stringBuilder.insert(
                s, deleted);

        actionsStack.add(action);
        return this;
    }

    public MStringBuilder append(String str) {
        stringBuilder.append(str);

        UndoAction action = () -> stringBuilder.delete(
                stringBuilder.length() - str.length() - 1,
                stringBuilder.length());

        actionsStack.add(action);
        return this;
    }

    // Method which run last action from stack
    public void undo() {
        if (!actionsStack.isEmpty()) {
            actionsStack.pop().undo();
        }
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }

}
