package Communication;

import java.io.IOException;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author joshu
 */
/**
 * Utility class for handling client-server communication by formatting and
 * parsing messages.
 */
public class Communication {

    private String username;
    private String command;
    private String[] data;
    private String divider = ";";
    private String message;

    public Communication() {
        this.command = "";
        this.data = null;
        this.message = "";
    }

    public Communication(String _message) throws IOException {
        this.setMessage(_message);
        this.TranslateMessage();
    }

    public Communication(String _username, String _command, String[] _data) throws IOException {
        this.setCommand(_command);
        this.setUsername(_username);
        if (_data != null) {

            this.setData(_data);
        }
        else
        {
            this.data = new String[0];
        }
        this.CreateMessage();
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String _message) throws IOException {
        if (!_message.equals("")) {
            this.message = _message;
        } else {
            throw new IOException("Communication message can't be empty");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // FUNCTION ----------------------------------------------------------------
    private void TranslateMessage() {
        String[] buf = this.message.split(this.divider);
        this.setCommand(buf[0]);
        this.setUsername(buf[1]);
        if (buf.length > 4) {
            this.data = new String[(buf.length - 4) - 0];
            for (int i = 3; i < (buf.length - 1); i++) {
                data[i - 2] = buf[i];
            }
        } else {
            this.setData(null);
        }
    }

    private void CreateMessage() throws IOException {
        String buf = this.command + this.divider + this.username + this.divider + "DATA-BEGIN" + this.divider;
        for (String s : this.data) {
            buf += s + this.divider;
        }
        buf += "DATA-END";
        this.setMessage(buf);
    }
}
