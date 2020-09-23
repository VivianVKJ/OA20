package Robinhood;

import java.util.Stack;

public class newText {
    public static void main(String[] args) {
        String[] operations = {"INSERT Da", "COPY 0", "UNDO", "PASTE", "PASTE", "COPY 2", "PASTE", "PASTE", "DELETE", "INSERT aaam"};
        System.out.println(newTextEditor(operations));
    }

    public static String newTextEditor(String[] operations) {
        StringBuilder str = new StringBuilder();
        Stack<String> lastCopied = new Stack<>();
        Stack<String> lastInserted = new Stack<>();
        Stack<String> lastDeleted = new Stack<>();
        Stack<String> lastPaste = new Stack<>();

        Stack<String> lastOperation = new Stack<>();

        for (String op : operations) {
            String[] list = op.split(" ");
            if (list[0].compareTo("DELETE") == 0) {
                if (str.length() > 0) {
                    lastDeleted.push(str.substring(str.length() - 1, str.length()));
                    str.delete(str.length() - 1, str.length());
                    lastOperation.push(list[0]);
                }
            } else if (list[0].compareTo("PASTE") == 0) {
                if (!lastCopied.empty()) {
                    lastPaste.push(lastCopied.peek());
                    str.append(lastCopied.peek());
                    lastOperation.push(list[0]);
                }
            } else if (list[0].compareTo("UNDO") == 0) {
                while (lastOperation.peek().compareTo("COPY") == 0) {
                    lastOperation.pop();
                }
                String lastOp = lastOperation.pop();
                if (lastOp.compareTo("DELETE") == 0) {
                    String recover = lastDeleted.pop();
                    str.append(recover);
                } else if (lastOp.compareTo("PASTE") == 0) {
                    lastPaste.pop();
                } else if (lastOp.compareTo("INSERT") == 0) {
                    String inserted = lastInserted.pop();
                    str.delete(str.length() - inserted.length(), str.length());
                }
            } else if (list[0].compareTo("INSERT") == 0) {
                if (list.length == 2) {
                    lastInserted.push(list[1]);
                    str.append(lastInserted.peek());
                    lastOperation.push(list[0]);
                }
            } else if (list[0].compareTo("COPY") == 0) {
                lastCopied.push(str.substring(Integer.parseInt(list[1]), str.length()));
                lastOperation.push(list[0]);
            }

        }
        return str.toString();
    }
}
