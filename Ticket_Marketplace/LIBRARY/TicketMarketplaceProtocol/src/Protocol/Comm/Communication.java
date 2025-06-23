/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Protocol.Comm;

/**
 *
 * @author joshu
 */
public class Communication {

    private String command;
    private boolean status; //success (true) or failed (false)
    private String[] data;
    private String divider = ";";
    private String message;

    public Communication() {
        this.command = "";
        this.data = null;
        this.message = "";
    }

    public Communication(String _message) throws Exception {
        this.setMessage(_message);
        this.TranslateMessage();
    }

    public Communication(String _command, boolean status, String[] _data) throws Exception {
        this.setCommand(_command);
        this.setStatus(status);
        if (_data != null) {
            this.setData(_data);
        } else {
            this.data = new String[0];
        }
        this.CreateMessage();
    }

    public Communication(String _command, boolean status) throws Exception {
        this.setCommand(_command);
        this.setStatus(status);
        this.data = new String[0];
        this.CreateMessage();
    }

    public void setStatus(boolean status) throws Exception {
        this.status = status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) throws Exception {
        if (command.equals("") || command == null) {
            throw new Exception("Communication's Command can't be empty");
        } else {
            this.command = command;
        }
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) throws Exception{
        if (data != null) {
            this.data = data;
        } else {
            throw new Exception("Communication's Data can't be empty");
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String _message) throws Exception {
        if (!_message.equals("")) {
            this.message = _message;
        } else {
            throw new Exception("Communication's message can't be empty");
        }
    }

    // FUNCTION ----------------------------------------------------------------
    private void TranslateMessage() throws Exception {
        String[] buf = this.message.split(this.divider);
        this.setCommand(buf[0]);
        this.setStatus(Boolean.getBoolean(buf[1]));
        if (buf.length > 4) {
            this.data = new String[(buf.length - 4)];
            for (int i = 3; i < (buf.length - 1); i++) {
                data[i - 3] = buf[i];
            }
        } else {
            this.setData(null);
        }
    }

    private void CreateMessage() throws Exception {
        String buf = this.command + this.divider + this.status + this.divider + "DATA-BEGIN" + this.divider;
        for (String s : this.data) {
            buf += s + this.divider;
        }
        buf += "DATA-END";
        this.setMessage(buf);
    }
}
