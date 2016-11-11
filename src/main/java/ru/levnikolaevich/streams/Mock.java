package ru.levnikolaevich.streams;

public class Mock {
    private StreamReader streamReader;
    private StreamWriter streamWriter;

    public String calculate(long id)
    {
         String obj = this.streamReader.read(id);
         return calculateObj(obj);
    }

    private  String calculateObj(String obj)
    {
        return "calculated " + obj;
    }

    public StreamReader getStreamReader() {
        return streamReader;
    }

    public void setStreamReader(StreamReader streamReader) {
        this.streamReader = streamReader;
    }

    public StreamWriter getStreamWriter() {
        return streamWriter;
    }

    public void setStreamWriter(StreamWriter streamWriter) {
        this.streamWriter = streamWriter;
    }
}
