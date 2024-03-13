package app.exceptions;

public class DatabaseException extends  Exception {


    public DatabaseException(String msg, String userMsg){

        super(msg + " " + userMsg);
    }

    public DatabaseException(String msg){
        super(msg);
    }

    public DatabaseException(){
        super();
    }
}
