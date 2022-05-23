package spellingbee.core.managers;

import spellingbee.core.data.DataFilter;
import spellingbee.core.data.DataReader;

public class GameManager implements GameService {
    private DataReader dataReader;
    private DataFilter dataFilter;


    public  GameManager(DataFilter dataFilter, DataReader dataReader){
        this.dataFilter=dataFilter;
        this.dataReader=dataReader;
    }



}
